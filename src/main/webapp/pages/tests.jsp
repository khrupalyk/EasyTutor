<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 27.06.15
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link href="<%=request.getContextPath()%>/resources/css/table.test.css" rel="stylesheet"/>
</head>
<body>
<%@include file="template/header.jsp"%>
<table>
  <thead>

  <tr>
    <th>Test name</th>
    <th>Discipline</th>
    <th>Group</th>
    <th>Course</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${tests}" var="i">
    <tr>
      <td><strong><a href="test/${i.testId}/questions">${i.name}</a></strong></td>
      <td>${i.discipline}</td>
      <td>${i.group}</td>
      <td>${i.course}</td>
    </tr>
  </c:forEach>

  </tbody>
</table>

</body>
</html>
