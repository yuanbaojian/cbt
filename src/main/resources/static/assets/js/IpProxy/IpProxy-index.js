//# sourceURL=IpProxy-index.js

var IpProxyIndex=function () {
    var IpProxyGrid;
    var tbl="#tbl_IpList"

    return{

        init:function () {
            IpProxyIndex.initAuditFileGrid();
        },



        initAuditFileGrid:function(e) {
            if(IpProxyGrid){
                IpProxyGrid.ajax.url(
                    "IpProxy/getAllIp?json"
                ).load();
            } else {
                IpProxyGrid = $(tbl)
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
                            "bFilter": true, //去掉自带的搜索框
                            "bLengthChange": true, //去掉每页显示多少条数据的框框
                            "ajax" : {
                                "url" :
                                    "IpProxy/getAllIp?json",
                                "type" : "POST",
                                "cache" : false,
                                "contentType" : "application/json; charset=utf-8",
                                "dataSrc" : ""
                            },

                            "aoColumnDefs" : [ {
                                sDefaultContent : '',
                                aTargets : [ '_all' ]
                            } ],
                            // 填入列数据
                            "columns" : [
                                {"data" : "protocolType","width": "20%"},
                                {"data": "ipAddress", "width": "10%"},
                                {"data": "ipPort", "width": "10%"},
                                {"data" : "serverAddress", "width": "20%"},
                                {"data" : "anonymityType","width": "12%"},

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

        response:function (url) {
            $.ajax({
                url:url,
                type:"post",
                success:function () {
                    
                }
            })
        }








    }

}();