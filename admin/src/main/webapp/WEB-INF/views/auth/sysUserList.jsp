<%@ page pageEncoding="UTF-8" language="java" %>
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
      <span>用户管理</span>
    </li>
  </ul>
  <div class="page-toolbar">
    <button type="button" class="btn blue btn-outline"><i class="fa fa-plus"></i> 添加</button>
  </div>
</div>
<div class="row">
  <div class="col-md-12">
    <table id="dataTable" class="table table-striped table-bordered table-hover table-header-fixed"></table>
  </div>
</div>
<script type="text/javascript" src="${_PATH}/static/plugins/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
  $(function () {
    $('table').dataList({
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
        },
        {
          "sTitle": "操作", "name": "createTime", "data": function (d) {
          return '<div class="btn-group btn-group-xs btn-group-solid">' +
              '<button onclick="edit(\'' + d.id + '\');" type="button" class="btn blue" title="修改"><i class="fa fa-edit"></i></button>' +
              '<button onclick="del(\'' + d.id + '\');" type="button" class="btn red" title="删除"><i class="fa fa-trash"></i></button>' +
              '</div>';
        }, "sWidth": "8%"
        }
      ]
    });
  });
</script>