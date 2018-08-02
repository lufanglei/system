var coosv_table_template="<div class=\"row\"><div class=\"col-12 mb-1\">"
		+"<form>"
			+"<div class=\"form-row\">"
				+"<div class=\"form-group hidden-div\" hidden>"
					+"<input type=\"hidden\" name=\"pageNo\" id=\"pageNo\">"
					+"<input type=\"hidden\" name=\"pageSize\" id=\"pageSize\">"
				+"</div>"
				+"<template v-for=\"head in heads\">"
					+"<template v-if=\"head.filter != undefined\">"
					+"<div :class=\"head.filter.col+' mt-1'\">"
						+"<div class=\"input-group input-group-sm\">"
							+"<div class=\"input-group-prepend\">"
								+"<span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">{{ head.name }}：</span>"
							+"</div>"
							+"<input :type=\"head.filter.type\" :name=\"head.id\" :id=\"head.id\" :placeholder=\"head.name\" class=\"form-control\">"
						+"</div>"
					+"</div>"
					+"</template>"
                +"</template>"
                +"<div class=\"col-1\">"
                    +"<button type=\"button\" v-if=\"filterCount() > 0\" v-on:click=\"pageCallback(1)\" class=\"btn btn-sm btn-primary\">查询</button>"
                +"</div>"
			+"</div>"   
		+"</form>"
    +"</div>"
    +"<div class=\"col-12\">"
    	+"<template v-for=\"button in buttons\">"
    		+"<template v-if=\"button.click != undefined\">"
    		+"<button type=\"button\" v-on:click=\"button.click\" class=\"btn btn-sm btn-primary mr-1\">{{ button.name }}</button>"
    		+"</template>"
    		+"<template v-else-if=\"button.toggle != undefined\">"
    		+"<button type=\"button\" v-on:click=\"buttonToDo\" :title=\"button.toggle.title\" :data-url=\"button.toggle.url\" :data-toggle=\"button.toggle.type\" class=\"btn btn-sm btn-primary mr-1\">{{ button.name }}</button>"
			+"</template>"
		+"</template>"
    +"</div>"
	+"<div class=\"col-12\">"
		+"<table class=\"table table-bordered table-striped table-hover table-sm\"\">"
			+"<thead>"
				+"<tr>"
					+"<template v-for=\"head in heads\">"
						+"<th :style=\"getHeadStyle(head)\" v-html=\"getHead(head)\"></th>"
					+"</template>"
				+"</tr>"
			+"</thead>"
			+"<tbody>"
				+"<tr v-for=\"per in page.list\" :key=\"per\">"
					+"<template v-for=\"head in heads\">"
					+"<td :style=\"head.width\">"
					+"<template v-if=\"head.type == 'checkbox'\">"
					+"<input type='checkbox' :name=\"head.id +'List'\" :value=\"per[head.id]\">"
					+"</template>"
					+"<template v-else-if=\"head.type == 'block'\">"
					+"<template v-for=\"link in head.links\">"
					+"<template v-if=\"link.type == 'link' && link.click != undefined\">"
	        		+"<a class=\"badge badge-primary mr-1\" v-on:click.prevent.self=\"link.click\" href=\"javascript:void(0);\">{{ link.name }}</a>"
	        		+"</template>"
	        		+"<template v-else-if=\"link.type == 'link' && link.toggle != undefined\">"
	        		+"<a class=\"badge badge-primary mr-1\" v-on:click.prevent.self=\"linkToDo\" :title=\"link.toggle.title\" :data-warn=\"link.toggle.warn\" :data-fresh=\"link.fresh\" :data-toggle=\"link.toggle.type\" :href=\"getLinkHref(link.toggle.url,per)\">{{ link.name }}</a>"
					+"</template>"
					+"</template>"
					+"</template>"
					+"<template v-else>"
					+"{{ per[head.id] }}"
					+"</template>"
					+"</td>"
					+"</template>"
				+"</tr>"
			+"</tbody>"
		+"</table>"
	+"</div>"
    +"<div class=\"col-12\">"
		+"<div class=\"pagination\"></div>"
    +"</div></div>";

