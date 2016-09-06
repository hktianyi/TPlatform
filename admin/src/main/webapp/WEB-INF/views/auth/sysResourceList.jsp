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
            <a href="#">权限管理</a>
            <i class="fa fa-angle-right"></i>
          </li>
          <li>
            <span>菜单管理</span>
          </li>
        </ul>
        <div class="page-toolbar">
          <button type="button" class="btn blue btn-outline"><i class="fa fa-plus"></i> 添加</button>
        </div>
      </div>
      <div class="row" style="margin-top: 6px;">
        <div class="col-md-12">
          <table id="treeGrid" class="easyui-treegrid"></table>
          <div id="rightKey" class="easyui-menu hide" style="width:120px;">
            <div onclick="curdTree('append')" data-options="iconCls:'icon-add'">添加同级</div>
            <div onclick="curdTree('append', 'sub')" data-options="iconCls:'icon-add'">添加下级</div>
            <div onclick="curdTree('update')" data-options="iconCls:'icon-edit'">修改</div>
            <div onclick="curdTree('remove')" data-options="iconCls:'icon-remove'">删除</div>
            <div class="menu-sep"></div>
            <div onclick="curdTree('expand')">展开</div>
            <div onclick="curdTree('collapse')">折叠</div>
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
  var treeGrid;
  $(function () {
    treeGrid = $('#treeGrid').treegrid({
      loader: function (param, success, error) {
        $.getJSON(_PATH + '/${MODULE_NAME}/loadTree', param, function (resp) {
          resp.data.forEach(function (item) {
            item.createTime = moment(item.createTime).format('YYYY-M-D H:m');
            item.status = 'VALID' === item.status ? '有效' : '无效';
            item.iconCls = 'fa fa-' + item.icon;
            if (!item.action) item.state = 'closed';
          });
          success(resp.data);
        });
      },
      idField: 'id',
      treeField: 'name',
      lines: true,
      columns: [[
        {field: 'id', title: '编码', width: '10%'},
        {field: 'name', title: '名称', width: '20%'},
        {field: 'action', title: '路径', width: '30%'},
        {field: 'status', title: '状态', width: '10%'},
        {field: 'sort', title: '排序号', width: '10%'},
        {field: 'createTime', title: '创建时间', width: '10%'}
      ]],
      onLoadSuccess: function (row) {
        treeGrid.treegrid('enableDnd');
        $('#rightKey').removeClass('hide');
      },
      onBeforeDrop: function (targetRow, sourceRow) {
        var pid = targetRow && targetRow.id;
        treeGrid.treegrid('expand', pid);
        $.post(_PATH + '/${MODULE_NAME}/updatePid', {pid: pid, id: sourceRow.id});
      },
      onContextMenu: function (e, row) {
        if (row) {
          e.preventDefault();
          $(this).treegrid('select', row.id);
          $('#rightKey').menu('show', {
            left: e.pageX,
            top: e.pageY
          });
        }
      }
    });
  });
  function curdTree(oper, level) {
    var layerIndex;
    var node = treeGrid.treegrid('getSelected');
    if (!node) return;
    switch (oper) {
      case 'append':
        if (level === 'sub') {
          window.sessionStorage.setItem("pid", node.id);
        } else {
          window.sessionStorage.setItem("pid", node.pid);
        }
        edit();
        break;
      case 'remove':
        layerIndex = layer.confirm('确认删除？', {
          btn: ['同步', '取消'] //按钮
        }, function () {
          $.ajax(_PATH + '/sysResource/delete/' + node.id, {
            type: 'DELETE',
            success: function (resp) {
              layer.msg(resp.data);
              treeGrid.treegrid(oper, node.id);
            },
            complete: function (jqXHR, textStatus) {
              layer.msg("删除失败！");
              layer.close(layerIndex);
            }
          });
        });
        break;
      case 'update':
        edit(node.id);
        break;
      default:
        treeGrid.treegrid(oper, node.id);
        break;
    }
  }
</script>
</body>
</html>