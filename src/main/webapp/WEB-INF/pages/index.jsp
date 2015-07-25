<?xml version="1.0" encoding="UTF-8"?>
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

    <div>
        <form class="col-md-12" style="width: 50%; text-align: center;">
            <div class="form-group">
                <input type="text" class="form-control input-lg" placeholder="Email">
            </div>
            <div class="form-group">
                <input type="password" class="form-control input-lg" placeholder="Password">
            </div>
            <div class="form-group">
                <input type="password" class="form-control input-lg" placeholder="Confirm Password">
            </div>
            <div class="form-group">
                <button class="btn btn-primary btn-lg btn-block">Sign In</button>
                <span><a href="#">Need help?</a></span>
                <span class="pull-right"><a href="#">New Registration</a></span>
            </div>
        </form>
    </div>

</body>

</html>