var coosv_form_template = "<form><div class=\"form-row\">"
	+"<div class=\"col\" hidden>"
	+"<template v-for=\"field in fields\">"
	+"<template v-if=\"field.type === 'hidden'\">"
		+"<input type=\"hidden\" :name=\"field.id\" :id=\"field.id\" :value=\"formData[field.id]\">"
	+"</template>"
	+"</template>"
	+"</div>"
	+"<template v-for=\"field in fields\">"
	+"<div v-if=\"field.type != 'hidden'\" :class=\"field.col+' mt-1'\" >"
		+"<div class=\"input-group input-group-sm\">"
			
		    +"<div class=\"input-group-prepend\">"
		        +"<span class=\"input-group-text\" id=\"inputGroup-sizing-sm\">{{ field.name }}：</span>"
		    +"</div>"
		    +"<input v-if=\"field.type == 'input' || field.type == 'password' || field.type == 'date'\"\" :type=\"field.type\" :name=\"field.id\" :id=\"field.id\" :readonly=\"field.readonly\" :value=\"formData[field.id]\" :placeholder=\"field.name\" :rules=\"JSON.stringify(field.rules)\" class=\"form-control\">"
		    +"<select v-if=\"field.type == 'select'\" class=\"form-control form-control-sm\" id=\"inputGroupSelect01\">"
				+"<option value=\"\">请选择</option>"
				+"<template v-if=\"field.data != undefined && field.data.list != undefined\">"
				+"<option v-for=\"per in field.data.list\" :key=\"per\" :value=\"per.value\">{{ per.key }}</option>"
				+"</template>"
			+"</select>"
			+"<div v-if=\"field.type == 'checkbox'\" class=\"form-check form-check-inline col\">"
				+"<template v-if=\"field.data != undefined && field.data.list != undefined\">"
				+"<template v-for=\"per in field.data.list\">"
				+"<input class=\"form-check-input ml-1\" :name=\"field.id\" type=\"checkbox\" :id=\"per.key\" :value=\"per.value\">"
				+"<label class=\"form-check-label  mr-1\" :for=\"per.key\">{{ per.key }}</label>"
				+"</template>"
				+"</template>"
			+"</div>"
			+"<div v-if=\"field.type == 'radio'\" class=\"form-check form-check-inline col\">"
				+"<template v-if=\"field.data != undefined && field.data.list != undefined\">"
				+"<template v-for=\"per in field.data.list\">"
				+"<input class=\"form-check-input ml-1\" :name=\"field.id\" type=\"radio\" :id=\"per.key\" :value=\"per.value\">"
				+"<label class=\"form-check-label  mr-1\" :for=\"per.key\">{{ per.key }}</label>"
				+"</template>"
				+"</template>"
			+"</div>"
			+"<template v-if=\"field.type == 'file'\">"
				+"<div class=\"custom-file\">"
					+"<input type=\"file\" :name=\"field.id\" class=\"custom-file-input\" id=\"field.id\">"
					+"<label class=\"custom-file-label\" for=\"field.id\">选择文件</label>"
				+"</div>"
			+"</template>"
		    +"<textarea v-if=\"field.type == 'textarea'\" :name=\"field.id\" :id=\"field.id\" :placeholder=\"field.name\" class=\"form-control\">{{ formData[field.id] }}</textarea>"
		+"</div>"
	+"</div>"
	+"</template>"
	+"<template v-if=\"ok==undefined || (ok!=undefined && ok.show != false) || cancel==undefined || (cancel!=undefined && cancel.show != false) || reset==undefined || (reset!=undefined && reset.show != false)\">"
	+"<div class=\"col-12 mt-3 form-error\"></div>"
	+"<div class=\"col-12\">"
		+"<div class=\"row justify-content-end\">"
			+"<button v-if=\"cancel.show != false\" type=\"button\" v-on:click=\"cancel.click\" class=\"btn ml-1 form-cancel-btn btn-secondary\">{{ cancel.name }}</button>"
			+"<button v-if=\"reset.show != false\" type=\"button\" v-on:click=\"reset.click\" class=\"btn ml-1 form-reset-btn btn-info\">{{ reset.name }}</button>"
			+"<button v-if=\"ok.show != false\" type=\"button\" v-on:click=\"ok.click\" class=\"btn ml-1 form-ok-btn btn-primary\">{{ ok.name }}</button>"
        +"</div>"
	+"</div>"
	+"</template>"
