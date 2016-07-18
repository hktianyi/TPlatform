<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<div class="page-bar">
  <ul class="page-breadcrumb">
    <li>
      <a href="${_PATH}/main.html">首页</a>
      <i class="fa fa-angle-right"></i>
    </li>
    <li>
      <span>会员信息</span>
    </li>
  </ul>
</div>
<div class="row">
  <div class="col-md-12">
    <div class="portlet box blue">
      <div class="portlet-title">
        <div class="caption">
          <i class="fa fa-user"></i>会员信息
        </div>
      </div>
      <div class="portlet-body form">
        <form class="form-horizontal" role="form">
          <div class="form-body">
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">用户名:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.useremail}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">称谓:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.appellation eq 1 ? '先生' : '女士'}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">姓名:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.fullname}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">状态:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.status eq 'VALID' ? '活跃' : '冻结'}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">联系电话:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.telphone}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">手机:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.mobile}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">公司名:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.company}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">职位:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.job}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">国家:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.country}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">城市:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.city}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">邮编:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.zipcode}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">创建时间:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.createTime}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">地址一:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.addressA}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">地址二:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.addressB}</p>
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
                    <%--<button type="submit" class="btn green">
                      <i class="fa fa-pencil"></i> Edit
                    </button>--%>
                    <button type="button" class="btn default" onclick="history.back()">取消</button>
                  </div>
                </div>
              </div>
              <div class="col-md-6"></div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>