<%@ page pageEncoding="UTF-8" language="java" %>
<div class="row">
    <div class="col-md-12">
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption font-dark">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject bold uppercase">案例信息</span>
                </div>
                <div class="actions">
                </div>
            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover table-header-fixed"></table>
            </div>
        </div>
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
                "url": _PATH + "/plan/load",
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

                        //操作按钮
                        data[i].action = '<a href="' + _PATH + '/plan/edit?id=' + data[i].id + '" type="button" class="btn btn-primary btn-xs">详情</a>';
                    }
                    return data;
                }
            },
            "columns": [
                {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
                {"sTitle": "案例编号", "name": "caseCode", "data": "caseCode", "sWidth": "8%"},
                {"sTitle": "案例名称", "name": "caseName", "data": "caseName", "sWidth": "6%"},
                {"sTitle": "品牌名称", "name": "brandName", "data": "brandName", "sWidth": "6%"},
                {"sTitle": "广告客户名称", "name": "customerName", "data": "customerName", "sWidth": "8%"},
                {"sTitle": "参赛类别", "name": "caseType", "data": "caseType", "sWidth": "8%"},
                {"sTitle": "用户", "name": "operator", "data": "operator", "sWidth": "8%"},
                {"sTitle": "状态", "name": "bizStatus", "data": "bizStatus", "sWidth": "6%"},
                {"sTitle": "操作", "data": "action", "sWidth": "6%"}
            ]
        });
    }
    function del(id, name) {
        layer.confirm('确认删除【' + name + '】?？', {
            btn: ['删除', '取消'] //按钮
        }, function () {
            $.ajax(_PATH + '/plan/delete?id=' + id, {
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