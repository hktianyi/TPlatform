<%@ page pageEncoding="UTF-8" language="java" %>
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
<script src="${_PATH}/static/themes/metronic/js/app.min.js" type="text/javascript"></script>
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
<script src="${_PATH}/static/plugins/jquery/jquery.autocomplete.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/jquery-validation/additional-methods.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/fine-uploader/fine-uploader.js"></script>
<script src="${_PATH}/static/plugins/tplatform.js?${_VERSION}" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="${_PATH}/static/themes/metronic/js/layout.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/themes/metronic/js/demo.min.js" type="text/javascript"></script>
<script src="${_PATH}/static/themes/metronic/js/quick-sidebar.js" type="text/javascript"></script>
<!-- END THEME LAYOUT SCRIPTS -->
<script type="text/template" id="qq-template">
  <div class="qq-uploader-selector qq-uploader qq-gallery" qq-drop-area-text="拖拽文件上传">
    <div class="qq-upload-drop-area-selector qq-upload-drop-area" qq-hide-dropzone>
      <span class="qq-upload-drop-area-text-selector"></span>
    </div>
    <button type="button" class="qq-upload-button-selector qq-upload-button btn btn-social btn-fill bg-gold" style="border: none;">
      <i class="fa fa-cloud-upload"></i> 选择文件
    </button>
    <span class="qq-drop-processing-selector qq-drop-processing">
            <span>处理中...</span>
            <span class="qq-drop-processing-spinner-selector qq-drop-processing-spinner"></span>
        </span>
    <div class="row qq-upload-list-selector qq-upload-list" role="region" aria-live="polite" aria-relevant="additions removals" style="box-shadow:none;">
      <div class="col-md-6 col-sm-12">
        <span role="status" class="qq-upload-status-text-selector qq-upload-status-text"></span>
        <div class="qq-progress-bar-container-selector qq-progress-bar-container">
          <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-progress-bar-selector qq-progress-bar"></div>
        </div>
        <span class="qq-upload-spinner-selector qq-upload-spinner"></span>
        <div class="qq-thumbnail-wrapper fileinput-new thumbnail img-raised">
          <img class="qq-thumbnail-selector" style="max-height:320px;">
        </div>
        <button type="button" class="qq-upload-cancel-selector qq-upload-cancel">X</button>
        <button type="button" class="qq-upload-retry-selector qq-upload-retry">
          <span class="qq-btn qq-retry-icon" aria-label="Retry"></span>
          重试
        </button>
        <div class="qq-file-info" style="text-align: center;">
          <button type="button" class="qq-upload-delete-selector qq-upload-delete btn btn-social btn-fill btn-danger">
            <i class="fa fa-trash-o"></i> 删除
          </button>
        </div>
      </div>
    </div>

    <dialog class="qq-alert-dialog-selector">
      <div class="qq-dialog-message-selector"></div>
      <div class="qq-dialog-buttons">
        <button type="button" class="qq-cancel-button-selector">关闭</button>
      </div>
    </dialog>

    <dialog class="qq-confirm-dialog-selector">
      <div class="qq-dialog-message-selector"></div>
      <div class="qq-dialog-buttons">
        <button type="button" class="qq-cancel-button-selector">取消</button>
        <button type="button" class="qq-ok-button-selector btn-danger">删除</button>
      </div>
    </dialog>

    <dialog class="qq-prompt-dialog-selector">
      <div class="qq-dialog-message-selector"></div>
      <input type="text">
      <div class="qq-dialog-buttons">
        <button type="button" class="qq-cancel-button-selector">取消</button>
        <button type="button" class="qq-ok-button-selector">确认</button>
      </div>
    </dialog>
  </div>
</script>