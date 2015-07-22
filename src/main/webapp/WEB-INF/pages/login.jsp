<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Login Page</title>
  <style>
    .error {
      padding: 15px;
      margin-bottom: 20px;
      border: 1px solid transparent;
      border-radius: 4px;
      color: #a94442;
      background-color: #f2dede;
      border-color: #ebccd1;
    }

    .msg {
      padding: 15px;
      margin-bottom: 20px;
      border: 1px solid transparent;
      border-radius: 4px;
      color: #31708f;
      background-color: #d9edf7;
      border-color: #bce8f1;
    }

    #login-box {
      width: 300px;
      padding: 20px;
      margin: 100px auto;
      background: #fff;
      -webkit-border-radius: 2px;
      -moz-border-radius: 2px;
      border: 1px solid #000;
    }

    .panel-login {
      border-color: #ccc;
      -webkit-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
      -moz-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
      box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
    }
    .panel-login>.panel-heading {
      color: #00415d;
      background-color: #fff;
      border-color: #fff;
      text-align:center;
    }
    .panel-login>.panel-heading a{
      text-decoration: none;
      color: #666;
      font-weight: bold;
      font-size: 15px;
      -webkit-transition: all 0.1s linear;
      -moz-transition: all 0.1s linear;
      transition: all 0.1s linear;
    }
    .panel-login>.panel-heading a.active{
      color: #029f5b;
      font-size: 18px;
    }
    .panel-login>.panel-heading hr{
      margin-top: 10px;
      margin-bottom: 0px;
      clear: both;
      border: 0;
      height: 1px;
      background-image: -webkit-linear-gradient(left,rgba(0, 0, 0, 0),rgba(0, 0, 0, 0.15),rgba(0, 0, 0, 0));
      background-image: -moz-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
      background-image: -ms-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
      background-image: -o-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
    }
    .panel-login input[type="text"],.panel-login input[type="email"],.panel-login input[type="password"] {
      height: 45px;
      border: 1px solid #ddd;
      font-size: 16px;
      -webkit-transition: all 0.1s linear;
      -moz-transition: all 0.1s linear;
      transition: all 0.1s linear;
    }
    .panel-login input:hover,
    .panel-login input:focus {
      outline:none;
      -webkit-box-shadow: none;
      -moz-box-shadow: none;
      box-shadow: none;
      border-color: #ccc;
    }
    .btn-login {
      background-color: #59B2E0;
      outline: none;
      color: #fff;
      font-size: 14px;
      height: auto;
      font-weight: normal;
      padding: 14px 0;
      text-transform: uppercase;
      border-color: #59B2E6;
    }
    .btn-login:hover,
    .btn-login:focus {
      color: #fff;
      background-color: #53A3CD;
      border-color: #53A3CD;
    }
    .forgot-password {
      text-decoration: underline;
      color: #888;
    }
    .forgot-password:hover,
    .forgot-password:focus {
      text-decoration: underline;
      color: #666;
    }

    .btn-register {
      background-color: #1CB94E;
      outline: none;
      color: #fff;
      font-size: 14px;
      height: auto;
      font-weight: normal;
      padding: 14px 0;
      text-transform: uppercase;
      border-color: #1CB94A;
    }
    .btn-register:hover,
    .btn-register:focus {
      color: #fff;
      background-color: #1CA347;
      border-color: #1CA347;
    }
  </style>

</head>
<body onload='document.loginForm.username.focus();'>

<html>
<%@include file="template/header.jsp" %>
<%--<link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet"/>--%>
<%--<link href="<%=request.getContextPath()%>/resources/css/bootstrap-table.min.css" rel="stylesheet"/>--%>
<script>
  $(function() {

    $('#login-form-link').click(function(e) {
      $("#login-form").delay(100).fadeIn(100);
      $("#register-form").fadeOut(100);
      $('#register-form-link').removeClass('active');
      $(this).addClass('active');
      e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
      $("#register-form").delay(100).fadeIn(100);
      $("#login-form").fadeOut(100);
      $('#login-form-link').removeClass('active');
      $(this).addClass('active');
      e.preventDefault();
    });

  });

</script>
<div class="container">
  <div class="row">
    <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-login">
        <div class="panel-heading">
          <div class="row">
            <div class="col-xs-6">
              <a href="#" class="active" id="login-form-link">Login</a>
            </div>
            <div class="col-xs-6">
              <a href="#" id="register-form-link">Register</a>
            </div>
          </div>
          <hr>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-lg-12">
              <form id="login-form" action="<c:url value='j_spring_security_check' />" method="post" role="form" style="display: block;">
                <div class="form-group">
                  <input type="text" name="username" id="username" value="" tabindex="1" class="form-control" placeholder="Username">
                </div>
                <div class="form-group">
                  <input type="password" name="password" tabindex="2" class="form-control" placeholder="Password"/>
                </div>
                <div class="form-group text-center">
                  <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                  <label for="remember"> Remember Me</label>
                </div>
                <div class="form-group">
                  <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                      <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
                    </div>
                  </div>
                </div>
                <%--<div class="form-group">--%>
                  <%--<div class="row">--%>
                    <%--<div class="col-lg-12">--%>
                      <%--<div class="text-center">--%>
                        <%--<a href="http://phpoll.com/recover" tabindex="5" class="forgot-password">Forgot Password?</a>--%>
                      <%--</div>--%>
                    <%--</div>--%>
                  <%--</div>--%>
                <%--</div>--%>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
              </form>
              <form id="register-form" action="http://phpoll.com/register/process" method="post" role="form" style="display: none;">
                <div class="form-group">
                  <input type="text" name="username" id="username2" tabindex="1" class="form-control" placeholder="Username" value="">
                </div>
                <div class="form-group">
                  <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="">
                </div>
                <div class="form-group">
                  <input type="password" name="password" id="password2" tabindex="2" class="form-control" placeholder="Password">
                </div>
                <div class="form-group">
                  <input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirm Password">
                </div>
                <div class="form-group">
                  <div class="row">
                    <div class="col-sm-6 col-sm-offset-3">
                      <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Register Now">
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

  <c:if test="${not empty error}">
    <div class="error">${error}</div>
  </c:if>
  <c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
  </c:if>


</body>
</html>