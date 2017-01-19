<%@ page pageEncoding="UTF-8" language="java" %>
<%@include file="/WEB-INF/common/quicksidebar.jsp" %>
<div class="page-footer">
  <div class="page-footer-inner"> Copyright © 2015 - 2016 ${APP_NAME}. </div>
  <div class="scroll-to-top">
    <i class="icon-arrow-up"></i>
  </div>
</div>
<!--[if lt IE 9]>
<script src="${_PATH}/static/plugins/respond.min.js"></script>
<script src="${_PATH}/static/plugins/excanvas.min.js"></script>
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
<script src="${_PATH}/static/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"
        type="text/javascript"></script>
<script src="${_PATH}/static/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"
        type="text/javascript"></script>
<%--<script src="${_PATH}/static/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>--%>
<%--<script src="${_PATH}/static/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>--%>
<script src="${_PATH}/static/plugins/bootstrap-editable/js/bootstrap-editable.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/bootstrap-multiselect/js/bootstrap-multiselect.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="${_PATH}/static/common/js/layout.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/common/js/demo.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/common/js/quick-sidebar.js" type="text/javascript"></script>
<!-- END THEME LAYOUT SCRIPTS -->
<script type="text/javascript" src="${_PATH}/static/plugins/jquery/jquery.autocomplete.js"></script>
<script src="${_PATH}/static/plugins/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/jquery-validation/additional-methods.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/plupload/plupload.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/plupload/moxie.min.js" type="text/javascript"></script>
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
      area: ['800px', '500px'],
      content: (url || (_MODULE_NAME + '/edit')) + '?id=' + (id || ''),
      success: function (layero, index) {
      },
      btn: ['确认', '取消'],
      yes: function(index, layero) {
        window[layero.find('iframe')[0]['name']].save();
      },
      cancel: function(layero, index) {layer.close(index);}
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
  /**
   * 上传文件
   * @param btnId
   * @param url
   * @param filter
   * @param callback_added
   * @param callback_progress
   * @param callback_uploaded
   */
  function readyUploader(btnId, url, fileName, filter, callback_added, callback_uploaded, callback_progress) {
    new plupload.Uploader({
      runtimes: 'html5,flash,silverlight,html4',
      url: _PATH + url,
      browse_button: btnId,
      file_data_name: fileName || 'file',
      filters: {
        max_file_size : ((filter && filter.size) || 10) + 'MB',
        mime_types: [
          {extensions : ((filter && filter.extensions) || 'jpg,jpeg,gif,png')}
        ]
      },
      init: {
        FilesAdded: function (up, files) {
          if(callback_added) callback_added(up, files);
          else up.start();
        },
        UploadProgress: function (up, file) {
          if(callback_progress) callback_progress(up, file);
          else $(up.settings.browse_button).text('[上传中:'+ (file.percent-1) + '%]');
        },
        FileUploaded: function (up, file, resp) {
          if (resp.status === 200 && resp.response) {
            if(callback_uploaded)
              callback_uploaded(up, file, resp);
            else {
              $('#' + btnId).prev().val(resp.response);
              $(up.settings.browse_button).text('[上传完成]');
            }
          } else {
            alert('上传失败!');
          }
        },
        Error: function (up, err) {
          switch (err.code) {
            case -600: alert('上传文件超过最大限制'); break;
            case -601: alert('文件格式错误'); break;
            default: alert(err.message); break;
          }
        }
      }
    }).init();
  }
  //plupload中为我们提供了mOxie对象(https://github.com/moxiecode/moxie/wiki/API)
  function previewImage(file, callback) {
    if (!file || !/image\//.test(file.type)) return; //确保文件是图片
    if (file.type == 'image/gif') {//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
      var fr = new mOxie.FileReader();
      fr.onload = function () {
        callback(fr.result);
        fr.destroy();
        fr = null;
      };
      fr.readAsDataURL(file.getSource());
    } else {
      var preloader = new mOxie.Image();
      preloader.onload = function () {
        var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
        callback && callback(imgsrc);
        preloader.destroy();
        preloader = null;
      };
      preloader.load(file.getSource());
    }
  }
  !(function () {
    window.alert = layer.alert;
    window.confim = layer.confim;
    $('select[multiple="multiple"]:not(".double")').multiselect();
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
        console.log($(form).serialize());
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
    $.fn.serializeTable = function () {
      var $rows = this.find('tr:not(:hidden)');
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
    //将表单序列化成json格式的数据(但不适用于含有控件的表单，例如复选框、多选的select)
    $.fn.serializeJson = function () {
      var jsonData1 = {};
      var serializeArray = this.serializeArray();
      // 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
      $(serializeArray).each(function () {
        if (/.*\..*/.test(this.name)) {
          var names = this.name.split('\.');
          if (!jsonData1[names[0]]) jsonData1[names[0]] = {};
          if (jsonData1[names[0]][names[1]]) {
              if ($.isArray(jsonData1[names[0]][names[1]])) {
                  jsonData1[names[0]][names[1]].push(this.value);
              } else {
                  jsonData1[names[0]][names[1]] = [jsonData1[names[0]][names[1]], this.value];
              }
          } else {
              jsonData1[names[0]][names[1]] = this.value;
          }
        } else {
          if (jsonData1[this.name]) {
              if ($.isArray(jsonData1[this.name])) {
                  jsonData1[this.name].push(this.value);
              } else {
                  jsonData1[this.name] = [jsonData1[this.name], this.value];
              }
          } else {
              jsonData1[this.name] = this.value;
          }
        }

      });
      // 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
      var vCount = 0;
      // 计算json内部的数组最大长度
      for (var item in jsonData1) {
        var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
        vCount = (tmp > vCount) ? tmp : vCount;
      }

      if (vCount > 1) {
        var jsonData2 = new Array();
        for (var i = 0; i < vCount; i++) {
          var jsonObj = {};
          for (var item in jsonData1) {
            jsonObj[item] = jsonData1[item][i];
          }
          jsonData2.push(jsonObj);
        }
        return jsonData2;
      } else {
        return jsonData1;
      }
    };
  })(jQuery);
  function layerClose(msg) {
    parent.layer.msg(msg, {icon: 6, time: 1000});
    parent.layer.close(parent.layer.getFrameIndex(window.name));
  }
</script>