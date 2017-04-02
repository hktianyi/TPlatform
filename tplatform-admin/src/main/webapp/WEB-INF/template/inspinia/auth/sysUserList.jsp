<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <%@include file="../common/common.jsp" %>
</head>
<body class="mini-navbar fixed-sidebar">
<div id="wrapper">
  <div id="page-wrapper" class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
      <div class="row">
        <div class="col-lg-12">
          <div class="ibox float-e-margins">
            <div class="ibox-title">
              <h5>用户分角色管理</h5>
              <div id="roleBtns" data-toggle="buttons-radio" class="btn-group" style="margin-left: 30px;">
                <button class="btn btn-primary btn-xs active" type="button"><i class="fa fa-group"></i> 全部</button>
                <c:forEach items="${roles}" var="role">
                  <button class="btn btn-primary btn-xs" type="button" data-role-id="${role.id}"><i class="fa fa-${role.icon}"></i> ${role.name}</button>
                </c:forEach>
              </div>
            </div>
            <div class="ibox-content">
              <div class="table-responsive" style="overflow-x: hidden">
                <table class="table table-striped table-bordered table-hover dataTables-example"></table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="../common/footer.jsp" %>
</div>
<script type="text/javascript">
    $(function () {
        var dataTable = $('table').dataList({
            columns: [
                {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
                {"sTitle": "昵称", "name": "nickname", "data": "nickname", "sWidth": "8%"},
                {"sTitle": "用户名", "name": "username", "data": "username", "sWidth": "6%"},
                {"sTitle": "手机", "name": "mobile", "data": "mobile", "sWidth": "8%"},
                {"sTitle": "邮箱", "name": "email", "data": "email", "sWidth": "8%"},
                {
                    "sTitle": "状态", "name": "status", "data": function (data) {
                    return data.status === 'VALID' ? '正常' : '禁用';
                }, "sWidth": "6%"
                },
                {
                    "sTitle": "创建时间", "name": "createTime", "data": function (d) {
                    return moment(d.createTime).format('YYYY-M-D H:m');
                }, "sWidth": "8%"
                },
                {
                    "sTitle": "操作", "name": "createTime", "data": function (d) {
                    return '<div class="btn-group btn-group-xs btn-group-solid">' +
                        '<button onclick="edit(\'' + d.id + '\');" type="button" class="btn blue" title="修改"><i class="fa fa-edit"></i> 修改</button>' +
                        '<button onclick="del(\'' + d.id + '\');" type="button" class="btn red" title="删除"><i class="fa fa-trash"></i> 删除</button>' +
                        '</div>';
                }, "sWidth": "8%"
                }
            ]
        });
        $('#roleBtns > .btn').on('click', function () {
            dataTable.ajax.url(_PATH + '/sysUser/loadByRole?roleId=' + $(this).data('role-id')).load();
        })
    });
</script>
</body>
</html>