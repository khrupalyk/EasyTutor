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

<div style="margin: 0 auto; width: 500px;">
    <%--<form style="width: 500px; position: relative;">--%>
        <%--<h1>Contact with administration</h1>--%>
        <%--<br>--%>
        <%--<div class="form-group">--%>
            <%--<input class="form-control floating-label" type="text" placeholder="Name" required>--%>
        <%--</div>--%>
        <%--<br>--%>
        <%--<div class="form-group">--%>
            <%--<input class="form-control floating-label" type="email" placeholder="Email" required>--%>
        <%--</div>--%>
        <%--<br>--%>
        <%--<div class="form-group">--%>
            <%--<input class="form-control floating-label" type="text" placeholder="Message" required>--%>
        <%--</div>--%>

    <%--</form>--%>

    <form:form action="contact-with-admin" method="post" commandName="userMessage" cssStyle="width: 500px; position: relative;">
        <h1>Contact with administration</h1>
        <br>
        <div class="form-group">
            <form:input path="name" cssClass="form-control floating-label"
                        placeholder="Name" />
        </div>
        <br>
        <div class="form-group">
            <%--<input class="form-control floating-label" type="email" placeholder="Email" required>--%>
            <form:input path="email"  class="form-control floating-label"
                           placeholder="Email" />
        </div>
        <br>
        <div class="form-group">
            <%--<input class="form-control floating-label" type="text" placeholder="Message" required>--%>
            <form:input path="message" cssClass="form-control floating-label "
            placeholder="Message"/>
        </div>
        <div class="form-group">
            <button class="btn btn-primary" style="position: absolute;right: 0px;">SEND</button>
        </div>
    </form:form>
</div>
</body>
</html>
