<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
<head>
</head>

<body style="background: white">
<%@include file="template/header.jsp" %>
<style>
    p {
        text-indent: 20px; /* Отступ первой строки в пикселах */
    }

    .ps{
        font-size: 15px;
    }
</style>
<div class="jumbotron" style="margin: 0 auto; width: 90%;">
    <span style="    font-size: 50px">EasyTutor</span> <br>

    <p>
    <span style="font-size: 21px">Система призначена для студентів Тернопільського Національного Технічного Університету імені Івана Пулюя у якому використовується система дистанційного навчання на базі ATutor.

    </span>
    </p>

    <p>Можливості системи:

    <ul>
        <li>Збереження тестів</li>
        <li>Систематизація питань</li>
        <li>Можливість вибору правильного питання вручну</li>
        <li>Можливість використання системи під час здачі тесту (більше <a href="<c:url value="/chrome-extension-info"/>">тут</a>)</li>
    </ul>
    </p>
    <p></p>

    <p></p>

    <p>Залишились питання? Маєш пропозиції як вдосконалити проект? Хочеш зробити свій внесок в розвиток проекту? Тоді тобі <a href="<c:url value='/contact'/>">соди</a>!</p>
    <p ><span class="ps">P.S. Система розробляється у вільний від роботи та навчання час, тому велике прохання, якщо Ви знайшли помилку у її роботі, <a href="<c:url value='/contact'/>">сповістіть про це адміністратора</a>, щоб він міг оперативно її усунути.</span></p>
</div>
<%--<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">--%>
<%--<div class="wrapper">--%>
<%--<div class="container">--%>
<%--&lt;%&ndash;<h1>Welcome</h1>&ndash;%&gt;--%>

<%--&lt;%&ndash;<form class="form">&ndash;%&gt;--%>
<%--&lt;%&ndash;<input type="text" placeholder="Username">&ndash;%&gt;--%>
<%--&lt;%&ndash;<input type="password" placeholder="Password">&ndash;%&gt;--%>
<%--&lt;%&ndash;<button type="submit" id="login-button">Login</button>&ndash;%&gt;--%>
<%--&lt;%&ndash;</form>&ndash;%&gt;--%>
<%--</div>--%>

<%--<ul class="bg-bubbles">--%>
<%--<li></li>--%>
<%--<li></li>--%>
<%--<li></li>--%>
<%--<li></li>--%>
<%--<li></li>--%>
<%--<li></li>--%>
<%--<li></li>--%>
<%--<li></li>--%>
<%--<li></li>--%>
<%--<li></li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>--%>

<%--<script src="<%=request.getContextPath()%>/resources/javascript/index.js"></script>--%>


</body>

</html>
