<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="../../common/common.jsp" %>
  <link href="${_PATH}/static/plugins/jquery-treetable/jquery.treetable.css" rel="stylesheet" type="text/css" />
  <link href="${_PATH}/static/plugins/jquery-treetable/jquery.treetable.theme.default.css" rel="stylesheet" type="text/css" />
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
    <table id="treeGrid" class="table table-striped table-bordered table-hover table-header-fixed">
      <thead>
      <th>名称</th>
      <th>状态</th>
      <th>排序号</th>
      <th>操作</th>
      </thead>
    </table>
    <%--<div id="rightKey" class="easyui-menu hide" style="width:120px;">
      <div onclick="curdTree('append')" data-options="iconCls:'icon-add'">添加同级</div>
      <div onclick="curdTree('append', 'sub')" data-options="iconCls:'icon-add'">添加下级</div>
      <div onclick="curdTree('update')" data-options="iconCls:'icon-edit'">修改</div>
      <div onclick="curdTree('remove')" data-options="iconCls:'icon-remove'">删除</div>
      <div class="menu-sep"></div>
      <div onclick="curdTree('expand')">展开</div>
      <div onclick="curdTree('collapse')">折叠</div>
    </div>--%>
  </div>
</div>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="../../common/footer.jsp" %>
<script src="${_PATH}/static/plugins/jquery-treetable/jquery.treetable.js" type="text/javascript"></script>
<script type="text/javascript">
  var treeGrid;
  $(function () {
    treeGrid = $('#treeGrid').treetable({
      expandable: true,
      onNodeCollapse: function() {
        var node = this;
        treeGrid.treetable("unloadBranch", node);
      },
      onNodeExpand: function() {
        loader(this);
      },
      onInitialized: function () {
        loader(null, $('#treeGrid'));
      }
    });
    function loader(node, table) {
      $.ajax({
        async: false, // Must be false, otherwise loadBranch happens after showChildren?
        url: _MODULE_NAME + "/loadTree?id=" + (node?node.id:'')
      }).done(function(resp) {
        var rows = $.map(resp.data, function (data) {
          return $('<tr/>', {
            'data-tt-id': data.id,
            'data-tt-parent-id': data.pid,
            'data-tt-branch': true,
            html: '<td><i class="fa fa-'+data.icon+'"/> '+data.name+'</td>' +
            '<td>'+data.status+'</td>' +
            '<td>'+data.sort+'</td>' +
            '<td>'+data.id+'</td>'
          })[0];
        });
        (table||treeGrid).treetable("loadBranch", node, rows);
      });
    }
    // Highlight selected row
    $(document).on("mousedown", "#tree tbody tr", function() {
      $("tr.selected").removeClass("selected");
      $(this).addClass("selected");
    });
    // Drag & Drop Example Code
    $(document).on("mouseenter", "#tree .file, #tree .directory", function() {
      var el = $(this);
      if(!el.data("dndInit")) {
        el.data("dndInit", true);
        el.draggable({
          helper: "clone",
          opacity: .75,
          refreshPositions: true, // Performance?
          revert: "invalid",
          revertDuration: 300,
          scroll: true
        });
      }
    });
    $("#tree .directory").parents("tr").each(function() {
      droppableSetup.apply(this);
    });
  });
  function droppableSetup() {
    $(this).droppable({
      accept: ".file, .directory",
      drop: function(e, ui) {
        var droppedEl, node;
        droppedEl = ui.draggable.parents("tr");
        node = $("#tree").treetable("node", droppedEl.data("ttId"));
        $("#tree").treetable("move", node.id, $(this).data("ttId"));
        // Update server-side tree
        $.ajax({
          data: { node: { parent_id: node.parentId } },
          type: "PUT",
          url: "/nodes/" + node.id
        });
      },
      hoverClass: "accept",
      over: function(e, ui) {
        var droppedEl = ui.draggable.parents("tr");
        if(this != droppedEl[0] && !$(this).is(".expanded")) {
          $("#tree").treetable("expandNode", $(this).data("ttId"));
        }
      }
    });
  }
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