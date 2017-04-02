/**
 * Created by tianyi on 2017/3/22.
 */
(function ($) {
    'use strict';
    window.alert = layer.alert;
    window.confim = layer.confim;
    if (top != self)
        self.toastr = top.toastr;
    toastr.options = {
        closeButton: true,
        debug: false,
        progressBar: true,
        preventDuplicates: false,
        positionClass: "toast-top-right",
        onclick: null,
        showDuration: "400",
        hideDuration: "1000",
        timeOut: "7000",
        extendedTimeOut: "1000",
        showEasing: "swing",
        hideEasing: "linear",
        showMethod: "fadeIn",
        hideMethod: "fadeOut"
    };
    $(document).on('click', '.btn-back', function () {
        window.history.back();
    });
    // $('select[multiple="multiple"]:not(".double")').multiselect();
    // datepicker配置
    // $.fn.datepicker.defaults = $.extend($.fn.datepicker.defaults, {
    //     format: 'yyyy-mm-dd',
    //     autoclose: 'true',
    //     language: 'en',
    //     orientation: 'bottom right'
    // });
    // $(".date-picker").datepicker();
    $('.page-toolbar').on('click', 'button:has(".fa-plus")', function () {
        edit();
    });
    // DataTable配置
    $.fn.dataTable.ext.errMode = 'throw';
    $.fn.dataTable.defaults = $.extend($.fn.dataTable.defaults, {
        language: {
            decimal: ".", emptyTable: "对不起，查询不到任何数据", info: "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
            infoEmpty: "Showing 0 to 0 of 0 entries", infoFiltered: "(数据表中共为 _MAX_ 条记录)", infoPostFix: "", thousands: ",",
            lengthMenu: "每页显示 _MENU_ 条记录", loadingRecords: "正在加载中...", processing: "正在处理中...", search: "搜索:",
            zeroRecords: "对不起，查询不到任何相关数据", paginate: {first: "首页", last: "尾页", next: "下一页", previous: "上一页"},
            aria: {sortAscending: ": activer pour trier la colonne par ordre croissant", sortDescending: ": activer pour trier la colonne par ordre décroissant"}
        },
        dom: "<'row'<'col-sm-6'f>><'row'<'col-sm-12'tr>><'row'<'col-sm-3'l><'col-sm-3'i><'col-sm-6'p>>",
        lengthMenu: [[10, 50, 100, 300], [10, 50, 100, 300]], info: true, stateSave: true,
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
            $.ajax(_MODULE_NAME + '/save', {
                type: 'POST',
                data: $(form).serialize(),
                success: function (resp) {
                    if (resp.statusCode === 200) {
                        if (saveCallback) {
                            saveCallback();
                        } else {
                            layer.alert('保存成功!', function () {
                                try {
                                } catch (e) {
                                    window.location.href = _MODULE_NAME + '/list';
                                }
                            })
                        }
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
    //datatable快捷方法
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
        yes: function (index, layero) {
            window[layero.find('iframe')[0]['name']].save();
        },
        cancel: function (layero, index) {
            layer.close(index);
        }
    });
}
// 编辑
function del(id, url) {
    var layer_delete_index = layer.confirm('确认删除？', function () {
        $.ajax(url || (_MODULE_NAME + '/delete/' + id), {
            type: 'DELETE',
            success: function (resp) {
                layer.close(layer_delete_index);
                if (resp.statusCode === 200) {
                    toastr.success(resp.data);
                    if (dataTable) {
                        dataTable.draw();
                    } else {
                        window.location.reload();
                    }
                } else {
                    toastr.error(resp.errorInfo || '删除失败');
                }
            }
        });
    });
}