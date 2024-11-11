var WebuploaderFile = new Object();
    // 当domReady的时候开始初始化
var $wrap = $('.uploader-list-container'),
    // 图片容器
    $queue = $( '<ul class="filelist"></ul>' ).appendTo( $wrap.find( '.queueList' ) ),
    // 状态栏，包括进度和控制按钮
    $statusBar = $wrap.find( '.statusBar' ),
    // 文件总体选择信息。
    $info = $statusBar.find( '.info' ),
    // 上传按钮
    $upload = $wrap.find( '.uploadBtn' ),
    // 没选择文件之前的内容。
    $placeHolder = $wrap.find( '.placeholder' ),
    $progress = $statusBar.find( '.progress' ).hide(),
    // 添加的文件数量
    fileCount = 0,
    // 添加的文件总大小
    fileSize = 0,
    // 优化retina, 在retina下这个值是2
    ratio = window.devicePixelRatio || 1,
    // 缩略图大小
    thumbnailWidth = 110 * ratio,
    thumbnailHeight = 110 * ratio,
    // 可能有pedding, ready, uploading, confirm, done.
    state = 'pedding',
    // 所有文件的进度信息，key为file id
    percentages = {},
    // 判断浏览器是否支持图片的base64
    isSupportBase64 = ( function() {
        var data = new Image();
        var support = true;
        data.onload = data.onerror = function() {
            if( this.width != 1 || this.height != 1 ) {
                support = false;
            }
        }
        data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
        return support;
    } )(),
    // 检测是否已经安装flash，检测flash的版本
    flashVersion = ( function() {
        var version;

        try {
            version = navigator.plugins[ 'Shockwave Flash' ];
            version = version.description;
        } catch ( ex ) {
            try {
                version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                        .GetVariable('$version');
            } catch ( ex2 ) {
                version = '0.0';
            }
        }
        version = version.match( /\d+/g );
        return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
    } )(),
    supportTransition = (function(){
        var s = document.createElement('p').style,
            r = 'transition' in s ||
                    'WebkitTransition' in s ||
                    'MozTransition' in s ||
                    'msTransition' in s ||
                    'OTransition' in s;
        s = null;
        return r;
    })(),

    // WebUploader实例
    uploader;

/**
 * 初始化插件
 */
