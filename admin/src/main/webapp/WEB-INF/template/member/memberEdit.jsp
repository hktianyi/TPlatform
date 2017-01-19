<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="../../common/common.jsp" %>
  <link rel='stylesheet' href='${_PATH}/static/plugins/unitegallery/css/unite-gallery.css' type='text/css' />
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
            <span>客户管理</span>
          </li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="portlet box blue">
            <div class="portlet-title">
              <div class="caption">
                <i class="fa fa-tag"></i>客户详情
              </div>
            </div>
            <div class="portlet-body form">
              <form class="form-horizontal" role="form" id="memberForm">
                <input type="hidden" name="id" value="${data.id}"/>
                <div class="form-body">
                  <%--<div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">封面图片:</label>
                        <div class="col-md-8">
                          <img id="coverImg" src="${FILE_DOMAIN}${data.coverImg}" style="width: 200px; height: 150px; cursor: pointer;" alt="方案封面"/>
                          <input type="hidden" name="coverImg" value="${data.coverImg}"/>
                        </div>
                      </div>
                    </div>
                  </div>--%>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">客户名:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="username" value="${data.username}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">联系人:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="nickname" value="${data.nickname}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">职务:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="post" value="${data.post}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">性别:</label>
                        <div class="col-md-8">
                          <select class="form-control" id="gender" name="gender">
                            <option value="1" ${data.gender eq '1' ? 'selected' : ''}>男</option>
                            <option value="2" ${data.gender eq '2' ? 'selected' : ''}>女</option>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">状态:</label>
                        <div class="col-md-8">
                          <select class="form-control" name="status">
                            <option value="1" ${data.status eq '1' ? 'selected' : ''}>正常</option>
                            <option value="2" ${data.status eq '2' ? 'selected' : ''}>锁定</option>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">VIP等级:</label>
                        <div class="col-md-8">
                          <select class="form-control" name="vipLevel">
                            <option value="1" ${data.vipLevel eq '1' ? 'selected' : ''}>A类客户</option>
                            <option value="2" ${data.vipLevel eq '2' ? 'selected' : ''}>B类客户</option>
                            <option value="3" ${data.vipLevel eq '3' ? 'selected' : ''}>C类客户</option>
                            <option value="4" ${data.vipLevel eq '4' ? 'selected' : ''}>VIP客户</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">手机:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="mobile" value="${data.mobile}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">电话:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="phone" value="${data.phone}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">电子邮件:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="email" value="${data.email}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">QQ:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="qq" value="${data.qq}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">微信:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="wechat" value="${data.wechat}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">来源:</label>
                        <div class="col-md-8">
                          <select class="form-control" name="source">
                            <option value="1" ${data.source eq '1' ? 'selected' : ''}>网站</option>
                            <option value="2" ${data.source eq '2' ? 'selected' : ''}>同行推荐</option>
                            <option value="4" ${data.source eq '4' ? 'selected' : ''}>婚礼堂</option>
                            <option value="5" ${data.source eq '5' ? 'selected' : ''}>QQ群</option>
                            <option value="6" ${data.source eq '6' ? 'selected' : ''}>微信群</option>
                            <option value="3" ${data.source eq '3' ? 'selected' : ''}>其他</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                  <%--<div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">推荐人:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="referrer" value="${data.referrer}"/>
                        </div>
                      </div>
                    </div>
                  </div>--%>
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
  $('#memberForm').validate();
</script>
</body>
</html>