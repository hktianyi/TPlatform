<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="/WEB-INF/common/common.jsp" %>
  <link href="${_PATH}/static/plugins/jstree/themes/default/style.min.css" rel="stylesheet" type="text/css"/>
</head>
<!-- END HEAD -->
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-full-width">
<!-- BEGIN CONTAINER -->
<div class="page-container">
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content">
      <form role="form" id="editForm" class="margin-bottom-40">
        <div class="row">
          <div class="col-sm-8">
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group form-md-line-input">
                  <div class="input-icon">
                    <input type="text" class="form-control" id="name" name="name" value="${data.name}" placeholder="名称">
                    <i class="fa fa-edit"></i>
                    <span class="help-block"></span>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group form-md-line-input">
                  <div class="input-icon">
                    <input type="text" class="form-control" id="action" name="action" value="${data.action}"
                           placeholder="URL">
                    <i class="fa fa-internet-explorer"></i>
                    <span class="help-block"></span>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group form-md-line-input">
                  <div class="input-icon">
                    <input type="text" class="form-control" id="sort" name="sort" value="${data.sort}"
                           placeholder="排序号">
                    <i class="fa fa-sort-numeric-asc"></i>
                    <span class="help-block"></span>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <div class="form-group form-md-line-input">
                  <label class="col-sm-2 control-label">角色: </label>
                  <div class="col-sm-10">
                    <div class="md-checkbox-inline">
                      <c:forEach items="${roles}" var="role">
                        <div class="md-checkbox">
                          <input type="checkbox" id="role${role.id}" name="role[]" class="md-check" value="${role.id}"
                          <c:forEach items="${data.roles}" var="r">
                                 <c:if test="${r.id eq role.id}">checked</c:if>
                          </c:forEach>
                          >
                          <label for="role${role.id}">
                            <span></span>
                            <span class="check"></span>
                            <span class="box"></span> ${role.name} </label>
                        </div>
                      </c:forEach>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-6">
                <div class="form-group form-md-line-input">
                  <label class="col-sm-4 control-label">图标: </label>
                  <div class="col-sm-8">
                    <input type="hidden" id="icon" name="icon" value="${empty data.icon ? 'tag' : data.icon}">
                    <label for="icon"><i class="fa fa-${empty data.icon ? 'tag' : data.icon}"></i></label>
                  </div>
                </div>
              </div>
              <div class="col-sm-6">
                <div class="form-group form-md-line-input">
                  <label class="col-sm-4 control-label">状态: </label>
                  <div class="col-sm-8">
                    <input type="checkbox" id="status" value="VALID" name="status"
                           class="make-switch switch-small" ${data.status eq 'VALID' ? 'checked' : ''}
                           data-on-text="<i class='fa fa-check'></i>" data-on-color="info"
                           data-off-text="<i class='fa fa-times'></i>" data-off-color="danger"/>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-sm-4">
            <div class="form-group form-md-line-input">
              <label class="control-label">上级菜单</label>
              <div id="resourceTree"></div>
            </div>
          </div>
        </div>
        <input type="hidden" name="id" value="${data.id}"/>
        <input type="hidden" id="pid" name="pid" value="${data.pid}"/>
      </form>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="/WEB-INF/common/footer.jsp" %>
<script src="${_PATH}/static/plugins/jstree/jstree.min.js" type="text/javascript"></script>
<script type="text/javascript">
  $(function () {
    $('#resourceTree').on('select_node.jstree', function (e, data) {
      $('#pid').val(data.node.id);
    }).jstree({
      'core': {
        'data': {
          url: _MODULE_NAME + '/loadTree/jstree?selectedIds=${data.pid}',
          dataType: 'json',
          data: function (node) {
            return {'pid': node.id === '#' ? '0' : node.id};
          }
        }
      }
    });

    $('label[for="icon"]').on('click', function () {
      parent.layer.full(parent.layer.open({
        type: 2,
        content: _MODULE_NAME + '/icons',
        area: ['800px', '500px'],
        maxmin: true,
        success: function (layero, index) {
          window.parent[layero.find('iframe')[0]['name']].layerIndex = parent.layer.getFrameIndex(window.name);
        },
        end: function() {
          var icon = sessionStorage.getItem('icon_name');
          alert(icon);
          if(icon) {
            $('#icon').val(icon);
            $('label[for="icon"] i').attr('class', 'fa fa-'+icon);
          }
        }
      }));
    });
  });

  function save() {
    $.ajax(_MODULE_NAME + '/saveWithRole', {
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