WebuploaderFile.initWebuploader = function (params) {
	if (!WebUploader.Uploader.support('flash') && WebUploader.browser.ie ) {
		
		// flash 安装了但是版本过低。
		if (flashVersion) {
			(function(container) {
				window['expressinstallcallback'] = function( state ) {
					switch(state) {
					case 'Download.Cancelled':
						alert('您取消了更新！')
						break;
						
					case 'Download.Failed':
						alert('安装失败')
						break;
						
					default:
						alert('安装已成功，请刷新！');
					break;
					}
					delete window['expressinstallcallback'];
				};
				
				var swf = 'expressInstall.swf';
				// insert flash object
				var html = '<object type="application/' +
				'x-shockwave-flash" data="' +  swf + '" ';
				
				if (WebUploader.browser.ie) {
					html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
				}
				
				html += 'width="100%" height="100%" style="outline:0">'  +
				'<param name="movie" value="' + swf + '" />' +
				'<param name="wmode" value="transparent" />' +
				'<param name="allowscriptaccess" value="always" />' +
				'</object>';
				
				container.html(html);
				
			})($wrap);
			
			// 压根就没有安装。
		} else {
			$wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
		}
		
		return;
	} else if (!WebUploader.Uploader.support()) {
		alert( 'Web Uploader 不支持您的浏览器！');
		return;
	}
	
	// 实例化
	uploader = WebUploader.create({
		pick: {
			id: '#filePickerArea',
			label: '点击选择图片'
		},
		dnd: '#dndArea',
		paste: '#uploader',
		swf: '../../../lib/webuploader/0.1.5/Uploader.swf',
		chunked: false,
		chunkSize: 512 * 1024,
		server: '../../../lib/webuploader/0.1.5/server/fileupload.php',
		// 只允许选择图片文件。 
		accept: [{
			title: 'file',
			extensions: 'doc,docx,pdf,xls,xlsx,ppt,pptx,gif,jpg,jpeg,bmp,png,rar,zip',
			mimeTypes: '.doc,.docx,.pdf,.xls,.xlsx,.ppt,.pptx,.gif,.jpg,.jpeg,.bmp,.png,.rar,.zip'
		}],
		// 自动上传
		auto: false,
		// 多文件上传
		multiple: true,
		// 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
		disableGlobalDnd: true,
		fileNumLimit: 300,// 限制上传个数
		fileSizeLimit: 200 * 1024 * 1024,    //// 限制总文件大小 200 M
		fileSingleSizeLimit: 50 * 1024 * 1024    //限制单文件大小 50 M
	});
	
	// 拖拽时不接受 js, txt 文件。
	uploader.on( 'dndAccept', function( items ) {
		var denied = false,
		len = items.length,
		i = 0,
		// 修改js类型
		unAllowed = 'text/plain;application/javascript ';
		
		for ( ; i < len; i++ ) {
			// 如果在列表里面
			if ( ~unAllowed.indexOf( items[ i ].type ) ) {
				denied = true;
				break;
			}
		}
		
		return !denied;
	});
	
	uploader.on('dialogOpen', function() {
		console.log('here');
	});
	
	// 添加“添加文件”的按钮，
	uploader.addButton({
		id: '#filePicker',
		label: '继续添加'
	});
	
	uploader.on('ready', function() {
		window.uploader = uploader;
	});
	
	// 进度条渲染
	uploader.onUploadProgress = function( file, percentage ) {
        var $li = $('#'+file.id),
            $percent = $li.find('.progress span');

        $percent.css( 'width', percentage * 100 + '%' );
        percentages[ file.id ][ 1 ] = percentage;
        updateTotalProgress();
    };

    // 当有文件被添加进队列的时候
    uploader.onFileQueued = function( file ) {
        fileCount++;
        fileSize += file.size;

        if ( fileCount === 1 ) {
            $placeHolder.addClass( 'element-invisible' );
            $statusBar.show();
        }

        addFile( file );
        setState( 'ready' );
        updateTotalProgress();
    };

    // 当有文件被从队列移除的时候
    uploader.onFileDequeued = function( file ) {
        fileCount--;
        fileSize -= file.size;

        if ( !fileCount ) {
            setState( 'pedding' );
        }

        removeFile( file );
        updateTotalProgress();

    };

    // 根据情况设置文件状态
    uploader.on( 'all', function( type ) {
    	var stats;
        switch( type ) {
            case 'uploadFinished':
                setState( 'confirm' );
                params.callback();
                break;

            case 'startUpload':
                setState( 'uploading' );
                break;

            case 'stopUpload':
                setState( 'paused' );
                break;

        }
    });
    
    // 上传签处理：添加请求头
    uploader.on('uploadBeforeSend', function (object, data,headers) {
		headers['x-auth-token'] = SessionUtils.get('x-auth-token');
	});
    
    // 上传结果处理：
    uploader.on("uploadAccept", function(file, result){ 
    	if (result && result.code=="0000") { 
	    	return true; 
    	} 
    	else if (result && result.code == '90001') {
			SessionUtils.clear();
        	window.top.location.href = htmlPath + "login/index.html";
		}
    	else {
    		// 通过return false来告诉组件，此文件上传有错。
    		layer.msg(result.msg,{icon:5,time:1000});
    		return false;
    	}
    });
    
    //文件上传成功处理：
    uploader.on('uploadSuccess', function (file, result) {
    	
    });

    // 上传出错
    uploader.on('uploadError', function (file) {

    });
    
    // 绑定上传事件
    $upload.on('click', function() {
        if ( $(this).hasClass( 'disabled' ) ) {
            return false;
        }

        if ( state === 'ready' ) {
        	uploadToServer(params);
        } else if ( state === 'paused' ) {
        	uploadToServer(params);
        } else if ( state === 'uploading' ) {
            uploader.stop();
        }
    });

    $info.on( 'click', '.retry', function() {
        uploader.retry();
    } );

    $info.on( 'click', '.ignore', function() {
    	layer.msg('ignore',{icon:5,time:1000});
    } );

    $upload.addClass( 'state-' + state );
    updateTotalProgress();
	
}

/**
 * 当有文件添加进来时执行，负责view的创建
 * @param file
 * @returns
 */
