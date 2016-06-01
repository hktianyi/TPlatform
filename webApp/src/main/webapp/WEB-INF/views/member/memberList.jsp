<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
  <%@include file="/WEB-INF/common/common.jsp" %>
</head>
<%@include file="/WEB-INF/common/header.jsp" %>
<div class="page-container">
  <%@include file="/WEB-INF/common/menu.jsp" %>
  <div class="page-content-wrapper">
    <div class="page-content">
      <div class="row">
        <div class="col-md-12">
          <div class="portlet light bordered">
            <div class="portlet-title">
              <div class="caption font-dark">
                <i class="icon-settings font-dark"></i>
                <span class="caption-subject bold uppercase">用户信息</span>
              </div>
              <div class="actions">
                <%--<a href="javascript:;" class="btn btn-circle btn-default btn-sm">
                  <i class="fa fa-pencil"></i> Edit </a>--%>
                <a href="${_PATH}/${MODULE_NAME}/edit" class="btn btn-circle btn-default btn-sm">
                  <i class="fa fa-plus"></i> 添加 </a>
              </div>
            </div>
            <div class="portlet-body">
              <table class="table table-striped table-bordered table-hover table-header-fixed"></table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="/WEB-INF/common/footer.jsp" %>
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
        "url": _PATH + "/${MODULE_NAME}/find",
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
            if (!data[i].vipEndDate)
              data[i].status = "非会员";
            else if(data[i].vipEndDate >= time)
              data[i].status = "会员";
            else if (data[i].vipEndDate < time)
              data[i].status = "到期";

            data[i].timestamp = moment(data[i].timestamp).format('YYYY-M-D H:m');
            //操作按钮
            data[i].action = '<a href="' + _PATH + '/${MODULE_NAME}/edit?id=' + data[i].id + '" type="button" class="btn btn-primary btn-xs">详情</a>' +
                '<a href="javascript:del(\'' + data[i].id + '\',\'' + data[i].username + '\');" type="button" class="btn btn-danger btn-xs">删除</a>';
          }
          return data;
        }
      },
      "columns": [
        {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
        {"sTitle": "会员编号", "name": "vipCode", "data": "vipCode", "sWidth": "8%"},
        {"sTitle": "会员名", "name": "username", "data": "username", "sWidth": "6%"},
        {"sTitle": "公司名称", "name": "companyName", "data": "companyName", "sWidth": "6%"},
        {"sTitle": "电话", "name": "phone", "data": "phone", "sWidth": "8%"},
        {"sTitle": "状态", "name": "status", "data": "status", "sWidth": "6%"},
        {"sTitle": "注册时间", "name": "timestamp", "data": "timestamp", "sWidth": "8%"},
        {"sTitle": "操作", "data": "action", "sWidth": "10%"}
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
</html>