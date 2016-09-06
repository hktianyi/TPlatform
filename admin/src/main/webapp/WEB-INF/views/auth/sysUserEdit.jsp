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
      <div class="row">
        <div class="col-md-12">
          <!-- BEGIN FORM-->
          <form action="#" class="form-horizontal form-bordered">
            <div class="form-body">
              <div class="form-group">
                <label class="control-label col-md-3">First Name</label>
                <div class="col-md-9">
                  <input type="text" placeholder="small" class="form-control"/>
                  <span class="help-block"> This is inline help </span>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-md-3">Last Name</label>
                <div class="col-md-9">
                  <input type="text" placeholder="medium" class="form-control"/>
                  <span class="help-block"> This is inline help </span>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-md-3">Gender</label>
                <div class="col-md-9">
                  <select class="form-control">
                    <option value="">Male</option>
                    <option value="">Female</option>
                  </select>
                  <span class="help-block"> Select your gender. </span>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-md-3">Date of Birth</label>
                <div class="col-md-9">
                  <input type="text" class="form-control" placeholder="dd/mm/yyyy"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-md-3">Category</label>
                <div class="col-md-9">
                  <select class="form-control">
                    <option value="Category 1">Category 1</option>
                    <option value="Category 2">Category 2</option>
                    <option value="Category 3">Category 5</option>
                    <option value="Category 4">Category 4</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-md-3">Multi-Value Select</label>
                <div class="col-md-9">
                  <select class="form-control" multiple>
                    <optgroup label="NFC EAST">
                      <option>Dallas Cowboys</option>
                      <option>New York Giants</option>
                      <option>Philadelphia Eagles</option>
                      <option>Washington Redskins</option>
                    </optgroup>
                    <optgroup label="NFC NORTH">
                      <option>Chicago Bears</option>
                      <option>Detroit Lions</option>
                      <option>Green Bay Packers</option>
                      <option>Minnesota Vikings</option>
                    </optgroup>
                    <optgroup label="NFC SOUTH">
                      <option>Atlanta Falcons</option>
                      <option>Carolina Panthers</option>
                      <option>New Orleans Saints</option>
                      <option>Tampa Bay Buccaneers</option>
                    </optgroup>
                    <optgroup label="NFC WEST">
                      <option>Arizona Cardinals</option>
                      <option>St. Louis Rams</option>
                      <option>San Francisco 49ers</option>
                      <option>Seattle Seahawks</option>
                    </optgroup>
                    <optgroup label="AFC EAST">
                      <option>Buffalo Bills</option>
                      <option>Miami Dolphins</option>
                      <option>New England Patriots</option>
                      <option>New York Jets</option>
                    </optgroup>
                    <optgroup label="AFC NORTH">
                      <option>Baltimore Ravens</option>
                      <option>Cincinnati Bengals</option>
                      <option>Cleveland Browns</option>
                      <option>Pittsburgh Steelers</option>
                    </optgroup>
                    <optgroup label="AFC SOUTH">
                      <option>Houston Texans</option>
                      <option>Indianapolis Colts</option>
                      <option>Jacksonville Jaguars</option>
                      <option>Tennessee Titans</option>
                    </optgroup>
                    <optgroup label="AFC WEST">
                      <option>Denver Broncos</option>
                      <option>Kansas City Chiefs</option>
                      <option>Oakland Raiders</option>
                      <option>San Diego Chargers</option>
                    </optgroup>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-md-3">Membership</label>
                <div class="col-md-9">
                  <div class="radio-list">
                    <label>
                      <input type="radio" name="optionsRadios2" value="option1"/> Free </label>
                    <label>
                      <input type="radio" name="optionsRadios2" value="option2" checked/> Professional </label>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-md-3">Street</label>
                <div class="col-md-9">
                  <input type="text" class="form-control"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-md-3">City</label>
                <div class="col-md-9">
                  <input type="text" class="form-control"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-md-3">State</label>
                <div class="col-md-9">
                  <input type="text" class="form-control"></div>
              </div>
              <div class="form-group">
                <label class="control-label col-md-3">Post Code</label>
                <div class="col-md-9">
                  <input type="text" class="form-control"></div>
              </div>
              <div class="form-group last">
                <label class="control-label col-md-3">Country</label>
                <div class="col-md-9">
                  <select class="form-control"> </select>
                </div>
              </div>
            </div>
            <div class="form-actions">
              <div class="row">
                <div class="col-md-offset-3 col-md-9">
                  <button type="submit" class="btn green">
                    <i class="fa fa-check"></i> Submit
                  </button>
                  <button type="button" class="btn default">Cancel</button>
                </div>
              </div>
            </div>
          </form>
          <!-- END FORM-->

          <%--<div class="portlet box purple">
            <div class="portlet-title">
              <div class="caption"><i class="fa fa-gift"></i> 用户信息</div>
            </div>
            <div class="portlet-body">
              <form role="form" id="editForm">
                <div class="form-body">
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-btn"><button class="btn" type="button">头像</button></span>
                      <input type="text" class="form-control" name="avatarUrl" value="${data.avatarUrl}">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-btn"><button class="btn" type="button">昵称</button></span>
                      <input type="text" class="form-control" name="nickname" value="${data.nickname}">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-btn"><button class="btn" type="button">用户名</button></span>
                      <input type="text" class="form-control" name="username" value="${data.username}">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-btn"><button class="btn" type="button">密码</button></span>
                      <input type="text" class="form-control" name="password" value="${data.password}">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-btn"><button class="btn" type="button">手机号</button></span>
                      <input type="text" class="form-control" name="mobile" value="${data.mobile}">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-btn"><button class="btn" type="button">邮箱</button></span>
                      <input type="text" class="form-control" name="email" value="${data.email}">
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="input-group">
                      <span class="input-group-btn"><button class="btn" type="button">状态</button></span>
                      <select class="form-control" name="status">
                        <option value="VALID"
                                <c:if test="${data.status eq 'VALID'}">selected</c:if> >有效
                        </option>
                        <option value="INVALID"
                                <c:if test="${data.status eq 'INVALID'}">selected</c:if> >无效
                        </option>
                      </select>
                    </div>
                  </div>
                </div>
                <input type="hidden" name="id" value="${data.id}">
                <div class="form-actions right1">
                  <button type="button" class="btn green" onclick="save()">保存</button>
                  <button type="button" class="btn default">取消</button>
                </div>
              </form>
            </div>
          </div>--%>
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
  function save() {
    $.ajax(_PATH + '/${MODULE_NAME}/save', {
      type: 'POST',
      data: $('#editForm').serialize(),
      success: function (resp) {
        if (resp.statusCode === 200) {
          parent.window.location.reload();
          parent.layer.msg('添加成功', {icon: 6, time: 1000});
          parent.layer.close(parent.layer.getFrameIndex(window.name));
        }
        else parent.layer.msg('添加失败', {icon: 6});
      },
      error: function () {
        parent.layer.msg('添加失败', {icon: 6});
      }
    });
  }
</script>
</body>
</html>