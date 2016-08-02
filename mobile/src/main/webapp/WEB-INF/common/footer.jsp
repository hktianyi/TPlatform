<%@ page pageEncoding="UTF-8" language="java" %>
<%--<div class="copyright"> 2016 © ${APP_NAME}. 后台管理系统.</div>--%>
<!--[if lt IE 9]>
<script src="../../static/plugins/respond.min.js"></script>
<script src="../../static/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="${_PATH}/static/plugins/jquery.swipebox.min.js"></script>
<script src="${_PATH}/static/plugins/jquery.smoothState.min.js"></script>
<script src="${_PATH}/static/plugins/materialize.min.js"></script>
<script src="${_PATH}/static/plugins/swiper.min.js"></script>
<script src="${_PATH}/static/plugins/jquery.mixitup.min.js"></script>
<script src="${_PATH}/static/plugins/masonry.min.js"></script>
<script src="${_PATH}/static/plugins/chart.min.js"></script>
<script src="${_PATH}/static/plugins/functions.js"></script>
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
      success: function (layero, index) {
        console.log(layero, index);
      }
    });
  }
  // 编辑
  function del(id, url) {
    layer.confirm('确认删除？', function () {
      $.post(url || (_MODULE_NAME + '/delete/' + id), function (resp) {
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
    $.validator.setDefaults({
      onfocusout: function (element) {
        $(element).valid();
      },
      submitHandler: function (form) {
        for (var instance in CKEDITOR.instances)
          CKEDITOR.instances[instance].updateElement();
        $.ajax(_MODULE_NAME + '/save', {
          type: 'POST',
          data: $(form).serialize(),
          success: function (resp) {
            if (resp.statusCode === 200) {
              layer.alert('保存成功!', function () {
                try {
                  saveCallback && saveCallback()
                } catch (e) {
                  window.location.href = _MODULE_NAME + '/list';
                }
              })
            }
          },
          error: function (resp) {
            layer.alert('保存失败!');
            var img = new Image();
            img.src = _PATH + '/sendJSError?s=' + _MODULE_NAME + '&e=' + encodeURIComponent(JSON.stringify(resp));
          }
        });
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