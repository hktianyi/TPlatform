<%@ page pageEncoding="UTF-8" language="java" %>
<link rel="stylesheet" href="${_PATH}/static/plugins/jquery-easyui/themes/material/easyui.css" type="text/css"/>
<link rel="stylesheet" href="${_PATH}/static/plugins/jquery-easyui/themes/icon.css" type="text/css"/>
<style>
  .tree-folder, .tree-folder-open, .tree-file {
    background: none !important;
  }
</style>
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
<script type="text/javascript" src="${_PATH}/static/plugins/jquery-easyui/jquery.easyui.min.js"></script>
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
            {"sTitle": "创建时间", "name": "timestamp", "data": function (d) {
              return moment(d.timestamp).format('YYYY-M-D H:m');
            }, "sWidth": "8%"}
          ]
        });
      }
    });
  });
</script>
</html>