+"</div></form>";  


var coosv_detail_template = "<form><div class=\"form-row\">"
	+"<div class=\"col\" hidden>"
	+"<template v-for=\"field in fields\">"
	+"<template v-if=\"field.type === 'hidden'\">"
		+"<input type=\"hidden\" :name=\"field.id\" :id=\"field.id\" :value=\"formData[field.id]\">"
	+"</template>"
	+"</template>"
	+"</div>"
	+"<template v-for=\"field in fields\">"
	+"<div v-if=\"field.type != 'hidden'\" :class=\"field.col+' mt-1'\" >"
		+"<div class=\"input-group input-group-sm\">"
		    +"<div class=\"input-group-prepend\">"
		        +"<span class=\"input-group-text\" readonly=\"readonly\" id=\"inputGroup-sizing-sm\">{{ field.name }}：</span>"
		    +"</div>"
		    +"<template v-if=\"field.type == 'input' || field.type == 'password'\">"
		    +"<input :type=\"field.type\" :name=\"field.id\" :id=\"field.id\" disabled :value=\"formData[field.id]\" class=\"form-control\">"
		    +"</template>"
		    +"<template v-if=\"field.type == 'textarea'\">"
		    +"<textarea :name=\"field.id\" :id=\"field.id\" disabled class=\"form-control\">{{ formData[field.id] }}</textarea>"
		    +"</template>"
		+"</div>"
	+"</div>"
	+"</template>"
	+"<template v-if=\"ok==undefined || (ok!=undefined && ok.show != false) || cancel==undefined || (cancel!=undefined && cancel.show != false) || reset==undefined || (reset!=undefined && reset.show != false)\">"
	+"<div class=\"col-12 mt-3 form-error\"></div>"
	+"<div class=\"col-12\">"
		+"<div class=\"row justify-content-end\">"
			+"<button v-if=\"cancel.show != false\" type=\"button\" v-on:click=\"cancel.click\" class=\"btn ml-1 form-cancel-btn btn-secondary\">{{ cancel.name }}</button>"
        +"</div>"
	+"</div>"
	+"</template>"
+"</div></form>";  

var coosv_tree_template = "<ul id=\"tree\" class=\"ztree\"></ul>";


Vue.component('coosv-tree', {
	props: {
		title: String,
		href: String,
		action: String,
		setting: Object,
		nodes: Array,
		selectNodeId:String
	},
	data: function () {
		var comp = this;
		comp.nodes = [];
		comp.selectNodeId = 0;
		return new Object();
    },
    mounted: function () {
    	var comp = this;
		comp.showTree();
		
    },
    methods: {
    	showTree: function(e){
    		comp = this;
    		dataHelper.asyncGetByAuth(comp.href,"",function(data){
    			if(data.code=="200"){
    				comp.nodes = data.data;
    				console.log(comp.nodes);
    				if(comp.setting.add!= undefined){
    					if(comp.setting.add.type=="modal"){
    						console.log("modal....");
    						comp.setting.add.click = function(zt,param){
    							comp.selectNodeId = param.tId;
    							pageHelper.dialog({href:"/organization/add.html",size:"modal-md",title:"添加机构",closeCallback:function(){comp.showTree()}})
    						}
    					}
    				}
    				console.log(comp.selectNodeId);
    				
    				var tree = $.fn.zTree.init($(comp.$el), comp.setting, comp.nodes);
    				tree.selectNode(tree.getNodeByTId(comp.selectNodeId));
    			}else{
    				$(comp.$el).html("<li>数据加载异常</li>");
    			}
    			
    			
    			comp.$forceUpdate();
            });
    	}
	},
    template: coosv_tree_template
});


