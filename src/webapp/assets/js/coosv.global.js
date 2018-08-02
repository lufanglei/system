var global = new Object();

global.web_host = "http://localhost";
global.server_host = "http://127.0.0.1:8080";
global.levels = null;
	
global.init= function(){//初始化信息
	console.log("我在初始化页面");
}

global.setCookie = function(cname, cvalue, seconds) {
	var expires = "";
	if(seconds != undefined){
		var d = new Date();
	    d.setTime(d.getTime() + (seconds*1000));
	    expires = "expires="+d.toUTCString();
	}
    
    document.cookie = cname + "=" + cvalue + ";" + expires;
}


//获取cookie
global.getCookie = function(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}

//清除cookie  
global.clearCookie = function(name) {  
	global.setCookie(name, "", -1);  
}  

global.setAuth = function(token,rememberMe){
	if(rememberMe){
		global.setCookie('authentication', token, 7*60*60*24);
	}else{
		global.setCookie('authentication', token);
	}
	
}

global.removeAuth = function(){
	global.clearCookie("authentication");
	
}

global.getAuth = function(){
	var auth = global.getCookie('authentication');
	return auth;
}


