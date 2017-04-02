<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <%@include file="../common/common.jsp" %>
</head>
<body class="mini-navbar fixed-sidebar no-skin-config full-height-layout">
<div id="wrapper">
  <div id="page-wrapper" class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
      <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
          <h2>字典管理</h2>
          <ol class="breadcrumb">
            <li>
              <a href="${_PATH}/">首页</a>
            </li>
            <li>
              <a>系统管理</a>
            </li>
            <li class="active">
              <strong>字典管理</strong>
            </li>
          </ol>
        </div>
        <div class="col-lg-2">

        </div>
      </div>
      <div class="fh-breadcrumb">
        <div class="fh-column">
          <div class="full-height-scroll">
            <ul class="list-group elements-list">
              <c:forEach items="${dicTypeList}" var="item">
                <li class="list-group-item" data-dic-type="${item.enName}">
                  <a href="javascript:;">
                    <small class="pull-right text-muted"> ${item.enName}</small>
                    <strong>${item.zhName}</strong>
                    <div class="small m-t-xs">
                      <p class="m-b-none">
                        <i class="fa fa-calendar"></i> ${item.createTime}
                      </p>
                    </div>
                  </a>
                </li>
              </c:forEach>
            </ul>
          </div>
        </div>
        <div class="full-height">
          <div class="full-height-scroll white-bg border-left">
            <div class="element-detail-box">
              <table class="table table-striped table-bordered table-hover dataTables-example"></table>
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
                {"sTitle": "名称", "name": "zhName", "data": "zhName", "sWidth": "8%"},
                {"sTitle": "取值", "name": "value", "data": "value", "sWidth": "6%"},
                {"sTitle": "排序", "name": "sort", "data": "sort", "sWidth": "8%"},
                {
                    "sTitle": "状态", "name": "status", "data": function (data) {
                    return data.status === 1 ? '正常' : '禁用';
                }, "sWidth": "6%"
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
        $('.list-group > li').on('click', function () {
            dataTable.ajax.url(_MODULE_NAME + '/load?dicType=' + $(this).data('dicType')).load();
        });
    });
</script>
</body>
</html>