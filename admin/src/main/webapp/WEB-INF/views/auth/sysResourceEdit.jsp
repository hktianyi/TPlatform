<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<div class="row">
  <div class="col-md-12">
    <div class="portlet box purple">
      <div class="portlet-title">
        <div class="caption"><i class="fa fa-gift"></i> 菜单信息</div>
      </div>
      <div class="portlet-body">
        <form role="form" id="editForm">
          <div class="form-body">
            <div class="form-group">
              <div class="input-group">
                <span class="input-group-btn"><button class="btn" type="button">图标</button></span>
                <input type="text" class="form-control" name="icon" value="${data.icon}">
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <span class="input-group-btn"><button class="btn" type="button">名称</button></span>
                <input type="text" class="form-control" name="name" value="${data.name}">
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <span class="input-group-btn"><button class="btn" type="button">路径</button></span>
                <input type="text" class="form-control" name="action" value="${data.action}">
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <span class="input-group-btn"><button class="btn" type="button">排序号</button></span>
                <input type="text" class="form-control" name="sort" value="${data.sort}">
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <span class="input-group-btn"><button class="btn" type="button">状态</button></span>
                <select class="form-control" name="status">
                  <option value="VALID"
                          <c:if test="${data.status eq 'VALID'}">selected</c:if> >有效
                  </option>
                  <option value="INVALID"
                          <c:if test="${data.status eq 'INVALID'}">selected</c:if> >无效
                  </option>
                </select>
              </div>
            </div>
          </div>
          <input type="hidden" id="id" name="id" value="${data.id}">
          <input type="hidden" id="pid" name="pid" value="${data.pid}">
          <input type="hidden" name="type" value="MENU">
          <div class="form-actions right1">
            <button type="button" class="btn green" onclick="save()">保存</button>
            <button type="button" class="btn default">取消</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  $(function () {
    if(!$('#id').val()) {
      $('#pid').val(window.sessionStorage.getItem("pid") || 0);
      window.sessionStorage.removeItem("pid");
    }
  });
  function save() {
    $.ajax(_PATH + '/sysResource/save', {
      type: 'POST',
      data: $('#editForm').serialize(),
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
</html>