<%@ taglib prefix="view" uri="/taglib/view.tld" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<!-- BEGIN HEADER -->
<div class="page-header navbar navbar-fixed-top">
  <!-- BEGIN HEADER INNER -->
  <div class="page-header-inner ">
    <!-- BEGIN LOGO -->
    <div class="page-logo">
      <a href="${_PATH}/main.html">
        <img src="${_PATH}/static/common/img/logo.png" alt="logo" class="logo-default" /> </a>
      <div class="menu-toggler sidebar-toggler"> </div>
    </div>
    <!-- END LOGO -->
    <!-- BEGIN MEGA MENU -->
    <!-- DOC: Remove "hor-menu-light" class to have a horizontal menu with theme background instead of white background -->
    <!-- DOC: This is desktop version of the horizontal menu. The mobile version is defined(duplicated) in the responsive menu below along with sidebar menu. So the horizontal menu has 2 seperate versions -->
    <div class="hor-menu   hidden-sm hidden-xs">
      <ul class="nav navbar-nav">
        <!-- DOC: Remove data-hover="megamenu-dropdown" and data-close-others="true" attributes below to disable the horizontal opening on mouse hover -->
        <view:menu type="H" />
      </ul>
    </div>
    <!-- END MEGA MENU -->
    <!-- BEGIN HEADER SEARCH BOX -->
    <!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->
    <form class="search-form" action="#" method="GET">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search..." name="query">
        <span class="input-group-btn">
                            <a href="javascript:;" class="btn submit">
                                <i class="icon-magnifier"></i>
                            </a>
                        </span>
      </div>
    </form>
    <!-- END HEADER SEARCH BOX -->
    <!-- BEGIN RESPONSIVE MENU TOGGLER -->
    <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
    <!-- END RESPONSIVE MENU TOGGLER -->
    <!-- BEGIN TOP NAVIGATION MENU -->
    <div class="top-menu">
      <ul class="nav navbar-nav pull-right">
        <li class="dropdown dropdown-user">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
            <img alt="" class="img-circle" src="${_PATH}/static/pages/user/img/${_USER.avatarUrl}_small.jpg"/>
            <span class="username">
              ${_USER.nickname} </span>
            <i class="fa fa-angle-down"></i>
          </a>
          <ul class="dropdown-menu">
            <li>
              <a href="${_PATH}/sysUser/profile">
                <i class="fa fa-user"></i> 个人中心 </a>
            </li>
            <li>
              <a href="${_PATH}/logout.html">
                <i class="icon-power"></i> 退出 </a>
            </li>
          </ul>
        </li>
        <li class="dropdown dropdown-quick-sidebar-toggler">
          <a href="javascript:;" class="dropdown-toggle">
            <i class="icon-logout"></i>
          </a>
        </li>
      </ul>
    </div>
  </div>
</div>
<div class="clearfix"> </div>
