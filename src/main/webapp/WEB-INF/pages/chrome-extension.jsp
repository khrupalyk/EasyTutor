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

  <div class="jumbotron" style="margin: 0 auto; width: 60%;">
    <span style="font-size: 21px">Щоб використовувати систему EasyTutor під час здачі тесту потрібно встановити <a href="/chrome-extension.crx">це розширення (для Google Chrome).</a> <br/>
      Щоб встановити це розширення просто перетягніть його на сторінку із розширеннями в налаштуваннях браузера.<br/>
    Після встановлення плагіна можете сміло користувася ним! <br/>Для цього просто натистніть на питання яке вас цікавить, і ви отримаєте одне із декількох повідомлень.<br/>
    Якщо питання є у системі ви отримаєте коротку статистику по вибраних відповідях.</span> <br/><br/>
    <img src="../../resources/image/ExistInDatabase.PNG" style="max-width: 700px; margin: 0 auto" /><br/><br/>

    <span style="font-size: 21px">Якщо такого питання ще не було, то ви отримаєте ось таке повідомлення.</span><br/><br/>
    <img src="../../resources/image/NoExistInDatabase.PNG" style="max-width: 700px; margin: 0 auto"/>
  </div>

</body>
</html>