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
            <span>需求单管理</span>
          </li>
        </ul>
      </div>
      <%--<div class="row">
        <div class="col-md-12">
          <div class="portlet box blue">
            <div class="portlet-title">
              <div class="caption">
                <i class="fa fa-filter"></i>筛选
              </div>
              <div class="tools">
                <a href="javascript:;" class="expand" data-original-title="" title=""> </a>
              </div>
            </div>
            <div class="portlet-body form" style="display: none;">
              <form class="form-horizontal" role="form">
                <div class="form-body">
                  <div class="row">
                    <div class="col-md-3">
                      <div class="form-group">
                        <label class="control-label col-md-4">酒店名:</label>
                        <div class="col-md-8">
                          <input class="form-control" type="text" id="hotelName">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-3">
                      <div class="form-group">
                        <label class="control-label col-md-4">区域:</label>
                        <div class="col-md-8">
                          <select class="form-control" multiple="multiple" id="county">
                            <c:forEach items="${county}" var="county">
                              <option value="${county}">${county}</option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-3">
                      <div class="form-group">
                        <label class="control-label col-md-4">星级:</label>
                        <div class="col-md-8">
                          <select class="form-control" multiple="multiple" id="level">
                            <c:forEach items="${hotel_star}" var="level">
                              <option value="${level.value}">${level.zhName}</option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-2">
                      <div class="form-group">
                        <button type="button" class="btn blue" onclick="getDataList()"><i
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
      </div>--%>
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
          params.hotelName = $("#hotelName").val();
          params.county = $("#county").val() && $("#county").val().join();
          params.level = $("#level").val() && $("#level").val().join();
        }
      },
      "columns": [
        {"sTitle": "序号", "sWidth": "5%", "defaultContent": ''},
        {"sTitle": "人数", "name": "personNum", "data": "personNum", "sWidth": "8%"},
        {"sTitle": "预算", "name": "budget", "data": "budget", "sWidth": "8%"},
        {"sTitle": "时长", "name": "duration", "data": "duration", "sWidth": "8%"},
        {"sTitle": "开始日期", "name": "startDate", "data": "startDate", "sWidth": "8%"},
        {"sTitle": "会议类型", "name": "meetingType", "data": "meetingType", "sWidth": "8%"},
        {"sTitle": "电话", "name": "tel", "data": "tel", "sWidth": "6%"},
        {"sTitle": "备注", "name": "remark", "data": "remark", "sWidth": "6%"},
        {"sTitle": "用户IP", "name": "ip", "data": "ip", "sWidth": "8%"},
        {
          "sTitle": "状态", "name": "status", "data": function (data) {
          return data.status === 'VALID' ? '有效' : '无效';
        }, "sWidth": "8%"
        },
        {
          "sTitle": "创建时间", "name": "createTime", "data": function (data) {
          return moment(data.createTime).format('YYYY-MM-DD HH:mm');
        }, "sWidth": "8%"
        },
        {
          "sTitle": "操作", "name": "createTime", "data": function (data) {
          return '<a href="javascript:del(\'' + data.id + '\');" type="button" class="btn btn-danger btn-xs">删除</a>';
        }, "sWidth": "8%"
        }
      ]
    });
  }
</script>
</body>
</html>