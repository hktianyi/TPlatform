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
      <div class="page-bar">
        <ul class="page-breadcrumb">
          <li>
            <a href="${_PATH}/main.html">首页</a>
            <i class="fa fa-angle-right"></i>
          </li>
          <li>
            <a href="#">权限管理</a>
            <i class="fa fa-angle-right"></i>
          </li>
          <li>
            <span>角色管理</span>
          </li>
        </ul>
        <div class="page-toolbar">
          <button type="button" class="btn blue btn-outline"><i class="fa fa-plus"></i> 添加</button>
        </div>
      </div>
      <div class="row" style="margin-top: 6px;">
        <div style="margin:6px 0;"></div>
        <div class="col-md-3">
          <ul class="easyui-tree"></ul>
        </div>
        <div class="col-md-9">
          <table id="dataTable" class="table table-striped table-bordered table-hover table-header-fixed"></table>
        </div>
      </div>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="/WEB-INF/common/footer.jsp" %>
<script type="text/javascript">
  $(function () {
    $('.easyui-tree').tree({
      url: _MODULE_NAME + '/loadTree',
      loadFilter: function (data, parent) {
        data.forEach(function (d) {
          d.iconCls = 'fa fa-' + d.icon;
          d.text = d.name;
        });
        return data;
      },
      dnd: true,
      lines: true,
      onSelect: function (node) {
        $('#dataTable').dataList({
          url: _PATH + '/sysUser/load',
          data: {id: node.id},
          columns: [
            {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
            {"sTitle": "昵称", "name": "nickname", "data": "nickname", "sWidth": "8%"},
            {"sTitle": "用户名", "name": "username", "data": "username", "sWidth": "6%"},
            {"sTitle": "手机", "name": "mobile", "data": "mobile", "sWidth": "8%"},
            {"sTitle": "邮箱", "name": "email", "data": "email", "sWidth": "8%"},
            {"sTitle": "状态", "name": "status", "data": "status", "sWidth": "6%"},
            {
              "sTitle": "创建时间", "name": "createTime", "data": function (d) {
              return moment(d.createTime).format('YYYY-M-D H:m');
            }, "sWidth": "8%"
            }
          ]
        });
      }
    });
  });
</script>
</body>
</html>