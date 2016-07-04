<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" language="java" %>
      <div class="row">
        <div class="col-md-12">
          <div class="portlet light bordered">
            <%--<div class="portlet-title">
              <div class="caption">
                <i class="icon-equalizer font-red-sunglo"></i>
                <span class="caption-subject font-red-sunglo bold uppercase">${data.company}上市信息</span>
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
                          <input type="text" name="username" class="form-control" value="${data.useremail}">
                          <span class="help-block"></span>
                        </div>
                      </div>
                    </div>
                    <!--/span-->
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">称谓</label>
                        <div class="col-md-9">
                          <input type="text" name="appellation" class="form-control" value="${data.appellation}">
                          <span class="help-block"></span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">姓名</label>
                        <div class="col-md-9">
                          <input type="text" name="fullname" class="form-control" value="${data.fullname}">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">状态</label>
                        <div class="col-md-9">
                          <div class="radio-list">
                            <label class="radio-inline">
                              <input type="radio" name="status" value="VALID" <c:if test="${'VALID' eq data.status}">checked</c:if> /> 激活 </label>
                            <label class="radio-inline">
                              <input type="radio" name="status" value="INVALID" <c:if test="${'INVALID' eq data.status}">checked</c:if> /> 未激活 </label>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">联系电话</label>
                        <div class="col-md-9">
                          <input type="text" name="telphone" class="form-control" value="${data.telphone}">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">手机</label>
                        <div class="col-md-9">
                          <input type="text" name="mobile" class="form-control" value="${data.mobile}">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">公司名</label>
                        <div class="col-md-9">
                          <input type="text" name="companyName" class="form-control" value="${data.company}">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">职位</label>
                        <div class="col-md-9">
                          <input type="text" name="job" class="form-control" value="${data.job}">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">地址一</label>
                        <div class="col-md-9">
                          <input type="text" name="addressA" class="form-control" value="${data.addressA}">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">地址二</label>
                        <div class="col-md-9">
                          <input type="text" name="addressB" class="form-control" value="${data.addressB}">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">国家</label>
                        <div class="col-md-9">
                          <input type="text" name="country" class="form-control" value="${data.country}">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">城市</label>
                        <div class="col-md-9">
                          <input type="text" name="city" class="form-control" value="${data.city}">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">邮编</label>
                        <div class="col-md-9">
                          <input type="text" name="zipcode" class="form-control" value="${data.zipcode}">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">创建时间</label>
                        <div class="col-md-9">
                          <div class="input-group input-medium date date-picker">
                            <input type="text" class="form-control" value="${data.createTime}" readonly/>
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
                          <%--<button type="button" class="btn green" onclick="save()">保存</button>--%>
                          <button type="button" class="btn default" onclick="history.back();">返回</button>
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