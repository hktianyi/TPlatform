<%@ page pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta charset="utf-8" />
<%--<title>${APP_NAME}</title>--%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta name="renderer" content="webkit">
<!-- 防止被转码 -->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${_PATH}/static/plugins/font-awesome/css/fonts.googleapis.css" rel="stylesheet" type="text/css" />
<link href="${_PATH}/static/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${_PATH}/static/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
<link href="${_PATH}/static/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${_PATH}/static/plugins/uniform/css/uniform.default.min.css" rel="stylesheet" type="text/css" />
<link href="${_PATH}/static/plugins/bootstrap/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="${_PATH}/static/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
<link href="${_PATH}/static/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
<link href="${_PATH}/static/plugins/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME GLOBAL STYLES -->
<link href="${_PATH}/static/common/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
<link href="${_PATH}/static/common/css/plugins.min.css" rel="stylesheet" type="text/css" />
<!-- END THEME GLOBAL STYLES -->
<!-- BEGIN THEME LAYOUT STYLES -->
<link href="${_PATH}/static/common/css/layout.min.css" rel="stylesheet" type="text/css" />
<link href="${_PATH}/static/common/css/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="${_PATH}/static/common/css/custom.css" rel="stylesheet" type="text/css" />
<!-- END THEME LAYOUT STYLES -->
<link rel="shortcut icon" href="${_PATH}/favicon.ico" type="image/x-icon" />
<script src="${_PATH}/static/plugins/jquery/jquery-2.2.2.min.js" type="text/javascript"></script>
<script type="text/javascript">
    var _PATH = '${_PATH}', _MODULE_NAME = _PATH + '/${MODULE_NAME}';
</script>