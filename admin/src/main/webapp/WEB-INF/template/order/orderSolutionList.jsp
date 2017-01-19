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
            <a href="${_PATH}/order/list">订单管理</a>
            <i class="fa fa-angle-right"></i>
          </li>
          <li>
            <span>客户方案管理</span>
          </li>
        </ul>
        <div class="page-toolbar">
          <%--<a href="javascript:;" class="btn btn-circle grey btn-sm btn-outline search-advance-btn"><i class="fa fa-search-plus"></i> 高级搜索 </a>--%>
          <a href="${_PATH}/solution/edit/${orderId}" class="btn btn-circle green btn-sm"><i class="fa fa-plus"></i> 添加 </a>
          <a href="javascript:alert('http://www.changdiyun.com/solution/${orderId}');" class="btn btn-circle blue btn-sm"> <i class="fa fa-share"></i> 分享&nbsp;</a>
        </div>
        <%--<%@include file="../../common/search.jsp" %>--%>
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
  var dataTable, _orderId = '${orderId}';
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
        "url": _PATH + "/solution/load",
        "type": "POST",
        "data": function (params) {
          if(_orderId) params.orderId = _orderId;
        }
      },
      "columns": [
        {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
//        {"sTitle": "方案名称", "name": "planName", "data": "planName", "sWidth": "7%"},
        {"sTitle": "酒店名称", "name": "hotel.name", "data": "hotel.name", "sWidth": "7%"},
        {
          "sTitle": "状态", "name": "status", "data": function (data) {
          switch (data.status) {
            case 1: data.status = "制作中"; break;
            case 2: data.status = "已提交"; break;
            case 3: data.status = "采用"; break;
            case 4: data.status = "失效"; break;
            default : break;
          }
          return data.status;
        }, "sWidth": "8%"
        },
        {
          "sTitle": "创建时间", "name": "createTime", "data": function (data) {
          return moment(data.createTime).format('YYYY-MM-DD HH:mm');
        }, "sWidth": "8%"
        },
        {
          "sTitle": "操作", "name": "createTime", "data": function (data) {
          return '<a href="' + _PATH + '/solution/edit/' + _orderId + '?id=' + data.id + '" class="btn btn-xs default blue-stripe"> 详情 </a>' +
              '<a href="javascript:del(\'\', \''+_PATH+'/solution/delete/' + data.id + '\');" class="btn btn-xs default red-stripe"> 删除 </a>';
        }, "sWidth": "8%"
        }
      ]
    });
  }
</script>
</body>
</html>