<%@ page pageEncoding="UTF-8" language="java" %>
<div class="page-bar">
  <ul class="page-breadcrumb">
    <li>
      <a href="${_PATH}/main.html">首页</a>
      <i class="fa fa-angle-right"></i>
    </li>
    <li>
      <span>系统管理</span>
      <i class="fa fa-angle-right"></i>
    </li>
    <li>
      <span>数据池监控</span>
    </li>
  </ul>
  <div class="page-toolbar">
    <button type="button" class="btn btn-circle blue btn-outline btn-xs">&nbsp;?&nbsp;</button>
  </div>
</div>
<div class="row" style="margin-top: 6px;">
  <div class="col-md-12">
    <iframe scrolling="no" frameborder="0" width="100%" src="${_PATH}/sysInfo/druid/index.html"
            onload="this.style.height=this.contentDocument.body.scrollHeight +'px';"></iframe>
  </div>
</div>