Vue.component('coosv-form', {
	props: {
		title: String,
		href: String,
		action: String,
		fields: Array,
		ok: Object,
		cancel: Object,
		reset: Object
	},
	data: function () {
		var comp = this;
		if(comp.ok==undefined) comp.ok = new Object();
		if(comp.cancel==undefined) comp.cancel = new Object();
		if(comp.reset==undefined) comp.reset = new Object();
		comp.setDefault(comp.ok,"name","确定");
		comp.setDefault(comp.ok,"show",true);
		comp.setDefault(comp.ok,"click",comp.okToDo);
		comp.setDefault(comp.cancel,"name","取消");
		comp.setDefault(comp.cancel,"show",true);
		comp.setDefault(comp.cancel,"click",comp.cancelToDo);
		comp.setDefault(comp.reset,"name","重置");
		comp.setDefault(comp.reset,"show",true);
		comp.setDefault(comp.reset,"click",comp.resetToDo);
		comp.formData = {};
		console.log(comp);
		return new Object();
    },
    mounted: function () {
    	var comp = this;
    	if(comp.href != undefined){
			var href = $(comp.$el).parents('.coosv-dialog').attr("href");
			if(href != undefined){
				var request = pageHelper.getRequest(href);
				if(Object.keys(request).length>0){
					comp.href = pageHelper.getText(comp.href,request);
					dataHelper.asyncByAuth(comp.href,"","GET",function(data){
						if(data.code=="200"){
							comp.formData = data.data;
							comp.$forceUpdate();
						}
					});
				}
				
			}
			
			
		}
    },
    methods: {
    	cancelToDo: function(e){
    		var dialog_id = $(event.target).parents('.coosv-dialog').attr("id");
    		pageHelper.closeDialog(dialog_id);
    		
    	},
    	resetToDo: function(){
    		//alert("asdfasdf");
    		comp = this;
    		$(event.target).parents('form')[0].reset();
    		$(".form-error").html("");
    	},
    	okToDo: function(){
    		comp = this;
    		var dialog_id = $(event.target).parents('.coosv-dialog').attr("id");
    		if($($(event.target).parents('form')).valid()){
    			dataHelper.asyncByAuth(comp.action,$(event.target).parents('form').serialize(),"POST",function(data){
        			if(data.code=="200"){
        	    		pageHelper.closeDialog(dialog_id);
        				pageHelper.alert("操作成功。");
        			}else{
        				$(".form-error").html("<div class=\"alert alert-warning alert-dismissible fade show\"><strong>提交失败!</strong>"+data.message+"<button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span></button></div>");
        			}
        		});
    		}
    		
    	},
    	setDefault: function(obj,key,value){
    		if(obj[key]==undefined)obj[key] = value;
    	},
    	showButton: function(buttonType){
    		comp = this;
    		if(buttonType==undefined){
    			if(comp.ok!=undefined){
    				return true;
    			}
    		}else{
    			
    		}
    	}
	},
    template: coosv_form_template
});


Vue.component('coosv-detail', {
	props: {
		title: String,
		href: String,
		action: String,
		fields: Array,
		cancel: Object
	},
	data: function () {
		var comp = this;
		if(comp.cancel==undefined) comp.cancel = new Object();
		comp.setDefault(comp.cancel,"name","关闭");
		comp.setDefault(comp.cancel,"show",true);
		comp.setDefault(comp.cancel,"click",comp.cancelToDo);
		comp.formData = {};
		console.log(comp);
		return new Object();
    },
    mounted: function () {
    	var comp = this;
    	if(comp.href != undefined){
			var href = $(comp.$el).parents('.coosv-dialog').attr("href");
			if(href != undefined){
				var request = pageHelper.getRequest(href);
				if(Object.keys(request).length>0){
					comp.href = pageHelper.getText(comp.href,request);
					dataHelper.asyncByAuth(comp.href,"","GET",function(data){
						if(data.code=="200"){
							comp.formData = data.data;
							comp.$forceUpdate();
						}
					});
				}
				
			}
			
			
		}
    },
    methods: {
    	cancelToDo: function(e){
    		var dialog_id = $(event.target).parents('.coosv-dialog').attr("id");
    		pageHelper.closeDialog(dialog_id);
    		
    	},
    	setDefault: function(obj,key,value){
    		if(obj[key]==undefined)obj[key] = value;
    	},
    	showButton: function(buttonType){
    		comp = this;
    		if(buttonType==undefined){
    			if(comp.ok!=undefined){
    				return true;
    			}
    		}else{
    			
    		}
    	}
	},
    template: coosv_detail_template
});

