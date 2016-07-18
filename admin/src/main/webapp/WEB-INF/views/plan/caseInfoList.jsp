<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<div class="page-bar">
  <ul class="page-breadcrumb">
    <li>
      <a href="${_PATH}/main.html">首页</a>
      <i class="fa fa-angle-right"></i>
    </li>
    <li>
      <span>案例信息</span>
    </li>
  </ul>
</div>
<div class="row">
  <div class="col-md-12">
    <form class="form-horizontal" role="form">
      <div class="form-body">
        案例编号：<input type="text" id="caseCode">&nbsp;&nbsp;案例名称：<input type="text" id="caseName">&nbsp;&nbsp;
        品牌名称：<input type="text" id="brandName">&nbsp;&nbsp;参赛类别：<select id="caseType2">
        <option value="">请选择</option>
        <optgroup label="产品与服务类">
        <c:forEach items="${dic_caseTypeA}" var="caseTypeA">
          <option value="${caseTypeA.value}">${caseTypeA.zhName}</option>
        </c:forEach>
        </optgroup>
        <optgroup label="专项类">
        <c:forEach items="${dic_caseTypeB}" var="caseTypeB">
          <option value="${caseTypeB.value}">${caseTypeB.zhName}</option>
        </c:forEach>
        </optgroup>
      </select>
        <button type="button" class="btn blue btn-xs btn-circle" onclick="getDataList()"><i class="fa fa-search"></i> 查询 </button>
        <button type="button" class="btn blue btn-xs btn-circle" onclick="history.back()"><i class="fa fa-download"></i> 下载 </button>
      </div>
    </form>
  </div>
</div>
<div class="row">
  <div class="col-md-12">
    <table class="table table-striped table-bordered table-hover table-header-fixed"></table>
  </div>
</div>
<script type="text/javascript">
  var dataTable;
  var dic = {
    1: {
      <c:forEach items="${dic_caseTypeA}" var="caseTypeA">
      '${caseTypeA.value}': '${caseTypeA.zhName}',
      </c:forEach>
    }, 2: {
      <c:forEach items="${dic_caseTypeB}" var="caseTypeB">
      '${caseTypeB.value}': '${caseTypeB.zhName}',
      </c:forEach>
    }
  };
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
        "url": _PATH + "/plan/load",
        "type": "POST",
        "data": function (params) {
          params.caseCode = $("#caseCode").val();
          params.caseName = $("#caseName").val();
          params.brandName = $("#brandName").val();
          params.caseType2 = $("#caseType2").val();
        }
      },
      "columns": [
        {"sTitle": "序号", "sWidth": "3%", "defaultContent": ''},
        {"sTitle": "案例编号", "name": "caseCode", "data": "caseCode", "sWidth": "8%"},
        {"sTitle": "案例名称", "name": "caseName", "data": "caseName", "sWidth": "6%"},
        {"sTitle": "品牌名称", "name": "brandName", "data": "brandName", "sWidth": "6%"},
        {"sTitle": "广告客户名称", "name": "customerName", "data": "customerName", "sWidth": "8%"},
        {
          "sTitle": "参赛类别", "name": "caseType2", "data": function (data) {
          return data.caseType && data.caseType2 && dic[data.caseType][data.caseType2];
        }, "sWidth": "8%"
        },
        {"sTitle": "用户", "name": "operator", "data": "operator", "sWidth": "8%"},
        {
          "sTitle": "状态", "name": "bizStatus", "data": function (data) {
          var status = '';
          switch (data.bizStatus) {
            case '4':
              status = '待审核';
              break;
            case '5':
              status = '审核通过';
              break;
            case '6':
              status = '审核未通过';
              break;
            default:
              status = '待付款';
              break;
          }
          return status;
        }, "sWidth": "6%"
        },
        {
          "sTitle": "操作", "data": function (data) {
          return '<div class="btn-group btn-group-xs btn-group-solid">' +
              '<a href="' + _PATH + '/plan/edit?id=' + data.id + '" type="button" class="btn btn-primary btn-xs">详情</a>' +
//              '<div class="btn-group">' +
//              '<button class="btn grey btn-xs dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">' +
//              '更多<i class="fa fa-angle-down"></i></button>' +
//              '<ul class="dropdown-menu">' +
//              '<li><a href="javascript:approve(' + data.id + ', true)">审核通过</a></li>' +
//              '<li><a href="javascript:approve(' + data.id + ', false)">审核不通过</a></li>' +
//              '</ul></div>' +
              '</div>';
        }, "sWidth": "6%"
        }
      ]
    });
  }
  function approve(id, pass) {
    layer.prompt({
      title: '输入审核意见',
      formType: 2 //prompt风格，支持0-2
    }, function (remark) {
      $.ajax(_PATH + '/plan/approve?id=' + id + '&pass=' + pass + '&remark=' + remark, {
        type: 'POST',
        success: function (resp) {
          console.log(resp);
          if (resp.statusCode === 200) {
            layer.msg('审核成功');
            getDataList();
          }
          else  layer.msg('审核失败', {icon: 6});
        },
        error: function () {
          layer.msg('审核失败', {icon: 6});
        }
      });
    });
  }
</script>