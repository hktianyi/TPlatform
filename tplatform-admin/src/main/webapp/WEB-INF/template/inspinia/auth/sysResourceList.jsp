<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <%@include file="../common/common.jsp" %>
  <link href="http://cdn.bootcss.com/jquery-treetable/3.2.0/css/jquery.treetable.min.css" rel="stylesheet">
  <link href="http://cdn.bootcss.com/jquery-treetable/3.2.0/css/jquery.treetable.theme.default.min.css" rel="stylesheet">
  <link href="http://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.min.css" rel="stylesheet">
</head>
<body class="mini-navbar fixed-sidebar">
<div id="wrapper">
  <div id="page-wrapper" class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
      <div class="row">
        <div class="col-lg-12">
          <div class="ibox float-e-margins">
            <div class="ibox-title">
              <h5>资源管理</h5>
              <div id="roleBtns" data-toggle="buttons-radio" class="btn-group" style="margin-left: 30px;">
                <button class="btn btn-primary btn-xs active" type="button"><i class="fa fa-group"></i> 全部</button>
                <c:forEach items="${roles}" var="role">
                  <button class="btn btn-primary btn-xs" type="button" data-role-id="${role.role}"><i class="fa fa-${role.icon}"></i> ${role.name}</button>
                </c:forEach>
              </div>
            </div>
            <div class="ibox-content">
              <div class="table-responsive" style="overflow-x: hidden">
                <table class="table table-striped table-bordered table-hover dataTables-example">
                  <thead>
                  <th>名称</th>
                  <th>状态</th>
                  <th>类型</th>
                  <th>排序号</th>
                  <th>操作</th>
                  </thead>
                  <tbody>
                  <tr data-tt-id="0" data-tt-parent-id="" data-tt-branch="true" id="topTr">
                    <td colspan="5"><span><i class="fa fa-gear"></i> 资源菜单/按钮</span></td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="../common/footer.jsp" %>
</div>
<script src="http://cdn.bootcss.com/jquery-treetable/3.2.0/jquery.treetable.js"></script>
<script src="http://cdn.bootcss.com/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript">
    var treeGrid;
    $(function () {
        treeGrid = $('table').treetable({
            expandable: true,
            clickableNodeNames: true,
            onNodeCollapse: function () {
                treeGrid.treetable("unloadBranch", this);
            },
            onNodeExpand: function () {
                loader(this);
            },
            onInitialized: function () {
                loader(null, $('table'));
            }
        }).on("mousedown", "tr", function () {
            $(".selected").not(this).removeClass("selected");
            $(this).toggleClass("selected");
        });

        // 高亮选中行
        $(document).on("mousedown", "#tree tbody tr", function () {
            $("tr.selected").removeClass("selected");
            $(this).addClass("selected");
        });
    });
    function loader(node, table) {
        $.ajax({
            async: false, // Must be false, otherwise loadBranch happens after showChildren?
            url: _MODULE_NAME + "/loadTree/treetable?pid=" + (node ? node.id : 0)
        }).done(function (resp) {
            var rows = $.map(resp, function (data) {
                var tr = $('<tr/>', {
                    'data-tt-id': data.id,
                    'data-tt-parent-id': data.pid,
                    'data-tt-branch': true,
                    html: '<td><span><i class="fa fa-' + data.icon + '"/> ' + data.name + '</span></td>' +
                    '<td><i ' + (data.status === 1 ? 'class="fa fa-smile-o" style="color:green"' : 'class="fa fa-frown-o" style="color:red"') + '/></td>' +
                    '<td>' + (data.type === 'MENU' ? '菜单' : '按钮') + '</td>' +
                    '<td>' + data.sort + '</td>' +
                    '<td><div class="btn-group btn-group-xs btn-group-solid">' +
                    '<button onclick="edit(\'' + data.id + '\');" type="button" class="btn blue" title="修改"><i class="fa fa-edit"></i> 修改</button>' +
                    '<button onclick="setTop(\'' + data.id + '\');" type="button" class="btn red" title="移到一级节点"><i class="fa fa-edit"></i> 移到一级节点</button>' +
                    '<button onclick="del(\'' + data.id + '\');" type="button" class="btn red" title="删除"><i class="fa fa-trash"></i> 删除</button>' +
                    '</div></td>'
                });
                tr.draggable({
                    helper: "clone",
                    opacity: .75,
                    refreshPositions: true,
                    revert: "invalid",
                    revertDuration: 300,
                    scroll: true
                }).droppable({
                    accept: "tr",
                    drop: function (e, ui) {
                        var nodeId = ui.draggable.data("ttId"), pid = $(this).data("ttId");
                        $.post(_MODULE_NAME + '/updatePid', {id: nodeId, pid: pid}, function (resp) {
                            if (resp.statusCode === 200) {
                                $('table').treetable("move", nodeId, pid);
                            } else {
                                alert('拖拽更新服务器失败');
                            }
                        });
                    },
                    hoverClass: "accept",
                    over: function (e, ui) {
                        if (this != ui.draggable[0] && !$(this).is(".expanded")) {
                            $('table').treetable("expandNode", $(this).data("ttId"));
                        }
                    }
                });
                return tr[0];
            });
            (table || treeGrid).treetable("loadBranch", node, rows);
        });
    }
    function setTop(nodeId) {
        $.post(_MODULE_NAME + '/updatePid', {id: nodeId, pid: 0}, function (resp) {
            if (resp.statusCode === 200) {
                $('table').treetable("move", nodeId, 0);
            } else {
                alert('移到一级节点失败');
            }
        });
    }
</script>
</body>
</html>