<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="../common/common.jsp" %>
  <link href="http://cdn.bootcss.com/dropzone/4.3.0/min/basic.min.css" rel="stylesheet">
  <link href="http://cdn.bootcss.com/dropzone/4.3.0/min/dropzone.min.css" rel="stylesheet">
  <style>
    .dz-success-mark, .dz-error-mark { display: none }
  </style>
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
                <input type="hidden" name="id" value="${data.id}">
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
                        <label class="control-label col-md-3">封面:</label>
                        <div class="col-md-9">
                          <input type="hidden" id="imgUrl" name="imgUrl" value="${data.imgUrl}"/>
                          <div class="dropzone-file-area" id="imgWrap">
                            <h3 class="sbold">将文件拖拽到这里上传</h3>
                            <c:if test="${not empty data.imgUrl}">
                              <div class="dz-preview dz-processing dz-image-preview dz-success dz-complete">
                                <div class="dz-image">
                                  <img data-dz-thumbnail="" alt="" src="${FILE_DOMAIN}${data.imgUrl}" width="100px" height="100px"></div>
                                <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress="" style="width: 100%;"></span></div>
                                <div class="dz-error-message"><span data-dz-errormessage=""></span></div>
                                <a href="javascript:$('.dz-preview').remove();" class="btn red btn-sm btn-block">移除</a></div>
                            </c:if>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2">摘要:</label>
                        <div class="col-md-10">
                          <textarea class="form-control" name="summary">${data.summary}</textarea>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2"> * 内容:</label>
                        <div class="col-md-10">
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
<script src="http://cdn.bootcss.com/dropzone/4.3.0/min/dropzone.min.js"></script>
<script type="text/javascript">
    var img = new Dropzone("#imgWrap", {
        dictDefaultMessage: "将文件拖拽到这里上传",
        url: _PATH + "/doc/upload", paramName: "upload", acceptedFiles: "image/*", maxFiles: 1, maxFilesize: 1
    });
    img.on('addedfile', function (file) {
        // Create the remove button
        var removeButton = Dropzone.createElement("<a href='javascript:;' class='btn red btn-sm btn-block'>移除</a>");
        // Capture the Dropzone instance as closure.
        var _this = this;
        // Listen to the click event
        removeButton.addEventListener("click", function (e) {
            // Make sure the button click doesn't submit the form:
            e.preventDefault();
            e.stopPropagation();
            // Remove the file preview.
            _this.removeFile(file);
            // If you want to the delete the file on the server as well,
            // you can do the AJAX request here.
        });
        // Add the button to the file preview element.
        file.previewElement.appendChild(removeButton);
    }).on('success', function (file, resp) {
        $("#imgUrl").val(resp.path);
    });
    $('#docForm').validate({
        rules: {
            title: 'required',
            context: 'required'
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
</script>
</body>
</html>