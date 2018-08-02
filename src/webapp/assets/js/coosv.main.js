$(document).ready(function(){
	var auth = global.getAuth();
	if(auth!=''&&auth!=null&&auth!=undefined){
		if(location.href.indexOf('/login.html')>=0){
            top.location.href="index.html";
    	}
	}
	if(top.pageHelper.inited == false){
		pageHelper.initPage();
	}
	
	$(document).on("click",".head-menu-item",function(e){  
		var id = pageHelper.getDataID($(this).attr("id"));
		pageHelper.initAsideMenu(id)
	});
	
	$(document).on("click",".main-tab",function(e){  
		var id = pageHelper.getDataID($(this).attr("id"));
		pageHelper.showMainTab(id);
		e.stopPropagation();
	});
	
	$(document).on("click",".main-tab-close",function(e){  
		var id = pageHelper.getDataID($(this).parent().attr("id"));
		pageHelper.removeMainTab(id);
		e.stopPropagation();
	});
	$(document).on("click",".aside-submenu",function(){  
		var id = pageHelper.getDataID($(this).attr("id"));
		var title = $(this).html();
		var href = $(this).attr("href");

		pageHelper.showMainTab(id,title,href);
	});  
	
    $(".coosv-login-btn").on("click",function(){

		dataHelper.asyncGet("/token",$("#loginform").serialize(),function(data){
    		console.log(data);
            if(data.code=="200"){
                global.setAuth(data.data,$("#rememberMe").is(":checked"));
                location.href="index.html";
            }else{
            	$(".login-warn").html("请输入正确的用户名和密码。");
                $(".login-warn").removeAttr("hidden");
            }

    	});
    	
		
    });   
    
    $(".coosv-logout-btn").on("click",function(){
    	dataHelper.getByAuth("/logout","",function(data){
            if(data.code=="200"){
            	global.removeAuth();
            }else{
            	pageHelper.alert(data.message);
            }

    	});
        return false;
    }); 
});
