<%@ page pageEncoding="UTF-8" language="java" %>
<div class="page-bar">
  <ul class="page-breadcrumb">
    <li>
      <a href="${_PATH}/main.html">首页</a>
      <i class="fa fa-angle-right"></i>
    </li>
    <li>
      <span>发票信息</span>
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
        "url": _PATH + "/pay/load",
        "type": "POST",
        "data": function (params) {
          //params.hotelName = $("#hotelName").val();
          //params.counties = $("#counties").val();
          //params.levels = $("#levels").val();
        }
      },
      "columns": [
        {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
        {"sTitle": "单位名称", "name": "invoiceCompany", "data": "invoiceCompany", "sWidth": "8%"},
        {"sTitle": "纳税人识别码", "name": "taxpayer", "data": "taxpayer", "sWidth": "6%"},
        {"sTitle": "注册地址", "name": "regAddress", "data": "regAddress", "sWidth": "6%"},
        {"sTitle": "注册电话", "name": "regTel", "data": "regTel", "sWidth": "8%"},
        {"sTitle": "开户银行", "name": "bankName", "data": "bankName", "sWidth": "8%"},
        {"sTitle": "银行账户", "name": "bankAcc", "data": "bankAcc", "sWidth": "8%"},
        {
          "sTitle": "是否增值税发票", "name": "isAdd", "data": function (data) {
          return data.isAdd == '1' ? '是' : '否';
        }, "sWidth": "8%"
        },
        {
          "sTitle": "状态", "name": "status", "data": function (data) {
          return data.status == 'VALID' ? '有效' : '无效';
        }, "sWidth": "6%"
        },
        {
          "sTitle": "操作", "data": function (data) {
          return '<a href="' + _PATH + '/pay/edit?id=' + data.id + '" type="button" class="btn btn-primary btn-xs">详情</a>';
        }, "sWidth": "6%"
        }
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