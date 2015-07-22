<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <%--<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>--%>
          <%--<ul class="dropdown-menu">--%>
            <%--<li><a href="#">Page 1-1</a></li>--%>
            <%--<li><a href="#">Page 1-2</a></li>--%>
            <%--<li><a href="#">Page 1-3</a></li>--%>
          <%--</ul>--%>
        <%--</li>--%>
        <li><a href="<%=request.getContextPath()%>/tests">All tests</a></li>
        <li><a href="#">Page 3</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="<c:url value='/login' />"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>
<%--<header>--%>
  <%--<link href="<%=request.getContextPath()%>/resources/css/header.css" rel="stylesheet"/>--%>

  <%--<div class="nav">--%>
    <%--<ul>--%>
      <%--<li class="home"><a href="<%=request.getContextPath()%>/tests">All tests</a></li>--%>
      <%--<li class="tutorials"><a class="" href="#">Tutorials</a></li>--%>
      <%--<li class="about"><a href="#">About</a></li>--%>
      <%--<li class="news"><a href="#">Newsletter</a></li>--%>
      <%--<li class="contact"><a href="<%=request.getContextPath()%>/contact">Contact</a></li>--%>
    <%--</ul>--%>
  <%--</div>--%>
<%--</header>--%>
</body>