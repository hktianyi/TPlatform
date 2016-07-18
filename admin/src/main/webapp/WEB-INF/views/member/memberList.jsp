<%@ page pageEncoding="UTF-8" language="java" %>
<div class="page-bar">
  <ul class="page-breadcrumb">
    <li>
      <a href="${_PATH}/main.html">首页</a>
      <i class="fa fa-angle-right"></i>
    </li>
    <li>
      <span>会员信息</span>
    </li>
  </ul>
</div>
<div class="row">
  <div class="col-md-12">
    <table class="table table-striped table-bordered table-hover table-header-fixed"></table>
  </div>
</div>
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
        "url": _PATH + "/${MODULE_NAME}/load",
        "type": "POST",
        "data": function (params) {
          //params.hotelName = $("#hotelName").val();
          //params.counties = $("#counties").val();
          //params.levels = $("#levels").val();
        },
        "dataSrc": function (resp) {
          var data = resp.data;
          var time = new Date().getTime();
          for (var i = 0, len = data.length; i < len; i++) {
            data[i].appellation = (data[i].appellation == '1' ? '先生' : '女士');
            data[i].status = (data[i].status == 'VALID' ? '激活' : '未激活');

            //操作按钮
            data[i].action = '<a href="' + _PATH + '/${MODULE_NAME}/edit?id=' + data[i].id + '" type="button" class="btn btn-primary btn-xs">详情</a>' +
                '<a href="javascript:del(\'' + data[i].id + '\',\'' + data[i].username + '\');" type="button" class="btn btn-danger btn-xs">删除</a>';
          }
          return data;
        }
      },
      "columns": [
        {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
        {"sTitle": "用户名", "name": "fullname", "data": "fullname", "sWidth": "8%"},
        {"sTitle": "邮箱", "name": "useremail", "data": "useremail", "sWidth": "8%"},
        {"sTitle": "称谓", "name": "appellation", "data": "appellation", "sWidth": "6%"},
        {"sTitle": "职位", "name": "job", "data": "job", "sWidth": "6%"},
        {"sTitle": "电话", "name": "telphone", "data": "telphone", "sWidth": "8%"},
        {"sTitle": "状态", "name": "status", "data": "status", "sWidth": "6%"},
        {"sTitle": "操作", "data": "action", "sWidth": "6%"}
      ]
    });
  }
  function del(id, name) {
    layer.confirm('确认删除【' + name + '】?？', {
      btn: ['删除', '取消'] //按钮
    }, function () {
      $.ajax(_PATH + '/${MODULE_NAME}/delete?id=' + id, {
        type: 'DELETE',
        success: function (resp) {
          if (resp.statusCode === 200) {
            layer.msg('删除成功');
            getDataList();
          }
          else  layer.msg('删除失败', {icon: 6});
        },
        error: function () {
          layer.msg('删除失败', {icon: 6});
        }
      });
    });
  }
</script>