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
<%@include file="../../common/header.jsp" %>
<!-- BEGIN CONTAINER -->
<div class="page-container">
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content">
      <div class="page-bar">
        <ul class="page-breadcrumb">
          <li>
            <a href="${_PATH}/main.html">首页</a>
            <i class="fa fa-angle-right"></i>
          </li>
          <li>
            <span>BD管理</span>
          </li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="portlet box blue">
            <div class="portlet-title">
              <div class="caption">
                <i class="fa fa-tag"></i>BD详情
              </div>
            </div>
            <div class="portlet-body form">
              <form class="form-horizontal" role="form">
                <input type="hidden" name="id" value="${data.id}">
                <div class="form-body">
                  <h3 class="form-section">基础信息</h3>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">BD名称:</label>
                        <div class="col-md-8">
                          <input class="form-control" id="salesmanName" name="salesmanName" value="${data.salesmanName}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">性别:</label>
                        <div class="col-md-8">
                          <select class="form-control" id="gender" name="gender">
                            <option value="1" <c:if test="${data.gender eq '1'}">selected</c:if>>男</option>
                            <option value="2" <c:if test="${data.gender eq '2'}">selected</c:if>>女</option>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">状态:</label>
                        <div class="col-md-8">
                          <select class="form-control" id="status" name="status">
                            <option value="1" <c:if test="${data.status eq '1'}">selected</c:if>>有效</option>
                            <option value="0" <c:if test="${data.status eq '0'}">selected</c:if>>无效</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">职务:</label>
                        <div class="col-md-8">
                          <input class="form-control" id="duty" name="duty" value="${data.duty}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">手机:</label>
                        <div class="col-md-8">
                          <input class="form-control" id="tel" name="tel" value="${data.tel}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">邮箱:</label>
                        <div class="col-md-8">
                          <input class="form-control" id="email" name="email" value="${data.email}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">微信:</label>
                        <div class="col-md-8">
                          <input class="form-control" id="wechat" name="wechat" value="${data.wechat}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">QQ:</label>
                        <div class="col-md-8">
                          <input class="form-control" id="qq" name="qq" value="${data.qq}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">备注:</label>
                        <div class="col-md-8">
                          <input class="form-control" id="remark" name="remark" value="${data.remark}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">所属酒店:</label>
                        <div class="col-md-8">
                          <input type="hidden" id="hotelId" name="hotelId" value="${data.hotelId}"/>
                          <input class="form-control" id="hotelName" name="name" value="${data.hotel.name}"/>
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
                            <button type="button" class="btn default" onclick="history.back()">取消</button>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6"></div>
                    </div>
                  </div>
                </div>
                <input type="hidden" name="id" value="id">
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="/WEB-INF/common/footer.jsp" %>
<script type="text/javascript">
  $(function () {
    $('form').validate({
      rules: {
        salesmanName: "required",
        tel: "required",
        gender: "required"
      },
      submitHandler: function (form, event) {
        event.preventDefault();
        $.ajax(_MODULE_NAME + '/save', {
          type: "POST",
          data: $('form').serialize(),
          success: function (resp) {
            if (resp.statusCode === 200)
              window.location.href = _MODULE_NAME + "/list";
            else
              alert("保存失败！");
          },
          error: function (res) {
            alert(res.responseText);
          }
        })
      }
    });

    $('#hotelName').autocomplete({
      serviceUrl: _PATH + "/hotel/findSuggest",
      paramName: "hotelName",
      dataType: "json",
      transformResult: function(response) {
        return {
          suggestions: $.map(response.data, function(item) {
            return { value: item.name, data: item.id };
          })
        };
      },
      onSelect: function (suggestion) {
        $('#hotelId').val(suggestion.data);
      }
    });
  });
</script>
</body>
</html>