function addFile( file ) {
    var $li = $( '<li id="' + file.id + '">' +
            '<p class="title">' + file.name + '</p>' +
            '<p class="imgWrap"></p>'+
            '<p class="progress"><span></span></p>' +
            '</li>' ),

        $btns = $('<div class="file-panel">' +
            '<span class="cancel">删除</span>' +
            '<span class="rotateRight">向右旋转</span>' +
            '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
        $prgress = $li.find('p.progress span'),
        $wrap = $li.find( 'p.imgWrap' ),
        $info = $('<p class="error"></p>'),

        showError = function( code ) {
            switch( code ) {
                case 'exceed_size':
                    text = '文件大小超出';
                    break;

                case 'interrupt':
                    text = '上传暂停';
                    break;

                default:
                    text = '上传失败，请重试';
                    break;
            }

            $info.text( text ).appendTo( $li );
        };

    if ( file.getStatus() === 'invalid' ) {
        showError( file.statusText );
    } else {
        // @todo lazyload
        $wrap.text( '预览中' );
        uploader.makeThumb( file, function( error, src ) {
            var img;

            if ( error ) {
                $wrap.text( '不能预览' );
                return;
            }

            if( isSupportBase64 ) {
                img = $('<img src="'+src+'">');
                $wrap.empty().append( img );
            } else {
                $.ajax('lib/webuploader/0.1.5/server/preview.php', {
                    method: 'POST',
                    data: src,
                    dataType:'json'
                }).done(function( response ) {
                    if (response.result) {
                        img = $('<img src="'+response.result+'">');
                        $wrap.empty().append( img );
                    } else {
                        $wrap.text("预览出错");
                    }
                });
            }
        }, thumbnailWidth, thumbnailHeight );

        percentages[ file.id ] = [ file.size, 0 ];
        file.rotation = 0;
    }

    file.on('statuschange', function( cur, prev ) {
        if ( prev === 'progress' ) {
            $prgress.hide().width(0);
        } else if ( prev === 'queued' ) {
            $li.off( 'mouseenter mouseleave' );
            $btns.remove();
        }

        // 成功
        if ( cur === 'error' || cur === 'invalid' ) {
            console.log( file.statusText );
            showError( file.statusText );
            percentages[ file.id ][ 1 ] = 1;
        } else if ( cur === 'interrupt' ) {
            showError( 'interrupt' );
        } else if ( cur === 'queued' ) {
            percentages[ file.id ][ 1 ] = 0;
        } else if ( cur === 'progress' ) {
            $info.remove();
            $prgress.css('display', 'block');
        } else if ( cur === 'complete' ) {
            $li.append( '<span class="success"></span>' );
        }

        $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
    });

    $li.on( 'mouseenter', function() {
        $btns.stop().animate({height: 30});
    });

    $li.on( 'mouseleave', function() {
        $btns.stop().animate({height: 0});
    });

    $btns.on( 'click', 'span', function() {
        var index = $(this).index(),
            deg;

        switch ( index ) {
            case 0:
                uploader.removeFile( file );
                return;

            case 1:
                file.rotation += 90;
                break;

            case 2:
                file.rotation -= 90;
                break;
        }

        if ( supportTransition ) {
            deg = 'rotate(' + file.rotation + 'deg)';
            $wrap.css({
                '-webkit-transform': deg,
                '-mos-transform': deg,
                '-o-transform': deg,
                'transform': deg
            });
        } else {
            $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');

        }


    });

    $li.appendTo( $queue );
}

/**
 * 负责view的销毁
 */ 
function removeFile( file ) {
    var $li = $('#'+file.id);

    delete percentages[ file.id ];
    updateTotalProgress();
    $li.off().find('.file-panel').off().end().remove();
}

/**
 * 更新上传进度条
 * @returns
 */
function updateTotalProgress() {
    var loaded = 0,
        total = 0,
        spans = $progress.children(),
        percent;

    $.each( percentages, function( k, v ) {
        total += v[ 0 ];
        loaded += v[ 0 ] * v[ 1 ];
    } );

    percent = total ? loaded / total : 0;


    spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
    spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
    updateStatus();
}

/**
 * 更新文件上传状态
 * @returns
 */
function updateStatus() {
    var text = '', stats;

    if ( state === 'ready' ) {
        text = '选中' + fileCount + '张图片，共' +
                WebUploader.formatSize( fileSize ) + '。';
    } else if ( state === 'confirm' ) {
        stats = uploader.getStats();
        if ( stats.uploadFailNum ) {
            text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
        }

    } else {
        stats = uploader.getStats();
        text = '共' + fileCount + '张（' +
                WebUploader.formatSize( fileSize )  +
                '），已上传' + stats.successNum + '张';

        if ( stats.uploadFailNum ) {
            text += '，失败' + stats.uploadFailNum + '张';
        }
    }

    $info.html( text );
}

/**
 * 设置文件上传状态
 * @param val
 * @returns
 */
function setState( val ) {
    var file, stats;

    if ( val === state ) {
        return;
    }

    $upload.removeClass( 'state-' + state );
    $upload.addClass( 'state-' + val );
    state = val;

    switch ( state ) {
        case 'pedding':
            $placeHolder.removeClass( 'element-invisible' );
            $queue.hide();
            $statusBar.addClass( 'element-invisible' );
            uploader.refresh();
            break;

        case 'ready':
            $placeHolder.addClass( 'element-invisible' );
            $( '#filePicker' ).removeClass( 'element-invisible');
            $queue.show();
            $statusBar.removeClass('element-invisible');
            uploader.refresh();
            break;

        case 'uploading':
            $( '#filePicker' ).addClass( 'element-invisible' );
            $progress.show();
            $upload.text( '暂停上传' );
            break;

        case 'paused':
            $progress.show();
            $upload.text( '继续上传' );
            break;

        case 'confirm':
            $progress.hide();
            $( '#filePicker' ).removeClass( 'element-invisible' );
            $upload.text( '开始上传' );

            stats = uploader.getStats();
            if ( stats.successNum && !stats.uploadFailNum ) {
                setState( 'finish' );
                return;
            }
            break;
        case 'finish':
            stats = uploader.getStats();
            if (stats.successNum) {
            	layer.msg('上传成功!',{icon:1,time:1000});
            } else {
                // 没有成功的图片，重设
                state = 'done';
                location.reload();
            }
            break;
    }

    updateStatus();
}

/**
 * 文件上传方法：自定义参数
 * @returns
 */
function uploadToServer (params) {
	var opts = {};
    if (params) {
        if (params.url != null && params.url != "") {
            opts.server = params.url;
        }
        if (params.size != null && params.size != "") {
            opts.fileSingleSizeLimit = params.size;
        }
        if (params.args != null && params.args != "") {
            opts.formData = params.args;
        }
        if (opts) {
            $.extend(uploader.options, opts);
        }
    }
    // 上传
    uploader.upload();
}
