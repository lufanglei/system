var dataHelper = {
	page : function(url,callback){
		return this.data(url,"","GET","HTML",true,null,callback);
	},
	get : function(url,param,callback){
		return this.data(url,param,"GET","JSON",false,null,callback);
	},
	post : function(url,param,callback){
		return this.data(url,param,"POST","JSON",false,null,callback);
	},
	asyncGet : function(url,param,callback){
		return dataHelper.data(url,param,"GET","JSON",true,null,callback);
	},
	asyncPost : function(url,param,callback){
		return dataHelper.data(url,param,"POST","JSON",true,null,callback);
	},
	asyncByAuth : function(url,param,method,callback){
		
		return dataHelper.dataByAuth(url,param,method,"JSON",true,callback);
	},
	asyncGetByAuth : function(url,param,callback){
		
		return dataHelper.dataByAuth(url,param,"GET","JSON",true,callback);
	},
	asyncPostByAuth : function(url,param,callback){
		
		return dataHelper.dataByAuth(url,param,"POST","JSON",true,callback);
	},
	getByAuth : function(url,param,callback){
		
		return dataHelper.dataByAuth(url,param,"GET","JSON",false,callback);
	},
	postByAuth : function(url,param,callback){
		
		return dataHelper.dataByAuth(url,param,"POST","JSON",false,callback);
	},
	dataByAuth : function(url,param,method,dataType,async,callback){
		var auth = global.getAuth();
		if(auth==null){
			top.location.href="../../login.html";
			return false;
		}
		return dataHelper.data(url,param,method,dataType,async,auth,callback);
	},
	data : function(url,param,method,dataType,async,auth,callback){
		var data = null;
		if(param == undefined){
			param = "";
		}
		console.log("url:"+url);
		if(url.indexOf(".html")>0){
			url = global.web_host+url;
		}else if(url.indexOf("http")<0){
			url = global.server_host+url;
		}
		
		console.log("url2:"+url);
		console.log(param);
		$.ajax({
	        type: method,
	        url: url,
	        data: param,
	        dataType: dataType,
	        async:async,
	        beforeSend: function(request) {
	        	if(dataType=="JSON"){
	        		request.setRequestHeader("Accept", "application/json; charset=utf-8");
	        	}
	        	if(auth != undefined && auth != null && auth !=""){
	        		request.setRequestHeader("authorization", auth);
	        	}
	            
	        },
	        success: function(result){
	        	
        		if(result.code=="401"){//如果业务状态码为400说明没有认证
        			global.removeAuth();
	        		top.location.href="../../login.html";
	        	}
        		console.log(result);
		        data = result;
	        	
	        	if(callback!=null && callback!= undefined && callback !=""){
	        		callback(data);
	        	}
	        	
	        },
	        error: function (xhr, textStatus, errorThrown) {
	            /*错误信息处理*/
	        	console.log("访问服务器出错：原因：readyState="+xhr.readyState+" /status="+xhr.status+" /statusText="+xhr.statusText+" /textStatus="+textStatus+" /errorThrown="+errorThrown+" /responseText="+xhr.responseText);
	        	if(callback!=null && callback!= undefined && callback !=""){
	        		callback({code:xhr.status,data:"访问服务器出错"});
	        	}
	        }
	    });
		return data;
	}
};