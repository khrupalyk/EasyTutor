<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
<head>
    <link href="http://www.phonatetech.com/demo/boxlogin/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="http://www.phonatetech.com/demo/boxlogin/css/material.min.css" rel="stylesheet"/>
    <link href="http://www.phonatetech.com/demo/boxlogin/css/Boxes.css" rel="stylesheet"/>
</head>
<body style="background: white">
<%@include file="pages/template/header.jsp"%>
<center>
    <div class="box" style="width:500px;">

        <div class="info">

            <h4 class="text-center text-warning">Login</h4>

            <form id="loginform" class="form-signin" action="login" method="post">

                <div class="form-group has-warning">
                    <div class="form-control-wrapper">
                        <input type="text" class="form-control empty" id="login" name="login" />

                        <div class="floating-label">
                            ENTER YOUR USER ID
                        </div>
                        <span class="material-input"/>

                    </div>
                </div>
                <div class="form-group has-warning">
                    <div class="form-control-wrapper">
                        <input type="password" class="form-control empty" id="password" name="password"/>

                        <div class="floating-label">
                            ENTER PASSWORD
                        </div>
                        <span class="material-input"/>

                    </div>
                </div>

                <input type="submit" class="btn btn-warning " id="loginbtn2" value="send"/>
                <button class="btn btn-warning " id="loginbtn">Sign in<div class="ripple-wrapper"><div class="ripple ripple-on" style="left: 21.125px; top: 24px; -webkit-transform: scale(14); transform: scale(14); background-color: rgba(255, 255, 255, 0.843137);"></div></div></button>

            </form>
        </div>
    </div>
</center>

</body>

</html>