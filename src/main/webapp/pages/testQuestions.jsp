<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-theme.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-theme.css.map" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet"/>
</head>

<body>
<%@include file="template/header.jsp" %>
<div class="test_info">
    <div class="panel panel-default">
        <div class="panel-heading">Test information</div>
        <div class="panel-body">
            Panel content
        </div>
    </div>
</div>
<div>
    <c:forEach items="${test.testsQuestions}" var="testsQuestion">
        <div>
            <div class="question_header choices active_choice"><c:out value="${testsQuestion.question.name}"/></div>
            <ul class="choices">
                <c:forEach items="${testsQuestion.question.answers}" var="answer">
                    <li class="${answer.content.equals(testsQuestion.selectedAnswer.content) ? "correct_answer" : "active_choice active"}">
                        <div class="lastUnit"><c:out value="${answer.content}"/></div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
</div>

</body>
</html>
