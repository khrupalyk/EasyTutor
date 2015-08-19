<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 17.08.15
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<link href="<%=request.getContextPath()%>/resources/css/test.questions.css" rel="stylesheet"/>
<%@include file="template/header.jsp" %>
<script src="http://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.js" ></script>
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.css" />
<link href="<%=request.getContextPath()%>/resources/css/header.css" rel="stylesheet"/>
<div style="margin: 0 auto; width: 90%;">
    <div id="tests-panel">

    </div>
    <div id="proposed-answers-panel">


    </div>
    <%--<c:forEach items="${proposedAnswers}" var="proposed">--%>
    <%--<div class="jumbotron" style="width: 300px;">--%>
    <%--${proposed.test.name}--%>
    <%--</div>--%>
    <%--</c:forEach>--%>
</div>

<script>
    var template = "<div class='list-group'><div class='list-group-item'><div class='row-action-primary'><i class='mdi-file-folder'></i></div><div class='row-content'><div class='action-secondary'><i class='mdi-material-info'></i></div><h4 class='list-group-item-heading'>testName</h4><p class='list-group-item-text'>discipline</p></div></div><div class='list-group-separator'></div></div>"
    var answerTemplate = " <div class='panel panel-default'><div class='panel-body'>question</div><ul class='choices'><li class='active_choice active' ><div class='lastUnit' >answer</div></li><div style='width: 100%: position: relative;position: relative;'><button class='btn btn-success bnt-accept' style='left: 20px;'>Прийняти</button>" +
            "<a href='test_link' class='btn btn-primary' target='_blank'>Переглянути тест</a><button class='btn btn-danger btn-reject' " +
            "style='right: 0px; position: absolute;'>Відхиити</button> <input type=hidden' class='testIdClass' value='testIdUUID' " +
            "style='display: none'/> <input type=hidden' class='proposIdClass' style='display: none' value='proposIdInt'/></div></ul></div>";
    toastr.options = {
        "closeButton": false,
        "debug": true,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-center",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "2000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };

    $(function () {
        $.get("<c:url value='/proposed-answers-json'/>", function (data) {
            console.log(data);
            var objects = data;
            var html = "";
            for (var i = 0; i < objects.length; i++) {

                html += template.replace("testName", objects[i].testName).replace("discipline", objects[i].discipline)
            }

            $("#tests-panel").html(html);

            $(".list-group").click(function () {
                var testName = $(this).find(".list-group-item-heading").text().trim();

                for (var i = 0; i < objects.length; i++) {
                    if (testName === objects[i].testName) {
                        var sumTemplate = "";
                        for (var j = 0; j < objects[i].answers.length; j++) {
                            sumTemplate += answerTemplate
                                    .replace("question", objects[i].answers[j].question)
                                    .replace("answer", objects[i].answers[j].answer)
                                    .replace("testIdUUID", objects[i].answers[j].testId)
                                    .replace("proposIdInt", objects[i].answers[j].proposedAnswerId)
                                    .replace("test_link", '/easytutor/test/' + objects[i].answers[j].testId + "/questions#" + objects[i].answers[j].question.replace(" ", "_"));
                        }
                        $("#proposed-answers-panel").html(sumTemplate);
                    }
                }
                $(".bnt-accept").click(function () {
                    var mainBlock = $(this).parent().parent().parent();
                    var obj = {};
                    obj["question"] = $(mainBlock).find(".panel-body").text().trim();
                    obj["answer"] = $(mainBlock).find(".lastUnit").text().trim();
                    obj["testId"] = $(mainBlock).find(".testIdClass").attr("value").trim();
                    obj["id"] = $(mainBlock).find(".proposIdClass").attr("value").trim();


                    //TODO: Remove question from list when user accept it
                    $.post("<c:url value="/accept-proposed-answer"/>", obj).done(function( ) {
                        toastr["success"]("Відповідь прийтяно!!")
                    });

                    console.log(JSON.stringify(obj));

                });
            });


            // alert( "Load was performed." );
        });
    });
</script>
<style>
    #tests-panel {
        width: 350px;
        float: left;
    }

    #proposed-answers-panel {
        margin-left: 30px;
        float: left;
        width: 60%;
    }

    .btn-primary {
        margin-left: 30px;
    }

</style>
</body>
</html>
