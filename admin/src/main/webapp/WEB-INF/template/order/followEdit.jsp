<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="../../common/common.jsp" %>
</head>
<!-- END HEAD -->

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-full-width">
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content">
      <div class="row">
        <div class="col-md-12">
          <form class="form-horizontal" role="form">
            <input type="hidden" name="orderId" value="${order.id}">
            <div class="form-body">
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-2">订单状态:</label>
                    <div class="col-md-8">
                      <select class="form-control" name="status">
                        <option value="1" ${order.status eq '1' ? 'selected' : ''} >新订单</option>
                        <option value="2" ${order.status eq '2' ? 'selected' : ''} >跟进中</option>
                        <option value="3" ${order.status eq '3' ? 'selected' : ''} >已成单</option>
                        <option value="4" ${order.status eq '4' ? 'selected' : ''} >未成单</option>
                      </select>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label col-md-2">备注:</label>
                    <div class="col-md-8">
                      <input class="form-control" name="remark" value=""/>
                    </div>
                  </div>
                </div>
              </div>
              <div class="form-actions">
                <div class="row">
                  <div class="col-md-6">
                    <div class="row">
                      <div class="col-md-offset-3 col-md-9">
                        <button type="submit" class="btn green">
                          <i class="fa fa-pencil"></i> 保存
                        </button>
                        <button type="button" class="btn default" onclick="layerClose()">取消</button>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6"></div>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
<%@include file="/WEB-INF/common/footerWithoutDisInfo.jsp" %>
<script type="text/javascript" src="${_PATH}/static/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
  $(function () {
    $('form').validate({
      rules: {
        remark: "required"
      },
      submitHandler: function (form, event) {
        event.preventDefault();
        $.ajax(_PATH + '/order/saveFollow', {
          type: "POST",
          data: $('form').serialize(),
          success: function (resp) {
            if (resp.statusCode === 200)
              layerClose('保存成功');
            else
              alert("保存失败！");
          },
          error: function (res) {
            alert(res.responseText);
          }
        })
      }
    });
  });
</script>
</body>
</html>