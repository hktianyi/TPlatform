<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
  <%@include file="/WEB-INF/common/common.jsp" %>
  <link href="${_PATH}/static/pages/login/login-5.min.css" rel="stylesheet" type="text/css"/>
</head>
<body class="login">
<!-- BEGIN : LOGIN PAGE 5-1 -->
<div class="user-login-5">
  <div class="row bs-reset">
    <div class="col-md-6 bs-reset">
      <div class="login-bg" style="background-image:url(${_PATH}/static/pages/login/img/bg0.jpg)">
        <img class="login-logo" src="${_PATH}/static/pages/login/img/logo.png"/></div>
    </div>
    <div class="col-md-6 login-container bs-reset">
      <div class="login-content">
        <h1>${APP_NAME}</h1>
        <%--<p> Lorem ipsum dolor sit amet, coectetuer adipiscing elit sed diam nonummy et nibh euismod aliquam erat volutpat. Lorem ipsum dolor sit amet, coectetuer adipiscing. </p>--%>
        <form action="${_PATH}/login" class="login-form" method="post">
          <c:choose>
            <c:when test="${not empty errorMsg}">
              <div class="alert alert-danger">
                <button class="close" data-close="alert"></button>
                <span>${errorMsg}</span>
              </div>
            </c:when>
            <c:otherwise>
              <div class="alert alert-danger display-hide">
                <button class="close" data-close="alert"></button>
                <span>请输入用户名和密码！</span>
              </div>
            </c:otherwise>
          </c:choose>
          <div class="row">
            <div class="col-xs-6">
              <input class="form-control form-control-solid placeholder-no-fix form-group" type="text" autocomplete="off" placeholder="用户名" name="username" required/></div>
            <div class="col-xs-6">
              <input class="form-control form-control-solid placeholder-no-fix form-group" type="password" autocomplete="off" placeholder="密码" name="password" required/></div>
          </div>
          <div class="row">
            <div class="col-sm-4">
              <div class="rem-password">
                <p>记住我
                  <input type="checkbox" class="rem-checkbox" />
                </p>
              </div>
            </div>
            <div class="col-sm-8 text-right">
              <%--<div class="forgot-password">
                <a href="javascript:;" id="forget-password" class="forget-password">忘记密码？</a>
              </div>--%>
              <button class="btn blue" type="submit">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
            </div>
          </div>
        </form>
        <!-- BEGIN FORGOT PASSWORD FORM -->
        <%--<form class="forget-form" action="javascript:;" method="post">
          <h3 class="font-green">忘记密码 ？</h3>
          <p> 请输入您的电子邮箱地址 </p>
          <div class="form-group">
            <input class="form-control placeholder-no-fix form-group" type="text" autocomplete="off" placeholder="Email" name="email"/></div>
          <div class="form-actions">
            <button type="button" id="back-btn" class="btn grey btn-default">返&nbsp;&nbsp;&nbsp;&nbsp;回</button>
            <button type="submit" class="btn blue btn-success uppercase pull-right">提&nbsp;&nbsp;&nbsp;&nbsp;交</button>
          </div>
        </form>--%>
        <!-- END FORGOT PASSWORD FORM -->
      </div>
      <div class="login-footer">
        <div class="row bs-reset">
          <div class="col-xs-5 bs-reset">
            <%--<ul class="login-social">
              <li>
                <a href="javascript:;">
                  <i class="icon-social-facebook"></i>
                </a>
              </li>
              <li>
                <a href="javascript:;">
                  <i class="icon-social-twitter"></i>
                </a>
              </li>
              <li>
                <a href="javascript:;">
                  <i class="icon-social-dribbble"></i>
                </a>
              </li>
            </ul>--%>
          </div>
          <div class="col-xs-7 bs-reset">
            <div class="login-copyright text-right">
              <p>Copyright &copy; ${APP_NAME} 2016</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!--[if lt IE 9]>
<script src="../../static/plugins/respond.min.js"></script>
<script src="../../static/plugins/excanvas.min.js"></script>
<![endif]-->
<!-- BEGIN CORE PLUGINS -->
<script src="${_PATH}/static/plugins/jquery/jquery-2.2.2.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/js.cookie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="${_PATH}/static/common/js/app.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="${_PATH}/static/common/js/layout.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/common/js/demo.min.js" type="text/javascript"></script>
<!-- END THEME LAYOUT SCRIPTS -->
<script src="${_PATH}/static/plugins/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/jquery/jquery.backstretch.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/pages/login/login-5.js" type="text/javascript"></script>
</body>
</html>