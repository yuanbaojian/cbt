//# sourceURL=user-index.js
/*
 * 系统账户管理相关的JS脚本
 */
var UserIndex = function() {

	var userGrid;
	var userTable="#userTable"

	return{
		//页面初始化入口函数
		init: function () {
		    UserIndex.initUserTable(userTable);
        },//end init()

		initAuthority : function(permissions) {
			var permissionArr = [];
			if(permissions){ //这里相当于隔离了未登录
				permissionArr = permissions.replace('[', '').replace(']', '').replace(/\s+/g, "").split(",");
			}
			var i = 0, length = permissionArr && permissionArr.length;
			for (; i < length; i++) {
				if (permissionArr[i] == 'user_manage_editUser') {
					canEdit= true;
				} else if (permissionArr[i] == 'user_manage_deleteUser') {
					canDelete= true;
				}
			}
		},
        
        //表单验证函数formInit
 	    formInit:function() {
 	    	//验证手机号码格式是否正确
 	    	jQuery.validator.addMethod("isPhone", function(value, element) {
 		        var phone =new RegExp("^(13|14|15|18)\\d{9}$");
 		        return this.optional(element) || (phone.test(value));  
 		    }, "请输入正确的电话号码");
 	    	//验证身份证号格式是否正确
 	    	jQuery.validator.addMethod("isIdCard", function(value, element) {
 		        var idcard =/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
 		        return this.optional(element) || (idcard.test(value));  
 		    }, "请输入正确的身份证号码");
 			// 在键盘按下并释放及提交后验证提交表单
 			  $("#addTeacherForm").validate({
 				    rules: {
 				    	userFullName: "required",
 				    	loginName: "required",
 				    	mobilePhone:{
 				    	   //required: true,
 	                 	   isPhone:true
 	                    },
 				    	workYear:{
 				    	//required:true,
 				    		number:true
 				    	},
 				    	remark:{
 					        //required: true,
 					        rangelength:[0,100]
 					        },
 					       idNo: {
 				    		required: true,
 				    		isIdCard: true
 				      }
 				      
 				    },
 				    messages: {
 				    	userFullName: "姓名不能为空",
 				    	loginName: "用户名不能为空",
 				    	mobilePhone:{
 				    		//required:"电话号码不能为空",
 				    		isPhone:"请输入正确的电话号码"
 				    	},
 				    	idNo: {
 				    		required:"身份证号码不能为空",
 				    		isPhone:"请输入正确的身份证号码"
 				    	},
 				    	workYear:{
 				    		//required:"工作年限不能为空",
 				    		number: "请输入有效的数字",
 				    		},
 				    	remark:{
 				    		//required:"描述不能为空",
 				    		rangelength: $.validator.format( "请输入 {0} 到 {1} 个汉字." )
 				    		}
 				    }
 				});
 			
        },//end formInit()

        //数据表格初始化用户信息initGrid
        initUserTable:function(table) {
        	if(userGrid){
				userGrid.ajax.url("user/getAllUser").load();
			}else{
				grid = $('#userTable').DataTable( {
				"lengthMenu": [
					            [10, 20, 30],
					            [10, 20, 30] // change per page values here
					        ],
					        "ordering":false,
					        "retrieve":true ,
					        "bDestory" :true,
							"pageLength": 10,
							"bAutoWidth" : false,
							 "ajax": {
								"url": "user/getAllUser",
								"type":"post",
					            "cache" : false,
								 "async": false,
					            "contentType": "application/json; charset=utf-8",
					            "dataSrc": ""
					        },
					        "aoColumnDefs" : [ {
					              sDefaultContent : '',
					              aTargets : [ '_all' ]
					          }],
					        "columns": [
					            { "data": "loginName" },
					            { "data": "userFullName" },
					            { "data": "gender", "mRender":function(data,type,full){
					            	if(data=="M")
					            		{
					            		data="男";
					            		}else{
					            		data="女";
					            		}
					            	  return data;
			 			        }},
					            { "data": "mobilePhone" },
					            { "data": "userType" , "mRender":function(data,type,full){
					            	if(data=="0") {
				            			data="系统用户";
				            		}else if(data=="1"){
				            			data="普通用户";
				            		} else{
					            		data="其他"
									}
									  return data;
		 			        	}},
					            { "data": "userRoles", "mRender":function(data,type,full){
					            	var rolesname= "";
					            	for(var index in data){  
					            		if(data[index].roleName!=undefined){
					            			rolesname += (data[index].roleName);
			     		            		  if (index < data.length-1){
			     		            			 rolesname += ","; 
			     		            		  }
					            		}
				            	    }
		    		            	  return rolesname;
		    			            }},
					            { "data": "remark" },
					            { "data": null, "mRender":function(data,type,full){
									let result="<a href='javascript:void(0)' title=\"查看明细\" onclick=\"UserIndex.toViewDetail(this)\">查看明细</a>&nbsp;&nbsp;";
									result+="<a href='javascript:void(0)' title=\"编辑\" onclick=\"UserIndex.editTeacher(this)\">编辑</a>&nbsp;&nbsp;";
									result+="<a href='javascript:void(0)' title=\"分配角色\" onclick=\"UserIndex.assignRole(this)\">分配角色</a>&nbsp;&nbsp;";
									result+="<a href='javascript:void(0)' title=\"删除\" onclick=\"UserIndex.delTeacher(this)\">删除</a>";
									return  result;
			    	            }}
					        ],
					        "columnDefs": [ {
			    	               "searchable": false,
			    	               "orderable": false,
			    	               "targets": [ '_all' ]
			    	           } ],
					        "oLanguage": {
					            "sProcessing": "正在加载中......",
					        	"sLengthMenu":"每页显示_MENU_条记录",
					            "sZeroRecords": "对不起，查询不到相关数据!",
					            "sEmptyTable": "无数据存在!",
					            "sInfo": "当前显示_START_到_END_条，共_TOTAL_条记录",
					            "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
					            "sSearch": "",
					            "oPaginate": {
					            	"sFirst": "首页",
					                "sPrevious": "上一页",
					                "sNext": "下一页",
					                "sLast": "末页"
					        	}
					        }, //多语言配置
					        "stateSave": true //save the state of a table 
					    }
					);
        	        $( table+' tbody').on('mouseover', 'td', function() {
        				var children = $(this).children();
        	    		if(children.length==0){
        	    			var fileName = $(this).html();
        	    			$(this).html('<p title="' + fileName + '">' + fileName + '</p>');
        						$(this).find('p').tooltipsy({
        							css : {
        								'padding' : '10px',
        								'max-width' : '400px',
        								'color' : '#303030',
        								'background-color' : '#f5f5b5',
        								'border' : '1px solid #deca7e',
        								'-moz-box-shadow' : '0 0 10px rgba(0, 0, 0, .5)',
        								'-webkit-box-shadow' : '0 0 10px rgba(0, 0, 0, .5)',
        								'box-shadow' : '0 0 10px rgba(0, 0, 0, .5)',
        								'text-shadow' : 'none'
        							}
        						});
        					}
        			});
				   $( table+ "_length>label").css({"width":"200px","height":"28px","display":"block"});
        		   $(table+ '_wrapper .dataTables_filter input').addClass("form-control input-sm").attr("placeholder", "搜索");
        		   $(table+ '_wrapper .dataTables_length select').addClass("m-wrap small form-control input-sm");
        		   $(table+ '_column_toggler input[type="checkbox"]').change(function () {
        	           var iCol = parseInt($(this).attr("data-column"));
        	           var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
        	           oTable.fnSetColumnVis(iCol, (bVis ? false : true));
        	       });
        		   $(table+' tbody').on('click', 'tr', function() {
        	           $(table+ " tbody tr").removeClass("selected");
        	           $(this).addClass('selected');
        	       });    
			}
        },



	};
}();