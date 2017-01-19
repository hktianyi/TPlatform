<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <span>客户订单管理</span>
          </li>
        </ul>
        <div class="page-toolbar">
          <a href="javascript:;" class="btn btn-circle grey btn-sm btn-outline search-advance-btn"><i class="fa fa-search-plus"></i> 高级搜索 </a>
          <a href="javascript:detail();" class="btn btn-circle green btn-sm"><i class="fa fa-plus"></i> 添加 </a>
        </div>
        <%@include file="/WEB-INF/common/search.jsp" %>
        <div class="row" id="search-advance" style="display: none;">
          <div class="col-md-12">
            <form class="form-horizontal" role="form">
              <div class="form-body">
                <div class="row">
                  <div class="col-md-3">
                    <div class="form-group">
                      <label class="control-label col-md-4">客户名:</label>
                      <div class="col-md-8">
                        <input class="form-control" type="text" id="customerName">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-3">
                    <div class="form-group">
                      <label class="control-label col-md-4">联系人:</label>
                      <div class="col-md-8">
                        <input class="form-control" type="text" id="contacts">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-3">
                    <div class="form-group">
                      <label class="control-label col-md-4">电话:</label>
                      <div class="col-md-8">
                        <input class="form-control" type="text" id="tel">
                      </div>
                    </div>
                  </div>
                  <div class="col-md-2">
                    <div class="form-group">
                      <label class="control-label col-md-4">状态:</label>
                      <div class="col-md-8">
                        <select class="form-control" id="status">
                          <option value="">全部</option>
                          <option value="1">新订单</option>
                          <option value="2">跟进中</option>
                          <option value="3">已成单</option>
                          <option value="4">未成单</option>
                        </select>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-1">
                    <div class="form-group">
                      <button type="button" class="btn blue" onclick="layer.msg('Comming soon...');"><i
                              class="fa fa-search"></i> 查询
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <table class="table table-striped table-bordered table-hover table-header-fixed"></table>
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
  var dataTable;
  $(function () {
    getDataList();
  });
  function getDataList() {
    if (typeof dataTable !== 'undefined') {
      dataTable.draw();
      return;
    }
    dataTable = $("table").DataTable({
      "ajax": {
        "url": _MODULE_NAME + "/load",
        "type": "POST",
        "data": function (params) {
          params.q = $("#q").val();
          params.customerName = $("#customerName").val();
          params.contacts = $("#contacts").val();
          params.tel = $("#tel").val();
          params.status = $("#status").val();
        }
      },
      "columns": [
        {"sTitle": "序号", "sWidth": "4%", "defaultContent": ''},
        {"sTitle": "跟进", "sWidth": "4%", "defaultContent": '<i class="row-details fa fa-plus-square-o"></i>'},
        {"sTitle": "客户", "name": "member.username", "data": function (data) {
            return data.member && data.member.username;
        }, "sWidth": "8%"},
        {"sTitle": "开始日期", "name": "startDate", "data": function (data) {
          return data.startDate ? moment(data.startDate).format('YYYY-MM-DD') : '-';
        }, "sWidth": "7%"},
        {"sTitle": "结束日期", "name": "endDate", "data": function (data) {
          return data.endDate ? moment(data.endDate).format('YYYY-MM-DD') : '-';
        }, "sWidth": "7%"},
        {"sTitle": "联系人", "name": "member.nickname", "data": function (data) {
            return data.member && data.member.nickname;
        }, "sWidth": "8%"},
        {"sTitle": "手机", "name": "member.mobile", "data": function (data) {
            return data.member && data.member.mobile;
        }, "sWidth": "8%"},
        {"sTitle": "电话", "name": "member.phone", "data": function (data) {
            return data.member && data.member.phone;
        }, "sWidth": "8%"},
        {
          "sTitle": "状态", "name": "status", "data": function (data) {
          switch (data.status) {
            case 1: data.status = "新订单"; break;
            case 2: data.status = "跟进中"; break;
            case 3: data.status = "已成单"; break;
            case 4: data.status = "未成单"; break;
            default : break;
          }
          return data.status;
        }, "sWidth": "5%"
        },
        {"sTitle": "跟进人", "name": "user.username", "data": function (data) {
          return data.user ? data.user.username : '';
        }, "sWidth": "6%"},
        {"sTitle": "活动要求", "name": "need", "data": function (data) {
          return '<a href="javascript:alert(\''+data.need+'\');">'+(data.need && data.need.length > 18 ? (data.need.substr(0,18) + '...') : data.need)+'</a>'
        }, "sWidth": "10%"},
        {
          "sTitle": "操作", "name": "createTime", "data": function (data) {
          return '<a href="javascript:detail(' + data.id + ');" class="btn btn-xs default blue-stripe"> 详情 </a>' +
              '<a href="javascript:addFollow(' + data.id + ');" class="btn btn-xs default green-stripe"> 跟进 </a>' +
              '<a href="' + _PATH + '/solution/list/' + data.id + '" class="btn btn-xs default purple-stripe"> 方案 </a>' +
              '<a href="javascript:del(\'' + data.id + '\');" class="btn btn-xs default red-stripe"> 删除 </a>';
        }, "sWidth": "8%"
        }
      ]
    });
    dataTable.on( 'draw.dt', function (a,b) {
      $('tbody tr').click(function(e) {
        if(e.target.localName === 'a') return;

        var obj = $(this).find('.row-details');
        var row = dataTable.row(this);
        if (row.child.isShown()) {
          row.child.hide();
          obj.removeClass('fa-minus-square-o').addClass('fa-plus-square-o');
        } else {
          fnFormatDetails(row.data().id, row);
          obj.removeClass('fa-plus-square-o').addClass('fa-minus-square-o');
        }
      });
    });
  }

  var layerIndex, callback;
  function detail(id) {
    layerIndex = layer.open({
      type: 2,
      title: "订单详情",
      area: ['90%', '85%'],
      offset: ['3%', '5%'],
      fix: false, //不固定
      maxmin: true,
      scrollbar: false,
      content: _PATH + '/order/edit' + (id ? '?id=' + id : '')
    });
    callback = function() {
      layer.close(layerIndex);
    };
  }
  function addFollow(id) {
    layerIndex = layer.open({
      type: 2,
      title: "添加跟进",
      area: ['40%', '45%'],
      fix: false, //不固定
      maxmin: true,
      scrollbar: false,
      content: _PATH + '/order/addFollow/' + id
    });
    callback = function() {
      layer.close(layerIndex);
    };
  }
  function add() {
    layerIndex = layer.open({
      type: 2,
      title: "订单详情",
      area: ['90%', '85%'],
      offset: ['3%', '5%'],
      fix: false, //不固定
      maxmin: true,
      content: _PATH + '/order/add'
    });
    callback = function() {
      getDataList();
      layer.close(layerIndex);
    };
  }
  function fnFormatDetails(id, row) {
    //if (typeof row.child() !== 'undefined') {
    //  row.child().show();
    //} else {
    $.get(_PATH + "/order/follows/" + id, function (json) {
      if (json.statusCode === 200) {
        var html = new Array();
        html.push('<table class="table"><tr><th>跟进人</th><th>跟进时间</th><th>跟进情况</th><th>订单状态</th></tr>');
        var data = json.data;
        for (var i in data) {
          switch (data[i].status) {
            case 1:
              data[i].status = "新订单";
              break;
            case 2:
              data[i].status = "跟进中";
              break;
            case 3:
              data[i].status = "已成单";
              break;
            case 4:
              data[i].status = "未成单";
              break;
            default :
              break;
          }
          html.push('<tr><th>' + (data[i].user ? data[i].user.username : '') + '</th><th>' + moment(data[i].createTime).format('YYYY-MM-DD HH:mm') + '</th><th>' + data[i].remark + '</th><th>' + data[i].status + '</th></tr>');
        }
        html.push('</table>');
        row.child(html.join('')).show();
      }
    });
    //}
  }
</script>
</body>
</html>