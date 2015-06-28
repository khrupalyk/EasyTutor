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
    <link href="<%=request.getContextPath()%>/resources/css/contact.form.css" rel="stylesheet"/>
</head>
<body>
<%@include file="template/header.jsp" %>

<div id="form-main">
    <div id="form-div">
        <form class="form" id="form1">

            <p class="name">
                <input name="name" type="text"
                       class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="Name"
                       id="name"/>
            </p>

            <p class="email">
                <input name="email" type="text" class="validate[required,custom[email]] feedback-input" id="email"
                       placeholder="Email"/>
            </p>

            <p class="text">
                <textarea name="text" class="validate[required,length[6,300]] feedback-input" id="comment"
                          placeholder="Comment"></textarea>
            </p>


            <div class="submit">
                <input type="submit" value="SEND" id="button-blue"/>

                <div class="ease"></div>
            </div>
        </form>
    </div>
</body>
</html>
