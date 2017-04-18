<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="../common/common.jsp" %>
</head>
<!-- END HEAD -->

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-full-width">
<%@include file="../common/header.jsp" %>
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
            <span>文章管理</span>
          </li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="portlet box blue">
            <div class="portlet-title">
              <div class="caption">
                <i class="fa fa-tag"></i>文章信息
              </div>
            </div>
            <div class="portlet-body form">
              <form class="form-horizontal" role="form" id="docForm">
                <input type="hidden" name="id" id="id" value="${data.id}">
                <div class="form-body">
                  <h3 class="form-section">基础信息</h3>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-4"> * 标题:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="title" value="${data.title}"/>
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-4">作者:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="author" value="${data.author}"/>
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-4">文章类型:</label>
                        <div class="col-md-8">
                          <t:dict key="wg_doc_type" type="select" className="form-control" name="type" value="${data.type}"/>
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-4">关键词:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="keyword" value="${data.keyword}"/>
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="control-label col-md-4">来源:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="originAddress" value="${data.originAddress}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <input type="hidden" id="imgUrl" name="imgUrl" value="${data.imgUrl}"/>
                        <label class="control-label col-md-3">封面:</label>
                        <div class="col-md-9" id="imgCover">
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2">摘要:</label>
                        <div class="col-md-9">
                          <textarea class="form-control" name="summary">${data.summary}</textarea>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2"> * 内容:</label>
                        <div class="col-md-9">
                          <textarea class="form-control ckeditor" id="context" name="context">${data.context}</textarea>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="form-actions">
                  <div class="row">
                    <div class="col-md-6">
                      <div class="row">
                        <div class="col-md-offset-3 col-md-8">
                          <button type="submit" class="btn green">
                            <i class="fa fa-pencil"></i> 保存
                          </button>
                          <button type="button" class="btn default" onclick="history.back()">取消</button>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6"></div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="../common/footer.jsp" %>
<script type="text/javascript" src="${_PATH}/static/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
    $(function () {
        var imgUploader = createUploader('imgCover', {suffix: ['png', 'jpg', 'gif'], size: 1000000, item: 1});
        var imgUrl = '${data.imgUrl}';
        if (imgUrl) {
            imgUploader.addInitialFiles([{
                'name': '${data.id}',
                'uuid': '${data.id}',
                'status': 'UPLOAD_SUCCESSFUL',
                'thumbnailUrl': '${FILE_DOMAIN}${data.imgUrl}'
            }]);
        }
        $('#docForm').validate({
            rules: {
                title: {
                    required: true,
                    maxlength: 30
                },
                summary: {
                    maxlength: 180
                },
                context: {
                    required: true
                }
            },
            messages: {
                title: {
                    required: '请输入文章标题',
                    maxlength: '最多30个字'
                },
                summary: {
                    maxlength: '最多180个字'
                },
                context: {
                    required: '请输入正文内容'
                }
            },
            submitHandler: function (form) {
                for (var instance in CKEDITOR.instances)
                    CKEDITOR.instances[instance].updateElement();

                $.ajax(_PATH + '/doc/save', {
                    type: 'POST',
                    data: $(form).serialize(),
                    success: function (resp) {
                        if (resp.statusCode === 200) {
                            layer.alert('保存成功!', function () {
                                window.location.href = _PATH + '/doc/list';
                            })
                        }
                    },
                    error: function (resp) {
                        layer.alert('保存失败!');
                        var img = new Image();
                        img.src = _PATH + '/sendJSError?s=' + _PATH + '/doc&e=' + encodeURIComponent(JSON.stringify(resp));
                    }
                });
            }
        });

        function createUploader(type, options) {
            return new qq.FineUploader({
                element: document.getElementById(type),
                template: options.template || 'qq-template',
                request: {
                    endpoint: _PATH + '/doc/saveFile',
                    inputName: 'file',
                    uuidName: 'fineId',
                    params: {
                        id: $('#id').val(),
                        type: type
                    }
                },
                deleteFile: {
                    enabled: true,
                    forceConfirm: true,
                    confirmMessage: "确认删除 {filename}?",
                    endpoint: _PATH + '/doc/deleteFile',
                    params: {
                        id: $('#id').val()
                    }
                },
                thumbnails: {
                    placeholders: {
                        waitingPath: _PATH + '/static/plugins/fine-uploader/processing.gif',
                        notAvailablePath: _PATH + '/static/plugins/fine-uploader/processing.gif'
                    }
                },
                validation: {
                    allowedExtensions: options.suffix,
                    itemLimit: options.item || 0,
                    sizeLimit: options.size || 3000000
                },
                text: {
                    formatProgress: "{percent}% of {total_size}",
                    failUpload: "上传失败",
                    waitingForResponse: "上传中...",
                    paused: "暂停"
                },
                messages: {
                    typeError: '{file} 文件格式错误， 允许的格式: {extensions}',
                    sizeError: '{file} 太大， 不应该超过 {sizeLimit}',
                    onLeave: 'The files are being uploaded, if you leave now the upload will be canceled',
                    tooManyItemsError: '最多可上传 {itemLimit} 个文件，请删除旧文件后再上传'
                },
                callbacks: {
                    onComplete: function (id, name, responseJSON, xhr) {
                        $("#imgUrl").val(responseJSON.path);
                    }
                }
            });
        }
    });

</script>
</body>
</html>