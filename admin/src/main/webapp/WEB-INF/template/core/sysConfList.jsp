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
            <span>系统管理</span>
            <i class="fa fa-angle-right"></i>
          </li>
          <li>
            <span>配置管理</span>
          </li>
        </ul>
      </div>
      <div class="note note-info">
        <p> 更改内容,将会自动保存. </p>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="portlet box blue">
            <div class="portlet-title">
              <div class="caption">
                <i class="fa fa-server"></i>文件服务器
              </div>
            </div>
            <div class="portlet-body form">
              <form class="form-horizontal" role="form" id="form_file">
                <div class="form-body">
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">主机:</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" id="host" name="host">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">端口:</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" id="port" name="port">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">账号:</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" id="username" name="username">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">密码:</label>
                        <div class="col-md-9">
                          <input type="password" class="form-control" id="password" name="password">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">目录:</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" id="root" name="root">
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="portlet box blue">
            <div class="portlet-title">
              <div class="caption">
                <i class="fa fa-envelope-o"></i>邮件服务器
              </div>
            </div>
            <div class="portlet-body form">
              <form class="form-horizontal" role="form" id="form_email">
                <div class="form-body">
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">发送服务器:</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" name="Email_Host"
                                 value="<conf:conf key="Email_Host"></conf:conf>">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">端口:</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" name="Email_Port"
                                 value="<conf:conf key="Email_Port"></conf:conf>">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">账号:</label>
                        <div class="col-md-9">
                          <input type="text" class="form-control" name="Email_Username"
                                 value="<conf:conf key="Email_Username"></conf:conf>">
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">密码:</label>
                        <div class="col-md-9">
                          <input type="password" class="form-control" name="Email_Password"
                                 value="<conf:conf key="Email_Password"></conf:conf>">
                        </div>
                      </div>
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
  $(function () {
    $.getJSON(_PATH + '/sysConf/find/ftpInfo', function (resp) {
      if (resp.statusCode === 200) {
        $('#form_file #host').val(resp.data[0]);
        $('#form_file #port').val(resp.data[1]);
        $('#form_file #username').val(resp.data[2]);
        $('#form_file #password').val(resp.data[3]);
        $('#form_file #root').val(resp.data[4]);
      }
    });
    $('#form_file input').blur(function () {
      $.ajax(_PATH + '/sysConf/saveFtpInfo', {
        type: 'POST',
        data: $('#form_file').serialize(),
        success: function (resp) {
          if (resp.statusCode !== 200) {
            $(this).parents('.form-group').addClass('has-error');
          } else {
            $(this).parents('.form-group').removeClass('has-error');
          }
        },
        error: function () {
          $(this).parents('.form-group').addClass('has-error');
        }
      })
    });
    $('#form_email input').blur(function () {
      console.log($(this).attr('name'));
      console.log($(this).val());
      $.ajax(_PATH + '/sysConf/saveVal', {
        type: 'POST',
        data: {
          confKey: $(this).attr('name'),
          value: $(this).val()
        },
        success: function (resp) {
          if (resp.statusCode !== 200) {
            $(this).parents('.form-group').addClass('has-error');
          } else {
            $(this).parents('.form-group').removeClass('has-error');
          }
        },
        error: function () {
          $(this).parents('.form-group').addClass('has-error');
        }
      })
    });
  });
</script>
</body>
</html>