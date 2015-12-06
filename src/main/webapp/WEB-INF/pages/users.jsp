<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 28.06.15
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <%--<link href="<%=request.getContextPath()%>/resources/css/contact.form.css" rel="stylesheet"/>--%>
</head>
<body>
<%@include file="template/header.jsp" %>

<div style="margin: 0 auto; width: 1000px;">
<table class="table table-striped table-hover ">
  <thead>
  <tr>
    <th>Login</th>
    <th>Email</th>
    <th>User name</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${users}" var="user">
  <tr>
    <td>${user.username}</td>
    <td>${user.email}</td>
    <td>${user.firstName} ${user.lastName}</td>
  </tr>
  </tbody>
  </c:forEach>
</table>

</div>
</body>
</html>