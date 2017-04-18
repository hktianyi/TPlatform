<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <%@include file="../common/common.jsp" %>
  <link href="http://cdn.bootcss.com/x-editable/1.5.1/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet">
</head>
<body class="mini-navbar fixed-sidebar">
<div id="wrapper">
  <div id="page-wrapper" class="gray-bg">
    <div class="row">
      <div class="col-lg-12">
        <div class="wrapper wrapper-content animated fadeInRight">
          <div class="ibox">
            <div class="ibox-title">
              <h5>系统配置</h5>
              <div class="ibox-tools">
                <a href="${_PATH}/config/edit" class="btn btn-primary btn-xs btn-add">添加</a>
              </div>
            </div>
            <div class="ibox-content">
              <div class="row m-b-sm m-t-sm">
                <div class="col-md-1">
                  <button type="button" id="loading-example-btn" class="btn btn-white btn-sm"><i class="fa fa-refresh"></i> 刷新</button>
                </div>
                <div class="col-md-11">
                  <div class="input-group"><input type="text" placeholder="Search" class="input-sm form-control"> <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-primary"> Go!</button> </span></div>
                </div>
              </div>
              <div class="project-list">
                <table class="table table-hover"></table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="../common/footer.jsp" %>
<script src="http://cdn.bootcss.com/x-editable/1.5.1/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<script type="text/javascript">
    var dataTable;
    $(function () {
        $('.btn-add').magnificPopup({
            type: 'iframe'
        });
        dataTable = $('table').dataList({
            columns: [
                {"sTitle": "#", "sWidth": "5%", "defaultContent": ''},
                {"sTitle": "配置项", "name": "name", "data": function (data) {
                    return '<a href="javascript:;" class="editable" data-pk="'+data.id+'" data-name="name">'+data.name+'</a>';
                }, "sWidth": "20%", "className": "project-title"},
                {"sTitle": "key", "name": "confKey", "data": function (data) {
                    return '<a href="javascript:;" class="editable" data-pk="'+data.id+'" data-name="confKey">'+data.confKey+'</a>';
                }, "sWidth": "20%"},
                {"sTitle": "值", "name": "val", "data": function (data) {
                    return '<a href="javascript:;" class="editable" data-pk="'+data.id+'" data-name="val">'+data.val+'</a>';
                }, "sWidth": "20%"},
                {"sTitle": "备注", "name": "remark", "data": function (data) {
                    return '<a href="javascript:;" class="editable">'+data.remark+'</a>';
                }, "sWidth": "15%"},
                {
                    "sTitle": "操作", "name": "createTime", "data": function (data) {
                    return '<a href="javascript:del(\''+data.id+'\');" class="btn btn-white btn-sm"><i class="fa fa-folder"></i> 删除 </a>';
                }, "sWidth": "8%", "className": "project-actions"
                }
            ]
        }).on('init.dt',function () {
            $('.editable').editable({
                ajaxOptions: {type: 'post'},
                type: 'text',
                url: _MODULE_NAME + '/updateSingle'
            });
        });
    });
</script>
</body>
</html>