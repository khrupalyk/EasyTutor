<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body onload='document.loginForm.username.focus();'>
<%@include file="template/header.jsp" %>
<link href="http://cdn.jsdelivr.net/bootstrap.material-design/0.3.0/css/material-fullpalette.css" rel="stylesheet"/>
<%--<link href="<%=request.getContextPath()%>/resources/css/bootstrap-table.min.css" rel="stylesheet"/>--%>

<center>
    <div class="jumbotron" style="width: 500px;">
        <form id="login-form" action="<c:url value='j_spring_security_check' />" method="post" role="form">

            <div class="form-group">
                <input class="form-control floating-label" name="username" id="username" type="text" tabindex="2"
                       placeholder="User name">
                <input class="form-control floating-label" type="password" name="password" tabindex="2"
                       placeholder="Password">

                <input type="submit" name="login-submit" id="login-submit" class="btn btn-primary" tabindex="3"/>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </div>
</center>
<%--<form id="login-form" action="<c:url value='j_spring_security_check' />" method="post" role="form"--%>
<%--style="display: block;">--%>
<%--<div class="form-group">--%>

<%--<input type="text" name="username" id="username" value="" tabindex="1" class="form-control"--%>
<%--placeholder="Username">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<input type="password" name="password" tabindex="2" class="form-control" placeholder="Password"/>--%>
<%--</div>--%>
<%--<div class="form-group text-center">--%>
<%--<input type="checkbox" tabindex="3" class="" name="remember" id="remember">--%>
<%--<label for="remember"> Remember Me</label>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div class="row">--%>
<%--<div class="col-sm-6 col-sm-offset-3">--%>
<%--<input type="submit" name="login-submit" id="login-submit" tabindex="4"--%>
<%--class="form-control btn btn-login" value="Log In">--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<div class="row">--%>
<%--<div class="col-lg-12">--%>
<%--<div class="text-center">--%>
<%--<a href="http://phpoll.com/recover" tabindex="5" class="forgot-password">Forgot Password?</a>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<input type="hidden" name="${_csrf.parameterName}"
       value="${_csrf.token}"/>
<%--</form>--%>
<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
</c:if>


</body>
</html>