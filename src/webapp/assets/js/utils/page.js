var pageHelper = {
	menuDataList:null,
	firstHeadMenuId:0,
	headMenuCount:0,
	currentHeadMenu:0,
	currentAsideMenu:0,
	asideMenuCount:0,
	currentAsideSubMenu:0,
	openTabList:"",
	win:top,
	inited:false,
	initPage : function(){
		if($(".coosv-header-navbar").length==0)return ;
		dataHelper.asyncGetByAuth("http://localhost/store/demo/resource.json","",function(data){
			
			pageHelper.inited=true;
			pageHelper.menuDataList = data.data;
        	pageHelper.initHeadMenu(pageHelper.menuDataList);
        	pageHelper.initAsideMenu();
        	var subMenu = pageHelper.getMenu(pageHelper.currentAsideSubMenu);
        	if(subMenu!=null && subMenu!=undefined){
        		pageHelper.showMainTab(subMenu.id,subMenu.name,subMenu.href);
        		$(".main-tab-close").remove();
        	}
        });
	},
	initHeadMenu : function(data){
		var head_menu_tpl = "{@each data as menu, index}$${menu|head_menu_item}{@/each}"
	          
        juicer.register('head_menu_item', this.headMenuItem); //注册自定义函数  
        var menucontent = juicer(head_menu_tpl,{data:data});
        $(".coosv-header-navbar").html(menucontent);
	},
	headMenuItem : function(menu){
		if(menu.parent==undefined || (menu.parent!=undefined && menu.parent.id==1)){
			pageHelper.headMenuCount++;
			var active = "";
			if(pageHelper.headMenuCount==1){
				pageHelper.currentHeadMenu = menu.id;
				active = "active";
			}
    		return "<li id=\"head-menu-"+menu.id+"\" class=\"head-menu-item nav-item "+active+"\"><a class=\"nav-link\" href=\"javascript:void(0);\">"+menu.name+"</a></li>";
    	}else{
    		return "";
    	}
	},
	initAsideMenu : function(headMenuId){
		if(headMenuId!=null && headMenuId != undefined){
			if(headMenuId===pageHelper.currentHeadMenu){
				return false;
			}
			pageHelper.currentHeadMenu = headMenuId;
			$(".head-menu-item").removeClass("active");
			$("#head-menu-"+headMenuId).addClass("active");
			pageHelper.asideMenuCount = 0;
		}
		if(pageHelper.currentAsideSubMenu!=0){
			pageHelper.currentAsideMenu = pageHelper.getMenu(pageHelper.currentAsideSubMenu).parent.id;
			
		}
		var aside_menu_tpl = "{@each data as menu, index}$${menu|aside_menu_item}{@/each}";
		juicer.register('aside_menu_item', this.asideMenuItem); //注册自定义函数  
        var menucontent = juicer(aside_menu_tpl,{data:pageHelper.menuDataList});
        $(".coosv-aside-menu").html(menucontent);
	},
	asideMenuItem : function(menu){
		var html = "";
		
		if(menu.parent!=undefined && menu.parent.id==pageHelper.currentHeadMenu){
			pageHelper.asideMenuCount++;
			var show = "";
			var active = "";
			if(pageHelper.currentAsideMenu!=0){
				if(pageHelper.currentAsideMenu==menu.id){
					ac="active";
					show="show";
				}
			}else if(pageHelper.asideMenuCount==1){
				show="show";
				active = "active";
			}
			var submenu = pageHelper.asideSubMenuItem(menu.id,active);
			
			html = "<div class=\"card\">"
				+"<div class=\"card-header\" data-toggle=\"collapse\" data-target=\"#nav_"+menu.id+"\">"+menu.name+"</div>"
				+"<div id=\"nav_"+menu.id+"\" class=\"collapse show "+show+"\"><div class=\"card-body\"><div class=\"list-group\">"+submenu+"</div></div></div></div>";
    		
    	}
		
		return html;
	},
	asideSubMenuItem : function(asideMenuId,active){
		var html = "";
		var count = 0;
		$(pageHelper.menuDataList).each(function(i,menu){
			var ac = "";
			
			if(menu.parent!=undefined && menu.parent.id==asideMenuId){
				count++;
				if(pageHelper.currentAsideSubMenu!=0){
					if(pageHelper.currentAsideSubMenu==menu.id){
						ac="active";
					}
				}else if(active=="active"&&count==1){
					ac="active";
					pageHelper.currentAsideSubMenu = menu.id;
				}
				html += "<span head-menu-id=\""+pageHelper.currentHeadMenu+"\" id=\"submenu-"+menu.id+"\" href=\""+menu.href+"\" class=\"aside-submenu list-group-item list-group-item-action "+ac+"\">"+menu.name+"</span>";
	    		
	    	}
		});
		
		return html;
	},
	showMainTab : function(id,title,href){
		if(id==null || id == undefined)return ;
		var tab_id = "nav-main-tab-"+id;
		var tab_content_id = "nav-main-tab-content-"+id;
		var submenu_id = "submenu-"+id;
		pageHelper.openTabList=pageHelper.openTabList.replace(","+id,"");
		pageHelper.openTabList=pageHelper.openTabList+","+id;
		$(".main-tabs .main-tab").removeClass("active");
		$(".main-tab-content .tab-pane").removeClass("active");
		$(".main-tab-content .tab-pane").removeClass("show");
		
		var headMenuId = $("#"+submenu_id).attr("head-menu-id");
		if(headMenuId == undefined){
			headMenuId = $("#"+tab_id).attr("head-menu-id");
		}
		pageHelper.currentAsideSubMenu = id;

		pageHelper.initAsideMenu(headMenuId);
		
		$(".coosv-aside-menu .aside-submenu").removeClass("active");
		$("#"+submenu_id).addClass("active");

		if($(".main-tabs").find("#"+tab_id).length>0){//如果存在则显示出来
			$("#"+tab_id).addClass("active");
			$("#"+tab_content_id).addClass("active");
			$("#"+tab_content_id).addClass("show");
		}else{
			$(".main-tabs").append("<a class=\"nav-item nav-link main-tab active\" head-menu-id=\""+headMenuId+"\" id=\""+tab_id+"\">"+title+"<i class=\"main-tab-close fas fa-times\"></i></a>");
			$(".main-tab-content").append("<iframe src=\""+href+"\" class=\"tab-pane fade show active\" id=\""+tab_content_id+"\"></iframe>");
		}
		
	},
	removeMainTab : function(id){
		var tab_id = "nav-main-tab-"+id;
		var tab_content_id = "nav-main-tab-content-"+id;
		$("#"+tab_id).remove();
		$("#"+tab_content_id).remove();
		pageHelper.openTabList = pageHelper.openTabList.replace(","+id,"");
		if(pageHelper.openTabList.length==0){
			return ;
		}
		pageHelper.showMainTab(pageHelper.openTabList.substring(pageHelper.openTabList.lastIndexOf(",")+1));
	},
	initBreadcrumb: function(dom){
		if(top.pageHelper.menuDataList==null)return ;
		var currentAsideSubMenu = pageHelper.getMenu(top.pageHelper.currentAsideSubMenu);
		var currentAsideMenu = pageHelper.getMenu(currentAsideSubMenu.parent.id);
		var currentHeadMenu = pageHelper.getMenu(top.pageHelper.currentHeadMenu);
		var template ="<nav class=\"coosv-breadcrumb\">"
			            +"<ol class=\"breadcrumb\">"
			            +"<li class=\"breadcrumb-item\"><a href=\"javascript:void(0)\">"+currentHeadMenu.name+"</a></li>"
			            +"<li class=\"breadcrumb-item\"><a href=\"javascript:void(0)\">"+currentAsideMenu.name+"</a></li>"
			            +"<li class=\"breadcrumb-item active\" aria-current=\"page\">"+currentAsideSubMenu.name+"</li>"
			        +"</ol>"
			    +"</nav>";
		$(dom).html(template);
	},
	getMenu: function(menuId){
		if(top.pageHelper.menuDataList==null)return ;
		for (var i = 0; i < top.pageHelper.menuDataList.length; i++) {
			if(top.pageHelper.menuDataList[i]["id"] == menuId){
				return top.pageHelper.menuDataList[i];
			}
		}
	},
	getDataID :function(domId){
		return domId.substring(domId.lastIndexOf("-")+1);
	},
	getText: function(template,data){
		return juicer(template,data);
	},
	getRequest: function(href){
		
	   var url = window.location.href; //获取url中"?"符后的字串   
	   if(href != undefined){
		   url = href;
	   }
	   var pageRequest = new Object();   
	   if (url.indexOf("?") != -1) {   
	      var str = url.substring(url.indexOf("?")+1);   
	      var strs = str.split("&");   
	      for(var i = 0; i < strs.length; i ++) {   
	    	  pageRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
	      }   
	   }

	   return pageRequest;   
	},
	uuid: function(len, radix){
		var chars = '0123456789abcdefghijklmnopqrstuvwxyz'.split('');
	    var uuid = [], i;
	    radix = radix || chars.length;
	 
	    if (len) {
	      // Compact form
	      for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
	    } else {
	      // rfc4122, version 4 form
	      var r;
	 
	      // rfc4122 requires these characters
	      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
	      uuid[14] = '4';
	 
	      // Fill in random data.  At i==19 set the high bits of clock sequence as
	      // per rfc4122, sec. 4.1.5
	      for (i = 0; i < 36; i++) {
	        if (!uuid[i]) {
	          r = 0 | Math.random()*16;
	          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
	        }
	      }
	    }
	 
	    return uuid.join('');
	},
	alert: function(text){
		var id = "dialog-alert";
		var options = {id:id,title:"提示",html:text,size:"modal-sm",ok:{title:"确定",callback:function(){pageHelper.closeDialog(id);}}};
		return pageHelper.modal(options);
	},
	confirm: function(text,ok_callback){
		var id = "dialog-confirm";
		if(ok_callback==undefined){
			ok_callback = function(){console.log("请写确认处理。")};
		}
		var options = {id:id,title:"询问",html:text,size:"modal-sm",ok:{title:"确定",callback:ok_callback},cancel:{title:"取消",callback:function(){pageHelper.closeDialog(id);}}};
		return pageHelper.modal(options);
	},
	dialog: function(options){
		var id = "dialog-"+pageHelper.uuid(6);
		options.id = id;
		if(options.href !=undefined && options.href !=null){
			dataHelper.page(options.href,function(html){
				options.html = html;
				return pageHelper.modal(options);
            });
		}else{
			return pageHelper.modal(options);
		}
		
	},
	closeDialog: function(id){
		pageHelper.win.$('#'+id).modal('hide');
	},
	modal: function(options){
		var id = options.id;
		
		if(options.title == undefined) options.title = "标题";
		if(options.size == undefined)options.size="modal-lg";
		if(options.html == undefined)options.html="<div>正在初始化...</div>";
		var dialog = pageHelper.dialogHtml(options);
		
		pageHelper.win.$("body").append(dialog);
		if(options.cancel!=undefined) pageHelper.win.$("#"+id).on("click",".dialog-cancel-btn",options.cancel.callback);
		if(options.reset!=undefined) pageHelper.win.$("#"+id).on("click",".dialog-reset-btn",options.reset.callback); 
		if(options.ok!=undefined) pageHelper.win.$("#"+id).on("click",".dialog-ok-btn",function(){pageHelper.closeDialog(id);options.ok.callback();}); 
		
		
		pageHelper.win.$('#'+id).on('hide.bs.modal', function (e) {
			if(options.cancel!=undefined) pageHelper.win.$("#"+id).off("click",".dialog-cancel-btn"); 
			if(options.reset!=undefined) pageHelper.win.$("#"+id).off("click",".dialog-reset-btn"); 
			if(options.ok!=undefined) pageHelper.win.$("#"+id).off("click",".dialog-ok-btn"); 
			e.target.remove();
			if(options.closeCallback != undefined){
				options.closeCallback();
			}
		});
		
		pageHelper.win.$('#'+id).modal('show');
		
		return id;
	},
	dialogHtml: function(options){
		
		return "<div class=\"modal coosv-dialog fade\" id=\""+options.id+"\" href=\""+options.href+"\" tabindex=\"-1\" role=\"dialog\">"
		        +"<div class=\"modal-dialog "+(options.size!=undefined?options.size:"")+" \" role=\"document\">"//modal-dialog-centered
		        +"<div class=\"modal-content\">"
		            +"<div class=\"modal-header\">"
		                +"<h5 class=\"modal-title\">"+options.title+"</h5>"
		                +"<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">"
		                    +"<span aria-hidden=\"true\">&times;</span>"
		                +"</button>"
		            +"</div>"
		            +"<div class=\"modal-body\">"+options.html+"</div>"
		            +(options.cancel!=undefined||options.reset!=undefined||options.ok!=undefined?"<div class=\"modal-footer\">"
		                +(options.cancel!=undefined?"<button type=\"button\" class=\"btn dialog-cancel-btn btn-secondary\">"+(options.cancel.title!=undefined?options.cancel.title:"取消")+"</button>":"")
		                +(options.reset!=undefined?"<button type=\"button\" class=\"btn dialog-reset-btn btn-info\">"+(options.reset.title!=undefined?options.reset.title:"重置")+"</button>":"")
		                +(options.ok!=undefined?"<button type=\"button\" class=\"btn dialog-ok-btn btn-primary\">"+(options.ok.title!=undefined?options.ok.title:"确定")+"</button>":"")
		            +"</div>":"")
		        +"</div>"
		    +"</div>"
		+"</div>";
		
		
	}
	
}