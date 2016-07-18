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
<body>
<div class="page-container">
    <div class="page-content-wrapper">
        <div class="page-content">

            <div class="portlet box purple">
                <div class="portlet-title">
                    <div class="caption"><i class="fa fa-gift"></i> 字典内容管理</div>
                </div>
                <div class="portlet-body">
                    <form role="form">
                        <div class="form-body">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-btn"><button class="btn" type="button">中文名</button></span>
                                    <input type="text" class="form-control" name="zhName" value="${data.zhName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-btn"><button class="btn" type="button">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;值</button></span>
                                    <input type="text" class="form-control" name="value" value="${data.value}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-btn"><button class="btn" type="button">排序号</button></span>
                                    <input type="text" class="form-control" name="sort" value="${data.sort}">
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="id" value="${data.id}">
                        <input type="hidden" name="dicTypeId" value="${dicTypeId}">
                        <input type="hidden" name="status" value="VALID">
                        <div class="form-actions right1">
                            <button type="button" class="btn green" onclick="save()">保存</button>
                            <button type="button" class="btn default">取消</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript">
    function save() {
        $.ajax(_PATH + '/dictionary/saveDictionary', {
            type: 'POST',
            data: $('form').serialize(),
            success: function (resp) {
                if (resp.statusCode === 200) {
                    parent.window.location.reload();
                    parent.layer.msg('添加成功', {icon: 6, time: 1000});
                    parent.layer.close(parent.layer.getFrameIndex(window.name));
                }
                else parent.layer.msg('添加失败', {icon: 6});
            },
            error: function () {
                parent.layer.msg('添加失败', {icon: 6});
            }
        });
    }
</script>
</body>
</html>