//# sourceURL=calendar.js

var calendar=function(){
    //被选中的教学内容ID
    var selectedTeachContentId;
    //被选中的课程ID
    var selectedCourseId;
    var currentUserId;
    //已选中的多个课程id
    var selectedCourseIds;
    //已选中的机型ID
    var selectedPmId;
    //被选中的课件ID
    var courseIdsChoosed;

    return {

        init:function(){
            calendar.CalendarInit();
        },

        getUserId: function () {
            $.ajax({
                url: "user/getUserId",
                type: "post",
                sync: false,
                success: function (data) {
                    currentUserId = data['data']['userId'];
                }
            });
        },

        getSelectedCourseId:function(){
          return selectedCourseId;
        },


        //begin 初始化日历
        CalendarInit : function(){
            var prevOrNext="now";
            $('#calendar').fullCalendar({
                height: (window.screen.availHeight-180),
                displayEventTime : true,
                locale: "zh-cn",
                eventLimit:true,
                eventLimit:1,
                header: {
                    left: 'month,agendaWeek,agendaDay',
                    center: 'title',
                    right: 'prev,today,next'
                },
                select: function (start, end) {
                    calendar.hideBtn()
                },
                timeFormat: 'HH:mm',
                dayRender: function (date, cell) {
                    var today = new Date();
                    var dateFormated=new Date(date);
                    if( moment(dateFormated.toDateString()).isBefore(today.toDateString()) ) {
                        console.log(today.toDateString());
                        cell.addClass("fc-past-day")
                    }
                    if (dateFormated.toDateString() == today.toDateString()) {
                        cell.css("background-color", "rgb(204, 255, 204)")
                    }
                },

                plugins: [ 'dayGrid' ],
                selectable: true,
                selectHelper: true,
                eventLimit: true,
                droppable: false,

                events: function (start, end, timezone, callback) {
                    $("#calendar").fullCalendar('removeEvents');
                    calendar.getUserId();
                    // 通过ajax动态查询教学内容
                    $.ajax({
                        url: 'teachContent/showAsCalendar?beginDate='+start,
                        dataType: 'json',
                        type: 'post',
                        success: function (teachContent) {
                            // 获取当前月的数据
                            var events = [];
                            if (teachContent != null) {
                                var len = teachContent.length;
                                for (var i = 0; i < len; i++) {
                                    var ifBelongTOMyself = "no";
                                    // 背景颜色
                                    var color = "";
                                    var textColor='white';
                                    // 课程状态
                                    var status;
                                    if(teachContent[i].status==1){
                                        status="signed"
                                    } else{
                                        status="unSigned"
                                    }
                                    var userList = teachContent[i].userList;
                                    var coachIds = [];
                                    for (var j = 0; j < userList.length; j++) {
                                        coachIds.push(userList[j].userId)
                                    }
                                    //自己的课
                                    if (coachIds.indexOf(currentUserId) != -1) {
                                        if (status == "unSigned") {
                                            //未上课
                                            color = '#ffc241';
                                            textColor='black'
                                        } else if(status=="signed") {
                                            //已上课
                                            color = "#1dc9b7";
                                        }
                                        ifBelongTOMyself = "yes";
                                    } else {
                                        //不是自己的课
                                        color="#886ab5"
                                    }
                                    var scheduleDate =moment(teachContent[i].classTime).format('YYYY-MM-DD HH:mm');
                                    var startTime=moment(teachContent[i].classTime).format('HH:mm');
                                    events.push({
                                        id: teachContent[i].contentId,
                                        title:  teachContent[i].contentName,
                                        start: scheduleDate ,
                                        classTime:teachContent[i].classTime,
                                        color: color,
                                        textColor:textColor,
                                        status: status,
                                        ifBelongTOMyself: ifBelongTOMyself
                                    })
                                    ;
                                }
                            }
                            callback(events);
                        }
                    });
                },


                eventClick: function (event) {
                    console.log(event.start);
                    let contentId = event.id;
                    selectedTeachContentId=contentId;
                    calendar.getTeachContentDetail(contentId);
                    selectedTeachContentId=event.id;
                    status=event.status;
                    let ifBelongTOMyself=event.ifBelongTOMyself;
                    //签到提前时间（分钟）
                    let signLeadTime=20;
                    let milliseconds=( Date.now()-event.classTime );
                    let acutalLeadTimeMinutes=milliseconds/60/1000;
                    let canSign=false;
                    if(  acutalLeadTimeMinutes <= signLeadTime  && acutalLeadTimeMinutes>=0 ){
                        canSign=true;
                    }
                    $("#ifSigned").html("");
                    if(status=="unSigned" && ifBelongTOMyself=="yes" && canSign  ){
                        $("#ifSigned").html("  <button id=\"goToTeach\" onclick=\"calendar.coachSignIn()\" type=\"button\" class=\"btn btn-warning\" >签到</button>")
                    } else if(ifBelongTOMyself=="no") {
                        $("#ifSigned").html("不是本人课程")
                    } else if(status=="unSigned" && ifBelongTOMyself=="yes" && !canSign){
                        if(milliseconds>0){
                            $("#ifSigned").html("开课后20分钟才能进行签到,已超时")
                        } else{
                            $("#ifSigned").html("开课后20分钟才能进行签到,未到时间 ")
                        }
                    } else if(status=="signed" && ifBelongTOMyself=="yes" ){
                        $("#ifSigned").html("已签到")
                    }
                },

            });
        },//end 初始化日历




        timeFormate:function (time) {
            return moment(time).format('YYYY-MM-DD HH:mm')
        },

        getTeachContentDetail:function (contentId, role) {
            $.ajax({
                    url: 'teachContent/getDetailByContentId',
                    data:{
                        contentId:contentId
                    },
                    dataType:"json",
                    type:"POST",
                success:function (teachContentDetail) {
                    calendar.showTeachContentDetailModal(teachContentDetail,role);
                }
                }
            )
        },
        // 显示操作按钮图标
        showSignInBtn : function(x,y){
            if(x==undefined || x==""){
                x=5
            }
            if (y==undefined || y==""){
                y=138
            }
            var evt = window.event || arguments.callee.caller.arguments[0];
            var scroTop;
            var hei;
            $("#btnDiv").css("display", "block");
            $("#btnDiv").css("left",evt.clientX+x);
            $("#btnDiv").css("z-index", 1000);
            if(document.body.scrollTop == 0 && document.documentElement.scrollTop ==0){
                hei = evt.screenY;
            }else{
                if(document.documentElement.scrollTop!=0){
                    scroTop = document.documentElement.scrollTop;
                }else{
                    scroTop = document.body.scrollTop;
                }
                hei = evt.screenY + scroTop;
            }
            $("#btnDiv").css("top", hei - y);
        },//end showBtn()

        // 隐藏操作按钮图标
        hideBtn: function(){
            $("#btnDiv").css("display", "none");
        },// end hideBtn()


        showTeachContentDetailModal:function (teachContentDetail, role) {
            var userList=teachContentDetail.userList;
            var userFullNames="";
            for(var index in userList){
                if(userList[index].userId!=undefined){
                    userFullNames += (userList[index].userFullName);
                    if (index < userList.length-1){
                        userFullNames += "/";
                    }
                }
            }
            var courseList=teachContentDetail.courseList;
            $("#teachContent-detail-modal  #relatedCourses" ).empty();
            for(var courseIndex in courseList){
                if(courseList[courseIndex].courseId!=undefined){
                    selectedCourseId=courseList[courseIndex].courseId;
                    var courseNo=courseList[courseIndex].courseNo;
                    var courseName=courseList[courseIndex].courseName;
                    var courseNoName =courseNo+ "(" + courseName + ")";
                    if (courseIndex < courseList.length-1){
                        courseNoName += ", ";
                    }
                    $("#teachContent-detail-modal  #relatedCourses" ).append("<a href=\"#\" value='' onclick='calendar.toviewCourse("+selectedCourseId+ ")'>"+courseNoName+ "</a>")
                }
            }
            if(role=="student"){
                $("#toRelateCourse").hide();
            } else{
                $("#toRelateCourse").show();
            }
            selectedPmId=teachContentDetail.pmId;
            if(teachContentDetail.contentType=="0"){
                $("#teachContent-detail-modal #contentType option[value='"+0+"']").attr("selected","selected");
            }else {
                $("#teachContent-detail-modal #contentType option[value='"+1+"']").attr("selected","selected");
            }
            $("#teachContent-detail-modal #groupName").val(teachContentDetail.groupName);
            $("#teachContent-detail-modal #planName").val(teachContentDetail.planName);
            $("#teachContent-detail-modal #planeModelName").val(teachContentDetail.planeModelName);
            $("#teachContent-detail-modal #contentName").val(teachContentDetail.contentName);
            $("#teachContent-detail-modal #classTime").val(calendar.timeFormate(teachContentDetail.classTime));
            $("#teachContent-detail-modal #classHours").val(teachContentDetail.classHours);
            $("#teachContent-detail-modal #coach").val(userFullNames);
            $("#teachContent-detail-modal #remark").val(teachContentDetail.remark);
            $("#teachContent-detail-modal #relatedCourse").val(teachContentDetail.contentName);
            $('#teachContent-detail-modal .modal-header .modal-title').empty().html('<button type="button" class="fa fa-edit btn btn-primary btn-xs" ></button>查看详情');
            $("#teachContent-detail-modal").modal('show');
        },

        coachSignIn: function () {
            $.ajax({
                url:"signIn/coachSignIn",
                type:"POST",
                sync:false,
                data:{
                    teachContentId:selectedTeachContentId
                },
                success:function () {
                    $('#calendar').fullCalendar('refetchEvents');
                    $("#ifSigned").html("已签到")
                }
            });
        },


        toviewCourse: function (courseId) {
            localStorage.setItem("courseId",courseId);
            window.open("views/preview/coursePreview.jsp", "previewCourse");
        },


        //begin 关联课件
        relateCourse :function() {
            calendar.selectChoosedCourseIds();
            calendar.initRelateCourseTree();
            $('#relateCourse-modal .modal-header .modal-title').empty().html('<button type="button" class="fa fa-edit btn btn-primary btn-xs" ></button>  关联课件');
            $('#relateCourse-modal').modal('show');
        },//end 关联课件


        commitRelatedCourse: function () {
            var courseIds =[];
            for(var i=0;i<courseIdsChoosed.length ;i++){
                var courseId ={
                    courseId:courseIdsChoosed[i].substring(0, courseIdsChoosed[i].length-1 )
                };
                courseIds.push(courseId);
            }
            $.ajax({
                url:"teachContentCourse/allotCourse",
                type:"POST",
                data:{
                    contentId: selectedTeachContentId,
                    courseIds:JSON.stringify(courseIds)
                },
                success:function(){
                    $("#relateCourse-modal").modal('hide');
                    $("body").css("padding-right","0px");
                    calendar.getTeachContentDetail(selectedTeachContentId);
                },
                error: function(data){
                    bootbox.alert("出现错误");
                }
            });


        },

        selectChoosedCourseIds:function () {
            $.ajax({
                type: 'post',
                url: 'teachContentCourse/selectChoosedCourseIdsByTeachContentId',
                cache: true,
                async:false,
                data:{
                    contentId: selectedTeachContentId
                },
                success: function(data) {
                    selectedCourseIds=[].concat(data);
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
        },

        //begin 生成关联课件树
        initRelateCourseTree : function() {
            $('#relateCourseTree').jstree('destroy');
            $('#relateCourseTree').data('jstree', false);
            var courseTree=$('#relateCourseTree').jstree(
                    {
                        'plugins': ["wholerow", "checkbox", "types"],
                        'core': {
                            "themes": {
                                "responsive": false
                            },
                            'data' : {
                                type : 'POST',
                                async : false,
                                'url' : 'ataFolder/getCourseTree',
                                'data' : function(node) {
                                    return {
                                        'parent' : node.id,
                                        'parentType' : node.type,
                                        'pmId': selectedPmId,
                                        'contentId':selectedTeachContentId
                                    };
                                },
                                'dataType' : 'json'
                            },
                        },
                        "types" : {
                            "#" : {
                                "icon" : "fa fa-plane icon-state-success"
                            },
                            "floder" : {
                                "icon" : "fa fa-folder text-primary fa-lg"
                            },
                            "file" : {
                                "icon" : "fa fa-file text-warning fa-lg"
                            },
                            default:{
                                "icon" : "fa fa-folder text-primary fa-lg"
                            },

                        },

                    })// end of $('#structureTree').jstree()

                .on('loaded.jstree',
                    function(e, data) {
                        $("#relateCourseTree").jstree('open_all');
                    })

                .on('changed.jstree', function (e, data) {
                    var i, j;
                    courseIdsChoosed=$("#relateCourseTree").jstree("get_checked"); //使用get_checked方法
                    for(i = 0, j = data.selected.length; i < j; i++) {
                        var nodeChoosed=data.instance.get_node(data.selected[i]);
                        if(nodeChoosed.type!="file"){
                            var index = courseIdsChoosed.indexOf(nodeChoosed.id);
                            if (index > -1) {
                                courseIdsChoosed.splice(index, 1);}
                        }
                    }
                })
        },
        //end 生成关联课件树

    }

}();
