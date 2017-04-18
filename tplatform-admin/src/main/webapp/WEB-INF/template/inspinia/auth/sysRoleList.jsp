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
              <h5>角色管理</h5>
              <div class="ibox-tools">
                <a href="" class="btn btn-primary btn-xs">添加</a>
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
                {"sTitle": "角色名称", "name": "name", "data": "name", "sWidth": "8%"},
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
    });
</script>
</body>
</html>