Vue.component('coosv-table', {
	props: {
		title: String,
		url: String,
		heads: Array,
		buttons: Array,
		flag: Boolean,
	},
	data: function () {
		var page={list:[{}]};
		this.page = page;
		return page;
    },
    created: function () {
    	var comp = this;
    	console.log(comp.url);
		dataHelper.asyncGetByAuth(comp.url,"",function(data){
			comp.page = data.data;
			comp.flag = false;
			$($(comp.$el).find(".pagination")).pagination(comp.page.count, {
				num_edge_entries: 3, //边缘页数
				num_display_entries: 4, //主体页数
				callback: comp.pageCallback,
				items_per_page:10, //每页显示1项
				prev_text: "« 上一页",
                next_text: "下一页 »"
			});
			
			comp.$forceUpdate();
        });
    },
    methods: {
    	pageCallback: function(page_index, jq){
    		console.log(jq);
    		var comp = this;
    		$(comp.$el).find("#pageNo").val(page_index);
    		$(comp.$el).find("#pageSize").val(10);
    		dataHelper.asyncGetByAuth(comp.url,$(comp.$el).find("form").serialize(),function(data){
    			comp.page = data.data;
    			
    			$($(comp.$el).find(".pagination")).pagination(comp.page.count, {
    				num_edge_entries: 3, //边缘页数
    				num_display_entries: 4, //主体页数
    				callback: comp.pageCallback,
    				current_page:page_index,
    				items_per_page:10, //每页显示1项
    				prev_text: "« 上一页",
                    next_text: "下一页 »"
    			});
    			
    			comp.$forceUpdate();
            });
    		return false;
    	},
    	buttonToDo: function (event) {
    		var comp = this;
    		var togger = $(event.target).attr("data-toggle");
    		var href = $(event.target).attr("data-url");
    		var title = $(event.target).attr("title");
    		console.log($(event.target));
    		if(togger=="modal"){
    			pageHelper.dialog({title:title,href:href,closeCallback:function(){comp.pageCallback(1)}});
    		}
    	    //eventHub.$emit('delete-todo', id)
    	},
    	getLinkHref: function(url,data){
    		url = pageHelper.getText(url,data);
    		return url;
    	},
    	linkToDo: function(event){
    		var comp = this;
    		var togger = $(event.target).attr("data-toggle");
    		var href = $(event.target).attr("href");
    		var title = $(event.target).attr("title");
    		var fresh = $(event.target).attr("data-fresh");
    		var warn = $(event.target).attr("data-warn");
    		if(togger=="modal"){
    			var closeCallback = null;
    			if(fresh==="true"){
    				closeCallback = function(){comp.pageCallback(1)};
    			}
    			pageHelper.dialog({title:title,href:href,closeCallback:closeCallback});
    		}else if(togger=="load"){
    			if(warn!=undefined){
    				pageHelper.confirm(warn,function(){
    					dataHelper.asyncByAuth(href,"","GET",function(data){
        					if(data.code=="200"){
        						comp.pageCallback(1);
        					}else{
        						pageHelper.alert("操作失败。");
        					}
        				});
    				});
    			}else{
    				dataHelper.asyncByAuth(href,"","GET",function(data){
    					if(data.code=="200"){
    						comp.pageCallback(1);
    					}else{
    						pageHelper.alert("操作失败。");
    					}
    				});
    			}
    			
    		}
    		return false;
    	},
    	is_obj:function(val){
    	      return Object.prototype.toString.call(val) === '[object Object]'
    	},
    	getHead: function(obj){
    		var comp = this;
    		var type = obj["type"];
    		if(type=="checkbox"){
    			$(comp.$el).on("click","input[name='batchSelect']",function(e){  
    				if($(this).is(':checked')){
    					$(comp.$el).find("input[type='checkbox']").prop("checked",true); 
    				}else{
    					$(comp.$el).find("input[type='checkbox']").prop("checked",false);
    				}
    			});  
    			return "<input type='checkbox' name='batchSelect'>";
    		}
    		return obj["name"];
    	},
    	getHeadStyle: function(obj){
    		return {width:obj["width"]};
    	},
    	filterCount: function(){
    		var comp = this;
    		var count = 0;
    		$(comp.heads).each(function(){
    			if(this.filter!=undefined){
    				count++;
    			}
    		});
    		return count;
    	}
    	
    
	},
    template: coosv_table_template
});