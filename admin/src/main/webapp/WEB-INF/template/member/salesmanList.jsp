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
            <span>BD管理</span>
          </li>
        </ul>
        <div class="page-toolbar">
          <a href="${_PATH}/${_MODULE_NAME}/edit" class="btn btn-circle blue btn-outline btn-xs">&nbsp;添加&nbsp;</a>
        </div>
        <jsp:include page="/WEB-INF/common/search.jsp?qNames=salesmanName,tel"/>
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
        "url": _MODULE_NAME + "/load",
        "type": "POST",
        "data": function (params) {
          params.qNames = $("#qNames").val();
          params.q = $("#q").val();
        }
      },
      "columns": [
        {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
        {"sTitle": "BD名", "name": "salesmanName", "data": "salesmanName", "sWidth": "10%"},
        {"sTitle": "酒店名", "name": "hotel.name", "data": function (data) {
          return data.hotel && data.hotel.name;
        }, "sWidth": "15%"},
        {"sTitle": "性别", "name": "gender", "data": function (data) {
          return data.gender == 1 ? '男' : (data.gender == 2 ? '女' : '未知');
        }, "sWidth": "5%"},
        {"sTitle": "职务", "name": "duty", "data": "duty", "sWidth": "8%"},
        {"sTitle": "手机", "name": "tel", "data": "tel", "sWidth": "8%"},
        {
          "sTitle": "状态", "name": "status", "data": function (data) {
          return data.status === '1' ? '有效' : '无效';
        }, "sWidth": "8%"
        },
        {
          "sTitle": "创建时间", "name": "createTime", "data": function (data) {
          return moment(data.createTime).format('YYYY-MM-DD HH:mm');
        }, "sWidth": "8%"
        },
        {
          "sTitle": "操作", "name": "createTime", "data": function (data) {
          return '<a href="' + _PATH + '/salesman/edit?id=' + data.id + '" type="button" class="btn btn-primary btn-xs">详情</a>' +
              '<a href="javascript:del(\'' + data.id + '\');" type="button" class="btn btn-danger btn-xs">删除</a>';
        }, "sWidth": "8%"
        }
      ]
    });
  }
</script>
</body>
</html>