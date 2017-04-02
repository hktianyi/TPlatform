<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <%@include file="/WEB-INF/template/metronic/common/common.jsp" %>
  <link href="${_PATH}/static/themes/metronic/css/login.min.css?${_VERSION}" rel="stylesheet" type="text/css"/>
</head>
<body class="login">
<div id="parallax">
  <!-- BEGIN LOGO -->
  <div class="logo layer" data-depth="0.6">
    <a href="${_PATH}/index.html">
      <img src="${_PATH}/static/common/image/logo-color.png" alt="" width="120px" height="120px"/> </a>
  </div>
  <!-- END LOGO -->
  <!-- BEGIN LOGIN -->
  <div class="content layer" data-depth="0.4" style="left: 0;right: 0;">
    <!-- BEGIN LOGIN FORM -->
    <form action="${_PATH}/login" class="login-form" method="post">
      <h4 class="form-title font-green" style="text-align: center;font-weight:500">${APP_NAME}</h4>
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
      <div class="form-group">
        <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
        <label class="control-label visible-ie8 visible-ie9">用户名</label>
        <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名"
               name="username"/></div>
      <div class="form-group">
        <label class="control-label visible-ie8 visible-ie9">密码</label>
        <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off"
               placeholder="密码" name="password"/></div>
      <div class="form-actions">
        <button type="submit" class="btn green uppercase">登录</button>
        <label class="rememberme check">
          <input type="checkbox" name="remember" value="1"/>记住我 </label>
        <a href="javascript:;" id="forget-password" class="forget-password">忘记密码?</a>
      </div>
      <%--<div class="login-options">
        <h4>Or login with</h4>
        <ul class="social-icons">
          <li>
            <a class="social-icon-color facebook" data-original-title="facebook" href="javascript:;"></a>
          </li>
          <li>
            <a class="social-icon-color twitter" data-original-title="Twitter" href="javascript:;"></a>
          </li>
          <li>
            <a class="social-icon-color googleplus" data-original-title="Goole Plus" href="javascript:;"></a>
          </li>
          <li>
            <a class="social-icon-color linkedin" data-original-title="Linkedin" href="javascript:;"></a>
          </li>
        </ul>
      </div>--%>
      <%--<div class="create-account">
        <p>
          <a href="javascript:;" id="register-btn" class="uppercase">创建账号</a>
        </p>
      </div>--%>
    </form>
    <!-- END LOGIN FORM -->
    <!-- BEGIN FORGOT PASSWORD FORM -->
    <form class="forget-form" action="index.html" method="post">
      <h3 class="font-green">Forget Password ?</h3>
      <p> Enter your e-mail address below to reset your password. </p>
      <div class="form-group">
        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email"/>
      </div>
      <div class="form-actions">
        <button type="button" id="back-btn" class="btn btn-default">Back</button>
        <button type="submit" class="btn btn-success uppercase pull-right">Submit</button>
      </div>
    </form>
    <!-- END FORGOT PASSWORD FORM -->
    <!-- BEGIN REGISTRATION FORM -->
    <form class="register-form" action="index.html" method="post">
      <h3 class="font-green">Sign Up</h3>
      <p class="hint"> Enter your personal details below: </p>
      <div class="form-group">
        <label class="control-label visible-ie8 visible-ie9">Full Name</label>
        <input class="form-control placeholder-no-fix" type="text" placeholder="Full Name" name="fullname"/></div>
      <div class="form-group">
        <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
        <label class="control-label visible-ie8 visible-ie9">Email</label>
        <input class="form-control placeholder-no-fix" type="text" placeholder="Email" name="email"/></div>
      <div class="form-group">
        <label class="control-label visible-ie8 visible-ie9">Address</label>
        <input class="form-control placeholder-no-fix" type="text" placeholder="Address" name="address"/></div>
      <div class="form-group">
        <label class="control-label visible-ie8 visible-ie9">City/Town</label>
        <input class="form-control placeholder-no-fix" type="text" placeholder="City/Town" name="city"/></div>
      <div class="form-group">
        <label class="control-label visible-ie8 visible-ie9">Country</label>
        <select name="country" class="form-control">
          <option value="">Country</option>
          <option value="CN">China</option>
        </select>
      </div>
      <p class="hint"> Enter your account details below: </p>
      <div class="form-group">
        <label class="control-label visible-ie8 visible-ie9">Username</label>
        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username"
               name="username"/></div>
      <div class="form-group">
        <label class="control-label visible-ie8 visible-ie9">Password</label>
        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="register_password"
               placeholder="Password" name="password"/></div>
      <div class="form-group">
        <label class="control-label visible-ie8 visible-ie9">Re-type Your Password</label>
        <input class="form-control placeholder-no-fix" type="password" autocomplete="off"
               placeholder="Re-type Your Password" name="rpassword"/></div>
      <div class="form-group margin-top-20 margin-bottom-20">
        <label class="check">
          <input type="checkbox" name="tnc"/> I agree to the
          <a href="javascript:;"> Terms of Service </a> &
          <a href="javascript:;"> Privacy Policy </a>
        </label>
        <div id="register_tnc_error"></div>
      </div>
      <div class="form-actions">
        <button type="button" id="register-back-btn" class="btn btn-default">Back</button>
        <button type="submit" id="register-submit-btn" class="btn btn-success uppercase pull-right">Submit</button>
      </div>
    </form>
    <!-- END REGISTRATION FORM -->
  </div>
  <div id="particles" class="particles layer" data-depth="0.3"></div>
</div>
<%@include file="/WEB-INF/template/metronic/common/footerWithoutDisInfo.jsp" %>
<script src="${_PATH}/static/plugins/particles.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/themes/metronic/js/login.js?${_VERSION}" type="text/javascript"></script>
</body>
</html>