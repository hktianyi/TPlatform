<%@ taglib prefix="conf" uri="/taglib/conf.tld" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="/WEB-INF/common/common.jsp" %>
  <link href="${_PATH}/static/pages/user/css/profile.min.css" rel="stylesheet" type="text/css"/>
</head>
<!-- END HEAD -->
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-full-width">
<%@include file="/WEB-INF/common/header.jsp" %>
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
            <span>账户</span>
          </li>
        </ul>
        <div class="page-toolbar">
          <button type="button" class="btn btn-circle blue btn-outline btn-xs">&nbsp;?&nbsp;</button>
        </div>
      </div>
      <h3 class="page-title"> 账户配置信息 </h3>
      <!-- END PAGE TITLE-->
      <!-- END PAGE HEADER-->
      <div class="row">
        <div class="col-md-12">
          <!-- BEGIN PROFILE SIDEBAR -->
          <div class="profile-sidebar">
            <!-- PORTLET MAIN -->
            <div class="portlet light profile-sidebar-portlet ">
              <!-- SIDEBAR USERPIC -->
              <div class="profile-userpic">
                <img src="${_PATH}/static/pages/user/img/${_USER.avatarUrl}.jpg" class="img-responsive" alt=""></div>
              <div class="profile-usertitle">
                <div class="profile-usertitle-name"> ${_USER.nickname} </div>
                <div class="profile-usertitle-job"> ${_USER.username} </div>
              </div>
              <%--<div class="profile-userbuttons">
                <button type="button" class="btn btn-circle green btn-sm">Follow</button>
                <button type="button" class="btn btn-circle red btn-sm">Message</button>
              </div>--%>
              <div class="profile-usermenu">
                <ul class="nav">
                  <%--<li>
                    <a href="page_user_profile_1.html">
                      <i class="icon-home"></i> 概览 </a>
                  </li>--%>
                  <li class="active">
                    <a href="${_PATH}/sysUser/profile">
                      <i class="icon-settings"></i> 账户配置 </a>
                  </li>
                  <%--<li>
                    <a href="page_user_profile_1_help.html">
                      <i class="icon-info"></i> 帮助 </a>
                  </li>--%>
                </ul>
              </div>
            </div>
            <%--<div class="portlet light ">
              <div class="row list-separated profile-stat">
                <div class="col-md-4 col-sm-4 col-xs-6">
                  <div class="uppercase profile-stat-title"> 37</div>
                  <div class="uppercase profile-stat-text"> Projects</div>
                </div>
                <div class="col-md-4 col-sm-4 col-xs-6">
                  <div class="uppercase profile-stat-title"> 51</div>
                  <div class="uppercase profile-stat-text"> Tasks</div>
                </div>
                <div class="col-md-4 col-sm-4 col-xs-6">
                  <div class="uppercase profile-stat-title"> 61</div>
                  <div class="uppercase profile-stat-text"> Uploads</div>
                </div>
              </div>
              <div>
                <h4 class="profile-desc-title">About Marcus Doe</h4>
                <span class="profile-desc-text"> Lorem ipsum dolor sit amet diam nonummy nibh dolore. </span>
                <div class="margin-top-20 profile-desc-link">
                  <i class="fa fa-globe"></i>
                  <a href="http://www.keenthemes.com">www.keenthemes.com</a>
                </div>
                <div class="margin-top-20 profile-desc-link">
                  <i class="fa fa-twitter"></i>
                  <a href="http://www.twitter.com/keenthemes/">@keenthemes</a>
                </div>
                <div class="margin-top-20 profile-desc-link">
                  <i class="fa fa-facebook"></i>
                  <a href="http://www.facebook.com/keenthemes/">keenthemes</a>
                </div>
              </div>
            </div>--%>
          </div>
          <div class="profile-content">
            <div class="row">
              <div class="col-md-12">
                <div class="portlet light ">
                  <div class="portlet-title tabbable-line">
                    <div class="caption caption-md">
                      <i class="icon-globe theme-font hide"></i>
                      <span class="caption-subject font-blue-madison bold uppercase">账户</span>
                    </div>
                    <ul class="nav nav-tabs">
                      <li class="active">
                        <a href="#tab_account" data-toggle="tab">个人信息</a>
                      </li>
                      <li>
                        <a href="#tab_pwd" data-toggle="tab">修改密码</a>
                      </li>
                    </ul>
                  </div>
                  <div class="portlet-body">
                    <div class="tab-content">
                      <!-- PERSONAL INFO TAB -->
                      <div class="tab-pane active" id="tab_account">
                        <form role="form">
                          <div class="form-group">
                            <label class="control-label">用户名</label>
                            <input type="text" class="form-control" name="username" value="${_USER.username}"
                                   disabled="disabled"/></div>
                          <div class="form-group">
                            <label class="control-label">昵称</label>
                            <input type="text" placeholder="请填写昵称" class="form-control" name="nickname"
                                   value="${_USER.nickname}"/></div>
                          <div class="form-group">
                            <label class="control-label">手机</label>
                            <input type="text" placeholder="请填写手机号" class="form-control" name="mobile"
                                   value="${_USER.mobile}"/></div>
                          <div class="form-group">
                            <label class="control-label">邮箱</label>
                            <input type="text" placeholder="请填写电子邮箱" class="form-control" name="email"
                                   value="${_USER.email}"/>
                          </div>
                          <div class="form-group">
                            <label class="control-label">状态</label>
                            <select class="form-control" name="status">
                              <option value="1"
                                      <c:if test="${_USER.status eq '1'}">SELECTED</c:if> >激活
                              </option>
                              <option value="0"
                                      <c:if test="${_USER.status eq '0'}">SELECTED</c:if> >冻结
                              </option>
                            </select>
                          </div>
                          <div class="form-group">
                            <label class="control-label">加入时间</label>
                            <input type="text" class="form-control" name="createTime" value="${_USER.createTime}"
                                   disabled="disabled"/>
                          </div>
                          <div class="margiv-top-10">
                            <button type="submit" class="btn green"> 修改</button>
                            <button type="button" class="btn default" onclick="history.back();"> 取消</button>
                          </div>
                        </form>
                      </div>
                      <div class="tab-pane" id="tab_pwd">
                        <form role="form">
                          <div class="form-group">
                            <label class="control-label">当前密码</label>
                            <input type="password" class="form-control" name="oldPwd"/></div>
                          <div class="form-group">
                            <label class="control-label">新密码</label>
                            <input type="password" class="form-control" name="newPwd"/></div>
                          <div class="form-group">
                            <label class="control-label">确认密码</label>
                            <input type="password" class="form-control" name="renewPwd"/></div>
                          <div class="margin-top-10">
                            <button type="submit" class="btn green"> 修改</button>
                            <button type="button" class="btn default" onclick="history.back();"> 取消</button>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- END PROFILE CONTENT -->
        </div>
      </div>
    </div>
    <!-- END CONTENT -->
  </div>
  <!-- END CONTAINER -->
  <%@include file="/WEB-INF/common/footer.jsp" %>

  <script type="text/javascript">
    $(function () {
      $('body').addClass('page-container-bg-solid page-sidebar-closed');
      $('ul.page-sidebar-menu').addClass('page-sidebar-menu-closed');

      $('#tab_pwd > form').validate({
        onfocusout: function (element) {
          $(element).valid();
        },
        submitHandler: function (form) {
          $.post(_PATH + '/sysUser/updatePwd', $(form).serialize(), function (resp) {
            if (resp.statusCode === 200) {
              alert('修改成功，请重新登录', function () {
                window.location.reload();
              });
            } else {
              alert(resp.errorInfo || '修改失败');
            }
          });
        },
        rules: {
          oldPwd: {
            required: true,
            remote: _PATH + '/sysUser/checkOldPwd'
          },
          newPwd: {
            required: true,
            rangelength: [6, 20]
          },
          renewPwd: {
            equalTo: 'input[name="newPwd"]'
          }
        },
        messages: {
          oldPwd: {
            required: '请输入当前密码',
            remote: '当前密码输入错误'
          },
          newPwd: {
            required: '请输入新密码',
            rangelength: $.validator.format("新密码长度必须介于 {0} 到 {1} 之间。"),
          },
          renewPwd: {
            equalTo: '两次密码输入不一致'
          }
        }
      });
      $('#tab_account > form').validate({
        onfocusout: function (element) {
          $(element).valid();
        },
        submitHandler: function (form) {
          $.post(_PATH + '/sysUser/updateAccount', $(form).serialize(), function (resp) {
            if (resp.statusCode === 200) {
              alert('修改成功');
            } else {
              alert(resp.errorInfo || '修改失败');
            }
          });
        },
        rules: {
          nickname: {
            required: true,
            rangelength: [1, 10]
          },
          mobile: {
            required: true,
            digits: true
          },
          email: {
            required: true,
            email: true
          }
        },
        messages: {
          nickname: {
            rangelength: $.validator.format("昵称长度必须介于 {0} 到 {1} 之间。"),
          },
          mobile: {
            required: '请输入电话号码',
            digits: '电话号码只能为数字',
          },
          email: {
            required: '请输入邮箱地址',
            email: '邮箱格式输入错误'
          }
        }
      });
    });
  </script>
</body>
</html>