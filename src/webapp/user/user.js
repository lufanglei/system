var formData = {
    	fields:[{id:"id",type:"hidden",name:"唯一标志"}
        ,{id:"loginName",type:"input",name:"登录名",col:"col-sm-12 col-md-6 col-lg-4",rules:{required:true,maxlength:6}}
        ,{id:"password",type:"password",name:"密码",col:"col-sm-12 col-md-6 col-lg-4"}
    	,{id:"name",type:"input",name:"名称",col:"col-sm-12 col-md-6 col-lg-4",rules:{required:true,maxlength:6}}
    	,{id:"mobile",type:"input",name:"电话",col:"col-sm-12 col-md-6 col-lg-4"}
    	,{id:"radio",type:"radio",data:{list:[{key:"单选1",value:"radio1"},{key:"单选2",value:"radio2"}]},name:"单选框",col:"col-sm-12 col-md-6 col-lg-4"}
    	,{id:"checkbox",type:"checkbox",data:{list:[{key:"复选1",value:"checkbox1"},{key:"复选2",value:"checkbox2"}]},name:"复选框",col:"col-sm-12 col-md-6 col-lg-4"}
    	,{id:"select",type:"select",data:{list:[{key:"下拉1",value:"selcect1"},{key:"下拉2",value:"selcect2"},{key:"下拉3",value:"selcect3"}]},name:"下拉",col:"col-sm-12 col-md-6 col-lg-4"}
    	,{id:"email",type:"input",name:"邮箱",col:"col-sm-12 col-md-6 col-lg-4"}
    	,{id:"no", type:"input", name:"工号",col:"col-sm-12 col-md-6 col-lg-4"}
    	,{id:"date", type:"date", name:"日期",col:"col-sm-12 col-md-6 col-lg-4"}
    	,{id:"headpic", type:"file", name:"头像",col:"col-12"}
    	,{id:"remarks",type:"textarea",name:"备注",col:"col-12"}]
	}

var tableData = {
    	title:"就是得哈",
    	heads:[{id:"id",type:"checkbox",width:"20px",name:"唯一标志"}
            	,{id:"name",type:"row",name:"名称",filter:{type:"input",col:"col-sm-6 col-md-3 col-lg-2"}}
            	,{id:"no", type:"row", name:"工号",filter:{type:"input",col:"col-sm-6 col-md-3 col-lg-2"}}
            	,{id:"remarks",type:"row",name:"备注",filter:{type:"input",col:"col-sm-6 col-md-3 col-lg-2"}}
            	,{type:"block",width:"150px",name:"操作",links:[
            		{type:"link",name:"修改",fresh:true,toggle:{type:"modal",title:"修改用户",url:"edit.html?id=${id}"}}
            		,{type:"link",name:"删除",fresh:true,toggle:{type:"load",title:"删除用户",warn:"是否删除此数据？",url:"http://localhost:8767/auth/user/delete?userid=${id}"}}
            		,{type:"link",name:"查看",fresh:false,toggle:{type:"modal",title:"查看用户",url:"detail.html?id=${id}"}}
            	]}
        ],
    	buttons:[
    		{name:"添加",permit:"shiro:user:add",fresh:true,toggle:{type:"modal",title:"添加一个用户",url:"http://localhost/user/add.html"}}
    		,{name:"删除",permit:"shiro:user:del",toggle:{type:"load",url:"/auth/user/delete",warn:"是否删除此数据？"}}
    		,{name:"导出",permit:"shiro:user:export",click:function(){
    			pageHelper.alert("abc");
    		}}
    	]
	}