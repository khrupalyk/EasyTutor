<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body onload='document.loginForm.username.focus();'>
<%@include file="template/header.jsp" %>
<center>
    <c:if test="${not empty msg}">
        <div class="alert alert-dismissable alert-success" style="width: 500px;">
            <div class="msg">${msg}</div>
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-dismissable alert-danger"  style="width: 500px;">
                ${error}
        </div>
    </c:if>
    <div class="jumbotron" style="width: 500px;">
        <form id="login-form" action="<c:url value='j_spring_security_check' />" method="post" role="form">
            <div class="form-group">
                <input class="form-control floating-label size-form-control" name="username" id="username" type="text"
                       tabindex="2"
                       placeholder="User name">


                <input class="form-control floating-label size-form-control" type="password" name="password"
                       tabindex="2"
                       placeholder="Password">

                <input type="submit" name="login-submit" id="login-submit" class="btn btn-primary"
                       style="margin-top: 30px;" tabindex="3"/>
            </div>
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>

    </div>

</center>

<link href="http://cdn.jsdelivr.net/bootstrap.material-design/0.3.0/css/material-fullpalette.css" rel="stylesheet"/>


</body>
</html>