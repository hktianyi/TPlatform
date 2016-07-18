<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<link href="${_PATH}/static/plugins/jplayer/css/jplayer.blue.monday.min.css" rel="stylesheet" type="text/css"/>
<div class="page-bar">
  <ul class="page-breadcrumb">
    <li>
      <a href="${_PATH}/main.html">首页</a>
      <i class="fa fa-angle-right"></i>
    </li>
    <li>
      <span>案例信息</span>
    </li>
  </ul>
</div>
<div class="row">
  <div class="col-md-12">
    <div class="portlet box blue">
      <div class="portlet-title">
        <div class="caption">
          <i class="fa fa-tag"></i>案例信息
        </div>
      </div>
      <div class="portlet-body form">
        <form class="form-horizontal" role="form">
          <div class="form-body">
            <h3 class="form-section">基础信息</h3>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">案例编号:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.caseCode}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">状态:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">
                      <c:choose>
                      <c:when test="${data.bizStatus eq 4}"><span id="bizStatus">待审核</span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="btn-group btn-group-xs btn-group-solid">
                      <button type="button" class="btn green" onclick="approve('${data.id}', true)">通过</button>
                      <button type="button" class="btn red" onclick="approve('${data.id}', false)">不通过</button>
                    </div>
                    </c:when>
                    <c:when test="${data.bizStatus eq 5}">审核通过</c:when>
                    <c:when test="${data.bizStatus eq 6}">审核未通过</c:when>
                    <c:otherwise>待付款</c:otherwise>
                    </c:choose>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">参赛类别:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.caseType eq 1 ? '产品与服务类' : '专项类'}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">参赛类别2:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">
                      <c:forEach items="${data.caseType eq 1 ? dic_caseTypeA : dic_caseTypeB}" var="dic_caseType">
                        <c:if test="${data.caseType2 eq dic_caseType.value}">${dic_caseType.zhName}</c:if>
                      </c:forEach>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">案例名称:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.caseName}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">品牌名称:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.brandName}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">广告客户名称:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.customerName}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">产品服务类别描述:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.productDesc}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">营销传播范围:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">
                      <c:choose>
                        <c:when test="${data.marketingRange eq 1}">地市级</c:when>
                        <c:when test="${data.marketingRange eq 2}">省级</c:when>
                        <c:when test="${data.marketingRange eq 3}">跨地区</c:when>
                        <c:when test="${data.marketingRange eq 4}">全国</c:when>
                        <c:when test="${data.marketingRange eq 5}">跨国</c:when>
                        <c:otherwise>${data.marketingRange}</c:otherwise>
                      </c:choose>
                    </p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">备注:</label>
                  <div class="col-md-9">
                    <p class="form-control-static" id="remarks">${data.remarks}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">案例概述（中文）:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.summaryChina}</p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">案例概述（英文）:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">${data.summaryEng}</p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">案例出版权限:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">
                      <c:choose>
                        <c:when test="${data.visionAccess eq 1}">允许</c:when>
                        <c:when test="${data.visionAccess eq 2}">允许编辑后发布</c:when>
                        <c:when test="${data.visionAccess eq 3}">不允许</c:when>
                        <c:otherwise>${data.visionAccess}</c:otherwise>
                      </c:choose>
                    </p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">案例投放地区:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">
                      <c:if test="${fn:contains(data.caseRegion, '1')}">中国大陆,</c:if>
                      <c:if test="${fn:contains(data.caseRegion, '2')}">台湾,</c:if>
                      <c:if test="${fn:contains(data.caseRegion, '3')}">香港,</c:if>
                      <c:if test="${fn:contains(data.caseRegion, '4')}">澳门</c:if>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">参赛表:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">
                      <c:forEach items="${data.competition}" var="competition">
                        <a href="${FILE_DOMAIN}${competition}">预览/下载</a>
                      </c:forEach>
                    </p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">媒体附录:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">
                      <c:if test="${not empty data.recordid}">
                        <a href="javascript:mtflView('${data.id}')">点击查看</a>
                      </c:if>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">公司LOGO:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">
                      <c:forEach items="${data.logo}" var="logo">
                        <c:if test="${not empty logo}"><a
                            href="${FILE_DOMAIN}${logo}">点击下载</a>&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                      </c:forEach>
                    </p>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">授权确认书:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">
                      <c:if test="${not empty data.authConfim}"><a
                          href="${FILE_DOMAIN}${data.authConfim}">预览/下载</a>
                      </c:if>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">创意平面:</label>
                  <div class="col-md-9">
                    <p class="form-control-static">
                    <div id="originality">
                      <c:forEach items="${data.originality}" var="originality">
                        <c:if test="${not empty originality}">
                          <a href="${FILE_DOMAIN}${originality}" rel="lightbox-group">
                            <img src="${FILE_DOMAIN}${originality}" style="width:200px;"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                      </c:forEach>
                    </div>
                    </p>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">创意视频:</label>
                  <div class="col-md-9">
                    <c:if test="${not empty data.caseVideo}">
                      <div id="jp_container_1" class="jp-video jp-video-270p" role="application"
                           aria-label="media player">
                        <div class="jp-type-single">
                          <div id="jquery_jplayer_1" class="jp-jplayer"></div>
                          <div class="jp-gui">
                            <div class="jp-video-play">
                              <button class="jp-video-play-icon" role="button" tabindex="0">播放</button>
                            </div>
                            <div class="jp-interface">
                              <div class="jp-progress">
                                <div class="jp-seek-bar">
                                  <div class="jp-play-bar"></div>
                                </div>
                              </div>
                              <div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
                              <div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
                              <div class="jp-controls-holder">
                                <div class="jp-controls">
                                  <button class="jp-play" role="button" tabindex="0">播放</button>
                                  <button class="jp-stop" role="button" tabindex="0">停止</button>
                                </div>
                                <div class="jp-volume-controls">
                                  <button class="jp-mute" role="button" tabindex="0">静音</button>
                                  <button class="jp-volume-max" role="button" tabindex="0">最大音量</button>
                                  <div class="jp-volume-bar">
                                    <div class="jp-volume-bar-value"></div>
                                  </div>
                                </div>
                                <div class="jp-toggles">
                                  <button class="jp-repeat" role="button" tabindex="0">重复</button>
                                  <button class="jp-full-screen" role="button" tabindex="0">全屏</button>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </c:if>
                  </div>
                </div>
              </div>
            </div>
            <h3 class="form-section">公司信息</h3>
            <div class="row">
              <div class="panel-group accordion" id="companyList">
                <c:forEach items="${data.companyList}" var="proxy" varStatus="status">
                  <c:choose>
                    <c:when test="${proxy.companyType eq 11}">
                      <div class="panel panel-default">
                        <div class="panel-heading">
                          <h4 class="panel-title">
                            <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse"
                               data-parent="#accordion3" href="#collapse_${status.index}">
                              <c:choose>
                                <c:when
                                    test="${fn:length(proxy.companyName) > 30}">${fn:substring(proxy.companyName,0,30)}...</c:when>
                                <c:otherwise>${proxy.companyName}</c:otherwise>
                              </c:choose>
                            </a>
                          </h4>
                        </div>
                        <div id="collapse_${status.index}" class="panel-collapse in">
                          <div class="panel-body">
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">公司名称:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.companyName}</p>
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">官网:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.website}</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">办公地址:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.address}</p>
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">城市:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.city}</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">客户公司网络:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.network}</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">主要联系人称谓:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">
                                      <c:forEach items="${projectTitle}" var="projectTitle">
                                        <c:if test="${proxy.projectTitle eq projectTitle.value}">${projectTitle.zhName}</c:if>
                                      </c:forEach>
                                    </p>
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">主要联系人全名:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.projectName}</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">主要联系人职位:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.projectPosition}</p>
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">主要联系人电话:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.projectTel}</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">主要联系人手机:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.projectPhone}</p>
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">主要联系人邮箱:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.projectEmail}</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </c:when>
                    <c:otherwise>
                      <div class="panel panel-default">
                        <div class="panel-heading">
                          <h4 class="panel-title">
                            <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse"
                               data-parent="#accordion3" href="#collapse_${status.index}">
                              <c:choose>
                                <c:when
                                    test="${fn:length(proxy.companyName) > 30}">${fn:substring(proxy.companyName,0,30)}...</c:when>
                                <c:otherwise>${proxy.companyName}</c:otherwise>
                              </c:choose>
                            </a>
                          </h4>
                        </div>
                        <div id="collapse_${status.index}" class="panel-collapse in">
                          <div class="panel-body">
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">公司名称:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.companyName}</p>
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">官网:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.website}</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">地址:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.address}</p>
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">城市:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.city}</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">代理公司网络:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">
                                      <c:forEach items="${network}" var="network">
                                        <c:if test="${proxy.network eq network.value}">
                                          ${network.zhName}
                                          <c:if test="${proxy.network eq 99}">
                                            &nbsp;&nbsp;${proxy.netOther}
                                          </c:if>
                                        </c:if>
                                      </c:forEach>
                                    </p>
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">控股集团:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">
                                      <c:forEach items="${holdingGroup}" var="holdingGroup">
                                        <c:if test="${proxy.holdingGroup eq holdingGroup.value}">
                                          ${holdingGroup.zhName}
                                          <c:if test="${proxy.holdingGroup eq 99}">
                                            &nbsp;&nbsp;${proxy.holdOther}
                                          </c:if>
                                        </c:if>
                                      </c:forEach>
                                    </p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">项目联系人称谓:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">
                                      <c:forEach items="${projectTitle}" var="projectTitle">
                                        <c:if test="${proxy.projectTitle eq projectTitle.value}">${projectTitle.zhName}</c:if>
                                      </c:forEach>
                                    </p>
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">项目联系人全名:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.projectName}</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">项目联系人职位:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.projectPosition}</p>
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">项目联系人电话:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.projectTel}</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="row">
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">项目联系人手机:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.projectPhone}</p>
                                  </div>
                                </div>
                              </div>
                              <div class="col-md-6">
                                <div class="form-group">
                                  <label class="control-label col-md-3">项目联系人邮箱:</label>
                                  <div class="col-md-9">
                                    <p class="form-control-static">${proxy.projectEmail}</p>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
              </div>
            </div>
            <h3 class="form-section">联系人信息</h3>
            <div class="row">
              <div class="panel-group accordion" id="personList">
                <c:forEach items="${data.personList}" var="person" varStatus="status">
                  <div class="panel panel-default">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse"
                           data-parent="#accordion3" href="#person_${status.index}"> ${person.name} </a>
                      </h4>
                    </div>
                    <div id="person_${status.index}" class="panel-collapse in">
                      <div class="panel-body">
                        <div class="row">
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">姓名:</label>
                              <div class="col-md-9">
                                <p class="form-control-static">${person.name}</p>
                              </div>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">职位:</label>
                              <div class="col-md-9">
                                <p class="form-control-static">${person.position}</p>
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">电子邮箱:</label>
                              <div class="col-md-9">
                                <p class="form-control-static">${person.email}</p>
                              </div>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">公司名称:</label>
                              <div class="col-md-9">
                                <p class="form-control-static">${person.companyName}</p>
                              </div>
                            </div>
                          </div>
                        </div>
                        <%--<div class="row">
                          <div class="col-md-12">
                            <div class="form-group">
                              <label class="control-label col-md-3">备注:</label>
                              <div class="col-md-9">
                                <p class="form-control-static">${person.remarks}</p>
                              </div>
                            </div>
                          </div>
                        </div>--%>
                      </div>
                    </div>
                  </div>
                </c:forEach>
              </div>
            </div>
          </div>
          <div class="form-actions">
            <div class="row">
              <div class="col-md-6">
                <div class="row">
                  <div class="col-md-offset-3 col-md-9">
                    <%--<button type="submit" class="btn green">
                      <i class="fa fa-pencil"></i> Edit
                    </button>--%>
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
<%@include file="/WEB-INF/common/footer.jsp" %>
<script src="${_PATH}/static/plugins/layer/extend/layer.ext.js" type="text/javascript"></script>
<script src="${_PATH}/static/plugins/jplayer/jquery.jplayer.min.js"></script>
<script type="text/javascript">
  $("#jquery_jplayer_1").jPlayer({
    ready: function () {
      $(this).jPlayer("setMedia", {
        m4v: "${FILE_DOMAIN}${data.caseVideo}"
      });
    },
    swfPath: _PATH + "/static/plugin/jplayer",
    supplied: "webmv, ogv, m4v",
    size: {
      width: "480px",
      cssClass: "jp-video-270p"
    },
    useStateClassSkin: true,
    autoBlur: false,
    smoothPlayBar: true,
    keyEnabled: true,
    remainingDuration: true,
    toggleDuration: true
  });
  layer.ready(function () {
    layer.photos({
      shift: 5,
      photos: '#originality'
    });
  });
  function approve(id, pass) {
    layer.prompt({
      title: '输入审核意见',
      formType: 2 //prompt风格，支持0-2
    }, function (remark) {
      $.ajax(_PATH + '/plan/approve?id=' + id + '&pass=' + pass + '&remark=' + remark, {
        type: 'POST',
        success: function (resp) {
          console.log(resp);
          if (resp.statusCode === 200) {
            layer.msg('审核成功');
            $('#bizStatus').html(pass ? '审核通过' : '审核未通过');
            $('#remarks').html(remark);
          }
          else layer.msg('审核失败', {icon: 6});
        },
        error: function () {
          layer.msg('审核失败', {icon: 6});
        }
      });
    });
  }
  function mtflView(id) {
    layer.open({
      type: 2,
      title: '案例预览',
      closeBtn: 2,
      shadeClose: true,
      shade: 0.8,
      area: ['90%', '500px'],
      content: _PATH + '/plan/mtflView/' + id + '?layer=2' //iframe的url
    });
  }
</script>