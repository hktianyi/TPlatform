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
                  <h5>添加配置</h5>
                </div>
                <div class="ibox-content">
                  <form class="form-horizontal" action="${_PATH}/config/save" method="post">
                    <input type="hidden" name="id" value="${data.id}">
                    <div class="form-group">
                      <label class="col-md-2 control-label">*名称:</label>
                      <div class="col-md-10"><input type="text" name="name" placeholder="配置名称" class="form-control"></div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-2 control-label">类型:</label>
                      <div class="col-md-10"><input type="text" name="type" placeholder="类型" class="form-control"></div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-2 control-label">*Key:</label>
                      <div class="col-md-10"><input type="text" name="confKey" placeholder="confKey" class="form-control"></div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-2 control-label">*值:</label>
                      <div class="col-md-10"><input type="text" name="val" placeholder="值" class="form-control"></div>
                    </div>
                    <div class="form-group">
                      <label class="col-md-2 control-label">备注:</label>
                      <div class="col-md-10"><input type="text" name="remark" placeholder="remark" class="form-control"></div>
                    </div>
                    <div class="form-group">
                      <div class="col-lg-offset-2 col-lg-10">
                        <button class="btn btn-sm btn-white" type="submit">保存</button>
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
                type: {
                    required: true,
                    maxlength: 32
                },
                confKey: {
                    required: true,
                    maxlength: 32
                },
                val: {
                    required: true,
                    maxlength: 100
                },
                remark: {
                    maxlength: 100
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