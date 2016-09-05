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
          <div class="caption"><i class="fa fa-gift"></i> 字典类型管理</div>
        </div>
        <div class="portlet-body">
          <form role="form">
            <div class="form-body">
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-btn"><button class="btn" type="button">英文名</button></span>
                  <input type="text" class="form-control" name="enName" value="${data.enName}"></div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-btn"><button class="btn" type="button">中文名</button></span>
                  <input type="text" class="form-control" name="zhName" value="${data.zhName}"></div>
              </div>
              <div class="form-group">
                <div class="input-group">
                  <span class="input-group-btn"><button class="btn"
                                                        type="button">类&nbsp;&nbsp;&nbsp;&nbsp;型</button></span>
                  <select class="form-control" name="type">
                    <option value="SELECT"
                            <c:if test="${data.type eq 'SELECT'}">aria-checked="checked"</c:if> >下拉框
                    </option>
                    <option value="CHECKBOX"
                            <c:if test="${data.type eq 'CHECKBOX'}">aria-checked="checked"</c:if> >复选框
                    </option>
                    <option value="RADIO"
                            <c:if test="${data.type eq 'RADIO'}">aria-checked="checked"</c:if> >单选框
                    </option>
                  </select></div>
              </div>
            </div>
            <input type="hidden" name="id" value="${data.id}">
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
<script src="${_PATH}/static/plugins/jquery/jquery-2.2.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
  function save() {
    $.ajax(_PATH + '/dictionary/saveDictionaryType/${menuType}', {
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