<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 17.08.15
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%@include file="template/header.jsp" %>

<div style="margin: 0 auto; width: 90%;">
${json.toString()}
    <%--<c:forEach items="${proposedAnswers}" var="proposed">--%>
        <%--<div class="jumbotron" style="width: 300px;">--%>
                <%--${proposed.test.name}--%>
        <%--</div>--%>
    <%--</c:forEach>--%>
</div>

</body>
</html>
