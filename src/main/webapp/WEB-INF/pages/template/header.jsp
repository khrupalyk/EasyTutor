<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<body>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link href="http://cdn.jsdelivr.net/bootstrap.material-design/0.3.0/css/material-fullpalette.css" rel="stylesheet"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login.css"/>
<link href="https://fezvrasta.github.io/bootstrap-material-design/dist/css/material-fullpalette.min.css" rel="stylesheet"/>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/">EasyTutor</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <%--<li class="active"><a href="#">Home</a></li>--%>
        <%--<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>--%>
          <%--<ul class="dropdown-menu">--%>
            <%--<li><a href="#">Page 1-1</a></li>--%>
            <%--<li><a href="#">Page 1-2</a></li>--%>
            <%--<li><a href="#">Page 1-3</a></li>--%>
          <%--</ul>--%>
        <%--</li>--%>
        <li><a href="<%=request.getContextPath()%>/tests">Тести</a></li>
        <li><a href="<c:url value='/contact'/>">Звязатися із адміністрацією</a></li>
        <li><a href="<c:url value='/chrome-extension-info'/>">Завантажити розширення</a></li>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
          <li><a href="<c:url value='/proposed-answers'/>">Запропоновані відповіді</a></li>
          <li><a href="<c:url value='/users'/>">Користувачі</a></li>
        </sec:authorize>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <sec:authorize access="isAnonymous()">
          <li><a href="<c:url value='/signup' />"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
          <li><a href="<c:url value='/login' />"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
          <li><a href="<c:url value='/j_spring_security_logout' />"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </sec:authorize>
      </ul>
    </div>
  </div>
</nav>
</body>