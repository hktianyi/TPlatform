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
<%@include file="/WEB-INF/common/header.jsp" %>
<div class="page-container">
  <%@include file="/WEB-INF/common/menu.jsp" %>
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <div class="page-content">
      <div class="row">
        <div class="col-md-12">
          <div class="portlet light bordered">
            <%--<div class="portlet-title">
              <div class="caption">
                <i class="icon-equalizer font-red-sunglo"></i>
                <span class="caption-subject font-red-sunglo bold uppercase">${data.companyName}上市信息</span>
                <span class="caption-helper">some info...</span>
              </div>
              <div class="tools">
                <a href="" class="collapse"> </a>
                <a href="#portlet-config" data-toggle="modal" class="config"> </a>
                <a href="" class="reload"> </a>
                <a href="" class="remove"> </a>
              </div>
            </div>--%>
            <div class="portlet-body form">
              <!-- BEGIN FORM-->
              <form class="form-horizontal">
                <div class="form-body">
                  <h3 class="form-section">会员信息</h3>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">用户名</label>
                        <div class="col-md-9">
                          <input type="text" name="username" class="form-control" value="${data.username}">
                          <span class="help-block"></span>
                        </div>
                      </div>
                    </div>
                    <!--/span-->
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">电话</label>
                        <div class="col-md-9">
                          <input type="text" name="phone" class="form-control" value="${data.phone}">
                          <span class="help-block"></span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <%--<div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">股票代码</label>
                        <div class="col-md-9">
                          <input type="text" name="stockCode" class="form-control" value="${data.stockCode}">
                        </div>
                      </div>
                    </div>--%>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">状态</label>
                        <div class="col-md-9">
                          <div class="radio-list">
                            <label class="radio-inline">
                              <input type="radio" name="status" value="VALID" <c:if test="${'VALID' eq data.status}">checked</c:if> /> 有效 </label>
                            <label class="radio-inline">
                              <input type="radio" name="status" value="INVALID" <c:if test="${'INVALID' eq data.status}">checked</c:if> /> 无效 </label>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!--/span-->
                  </div>
                  <!--/row-->
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">公司名</label>
                        <div class="col-md-9">
                          <input type="text" name="companyName" class="form-control" value="${data.companyName}">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">会员编号</label>
                        <div class="col-md-9">
                          <input type="text" name="vipCode" class="form-control" value="${data.vipCode}">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">会员开始日期</label>
                        <div class="col-md-9">
                          <div class="input-group input-medium date date-picker">
                            <input type="text" class="form-control" name="vipStartDate" value="<fmt:formatDate value="${data.vipStartDate}" type="DATE"/>" readonly/>
                              <span class="input-group-btn">
                                  <button class="btn default" type="button">
                                    <i class="fa fa-calendar"></i>
                                  </button>
                              </span>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">会员到期日期</label>
                        <div class="col-md-9">
                          <div class="input-group input-medium date date-picker">
                            <input type="text" class="form-control" name="vipEndDate" value="<fmt:formatDate value="${data.vipEndDate}" type="DATE"/>" readonly/>
                              <span class="input-group-btn">
                                  <button class="btn default" type="button">
                                    <i class="fa fa-calendar"></i>
                                  </button>
                              </span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="form-actions">
                  <div class="row">
                    <div class="col-md-6">
                      <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                          <button type="button" class="btn green" onclick="save()">保存</button>
                          <button type="button" class="btn default" onclick="history.back();">取消</button>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6"></div>
                  </div>
                </div>
                <input type="hidden" name="id" value="${data.id}">
              </form>
              <!-- END FORM-->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="/WEB-INF/common/footer.jsp" %>
<script type="text/javascript">
  function save() {
    $.ajax(_PATH + '/${MODULE_NAME}/save', {
      type: 'POST',
      data: $('form').serialize(),
      success: function(resp) {
        if(resp.statusCode === 200) {
          window.location.href = _PATH + '/${MODULE_NAME}/list';
        }
        else layer.msg('添加失败',{icon: 6});
      },
      error: function() {
        layer.msg('添加失败',{icon: 6});
      }
    });
  }
</script>
</html>