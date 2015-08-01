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

<center>
    <div class="jumbotron" style="width: 500px;">

        <form:form action="signup" method="post" commandName="user">

            <div class="form-group">
                <form:input path="username" cssClass="form-control floating-label size-form-control"
                            placeholder="User name"/>
                <form:errors path="username" cssClass="text-danger"/>
                <form:password path="password" class="form-control floating-label size-form-control"
                               placeholder="Password"/>
                <form:errors path="password" cssClass="text-danger"/>

                <form:password path="confirmPassword" cssClass="form-control floating-label size-form-control"
                               placeholder="Confirm password"/>
                <form:errors path="confirmPassword" cssClass="text-danger"/><br/>
                <input type="submit" name="login-submit" id="login-submit" class="btn btn-primary" tabindex="4"/>
            </div>
        </form:form>
    </div>
</center>
</body>
</html>
