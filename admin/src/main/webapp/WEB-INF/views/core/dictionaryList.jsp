<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" language="java" %>
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
      <span>字典管理</span>
    </li>
  </ul>
</div>
<div class="row">
  <div class="col-md-4">
    <div class="portlet light portlet-fit full-height-content full-height-content-scrollable bordered">
      <div class="portlet-title">
        <div class="caption">
          <i class=" icon-layers font-green"></i>
          <span class="caption-subject font-green bold uppercase">字典分类</span>
        </div>
        <div class="actions">
          <a href="javascript:editDictionaryType();" class="btn btn-circle btn-default btn-sm">
            <i class="fa fa-plus"></i> 添加 </a>
        </div>
      </div>
      <div class="portlet-body">
        <div class="full-height-content-body">
          <ul class="list-group">
            <c:forEach items="${dictionaryTypes}" var="dictionaryType">
              <li class="list-group-item" onclick="findDictionary('${dictionaryType.id}')"> ${dictionaryType.zhName}
                    <span class="badge badge-danger">
                      <a href="javascript:delDictionaryType('${dictionaryType.id}');">
                        <i class="icon-trash"></i></a>
                    </span>
                    <span class="badge badge-warning">
                      <a href="javascript:editDictionaryType('${dictionaryType.id}');">
                        <i class="icon-wrench"></i></a>
                    </span>
              </li>
            </c:forEach>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="col-md-8">
    <div class="portlet light portlet-fit full-height-content full-height-content-scrollable bordered">
      <div class="portlet-title">
        <div class="caption">
          <i class=" icon-layers font-green"></i>
          <span class="caption-subject font-green bold uppercase">字典内容</span>
        </div>
        <div class="actions">
          <a href="javascript:editDictionary();" class="btn btn-circle btn-default btn-sm">
            <i class="fa fa-plus"></i> 添加 </a>
        </div>
      </div>
      <div class="portlet-body">
        <div class="full-height-content-body">
          <table class="table table-striped table-hover table-bordered" id="dictionary">
            <thead>
            <tr>
              <th> 中文名</th>
              <th> 值</th>
              <th> 排序号</th>
              <th> 操作</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="${_PATH}/static/plugins/jquery-nestable/jquery.nestable.js" type="text/javascript"></script>
<script type="text/javascript">
  var dicTypeId = '';
  function editDictionary(id) {
    if (!dicTypeId) {
      layer.alert('请选择字典分类');
      return;
    }
    layer.open({
      type: 2,
      shadeClose: true,
      title: false, //不显示标题
      closeBtn: 2, //关闭按钮
      shift: 2,
      shade: 0.6,
      area: ['500px', '500px'],
      content: _PATH + '/dictionary/editDictionary?dicTypeId=' + dicTypeId + (id ? '&id=' + id : '')
    });
  }
  function delDictionary(id) {
    layer.confirm('确认删除？', {
      btn: ['删除', '取消'] //按钮
    }, function () {
      $.ajax(_PATH + '/dictionary/deleteDictionary?id=' + id, {
        type: 'DELETE',
        success: function (resp) {
          if (resp.statusCode === 200) {
            layer.msg('删除成功');
            window.location.reload();
          }
          else  layer.msg('删除失败', {icon: 6});
        },
        error: function () {
          layer.msg('删除失败', {icon: 6});
        }
      });
    });
  }

  //----------------------------字典分类---------------------
  function editDictionaryType(id) {
    layer.open({
      type: 2,
      shadeClose: true,
      title: false, //不显示标题
      closeBtn: 2, //关闭按钮
      shift: 2,
      shade: 0.6,
      area: ['500px', '500px'],
      content: _PATH + '/dictionary/editDictionaryType/${menuType}' + (id ? '?id=' + id : '')
    });
  }
  function delDictionaryType(id) {
    layer.confirm('确认删除？', {
      btn: ['删除', '取消'] //按钮
    }, function () {
      $.ajax(_PATH + '/dictionary/deleteDictionaryType?id=' + id, {
        type: 'DELETE',
        success: function (resp) {
          if (resp.statusCode === 200) {
            layer.msg('删除成功');
            window.location.reload();
          }
          else  layer.msg('删除失败', {icon: 6});
        },
        error: function () {
          layer.msg('删除失败', {icon: 6});
        }
      });
    });
  }
  function findDictionary(id) {
    $.ajax(_PATH + '/dictionary/findDictionary?dicTypeId=' + id, {
      type: 'GET',
      success: function (resp) {
        dicTypeId = id;
        var html = '';
        if (resp.statusCode === 200) {
          var data = resp.data;
          for (var i = 0, len = data.length; i < len; i++) {
            html += '<tr><td>' + data[i].zhName + '</td><td>' + data[i].value + '</td><td>' + data[i].sort + '</td>' +
                '<td><a href="javascript:delDictionary(\'' + data[i].id + '\')">删除</a></td></tr>';
          }
        }
        else  layer.msg('查询失败', {icon: 6});
        $('#dictionary tbody').html(html);
      },
      error: function () {
        layer.msg('查询失败', {icon: 6});
      }
    });
  }
</script>