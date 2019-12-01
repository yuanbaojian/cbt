//# sourceURL=auditFile.js

var auduitFileIndex=function () {

    //上下文
    var basepath;
    //内审文件表
    var interiorAuditFileGrid;
    //外审文件表
    var externalAuditFileGrid;
    //内审文件
    var interiorFile=0;
    //外审文件
    var externalFile=1;
    var docName="";
    var startDate="";
    var endDate="";
    //被选中的文件类型
    var selectedDocType;
    var selectedGrid;
    var selectedTbl;
    var tbl_interiorFile="#tbl_interiorFile";
    var tbl_externalFile="#tbl_externalFile";

    //权限校验
    var canDownload=false;
    var canEdit=false;
    var canPreview=false;
    var canDelete=false;

    return{

        init:function (basePath,permissions) {
            basepath=basePath;
            auduitFileIndex.changeSelectParam("interior");
            auduitFileIndex.initAuditFileGrid(externalAuditFileGrid, tbl_externalFile, externalFile);
            auduitFileIndex.initAuditFileGrid(interiorAuditFileGrid, tbl_interiorFile, interiorFile);
            auduitFileIndex.initDatePicker();
            auduitFileIndex.initValidate();
            auduitFileIndex.initAuthority(permissions);
            auduitFileIndex.initFileNameListener();
        },

        initValidate:function(){
            $('#auditFile-add-edit-form').validate({
                errorElement: 'span', //default input error message container
                errorClass: 'error', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules : {
                    docName : {
                        required : true,
                        maxlength: 255
                    },
                    remark:{
                        maxlength: 255
                    }
                },
                messages : {
                    docName : {
                        required : "请输入名称",
                        maxlength : $.validator.format("名称不能超过{0}个字符")
                    },
                    remark:{
                        maxlength: $.validator.format("名称不能超过{0}个字符")
                    }
                },
            });
        },

        initAuditFileGrid:function(grid,tbl, docType) {
            if(grid){
                grid.ajax.url(
                    "auditFile/list?json"
                    +"&docType="+docType
                    +"&docName=" + $("#searchBox #docName").val()
                    +"&startDate=" + $("#searchBox #startDate").val()
                    +"&endDate="+  $("#searchBox #endDate").val()
                ).load();
            } else {
                grid = $(tbl)
                    .DataTable(
                        {
                            "lengthMenu" : [ [ 10, 20, 30 ],
                                [ 10, 20, 30 ] // change per page
                                // values here
                            ],
                            "ordering":false,
                            "retrieve":true ,
                            "bDestory" :true,
                            "pageLength": 10,
                            "bAutoWidth" : false,
                            "bFilter": false, //去掉自带的搜索框
                            "bLengthChange": false, //去掉每页显示多少条数据的框框
                            "ajax" : {
                                "url" :
                                    "auditFile/list?json"
                                    +"&docType="+docType
                                    +"&docName=" +docName
                                    +"&startDate=" +startDate
                                    +"&endDate="+endDate,
                                "type" : "get",
                                "cache" : false,
                                "contentType" : "application/json; charset=utf-8",
                                "dataSrc" : ""
                            },
                            "drawCallback" : function(row, data) {
                                if(docType==interiorFile){
                                    interiorAuditFileGrid=grid;
                                } else {
                                    externalAuditFileGrid=grid;
                                }
                                selectedDocType=docType
                            },
                            "aoColumnDefs" : [ {
                                sDefaultContent : '',
                                aTargets : [ '_all' ]
                            } ],
                            // 填入列数据
                            "columns" : [
                                {"data" : "docName","width": "20%"},
                                {"data" : "fileType"},
                                {"data" : "fileSizeLength"},
                                {"data" : "remark", "width": "20%"},
                                {"data" : "updateByString","width": "12%"},
                                {"data" : function( row, type, val, meta ){
                                        return moment(row.updateTime).format('YYYY-MM-DD HH:mm');
                                    }, "width": "15%"},
                                { "data": null, "mRender":function(data,type,full){
                                        let result="";
                                        if(canEdit){
                                            result += "<a href='javascript:void(0)'  title=\"修改\"onclick=\"auduitFileIndex.showUpdateAuditFileModal(this)\" >修改</a>&nbsp;&nbsp;"
                                        }
                                        if(canDelete){
                                            result +="<a href='javascript:void(0)'  title=\"删除\" onclick=\"auduitFileIndex.deleteAuditFile(this)\" >删除</a>&nbsp;&nbsp;"
                                        }
                                        if(canDownload){
                                            result +="<a href='javascript:void(0)' title=\"下载\"  id=\"download\" onclick=\"auduitFileIndex.downloadAuditFile(this)\">下载</a>"
                                        }
                                        return  result;
                                    }, "width": "12%"}
                            ],
                            "oLanguage" : {
                                "sProcessing" : "正在加载中......",
                                "sLengthMenu" : "每页显示_MENU_条记录",
                                "sZeroRecords" : "对不起，查询不到相关数据！",
                                "sEmptyTable" : "无数据存在！",
                                "sInfo" : "当前显示_START_到_END_条，共_TOTAL_条记录",
                                "sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
                                "sSearch" : "",
                                "oPaginate" : {
                                    "sFirst" : "首页",
                                    "sPrevious" : "上一页",
                                    "sNext" : "下一页",
                                    "sLast" : "末页"
                                }
                            }, // 多语言配置
                            "stateSave" : true
                            // save the state of a table
                        });
            }
            $( tbl+ ' tbody').on('mouseover', 'td', function() {
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
            $(tbl+ "_length>label").css({"width":"200px","height":"28px","display":"block"});
            //可以进行多表查询应该
            $(tbl+ '_wrapper .dataTables_filter input').addClass("form-control input-sm").attr("placeholder", "搜索");
            $(tbl+ '_wrapper .dataTables_length select').addClass("m-wrap small form-control input-sm");

            $(tbl+ '_column_toggler input[type="checkbox"]').change(function () {
                var iCol = parseInt($(this).attr("data-column"));
                var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
                oTable.fnSetColumnVis(iCol, (bVis ? false : true));
            });
            $(tbl+ ' tbody').on('click', 'tr', function() {
                $(tbl+ " tbody tr").removeClass("selected");
                $(this).addClass('selected');
            });
        },




        showAddFileModal:function () {
            $('#auditFile-add-edit-form').validate().resetForm();
            $('#docId').val("");
            if( $("a[href='#interiorFileTable']").hasClass("active") ){
                $('#auditFile-add-edit-modal .modal-header .modal-title').empty().html('<button type="button" class="fa fa-plus btn btn-primary btn-xs" ></button>  添加内审文件');
            } else {
                $('#auditFile-add-edit-modal .modal-header .modal-title').empty().html('<button type="button" class="fa fa-plus btn btn-primary btn-xs" ></button>  添加外审文件');
            }
            $('#doc-reset').click();
            $('#auditFile-add-edit-modal').modal('show');
        },

        insertAuditFile:function () {
            var ifRepeated=auduitFileIndex.checkNameRepeated();
            if(ifRepeated){
                bootbox.alert("名称 " + "<span style=\"font-size:120%;color:red;\">"
                    +  $("#auditFile-add-edit-form #docName").val() + "</span>" + " 重复");
                return;
            }
            var ifAuditFileExisted=auduitFileIndex.checkAuditFileExisted();
            if(!ifAuditFileExisted){
                bootbox.alert("请上传文件");
                return;
            }
            if ($('#auditFile-add-edit-form').validate().form()) {
            $("#auditFile-add-edit-form").ajaxSubmit({
                url: basepath+ "auditFile/file/"+ selectedDocType ,
                type:"POST",
                beforeSend:function(){
                    $('body').loadingModal({
                        text: '加载中...',
                        animation:"circle"
                    });
                },
                success:function () {
                    $("#auditFile-add-edit-modal").modal('hide');
                    $("body").css("padding-right","0px");
                    if( $("a[href='#interiorFileTable']").hasClass("active") ){
                        selectedGrid=interiorAuditFileGrid;
                    } else{
                        selectedGrid=externalAuditFileGrid
                    }
                    auduitFileIndex.initAuditFileGrid(selectedGrid,selectedTbl,selectedDocType);
                },
                complete:function () {
                    $('body').loadingModal('hide');
                    $('body').loadingModal('destroy');
                }
            })
            }
        },

        updateAuditFile: function () {
            var ifRepeated=auduitFileIndex.checkNameRepeated();
            if(ifRepeated){
                bootbox.alert("名称 " + "<span style=\"color:red;\">"
                    +  $("#auditFile-add-edit-form #docName").val() + "</span>" + " 重复");
                return;
            }
            var ifAuditFileExisted=auduitFileIndex.checkAuditFileExisted();
            if(!ifAuditFileExisted){
                bootbox.alert("请上传文件");
                return;
            }
            if ($('#auditFile-add-edit-form').validate().form()) {
                $("#auditFile-add-edit-form").ajaxSubmit({
                    url: basepath + "auditFile/update",
                    type: "POST",
                    beforeSend:function(){
                        $('body').loadingModal({
                            text: '加载中...',
                            animation:"circle"
                        });
                    },
                    success: function (data) {
                        if (data["code"] == 0) {
                            $("#auditFile-add-edit-modal").modal('hide');
                            $("body").css("padding-right", "0px");
                            auduitFileIndex.initAuditFileGrid(selectedGrid, selectedTbl, selectedDocType);
                        } else {
                            bootbox.alert("wrong");
                        }
                    },
                    complete:function () {
                        $('body').loadingModal('hide');
                        $('body').loadingModal('destroy');
                    }
                })
            }
        },

        saveAuditFile: function () {
            var url;
            if($('#docId').val()==""){
                auduitFileIndex.insertAuditFile();
            } else{
                auduitFileIndex.updateAuditFile();
            }
        },


        changeSelectParam:function(type){
            if(type=="interior"){
                selectedDocType=interiorFile;
                selectedGrid=interiorAuditFileGrid;
                selectedTbl=tbl_interiorFile
            }else{
                selectedDocType=externalFile;
                selectedGrid=externalAuditFileGrid;
                selectedTbl=tbl_externalFile;
            }
        },


        deleteAuditFile:function(ele) {
            var selected = $(ele).closest('tr');
            //已选中的表格， 暂时选择器这样写
            if( $("a[href='#interiorFileTable']").hasClass("active") ){
                selectedGrid=interiorAuditFileGrid;
            } else {
                selectedGrid=externalAuditFileGrid;
            }
            var row = selectedGrid.row(selected[0]);
            var rowData = row.data();
            var docId=rowData.docId;
            var alertMessage = "";
            alertMessage ="确认要删除吗?";
            bootbox.confirm({
                buttons: {
                    confirm: {
                        label: '确定',
                        className: 'btn-primary'
                    },
                    cancel: {
                        label: '取消',
                        className: 'btn-default'
                    }
                },
                message:alertMessage,
                title:'删除确认',
                callback: function(result){
                    if (result) {
                        $.ajax({
                            type: 'delete',
                            url: 'auditFile/file/'+docId,
                            cache: false,
                            success: function(data) {
                                if (data['code'] == 0) {
                                    auduitFileIndex.initAuditFileGrid(selectedGrid,selectedTbl,selectedDocType);
                                } else  {
                                    bootbox.alert("wrong");
                                }
                            },
                            error: function(data) {
                                bootbox.alert(JSON.stringify(data));
                                if (data.status == 401) {
//                             	     window.location.href = '';
                                } else if(data.status == 500) {
                                    bootbox.alert('服务器无法响应，可能是会话失效，请尝试重新登录');
//                                   window.location.href = '';
                                } else {
                                    bootbox.alert(JSON.stringify(data));
                                }
                            }
                        });
                    }
                }
            });
        },
        showUpdateAuditFileModal: function (ele) {
            if( $("a[href='#interiorFileTable']").hasClass("active") ){
                selectedGrid=interiorAuditFileGrid;
            } else {
                selectedGrid=externalAuditFileGrid;
            }
            var selected = $(ele).closest('tr');
            var row = selectedGrid.row($(selected[0]));
            var rowData = row.data();
            var remark=rowData.remark;
            var docName=rowData.docName;
            var docId=rowData.docId;


            $("#auditFile-add-edit-form #docName").val(docName);
            $("#auditFile-add-edit-form #remark").val(remark);
            $("#auditFile-add-edit-form #docId").val(docId);
            $("a[data-dismiss='fileinput']").click();
            var filePath=auduitFileIndex.getFilePathByDocId(docId);
            if(filePath != null){
                $("#aduitFileNameDiv").addClass('fileinput-exists').removeClass('fileinput-new');
                var fileName=filePath.substring( filePath.lastIndexOf('\\')+1);
                $("#auditFileName").text(fileName);
            }
            if( $("a[href='#interiorFileTable']").hasClass("active") ){
                $('#auditFile-add-edit-modal .modal-header .modal-title').empty().html('<button type="button" class="fa fa-edit btn btn-primary btn-xs" ></button>  编辑内审文件');
            } else {
                $('#auditFile-add-edit-modal .modal-header .modal-title').empty().html('<button type="button" class="fa fa-edit btn btn-primary btn-xs" ></button>  编辑外审文件');
            }
            $("#auditFile-add-edit-modal").modal("show");
        },
        getFilePathByDocId: function (docId) {
            let filePath;
            $.ajax({
                url:basepath+ "auditFile/getFilePathByDocId/"+ docId,
                type:"get",
                async:false,
                dataType:"json",
                success:function (data) {
                    filePath= data["data"]["filePath"];
                }
            });
            return filePath;
        },

        initDatePicker:function () {
            $('#startDate').datepicker({
                format:'yyyy-mm-dd',
                language: "zh-CN",
                locale:moment.locale('zh-CN'),
                todayHighlight: true
            });
            $('#endDate').datepicker({
                format:'yyyy-mm-dd',
                locale:moment.locale('zh-CN'),
                todayHighlight: true
            });
        },

        conditionSearch:function () {
            if( $("a[href='#interiorFileTable']").hasClass("active") ){
                auduitFileIndex.initAuditFileGrid(interiorAuditFileGrid, tbl_interiorFile, interiorFile);
            } else{
                auduitFileIndex.initAuditFileGrid(externalAuditFileGrid, tbl_externalFile, externalFile);
            }
        },

        downloadAuditFile: function (ele) {
            if( $("a[href='#interiorFileTable']").hasClass("active") ){
                selectedGrid=interiorAuditFileGrid;
            } else {
                selectedGrid=externalAuditFileGrid;
            }
            var selected = $(ele).closest('tr');
            var row = selectedGrid.row($(selected[0]));
            var rowData = row.data();
            var docId=rowData.docId;
            $("#downloadForm #docId").val(docId);
            $("#downloadForm").submit();
        },

        checkNameRepeated:function () {
            var result;
            $.ajax({
                url:basepath  + "auditFile/checkNameRepeated",
                data:{
                    docId: $("#auditFile-add-edit-form #docId").val(),
                    docName:$("#auditFile-add-edit-form #docName").val(),
                    docType:selectedDocType
                },
                async:false,
                dataType:"json",
                success:function (data) {
                    if(data['code']==0){
                       result=false;
                    } else if(data['code']==1){
                        result=true;
                    }
                }
            });
            return result;
        },
        checkAuditFileExisted:function () {
            var result;
            if($("#auditFile-add-edit-form #auditFile").val()=="" && $("#docId").val()==""){
                result=false;
            } else{
                result=true;
            }
            return result;
        },

        initAuthority:function(permissionsJsp) {
            var permissionArr = [];
            if(permissionsJsp){ //这里相当于隔离了未登录
                permissionArr = permissionsJsp.replace('[', '').replace(']', '').replace(/\s+/g, "").split(",");
            }
            var i = 0, length = permissionArr && permissionArr.length;
            for (; i < length; i++) {
                if (permissionArr[i] == 'quality_document_management_editDoc') {
                    canEdit= true;
                } else if (permissionArr[i] == 'quality_document_management_deleteDoc') {
                    canDelete= true;
                } else if (permissionArr[i] == 'quality_document_management_downloadDoc') {
                    canDownload= true;
                }
            }
        },
        initFileNameListener:function() {
            $('#auditFileName').bind('DOMNodeInserted',function(e) {
                if($("#docName").val()=="" ){
                    var originalFileName = $("#auditFileName").html();
                    var fileName=originalFileName.substring(0, originalFileName.lastIndexOf('.'));
                    $("#docName").val(fileName);
                }
            })
        }
    }

}();