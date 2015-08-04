<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 28.06.15
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%--<link href="<%=request.getContextPath()%>/resources/css/bootstrap-theme.css" rel="stylesheet"/>--%>
    <%--<link href="<%=request.getContextPath()%>/resources/css/bootstrap-theme.css.map" rel="stylesheet"/>--%>
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/test.questions.css" rel="stylesheet"/>
</head>

<body>
<%@include file="template/header.jsp" %>
<link href="<%=request.getContextPath()%>/resources/css/header.css" rel="stylesheet"/>


<div style="margin: 0 auto; width: 700px; ">


    <div class="test_questions" id="question-test">
        <c:forEach items="${test.testsQuestions}" var="testsQuestion">
            <div>
                    <%--<div class="question_header choices active_choice selected"><c:out value="${testsQuestion.question.name}"/></div>--%>
                <div class="jumbotron">
                    <h3><c:out value="${testsQuestion.question.name}"/></h3>
                    <ul class="choices">
                        <c:forEach items="${testsQuestion.question.answers}" var="answer">
                            <li class="${answer.content.equals(testsQuestion.selectedAnswer.content) ? "bnt btn-material-green-500 active_choice" : "active_choice active"}">
                                <div class="lastUnit" count="${answer.selectedCount}"> <c:out value="${answer.content}"/></div>
                                <div class="qq"></div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div class="right">
    <div id="fixed">
        <div class="panel panel-default">
            <div class="panel-body">
                ${test.name} <br/>
                ${test.discipline}<br/>
                ${test.group}-${test.course}<br/>
                ${test.submissionTime}<br/>
                <c:if test="${test.testResult != null}">
                    ${test.testResult}<br/>
                </c:if>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    ${test.userATutor.name}<br/>
                </sec:authorize>

            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="togglebutton">
                    Show statistic
                    <label style="float: right" >
                        <input type="checkbox" id="show-statistic"><span class="toggle" ></span>
                    </label>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

    $("#show-statistic").click(function(){
        if($("input[id='show-statistic']:checked").length >=1){

//        alert($("input[id='show-statistic']:checked").length);
            buildStatistic();
        } else {
            cleanStatistic();
        }
    });

    function cleanStatistic(){
        var divs = $("#question-test > div");
        divs.each(function(){
            var count = 0;

            $(this).find(".lastUnit").each(function(){
                count += parseInt($(this).attr("count"))
            });

            $(this).find(".active_choice").each(function(){
                var ddd = this;
                $(ddd).find(".qq").each(function(){
                    var l = (parseInt($(ddd).find(".lastUnit").attr("count"))*100) /count;
//                    $(this).attr("style", "width:0px;");
                    $(this).animate({ "width": "-=" + ((l * $(ddd).width() + 16)/100 ) + "px" }, 500);
//                    $(this).addClass("bnt btn-material-green-500");
                });
            });

        })
    }

    function buildStatistic(){
        var divs = $("#question-test > div");
        divs.each(function(){
            var count = 0;

            $(this).find(".lastUnit").each(function(){
                count += parseInt($(this).attr("count"))
            });

            $(this).find("li").each(function(){
                console.log($(this).height())
            });


            $(this).find(".active_choice").each(function(){
                var ddd = this;
                $(ddd).find(".qq").each(function(){
                    var l = (parseInt($(ddd).find(".lastUnit").attr("count"))*100) /count;
                    $(this).attr("style", "width:0px;");
                    $(this).animate({ "width": "+=" + ((l * $(ddd).width() + 16)/100 ) + "px" }, 500);
                    $(this).addClass("bnt btn-material-green-500");
                });
            });

        })
    }

    $(function () {
        var offset = $("#fixed").offset();
        var topPadding = 15;
        $(window).scroll(function () {
            if ($(window).scrollTop() > offset.top) {
                $("#fixed").stop().animate({marginTop: $(window).scrollTop() - offset.top + topPadding});
            }
            else {
                $("#fixed").stop().animate({marginTop: 0});
            }
            ;
        });
    });
</script>
<style>
    .right {
        margin-top: 2px;
        float: left;
        width: 20%;
    }

    #fixed {
        /*background: #CCC;*/
        padding: 20px;
    }
</style>
</body>
</html>
