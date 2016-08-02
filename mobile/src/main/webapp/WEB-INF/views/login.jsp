<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
  <%@include file="../common/common.jsp" %>
  <link href="${_PATH}/static/pages/login/login.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="m-scene" id="main">

  <!-- Page Content -->
  <div id="content" class="grey-blue login">

    <!-- Toolbar -->
    <div id="toolbar" class="tool-login primary-color animated fadeindown">
      <a href="javascript:history.back()" class="open-left">
        <i class="ion-android-arrow-back"></i>
      </a>
    </div>

    <!-- Main Content -->
    <div class="login-form animated fadeinup delay-2 z-depth-1">

      <h1>Login</h1>
      <div class="input-field">
        <i class="ion-android-contact prefix"></i>
        <input class="validate" id="login" type="text">
        <label for="login">Username</label>
      </div>

      <div class="input-field" style="margin-bottom:20px;">
        <i class="ion-android-lock prefix"></i>
        <input class="validate" id="login-psw" type="password">
        <label for="login-psw">Password</label>
      </div>

      <a class="waves-effect waves-light btn-large accent-color width-100 m-b-20 animated bouncein delay-4" href="index.html">Login</a>
      <span>Don't have an account? <a class="primary-text" href="signup.html">Sign Up</a></span>
    </div><!-- End of Main Contents -->


  </div> <!-- End of Page Content -->

</div> <!-- End of Page Container -->
<%@include file="../common/footer.jsp"%>
</body>
</html>