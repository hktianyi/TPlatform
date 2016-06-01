<%@ page pageEncoding="UTF-8" language="java" %>
<div class="row">
  <div class="col-md-12">
    <table id="dataTable" class="table table-striped table-bordered table-hover table-header-fixed"></table>
  </div>
</div>
<script type="text/javascript" src="${_PATH}/static/plugins/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
  var dataTable;
  $(function () {
    getDataList({columns: [
      {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
      {"sTitle": "昵称", "name": "nickname", "data": "nickname", "sWidth": "8%"},
      {"sTitle": "用户名", "name": "username", "data": "username", "sWidth": "6%"},
      {"sTitle": "手机", "name": "mobile", "data": "mobile", "sWidth": "8%"},
      {"sTitle": "邮箱", "name": "email", "data": "email", "sWidth": "8%"},
      {"sTitle": "状态", "name": "status", "data": "status", "sWidth": "6%"},
      {"sTitle": "创建时间", "name": "timestamp", "data": "timestamp", "sWidth": "8%"}
    ]});
  });
</script>
</html>