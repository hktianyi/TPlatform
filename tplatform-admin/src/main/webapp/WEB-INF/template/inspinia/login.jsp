<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>登录 - ${APP_NAME}</title>
  <%@include file="common/common.jsp" %>
</head>
<body class="gray-bg">
<div class="middle-box text-center loginscreen animated fadeInDown">
  <div>
    <div>
      <h1 class="logo-name">CDY</h1>
    </div>
    <h3>${APP_NAME}</h3>
    <p>${empty SPRING_SECURITY_LAST_EXCEPTION.message ? 'Welcome, please login.' : SPRING_SECURITY_LAST_EXCEPTION.message}</p>
    <form class="m-t login-form" role="form" action="${_PATH}/login" method="post">
      <div class="form-group">
        <input type="text" class="form-control" name="username" placeholder="Username">
      </div>
      <div class="form-group">
        <input type="password" class="form-control" name="password" placeholder="Password">
      </div>
      <button type="submit" class="btn btn-primary block full-width m-b">登录</button>

      <a href="${_PATH}/forgotPassword">
        <small>忘记密码？</small>
      </a>
      <p class="text-muted text-center">
        <small>Do not have an account?</small>
      </p>
      <a class="btn btn-sm btn-white btn-block" href="${_PATH}/register">创建帐号</a>
    </form>
    <p class="m-t">
      <small>${APP_NAME} &copy; 2015 - 2017</small>
    </p>
  </div>
</div>
<script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/js-cookie/latest/js.cookie.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-validate/1.16.0/jquery.validate.min.js"></script>
<script src="${_PATH}/static/themes/inspinia/js/login.js"></script>
</body>
</html>
