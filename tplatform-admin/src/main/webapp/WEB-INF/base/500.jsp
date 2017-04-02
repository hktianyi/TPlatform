<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>${APP_NAME}</title>
  <%@include file="../template/inspinia/common/common.jsp" %>
</head>
<body class="gray-bg">
<div class="middle-box text-center animated fadeInDown">
  <h1>WOW~</h1>
  <h3 class="font-bold">Internal Server Error</h3>
  <div class="error-desc">
    The server encountered something unexpected that didn't allow it to complete the request. We apologize.<br/>
    You can go back to main page: <br/><a href="${_PATH}/" class="btn btn-primary m-t">主页</a>
  </div>
</div>
<script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
