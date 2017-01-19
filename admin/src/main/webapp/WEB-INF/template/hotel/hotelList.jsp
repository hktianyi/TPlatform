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
            <span>酒店管理</span>
          </li>
        </ul>
        <div class="page-toolbar">
          <a href="javascript:;" class="btn btn-circle grey btn-sm btn-outline search-advance-btn"><i class="fa fa-search-plus"></i> 高级搜索 </a>
          <a href="${_PATH}/${_MODULE_NAME}/edit" class="btn btn-circle green btn-sm"><i class="fa fa-plus"></i> 添加 </a>
        </div>
        <%@include file="../../common/search.jsp" %>
        <div class="row" id="search-advance" style="display: none;">
          <div class="col-md-12">
            <form class="form-horizontal" role="form">
              <div class="form-body">
                <div class="row">
                  <div class="col-md-3">
                    <div class="form-group">
                      <label class="control-label col-md-4">酒店名:</label>
                      <div class="col-md-8">
                        <input class="form-control" type="text" id="name">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-3">
                    <div class="form-group">
                      <label class="control-label col-md-4">省:</label>
                      <div class="col-md-8">
                        <input class="form-control" type="text" id="province">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-3">容纳人数:</label>
                      <div class="col-md-8 input-group">
                        <input type="number" class="form-control" id="personNum_from">
                        <span class="input-group-addon"> 至 </span>
                        <input type="number" class="form-control" id="personNum_to">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-1">
                    <div class="form-group">
                      <button type="button" class="btn blue" onclick="layer.msg('Comming soon...');"><i
                          class="fa fa-search"></i> 查询
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
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
                "url": _MODULE_NAME + "/load",
                "type": "POST",
                "data": function (params) {
                    params.q = $("#q").val();
                    params.name = $("#hotelName").val();
                    params.province = $("#province").val();
                    params.attr0 = ($("#personNum_from").val() || 0) + ',' + ($("#personNum_to").val() || 9999999);
                }
            },
            "columns": [
                {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
                {"sTitle": "酒店名", "name": "name", "data": "name", "sWidth": "15%"},
                {
                    "sTitle": "星级", "name": "star", "data": function (data) {
                    switch (data.star) {
                        case 50:
                            data.star = "五星级";
                            break;
                        case 51:
                            data.star = "五星级";
                            break;
                        case 40:
                            data.star = "四星级";
                            break;
                        case 30:
                            data.star = "三星级";
                            break;
                        case 20:
                            data.star = "经济";
                            break;
                        default:
                            break;
                    }
                    return data.star;
                }, "sWidth": "8%"
                },
                {"sTitle": "省份", "name": "province", "data": "province", "sWidth": "8%"},
                {"sTitle": "城市", "name": "city", "data": "city", "sWidth": "8%"},
                {"sTitle": "区县", "name": "county", "data": "county", "sWidth": "8%"},
                {"sTitle": "容纳人数", "name": "peopleNumMax", "data": "peopleNumMax", "sWidth": "8%"},
                {"sTitle": "会场数量", "name": "venueNum", "data": "venueNum", "sWidth": "8%"},
                {
                    "sTitle": "推荐", "name": "grade", "data": function (data) {
                    return data.grade === 1 ? '推荐' : '';
                }, "sWidth": "6%"
                },
                {
                    "sTitle": "状态", "name": "status", "data": function (data) {
                    return data.status === 1 ? '有效' : '无效';
                }, "sWidth": "6%"
                },
                {
                    "sTitle": "创建时间", "name": "createTime", "data": function (data) {
                    return moment(data.createTime).format('YYYY-MM-DD HH:mm');
                }, "sWidth": "8%"
                },
                {
                    "sTitle": "操作", "name": "createTime", "data": function (data) {
                    return '<a href="' + _PATH + '/hotel/edit?id=' + data.id + '" type="button" class="btn btn-primary btn-xs">详情</a>' +
                        '<a href="javascript:del(\'' + data.id + '\',\'' + data.customerName + '\');" type="button" class="btn btn-danger btn-xs">删除</a>';
                }, "sWidth": "8%"
                }
            ]
        });
    }
</script>
</body>
</html>