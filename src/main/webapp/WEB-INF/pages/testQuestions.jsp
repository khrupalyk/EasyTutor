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
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-theme.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-theme.css.map" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/test.questions.css" rel="stylesheet"/>
</head>

<body>
<%@include file="template/header.jsp" %>
<link href="<%=request.getContextPath()%>/resources/css/header.css" rel="stylesheet"/>

<div>
    <div class="test_info">
        <div class="panel panel-default">
            <div class="panel-heading">Test information</div>
            <div class="panel-body">
                ${test.name} <br/>
                ${test.discipline}<br/>
                ${test.group}<br/>
                ${test.submissionTime}<br/>
                ${test.userATutor.name}<br/>
            </div>
        </div>
    </div>
    <div class="test_questions">
        <c:forEach items="${test.testsQuestions}" var="testsQuestion">
            <div>
                <div class="question_header choices active_choice selected wrong_answer"><c:out value="${testsQuestion.question.name}"/></div>
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
</div>
</body>
</html>
