<%@ page pageEncoding="UTF-8" language="java" %>
<div class="row">
    <div class="col-md-12">
        <div class="portlet light bordered">
            <div class="portlet-title">
                <div class="caption font-dark">
                    <i class="icon-settings font-dark"></i>
                    <span class="caption-subject bold uppercase">发票信息</span>
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
                "url": _PATH + "/pay/load",
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
                        data[i].isAdd = (data[i].isAdd == '1' ? '是' : '否');
                        data[i].status = (data[i].status == 'VALID' ? '有效' : '无效');

                        //操作按钮
                        data[i].action = '<a href="' + _PATH + '/${MODULE_NAME}/edit?id=' + data[i].id + '" type="button" class="btn btn-primary btn-xs">详情</a>';
                    }
                    return data;
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
                {"sTitle": "是否增值税发票", "name": "isAdd", "data": "isAdd", "sWidth": "8%"},
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
</html>