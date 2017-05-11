<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <%@include file="../common/common.jsp" %>
</head>
<body class="mini-navbar fixed-sidebar">
<div id="wrapper">
  <div id="page-wrapper" class="gray-bg">
    <div class="row">
      <div class="col-lg-12">
        <div class="wrapper wrapper-content animated fadeInUp">
          <div class="row">
            <div class="col-lg-12">
              <div class="ibox float-e-margins">
                <div class="ibox-title">
                  <h5>角色详情</h5>
                </div>
                <div class="ibox-content">
                  <form class="form-horizontal" action="${_PATH}/sysRole/save" method="post">
                    <div class="form-group">
                      <label class="col-xs-2 control-label">*图标:</label>
                      <div class="col-xs-10"><input type="text" name="icon" placeholder="icon" value="${data.icon}" class="form-control"></div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-2 control-label">*角色:</label>
                      <div class="col-xs-10"><input type="text" name="role" placeholder="角色" value="${data.role}" class="form-control"></div>
                    </div>
                    <div class="form-group">
                      <label class="col-xs-2 control-label">*名称:</label>
                      <div class="col-xs-10"><input type="text" name="name" placeholder="名称" value="${data.name}" class="form-control"></div>
                    </div>
                    <div class="form-group">
                      <div class="col-xs-offset-2 col-xs-10">
                        <button class="btn btn-sm btn-primary" type="submit">保存</button>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="../common/footer.jsp" %>
<script type="text/javascript">
  $(function () {
    $('form').validate({
      debug: true,
      rules: {
        name: {
          required: true,
          maxlength: 32
        },
        role: {
          required: true,
          maxlength: 32
        }
      }
    });
  });
  function saveCallback() {
    parent.dataTable.draw();
    $(parent.document.getElementsByClassName('mfp-close')).trigger('click');
  }
</script>
</body>
</html>