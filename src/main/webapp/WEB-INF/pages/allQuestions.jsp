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

<div class="test_questions">
  <c:forEach items="${questions}" var="question">
    <div>
        <%--<div class="question_header choices active_choice selected"><c:out value="${testsQuestion.question.name}"/></div>--%>
      <div class="jumbotron">
        <h3><c:out value="${question.name}"/></h3>
        <ul class="choices">
          <c:forEach items="${question.answers}" var="answer">
            <li class="${answer.content.equals(question.selectedAnswer.content) ? "bnt btn-material-green-500" : "active_choice active"}">
              <div class="lastUnit"><c:out value="${answer.content}"/></div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </c:forEach>
</div>
</body>
</html>
