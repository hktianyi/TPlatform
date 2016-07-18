<%@ page pageEncoding="UTF-8" language="java" %>
<%@include file="/WEB-INF/common/quicksidebar.jsp" %>
<div class="page-footer">
  <div class="scroll-to-top">
    <i class="icon-arrow-up"></i>
  </div>
</div>
<%--<div class="copyright"> 2016 © ${APP_NAME}. 后台管理系统.</div>--%>
<!--[if lt IE 9]>
<script src="../../static/plugins/respond.min.js"></script>
<script src="../../static/plugins/excanvas.min.js"></script>
<![endif]-->
<!-- BEGIN CORE PLUGINS -->
<script src="${_PATH}/static/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/js.cookie.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/bootstrap/js/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/jquery/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/jquery/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/bootstrap/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="${_PATH}/static/common/js/app.min.js" type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${_PATH}/static/plugins/datatables/datatables.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/layer/layer.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/layer/extend/layer.ext.js" type="text/javascript"></script>
<%--<script src="${_PATH}/static/plugins/pace.min.js" type="text/javascript"></script>--%>
<script src="${_PATH}/static/plugins/moment.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/bootstrap-editable/js/bootstrap-editable.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="${_PATH}/static/common/js/layout.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/common/js/demo.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/common/js/quick-sidebar.js" type="text/javascript"></script>
<!-- END THEME LAYOUT SCRIPTS -->
<script src="${_PATH}/static/plugins/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/jquery-validation/additional-methods.min.js" type="text/javascript"></script>
<script type="text/javascript">
  // 编辑
  function edit(id, url) {
    layer.open({
      type: 2,
      shadeClose: true,
      title: '&nbsp;', //不显示标题
      shift: 0,
      shade: 0.6,
      maxmin: true,
      moveType: 1,
      btn: ['确认', '取消'],
      area: ['800px', '500px'],
      content: (url || (_MODULE_NAME + '/edit')) + '?layer=1&id=' + (id || ''),
      success: function(layero, index){
        console.log(layero, index);
      }
    });
  }
  // 编辑
  function del(id, url) {
    layer.confirm('确认删除？', function () {
      $.post(url || (_MODULE_NAME + '/delete/'+id), function (resp) {
        layer.alert(resp.data);
        window.location.reload();
      })
    });
  }
  !(function () {
    window.alert = layer.alert;
    window.confim = layer.confim;
    // DataTable配置
    $.fn.dataTable.ext.errMode = 'throw';
    $.fn.dataTable.defaults = $.extend($.fn.dataTable.defaults, {
      language: {'sUrl': _PATH + '/static/plugins/datatables/zh-cn.lang'},
      dom: "<'row'<'col-sm-6'f>><'row'<'col-sm-12'tr>><'row'<'col-sm-3'l><'col-sm-3'i><'col-sm-6'p>>",
      lengthMenu: [[10, 50, 100], [10, 50, 100]], info: true, stateSave: true,
      processing: true, serverSide: true, deferRender: true, searching: false, ordering: false, pageLength: 50,
      fnCreatedRow: function (nRow, aData, iDataIndex) {
        $('td:eq(0)', nRow).html(++iDataIndex);
      }
    });
    // datepicker配置
    $.fn.datepicker.defaults = $.extend($.fn.datepicker.defaults, {
      format: 'yyyy-mm-dd',
      autoclose: 'true',
      language: 'en',
      orientation: 'bottom right'
    });
    $(".date-picker").datepicker();

    $('.page-toolbar').on('click', 'button:has(".fa-plus")', function () {
      edit();
    });
  })(window);
  (function ($) {
    'use strict';
    $.fn.dataList = function (options) {
      if ($(this).hasClass('dataTable')) {
        $(this).draw();
        return;
      }
      var setting = {
        url: _MODULE_NAME + '/load',
        type: 'POST'
      };
      $.extend(true, setting, options);
      return $(this).DataTable({
        'ajax': {
          'url': setting.url,
          'type': setting.type,
          'data': function (params) {
            if (setting.data)
              $.extend(params, setting.data);
          }
        },
        'columns': setting.columns
      });
    };

    //序列化table数据
    $.fn.serializeTable = function ($TABLE) {
      var $rows = $TABLE.find('tr:not(:hidden)');
      var headers = [];
      var index = [];
      var data = [];
      var i = 0;
      // Get the headers (add special header logic here)
      $($rows.first()).find('th').each(function () {
        if (typeof $(this).data("name") !== 'undefined') {
          headers.push($(this).data("name"));
          index.push(i);
        }
        i++;
      });
      // Turn all existing rows into a loopable array
      $rows.not(":first").each(function () {
        var $td = $(this).find('td');
        var h = {};
        // Use the headers from earlier to name our hash keys
        headers.forEach(function (header, i) {
          var input = $td.eq(index[i]).find('input');
          h[header] = (input.length > 0 && input.val()) || $td.eq(index[i]).data("val") || $td.eq(index[i]).text().replace(/^\s+|\s+$/g, "");
        });
        data.push(h);
      });
      return data;
    };
  })(jQuery);
</script>