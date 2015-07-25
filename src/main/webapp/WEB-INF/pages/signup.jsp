<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 24.07.15
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%@include file="template/header.jsp" %>
<div align="center">
  <form:form action="signup" method="post" commandName="user">
    <table border="0">
      <tr>
        <td>User Name:</td>
        <td><form:input path="username" /></td>
        <td><form:errors path="username" cssClass="error" /></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><form:password path="password" /></td>
        <td><form:errors path="password" cssClass="error" /></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><form:password path="confirmPassword" /></td>
        <td><form:errors path="confirmPassword" cssClass="error" /></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value="Register" /></td>
      </tr>
    </table>
  </form:form>
</div>
</body>
</html>
