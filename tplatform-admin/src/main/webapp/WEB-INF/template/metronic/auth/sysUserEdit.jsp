<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="/WEB-INF/template/metronic/common/common.jsp" %>
</head>
<!-- END HEAD -->
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-full-width">
<!-- BEGIN CONTAINER -->
<div class="page-container">
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content">
      <form role="form" id="editForm" class="margin-bottom-40">
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group form-md-line-input">
                  <div class="input-icon">
                    <input type="text" class="form-control" id="username" name="username" value="${data.username}" placeholder="用户名">
                    <i class="fa fa-user"></i>
                    <span class="help-block"></span>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group form-md-line-input">
                  <div class="input-icon">
                    <input type="text" class="form-control" id="nickname" name="nickname" value="${data.nickname}" placeholder="昵称">
                    <i class="fa fa-user"></i>
                    <span class="help-block"></span>
                  </div>
                </div>
              </div>
            </div>
            <c:if test="${empty data.id}">
              <div class="row">
                <div class="col-sm-12">
                  <div class="form-group form-md-line-input">
                    <div class="input-icon">
                      <input type="password" class="form-control" id="password" name="password" value="${data.password}" placeholder="密码">
                      <i class="fa fa-key"></i>
                      <span class="help-block"></span>
                    </div>
                  </div>
                </div>
              </div>
            </c:if>
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group form-md-line-input">
                  <div class="input-icon">
                    <input type="text" class="form-control" id="mobile" name="mobile" value="${data.mobile}" placeholder="手机">
                    <i class="fa fa-mobile"></i>
                    <span class="help-block"></span>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group form-md-line-input">
                  <div class="input-icon">
                    <input type="email" class="form-control" id="email" name="email" value="${data.email}" placeholder="邮箱">
                    <i class="fa fa-envelope-o"></i>
                    <span class="help-block"></span>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group form-md-line-input">
                  <label class="col-sm-2 control-label">角色: </label>
                  <div class="col-sm-10">
                    <div class="md-checkbox-inline">
                      <c:forEach items="${roles}" var="role">
                        <div class="md-checkbox">
                          <input type="checkbox" id="role${role.role}" name="role[]" class="md-check" value="${role.role}"
                          <c:forEach items="${data.roles}" var="r">
                                 <c:if test="${r.id eq role.role}">checked</c:if>
                          </c:forEach>
                          >
                          <label for="role${role.role}">
                            <span></span>
                            <span class="check"></span>
                            <span class="box"></span> ${role.name} </label>
                        </div>
                      </c:forEach>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-6">
                <div class="form-group form-md-line-input">
                  <label class="col-sm-4 control-label">头像: </label>
                  <div class="col-sm-8">
                    <input type="hidden" id="avatarUrl" name="avatarUrl" value="${empty data.avatarUrl ? 'avatarUrl' : data.avatarUrl}">
                    <label for="avatarUrl"><i class="fa fa-${empty data.avatarUrl ? 'avatarUrl' : data.avatarUrl}"></i></label>
                  </div>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group form-md-line-input">
                  <label class="col-sm-4 control-label">状态: </label>
                  <div class="col-sm-8">
                    <input type="checkbox" id="status" value="VALID" name="status"
                           class="make-switch switch-small" ${data.status eq 'VALID' ? 'checked' : ''}
                           data-on-text="<i class='fa fa-check'></i>" data-on-color="info"
                           data-off-text="<i class='fa fa-times'></i>" data-off-color="danger"/>
                  </div>
                </div>
              </div>
            </div>
        <input type="hidden" name="id" value="${data.id}"/>
      </form>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="/WEB-INF/template/metronic/common/footer.jsp" %>
<script type="text/javascript">
  $(function () {
  });

  function save() {
    $.ajax(_MODULE_NAME + '/saveWithRole', {
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
</body>
</html>