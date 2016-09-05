<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="../../common/common.jsp" %>
</head>
<!-- END HEAD -->

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-full-width">
<%@include file="../../common/header.jsp" %>
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
            <span>文章管理</span>
          </li>
        </ul>
        <div class="page-toolbar">
          <a href="${_PATH}/doc/edit" class="btn btn-circle blue btn-outline btn-xs">&nbsp;添加&nbsp;</a>
        </div>
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
<%@include file="../../common/footer.jsp" %>
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
        "url": _PATH + "/doc/load",
        "type": "POST",
        "data": function (params) {
          params.caseCode = $("#caseCode").val();
          params.caseName = $("#caseName").val();
          params.brandName = $("#brandName").val();
          params.caseType2 = $("#caseType2").val();
        }
      },
      "columns": [
        {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
        {"sTitle": "标题", "name": "title", "data": "title", "sWidth": "8%"},
        {"sTitle": "类型", "name": "type", "data": "type", "sWidth": "6%"},
        {"sTitle": "作者", "name": "author", "data": "author", "sWidth": "6%"},
        {"sTitle": "状态", "name": "status", "data": "status", "sWidth": "6%"},
        {
          "sTitle": "创建时间", "name": "createTime", "data": function (data) {
          return moment(data.createTime).format('YYYY-MM-DD HH:mm');
        }, "sWidth": "8%"
        },
        {
          "sTitle": "操作", "data": function (data) {
          return '<div class="btn-group btn-group-xs btn-group-solid">' +
              '<a href="' + _PATH + '/doc/edit?id=' + data.id + '" type="button" class="btn btn-primary btn-xs">详情</a>' +
              '<a href="javascript:del(\'' + data.id + '\');" type="button" class="btn btn-primary btn-xs red">删除</a>' +
              '</div>';
        }, "sWidth": "6%"
        }
      ]
    });
  }
</script>
</body>
</html>