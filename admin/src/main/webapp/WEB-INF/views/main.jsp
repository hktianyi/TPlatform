<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="/WEB-INF/common/common.jsp" %>
</head>
<!-- END HEAD -->
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-full-width">
<%@include file="/WEB-INF/common/header.jsp" %>
<!-- BEGIN CONTAINER -->
<div class="page-container">
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content">
      <!-- BEGIN PAGE HEADER-->
      <!-- BEGIN PAGE BAR -->
      <div class="page-bar">
        <ul class="page-breadcrumb">
          <li>
            <a href="index.html">Home</a>
            <i class="fa fa-circle"></i>
          </li>
          <li>
            <span>Horizontal Menu</span>
          </li>
        </ul>
        <div class="page-toolbar">
          <div class="btn-group pull-right">
            <button type="button" class="btn green btn-sm btn-outline dropdown-toggle" data-toggle="dropdown"> Actions
              <i class="fa fa-angle-down"></i>
            </button>
            <ul class="dropdown-menu pull-right" role="menu">
              <li>
                <a href="#">
                  <i class="icon-bell"></i> Action</a>
              </li>
              <li>
                <a href="#">
                  <i class="icon-shield"></i> Another action</a>
              </li>
              <li>
                <a href="#">
                  <i class="icon-user"></i> Something else here</a>
              </li>
              <li class="divider"></li>
              <li>
                <a href="#">
                  <i class="icon-bag"></i> Separated link</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!-- END PAGE BAR -->
      <!-- BEGIN PAGE TITLE-->
      <h3 class="page-title"> Dark Menu Menu
        <small>dark mega menu option</small>
      </h3>
      <!-- END PAGE TITLE-->
      <!-- END PAGE HEADER-->
      <div class="note note-info">
        <p> Dark mega menu style. </p>
      </div>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="/WEB-INF/common/footer.jsp" %>
</body>
</html>