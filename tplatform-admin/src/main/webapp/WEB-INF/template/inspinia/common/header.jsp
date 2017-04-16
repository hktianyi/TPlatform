<%@ page pageEncoding="UTF-8" language="java" %>
<nav class="navbar-default navbar-static-side" role="navigation">
  <div class="sidebar-collapse">
    <ul class="nav metismenu" id="side-menu">
      <li class="nav-header">
        <div class="dropdown profile-element">
          <span><img alt="image" class="img-circle" src="${_PATH}/static/common/image/profile/${_USER.avatarUrl}" onerror="this.src='${_PATH}/static/common/image/logo.png'" width="48" height="48"/></span>
          <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:;">
            <span class="clear">
              <span class="block m-t-xs"><strong class="font-bold">${empty _USER.nickname ? _USER.username : _USER.nickname}</strong></span>
              <span class="text-muted text-xs block">
                <c:forEach items="${_USER.roles}" var="role" varStatus="status">
                  ${role.name}${status.last ? '' : ','}
                </c:forEach>
                <b class="caret"></b>
              </span>
            </span>
          </a>
          <ul class="dropdown-menu animated fadeInRight m-t-xs">
            <li><a href="${_PATH}/profile">个人信息</a></li>
            <%--<li><a href="${_PATH}/contacts">联系人</a></li>
            <li><a href="${_PATH}/mailbox">收件箱</a></li>
            <li class="divider"></li>
            <li><a href="${_PATH}/logout">退出</a></li>--%>
          </ul>
        </div>
        <div class="logo-element">TP</div>
      </li>
      <t:menu template="<li class=\"\"><a href=\"%s\"><i class=\"fa fa-%s\"></i> <span class=\"nav-label\">%s</span>%s</a>%s</li>,<ul class=\"nav nav-second-level\">%s</ul>,<li class=\"\"><a href=\"%s\" icon=\"%s\">%s</a></li>" arrow="<span class=\"fa arrow\"></span>"/>
    </ul>
  </div>
</nav>