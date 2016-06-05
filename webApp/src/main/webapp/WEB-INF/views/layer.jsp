<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="view" uri="/taglib/view.tld" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<tiles:putAttribute name="common" value="/WEB-INF/common/common.jsp"/>
<tiles:putAttribute name="footer" value="/WEB-INF/common/footer.jsp"/>
<tiles:putAttribute name="title" value="${title}"/>
<c:if test="${not empty body}">
  <tiles:putAttribute name="body" value="/WEB-INF/views${body}"/>
</c:if>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
  <tiles:insertAttribute name="common"/>
  <title><tiles:insertAttribute name="title" ignore="true"/></title>
</head>
<body class="page-sidebar-closed-hide-logo page-content-white page-header-fixed page-sidebar-fixed">
<div class="page-container">
  <div class="page-content-wrapper">
    <div class="page-content">
      <tiles:insertAttribute name="body"/>
    </div>
  </div>
</div>
<tiles:insertAttribute name="footer"/>
</body>
</html>
