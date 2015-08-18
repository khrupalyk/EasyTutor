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
<%@include file="template/header.jsp" %>

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
    $(function () {
        $.get("<c:url value='/proposed-answers-json'/>", function (data) {
            console.log(data);
            var objects = data;
            var html = "";
            for(var i = 0 ; i < objects.length; i++){

                html += template.replace("testName", objects[i].testName).replace("discipline", objects[i].discipline)
            }

            $("#tests-panel").html(html);

            $(".list-group").click(function(){
                var testName = $(this).find(".list-group-item-heading").text().trim();

                for(var i = 0 ; i < objects.length; i++) {
                    if(testName === objects[i].testName) {
                        $("#proposed-answers-panel").html(JSON.stringify(objects[i]));
                    }
                }
            });



//            alert( "Load was performed." );
        });
    });
</script>
<style>
    #tests-panel {
        width: 350px;
        float: left;
    }
</style>
</body>
</html>
