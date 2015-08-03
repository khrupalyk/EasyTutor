<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 26.07.15
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>

<%@include file="template/header.jsp" %>
<link href="<%=request.getContextPath()%>/resources/css/header.css" rel="stylesheet"/>

<div style="width: 900px; margin: 0 auto;">
  <div class="test_questions" id="question-test">
    <c:forEach items="${questions}" var="question">
      <div>
          <%--<div class="question_header choices active_choice selected"><c:out value="${testsQuestion.question.name}"/></div>--%>
        <div class="jumbotron">
          <h3><c:out value="${question.name}"/></h3>
          <ul class="choices">
            <c:forEach items="${question.answers}" var="questionsAnswer">
              <li class="active_choice active answer">
                <div class="lastUnit" style="position: absolute;" count="<c:out value="${questionsAnswer.selectedCount}"/>"><c:out value="${questionsAnswer.content}"/>

                </div>
                <div class="qq"></div>

              </li>
            </c:forEach>
          </ul>
        </div>
      </div>
    </c:forEach>
  </div>
  <script>
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
            $(this).animate({ "width": "+=" + ((l * $(ddd).width() + 16)/100 ) + "px" }, 2000);
            $(this).addClass("bnt btn-material-green-500");
          });
        });

      })
    }
    buildStatistic();
  </script>
  <style>
    .choices li {
      padding: 10px 15px 0px;
    }
  </style>
</div>
</body>
</html>