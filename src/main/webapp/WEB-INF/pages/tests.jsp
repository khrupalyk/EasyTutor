<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 27.06.15
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%--<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">--%>
    <%--<link rel="stylesheet" href="http://bootstrap-table.wenzhixin.net.cn/dist/bootstrap-table.min.css">--%>
    <script src="<%=request.getContextPath()%>/resources/javascript/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/resources/javascript/bootstrap-table.js"></script>
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap-table.min.css" rel="stylesheet"/>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

    <%--<script src="http://wenzhixin.net.cn/p/bootstrap-table/docs/assets/bootstrap/js/bootstrap.min.js"></script>--%>
    <%--<link href="<%=request.getContextPath()%>/resources/css/table.test.css" rel="stylesheet"/>--%>
</head>

<%@include file="template/header.jsp" %>

<div class="container">
    <div class="table-responsive">

        <%--jo.put("testName", test.getName());--%>
        <%--jo.put("discipline", test.getDiscipline());--%>
        <%--jo.put("group", test.getGroup());--%>
        <%--jo.put("course", test.getCourse());--%>
        <%--jo.put("count", test.getTestCount());--%>
        <%--<a href="tests">--%>

        <table data-toggle="table"
               data-url="<%=request.getContextPath()%>/rest/test/tests/unique"
               data-query-params="queryParams"
               data-pagination="true"
               data-search="true"
               data-height="600"
               data-page-list="[50, 100, 200]">
            <thead>
            <tr>
                <th data-field="name" data-sortable="true" data-events="operateEvents"
                    data-formatter="operateFormatter">Test name
                </th>
                <th data-field="discipline" data-sortable="true">Discipline</th>
                <th data-field="group" data-sortable="true">Group</th>
                <th data-field="course" data-sortable="true">Course</th>
                <th data-field="count" data-sortable="true">Count</th>
            </tr>
            </thead>
        </table>
        <script>


            function operateFormatter(value, row, index) {
                return value !== undefined ? "<span class='like' style='cursor: pointer;'><a>" + value + "</a></span>" : "<span class='like'>-</span>";
            }
            window.operateEvents = {
                'click .like': function (e, value, row, index) {
                    delete row.count;
                    delete row.undefined;
                    window.location.href = "<%=request.getContextPath()%>/search?" + $.param(row);
                }
            };
        </script>
        <%--<table class="table">--%>
        <%--<thead>--%>

        <%--<tr>--%>
        <%--<th>Test name</th>--%>
        <%--<th>Discipline</th>--%>
        <%--<th>Group</th>--%>
        <%--<th>Course</th>--%>
        <%--<th>Count</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<c:forEach items="${tests}" var="i">--%>
        <%--<tr>--%>
        <%--<td><strong><a href="test/${i.testId}/questions">${i.name}</a></strong></td>--%>
        <%--<td>${i.discipline}</td>--%>
        <%--<td>${i.group}</td>--%>
        <%--<td>${i.course}</td>--%>
        <%--&lt;%&ndash;<td>${i.testResult == null ? "NaN" : i.testResult.current.toString().concat("/").concat(i.testResult.max)}</td>&ndash;%&gt;--%>
        <%--<td>${i.testCount}</td>--%>
        <%--</tr>--%>
        <%--</c:forEach>--%>

        <%--</tbody>--%>
        <%--</table>--%>
    </div>
</div>
</body>
</html>
