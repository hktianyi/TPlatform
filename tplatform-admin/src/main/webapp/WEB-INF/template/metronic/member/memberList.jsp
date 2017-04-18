<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <span>会员管理</span>
          </li>
        </ul>
        <div class="page-toolbar">
          <a href="${_PATH}/member/edit" class="btn btn-circle green btn-sm"><i class="fa fa-plus"></i> 添加 </a>
        </div>
        <%@include file="/WEB-INF/common/search.jsp" %>
      </div>
      <div class="row">
        <div class="col-md-12">
          <table class="table table-striped table-bordered table-hover table-header-fixed"></table>
        </div>
      </div>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="/WEB-INF/template/metronic/common/footer.jsp" %>
<script type="text/javascript">
  var dataTable;
  $(function () {
    getDataList();
  });
  function getDataList() {
    if (typeof dataTable !== 'undefined') {
      dataTable.draw();
      return;
    }
    dataTable = $("table").DataTable({
      "ajax": {
        "url": _MODULE_NAME + "/load",
        "type": "POST",
        "data": function (params) {
          params.q = $("#q").val();
        }
      },
      "columns": [
        {"sTitle": "序号", "sWidth": "4%", "defaultContent": ''},
        {"sTitle": "客户名", "name": "username", "data": "username", "sWidth": "7%"},
        {"sTitle": "联系人", "name": "nickname", "data": "nickname", "sWidth": "6%"},
        {"sTitle": "电话", "name": "phone", "data": "phone", "sWidth": "6%"},
        {"sTitle": "手机", "name": "mobile", "data": "mobile", "sWidth": "6%"},
        {"sTitle": "QQ", "name": "qq", "data": "qq", "sWidth": "8%"},
        {"sTitle": "微信", "name": "wechat", "data": "wechat", "sWidth": "8%"},
        {"sTitle": "邮箱地址", "name": "email", "data": "email", "sWidth": "8%"},
        {
          "sTitle": "来源", "name": "source", "data": function (data) {
          switch (data.source) {
            case 1: data.source = "网站"; break;
            case 2: data.source = "同行推荐"; break;
            case 4: data.source = "婚礼堂"; break;
            case 5: data.source = "QQ群"; break;
            case 6: data.source = "微信群"; break;
            case 3: data.source = "其他"; break;
            default : break;
          }
          return data.source;
        }, "sWidth": "5%"
        },
        {
          "sTitle": "操作", "name": "createTime", "data": function (data) {
          return '<a href="'+_PATH+'/member/edit?id=' + data.id + '" class="btn btn-xs default blue-stripe"> 详情 </a>' +
              '<a href="javascript:del(\'' + data.id + '\');" class="btn btn-xs default red-stripe"> 删除 </a>';
        }, "sWidth": "8%"
        }
      ]
    });
  }
</script>
</body>
</html>