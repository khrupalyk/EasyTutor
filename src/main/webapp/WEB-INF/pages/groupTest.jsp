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
  <%--<script src="<%=request.getContextPath()%>/resources/javascript/bootstrap-table.js"></script>--%>
  <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="<%=request.getContextPath()%>/resources/css/bootstrap-table.min.css" rel="stylesheet"/>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />


    <script>
        var object = undefined
    </script>
  <%--<link href="<%=request.getContextPath()%>/resources/css/table.test.css" rel="stylesheet"/>--%>
</head>

<%@include file="template/header.jsp" %>
<%--<script src="<%=request.getContextPath()%>/resources/javascript/bootstrap-table-all.js"></script>--%>
<%--<script src="<%=request.getContextPath()%>/resources/javascript/bootstrap-table-all.min.js"></script>--%>
<script src="<%=request.getContextPath()%>/resources/javascript/bootstrap-table.min.js"></script>
<div class="container">
  <div class="table-responsive">

    <%--jo.put("testName", test.getName());--%>
    <%--jo.put("discipline", test.getDiscipline());--%>
    <%--jo.put("group", test.getGroup());--%>
    <%--jo.put("course", test.getCourse());--%>
    <%--jo.put("count", test.getTestCount());--%>
    <%--<a href="tests">--%>

        <div id="custom-toolbar">
            <div class="form-inline" role="form">
                <div class="form-group">
                    <input type="button" class="btn btn-primary" value="Show all questions" id="showAllQuestions" disabled/>
                </div>

            </div>
        </div>

    <table data-toggle="table"
           data-url="<%=request.getContextPath()%>/group-test?${params}"
           data-query-params="queryParams"
           data-pagination="true"
           data-search="true"
           data-height="600"
           data-page-list="[50, 100, 200]"
           data-toolbar="#custom-toolbar"
           id="events-table">
      <thead>
      <tr>
        <%--<th data-field="id" data-sortable="true">Test id</th>--%>
        <th data-field="name" data-sortable="true" data-events="operateEvents"
            data-formatter="operateFormatter">Test name
        </th>
        <th data-field="discipline" data-sortable="true">Discipline</th>
        <th data-field="group" data-sortable="true">Group</th>
        <th data-field="course" data-sortable="true">Course</th>
        <th data-field="user" data-sortable="true">User</th>
        <th data-field="date" data-sortable="true">Date</th>
        <%--<th data-field="count" data-sortable="true">Count</th>--%>
      </tr>
      </thead>
    </table>
    <script>
        $('#events-table').bootstrapTable({
            onLoadSuccess: function (data) {
                if(data.length > 0) {
                    object = data[0];
                    $("#showAllQuestions").removeAttr("disabled");
                }
            }});

      function jsonToQueryString(json) {
        return '?' +
                Object.keys(json).map(function(key) {
                  return encodeURIComponent(key) + '=' +
                          encodeURIComponent(json[key]);
                }).join('&');
      }

      function operateFormatter(value, row, index) {
        return  "<strong><a href='test/" + row.id + "/questions'>" + value + "</a></strong>";
      }

      $('#showAllQuestions').click( function(){
//          alert('You clicked row '+ JSON.stringify($(".table tbody").find('tr')[0]));
          if(object !== undefined){
              delete object.date;
              delete object.id;
              delete object.user;
              console.log(object);
              console.log(JSON.stringify(object));
              window.location.href = "<%=request.getContextPath()%>/questions/_all";
          } else {
              console.log("Object undefined")
          }
      });

    </script>
  </div>
</div>
</body>
</html>
