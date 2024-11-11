/**
 * 浏览器缓存
 */
var SessionUtils = new Object({
	set: function (key, value) {
        window.sessionStorage[key] = value;
    },
    get: function (key, defaultValue) {
        return window.sessionStorage[key] || defaultValue;
    },
    getObject: function (key) {
        return JSON.parse(window.sessionStorage[key] || '{}');
    },
    remove: function (key){
    	sessionStorage.removeItem(key);
    },
    clear: function (){
    	sessionStorage.clear();
    }
});
