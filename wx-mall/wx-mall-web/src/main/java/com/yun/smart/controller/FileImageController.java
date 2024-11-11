package com.yun.smart.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baomidou.mybatisplus.plugins.Page;
import com.yun.smart.builder.JsonResultBuilder;
import com.yun.smart.consts.BizContstant;
import com.yun.smart.consts.SessionConsts;
import com.yun.smart.enums.BussinessType;
import com.yun.smart.log.BussinessLogger;
import com.yun.smart.log.BussinessLoggerPool;
import com.yun.smart.model.FileImage;
import com.yun.smart.param.FileImageAddParams;
import com.yun.smart.param.FileImageDeleteParams;
import com.yun.smart.param.FileImageSearchParams;
import com.yun.smart.param.FileImageUpdateParams;
import com.yun.smart.result.JsonResult;
import com.yun.smart.service.FileImageService;
import com.yun.smart.utils.AssertUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Controller - 图片
 * @author qihh
 * @version 0.0.1
 *
 */
@RestController
@RequestMapping("/smart/fileImage")
@Api(value = "图片接口")
public class FileImageController extends BaseController {

	private BussinessLogger logger = BussinessLoggerPool.getLogger(this.getClass(), BussinessType.FILEIMAGE);
	
	@Resource
	private FileImageService fileImageService;

	@RequestMapping(value="/pc/v1/searchPage",method=RequestMethod.POST)
	@ApiOperation(value = "分页查询图片", notes = "分页查询图片")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchPage(FileImageSearchParams params){
		logger.info("FileImageController-分页查询入参:{}",params);
		Page<Map<String,Object>> result = fileImageService.searchPage(params);
		return JsonResultBuilder.ok(result);
	}
	
	/**
	 * 根据业务ID查询所有图片
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/pc/v1/searchList",method=RequestMethod.POST)
	@ApiOperation(value = "查询图片列表", notes = "查询图片列表")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchList(FileImageSearchParams params){
		logger.info("FileImageController-查询列表入参:{}",params);
		List<FileImage> result = fileImageService.searchList(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/searchDetail",method=RequestMethod.POST)
	@ApiOperation(value = "查询图片详情", notes = "查询图片详情")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult searchDetail(FileImageSearchParams params){
		logger.info("FileImageController-查询详情入参:{}",params);
		FileImage result = fileImageService.searchDetail(params);
		return JsonResultBuilder.ok(result);
	}
	
	@RequestMapping(value="/pc/v1/add",method=RequestMethod.POST)
	@ApiOperation(value = "新建图片", notes = "新建图片")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult add(FileImageAddParams params){
		logger.info("FileImageController-新建入参:{}",params);
		fileImageService.add(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/update",method=RequestMethod.POST)
	@ApiOperation(value = "更新图片", notes = "更新图片")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult update(FileImageUpdateParams params){
		logger.info("FileImageController-更新入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getId(), "图片ID为空");
		fileImageService.update(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/delete",method=RequestMethod.POST)
	@ApiOperation(value = "删除图片", notes = "删除图片")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult delete(FileImageDeleteParams params){
		logger.info("FileImageController-删除入参:{}",params);
		fileImageService.delete(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/deleteByIds",method=RequestMethod.POST)
	@ApiOperation(value = "批量删除图片", notes = "批量删除图片")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult deleteByIds(FileImageDeleteParams params){
		logger.info("FileImageController-批量删除入参:{}",Arrays.toString(params.getIds()));
		fileImageService.deleteByIds(params);
		return JsonResultBuilder.ok();
	}
	
	@RequestMapping(value="/pc/v1/uploadImage",method=RequestMethod.POST)
	@ApiOperation(value = "上传图片", notes = "上传图片")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult uploadImage(FileImageAddParams params, MultipartFile file, HttpServletRequest request){
		logger.info("FileImageController-上传图片入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getBizNo(), "业务编号为空");
		AssertUtil.notNull(params.getBizType(), "业务类型为空");
		
		String realPath = request.getServletContext().getRealPath("/public/images");
		String realName = System.currentTimeMillis() + ".jpg";
		try {
			OutputStream output = new FileOutputStream(FileUtils.getFile(realPath, realName));
			IOUtils.copy(file.getInputStream(), output);
		} catch (IOException e) {
			logger.error("上传图片失败:" + e.getMessage());
		}
		
		String filePath = BizContstant.SYS_FILE_PRE +  "/images/" + realName;
		params.setFilePath(filePath);
		params.setMainLogo(FileImage.NOT_MAIN_LOGO);
		FileImage fileImage = fileImageService.uploadImage(params);
		
		return JsonResultBuilder.ok(fileImage);
	}
	
	@RequestMapping(value="/app/v1/uploadImage",method=RequestMethod.POST)
	@ApiOperation(value = "上传图片", notes = "上传图片")
	@ApiImplicitParams({
		@ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult uploadImageApp(FileImageAddParams params, HttpServletRequest request){
		logger.info("FileImageController-上传图片入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getBizNo(), "业务编号为空");
		AssertUtil.notNull(params.getBizType(), "业务类型为空");
		
		MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile file = req.getFile("fileImage");
		
		return this.uploadImage(params, file, request);
	}
	
	@RequestMapping(value="/pc/v1/changeMainLogo",method=RequestMethod.POST)
	@ApiOperation(value = "设置主图", notes = "设置主图")
	@ApiImplicitParams({
        @ApiImplicitParam(name = SessionConsts.AUTH_TOKEN_NAME, value = "token", paramType = "header", dataType = "string")
	})
	public JsonResult changeMainLogo(FileImageUpdateParams params){
		logger.info("FileImageController-设置主图入参:{}",params);
		AssertUtil.notNull(params, "参数为空");
		AssertUtil.notNull(params.getId(), "图片ID为空");
		fileImageService.changeMainLogo(params);
		return JsonResultBuilder.ok();
	}
	
}
