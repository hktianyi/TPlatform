<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="view" uri="/taglib/view.tld" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<tiles:putAttribute name="common" value="/WEB-INF/common/common.jsp"/>
<tiles:putAttribute name="header" value="/WEB-INF/common/header.jsp"/>
<tiles:putAttribute name="menu" value="/WEB-INF/common/menu.jsp"/>
<tiles:putAttribute name="footer" value="/WEB-INF/common/footer.jsp"/>
<tiles:putAttribute name="title" value="${APP_NAME}"/>
<c:if test="${not empty body}">
  <tiles:putAttribute name="body" value="/WEB-INF/views${body}"/>
</c:if>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<!--<![endif]-->
<head>
  <tiles:insertAttribute name="common"/>
  <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body class="page-sidebar-closed-hide-logo page-content-white page-header-fixed page-sidebar-fixed">
<tiles:insertAttribute name="header"/>
<div class="page-container">
  <div class="page-sidebar-wrapper">
    <div class="page-sidebar navbar-collapse collapse">
      <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true"
          data-slide-speed="200">
        <%--<li class="sidebar-toggler-wrapper">
          <div class="sidebar-toggler"> </div>
        </li>--%>
        <view:menu/>
      </ul>
    </div>
  </div>
  <div class="page-content-wrapper">
    <div class="page-content">
      <tiles:insertAttribute name="body" ignore="true"/>
    </div>
  </div>
</div>
<tiles:insertAttribute name="footer"/>
<script type="text/javascript">
  $(function () {
    $('.page-sidebar-menu').on('click', 'a[data-action^="/"]', function () {
      window.location.href = _PATH + $(this).data('action');
    })
  })
</script>
</body>
</html>
