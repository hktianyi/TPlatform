<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="../../common/common.jsp" %>
  <link href="${_PATH}/static/plugins/jplayer/css/jplayer.blue.monday.min.css" rel="stylesheet" type="text/css"/>
</head>
<!-- END HEAD -->

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-full-width">
<%@include file="../../common/header.jsp" %>
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
              <form class="form-horizontal" role="form">
                <input type="hidden" name="id" value="${data.id}">
                <div class="form-body">
                  <h3 class="form-section">基础信息</h3>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3"> * 标题:</label>
                        <div class="col-md-9">
                          <input class="form-control" name="title" value="${data.title}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <%--<div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">文章类别:</label>
                        <div class="col-md-9">
                          <input class="form-control" name="type" value="${data.type}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">状态:</label>
                        <div class="col-md-9">
                          <p class="form-control-static">
                            <c:choose>
                            <c:when test="${data.status eq 4}"><span id="bizStatus">待审核</span>&nbsp;&nbsp;&nbsp;&nbsp;
                          <div class="btn-group btn-group-xs btn-group-solid">
                            <button type="button" class="btn green" onclick="approve('${data.id}', true)">通过</button>
                            <button type="button" class="btn red" onclick="approve('${data.id}', false)">不通过</button>
                          </div>
                          </c:when>
                          <c:when test="${data.status eq 5}">审核通过</c:when>
                          <c:when test="${data.status eq 6}">审核未通过</c:when>
                          <c:otherwise>待付款</c:otherwise>
                          </c:choose>
                          </p>
                        </div>
                      </div>
                    </div>
                  </div>--%>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">关键词:</label>
                        <div class="col-md-9">
                          <input class="form-control" name="keyword" value="${data.keyword}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">来源:</label>
                        <div class="col-md-9">
                          <input class="form-control" name="originAddress" value="${data.originAddress}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">封面:</label>
                        <div class="col-md-9">
                          <input class="form-control" name="imgUrl" value="${data.imgUrl}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label col-md-3">作者:</label>
                        <div class="col-md-9">
                          <input class="form-control" name="author" value="${data.author}"/>
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
                        <div class="col-md-offset-3 col-md-9">
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
<%@include file="/WEB-INF/common/footer.jsp" %>
<script type="text/javascript" src="${_PATH}/static/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
  $('form').validate({
    rules: {
      title: 'required',
      context: 'required'
    }
  });
</script>
</body>
</html>