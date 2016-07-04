<%@ page pageEncoding="UTF-8" language="java" %>
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
  <!-- BEGIN HEADER INNER -->
  <div class="page-header-inner">
    <!-- BEGIN LOGO -->
    <div class="page-logo">
      <a href="${_PATH}/main.html">
        <img src="${_PATH}/static/common/img/logo.png" alt="logo" class="logo-default"/>
      </a>
      <div class="menu-toggler sidebar-toggler">
        <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
      </div>
    </div>
    <!-- END LOGO -->
    <!-- BEGIN RESPONSIVE MENU TOGGLER -->
    <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
    </a>
    <!-- END RESPONSIVE MENU TOGGLER -->
    <!-- BEGIN TOP NAVIGATION MENU -->
    <div class="top-menu">
      <ul class="nav navbar-nav pull-right">
        <%--<li class="dropdown">
          <a href="http://www.keenthemes.com/preview/metronic/theme/admin_1/" target="_blank" class="dropdown-toggle">
            <i class="icon-social-dribbble"></i>
          </a>
        </li>--%>
        <!-- BEGIN USER LOGIN DROPDOWN -->
        <li class="dropdown dropdown-user">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
            <img alt="" class="img-circle" src="${_PATH}/static/pages/user/img/${user.avatarUrl}_small.jpg"/>
                    <span class="username">
                    ${user.nickname} </span>
            <i class="fa fa-angle-down"></i>
          </a>
          <ul class="dropdown-menu">
            <%--<li>
              <a href="#">
                <i class="icon-member"></i> 个人中心 </a>
            </li>--%>
            <%--<li>
              <a href="page_calendar.html">
                <i class="icon-calendar"></i> My Calendar </a>
            </li>
            <li>
              <a href="inbox.html">
                <i class="icon-envelope-open"></i> My Inbox <span class="badge badge-danger">
                            3 </span>
              </a>
            </li>
            <li>
              <a href="#">
                <i class="icon-rocket"></i> My Tasks <span class="badge badge-success">
                            7 </span>
              </a>
            </li>
            <li class="divider">
            </li>
            <li>
              <a href="extra_lock.html">
                <i class="icon-lock"></i> Lock Screen </a>
            </li>--%>
            <li>
              <a href="${_PATH}/logout.html">
                <i class="icon-power"></i> 退出 </a>
            </li>
          </ul>
        </li>
        <!-- END USER LOGIN DROPDOWN -->
        <!-- BEGIN QUICK SIDEBAR TOGGLER -->
        <%--<li class="dropdown dropdown-quick-sidebar-toggler">
          <a href="javascript:;" class="dropdown-toggle">
            <i class="icon-bubbles"></i>
          </a>
        </li>--%>
        <!-- END QUICK SIDEBAR TOGGLER -->
      </ul>
    </div>
    <!-- END TOP NAVIGATION MENU -->
  </div>
  <!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix"> </div>