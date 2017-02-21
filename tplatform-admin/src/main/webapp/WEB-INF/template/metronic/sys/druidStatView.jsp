<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="/WEB-INF/template/metronic/common/common.jsp" %>
</head>
<!-- END HEAD -->

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-full-width">
<%@include file="/WEB-INF/template/metronic/common/header.jsp" %>
<!-- BEGIN CONTAINER -->
<div class="page-container">
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content">
      <div class="page-bar">
        <ul class="page-breadcrumb">
          <li>
            <a href="${_PATH}/main.html">首页</a>
            <i class="fa fa-angle-right"></i>
          </li>
          <li>
            <span>系统管理</span>
            <i class="fa fa-angle-right"></i>
          </li>
          <li>
            <span>数据池监控</span>
          </li>
        </ul>
        <div class="page-toolbar">
          <button type="button" class="btn btn-circle blue btn-outline btn-xs">&nbsp;?&nbsp;</button>
        </div>
      </div>
      <div class="row" style="margin-top: 6px;">
        <div class="col-md-12">
          <iframe scrolling="no" frameborder="0" width="100%" src="${_PATH}/sysInfo/druid/index.html"
                  onload="this.style.height=this.contentDocument.body.scrollHeight +'px';"></iframe>
        </div>
      </div>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="/WEB-INF/template/metronic/common/footer.jsp" %>
</body>
</html>