<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page pageEncoding="UTF-8" language="java" %>
<link href="${_PATH}/static/plugins/jplayer/css/jplayer.blue.monday.min.css" rel="stylesheet" type="text/css"/>
<div class="row">
  <div class="col-md-12">
    <div class="portlet light bordered">
      <%--<div class="portlet-title">
        <div class="caption">
          <i class="icon-equalizer font-red-sunglo"></i>
          <span class="caption-subject font-red-sunglo bold uppercase">${data.company}上市信息</span>
          <span class="caption-helper">some info...</span>
        </div>
        <div class="tools">
          <a href="" class="collapse"> </a>
          <a href="#portlet-config" data-toggle="modal" class="config"> </a>
          <a href="" class="reload"> </a>
          <a href="" class="remove"> </a>
        </div>
      </div>--%>
      <div class="portlet-body form">
        <!-- BEGIN FORM-->
        <form class="form-horizontal">
          <div class="form-body">
            <h3 class="form-section">案例信息</h3>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">案例编号</label>
                  <div class="col-md-9">
                    <input type="text" name="caseCode" class="form-control" value="${data.caseCode}">
                    <span class="help-block"></span>
                  </div>
                </div>
              </div>
              <!--/span-->
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">案例名称</label>
                  <div class="col-md-9">
                    <input type="text" name="caseName" class="form-control" value="${data.caseName}">
                    <span class="help-block"></span>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">品牌名称</label>
                  <div class="col-md-9">
                    <input type="text" name="brandName" class="form-control" value="${data.brandName}">
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">广告客户名称</label>
                  <div class="col-md-9">
                    <input type="text" name="customerName" class="form-control" value="${data.customerName}">
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">产品服务类别描述</label>
                  <div class="col-md-9">
                    <input type="text" name="productDesc" class="form-control" value="${data.productDesc}">
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">营销传播范围</label>
                  <div class="col-md-9">
                    <input type="text" name="marketingRange" class="form-control" value="${data.marketingRange}">
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">参赛类别</label>
                  <div class="col-md-9">
                    <input type="text" name="caseType" class="form-control" value="${data.caseType}">
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">参赛类别2</label>
                  <div class="col-md-9">
                    <input type="text" name="caseType2" class="form-control" value="${data.caseType2}">
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="control-label col-md-3">案例概述（中文）</label>
                  <div class="col-md-9">
                    <input type="text" name="summaryChina" class="form-control" value="${data.summaryChina}">
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label class="control-label col-md-3">案例概述（英文）</label>
                  <div class="col-md-9">
                    <input type="text" name="summaryEng" class="form-control" value="${data.summaryEng}">
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">案例出版权限</label>
                  <div class="col-md-9">
                    <input type="text" name="visionAccess" class="form-control" value="${data.visionAccess}">
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">案例投放地区</label>
                  <div class="col-md-9">
                    <input type="text" name="caseRegion" class="form-control" value="${data.caseRegion}">
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">参赛表</label>
                  <div class="col-md-9">
                    <c:forEach items="${data.competition}" var="competition">
                      <a href="${FILE_DOMAIN}${competition}">预览/下载</a>
                    </c:forEach>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">媒体附录</label>
                  <div class="col-md-9">
                    <c:choose>
                      <c:when test="${not empty data.recordid}">
                        <a href="${_PATH}/plan/mtflView/${data.id}">点击查看</a>
                      </c:when>
                      <c:otherwise>
                        <span style="color: rgb(153, 153, 153);">无</span>
                      </c:otherwise>
                    </c:choose>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">创意平面</label>
                  <div class="col-md-9">
                    <div id="originality">
                      <c:forEach items="${data.originality}" var="originality">
                        <c:if test="${not empty originality}">
                          <a href="${FILE_DOMAIN}${originality}" rel="lightbox-group">
                            <img src="${FILE_DOMAIN}${originality}" style="width:200px;"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:if>
                      </c:forEach>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">创意视频</label>
                  <div class="col-md-9">
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
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">公司LOGO</label>
                  <div class="col-md-9">
                    <c:forEach items="${data.logo}" var="logo">
                      <c:if test="${not empty logo}"><a
                          href="${FILE_DOMAIN}${logo}">点击下载</a>&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
                    </c:forEach>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">授权确认书</label>
                  <div class="col-md-9">
                    <a href="${FILE_DOMAIN}${data.authConfim}">预览/下载</a>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">付款凭证</label>
                  <div class="col-md-9">
                    <div id="photo">
                        <%--<img src="${FILE_DOMAIN}${data.payId}" style="width:200px;"/>--%>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">审核</label>
                  <div class="col-md-9">
                    <div class="radio-list">
                      <label class="radio-inline">
                        <input type="radio" name="bizStatus" value="5"
                               <c:if test="${'5' eq data.bizStatus}">checked</c:if> /> 通过 </label>
                      <label class="radio-inline">
                        <input type="radio" name="bizStatus" value="6"
                               <c:if test="${'6' eq data.bizStatus}">checked</c:if> /> 不通过 </label>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label class="control-label col-md-3">备注</label>
                  <div class="col-md-9">
                    <input type="text" name="remarks" class="form-control" value="${data.remarks}">
                  </div>
                </div>
              </div>
            </div>
            <h3 class="form-section">公司信息</h3>
            <div class="row">
              <div class="panel-group accordion" id="companyList">
                <c:forEach items="${data.companyList}" var="proxy" varStatus="status">
                  <div class="panel panel-default">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse"
                           data-parent="#accordion3" href="#collapse_${status.index}"> ${proxy.companyName} </a>
                      </h4>
                    </div>
                    <div id="collapse_${status.index}" class="panel-collapse in">
                      <div class="panel-body">
                        <div class="row">
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">公司名称</label>
                              <div class="col-md-9">
                                <input type="text" name="brandName" class="form-control" value="${proxy.companyName}">
                              </div>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">官网</label>
                              <div class="col-md-9">
                                <input type="text" name="customerName" class="form-control" value="${proxy.website}">
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">地址</label>
                              <div class="col-md-9">
                                <input type="text" name="brandName" class="form-control" value="${proxy.address}">
                              </div>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">城市</label>
                              <div class="col-md-9">
                                <input type="text" name="customerName" class="form-control" value="${proxy.city}">
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">代理公司网络</label>
                              <div class="col-md-9">
                                <input type="text" name="brandName" class="form-control" value="${proxy.network}">
                              </div>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">控股集团</label>
                              <div class="col-md-9">
                                <input type="text" name="customerName" class="form-control"
                                       value="${proxy.holdingGroup}">
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">项目联系人称谓</label>
                              <div class="col-md-9">
                                <input type="text" name="brandName" class="form-control" value="${proxy.projectTitle}">
                              </div>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">项目联系人全名</label>
                              <div class="col-md-9">
                                <input type="text" name="customerName" class="form-control"
                                       value="${proxy.projectName}">
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">项目联系人职位</label>
                              <div class="col-md-9">
                                <input type="text" name="brandName" class="form-control"
                                       value="${proxy.projectPosition}">
                              </div>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">项目联系人电话</label>
                              <div class="col-md-9">
                                <input type="text" name="customerName" class="form-control" value="${proxy.projectTel}">
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">项目联系人手机</label>
                              <div class="col-md-9">
                                <input type="text" name="brandName" class="form-control" value="${proxy.projectPhone}">
                              </div>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">项目联系人邮箱</label>
                              <div class="col-md-9">
                                <input type="text" name="customerName" class="form-control"
                                       value="${proxy.projectEmail}">
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
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
                              <label class="control-label col-md-3">姓名</label>
                              <div class="col-md-9">
                                <input type="text" name="brandName" class="form-control" value="${person.name}">
                              </div>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">职位</label>
                              <div class="col-md-9">
                                <input type="text" name="customerName" class="form-control" value="${person.position}">
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">电子邮箱</label>
                              <div class="col-md-9">
                                <input type="text" name="brandName" class="form-control" value="${person.email}">
                              </div>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="form-group">
                              <label class="control-label col-md-3">公司名称</label>
                              <div class="col-md-9">
                                <input type="text" name="customerName" class="form-control"
                                       value="${person.companyName}">
                              </div>
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col-md-12">
                            <div class="form-group">
                              <label class="control-label col-md-3">备注</label>
                              <div class="col-md-9">
                                <input type="text" name="brandName" class="form-control" value="${person.remarks}">
                              </div>
                            </div>
                          </div>
                        </div>
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
                    <%--<button type="button" class="btn green" onclick="save()">保存</button>--%>
                    <button type="button" class="btn default" onclick="history.back();">返回</button>
                  </div>
                </div>
              </div>
              <div class="col-md-6"></div>
            </div>
          </div>
          <input type="hidden" name="id" value="${data.id}">
        </form>
        <!-- END FORM-->
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
        m4v: "${FILE_DOMAIN}${caseInfo.caseVideo}"
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
//      layer.photos({
//          shift: 5,
//          photos: '.photo'
//      });
      layer.photos({
          shift: 5,
          photos: '#originality'
      });
  });
  function save() {
    $.ajax(_PATH + '/plan/save', {
      type: 'POST',
      data: $('form').serialize(),
      success: function (resp) {
        if (resp.statusCode === 200) {
          window.location.href = _PATH + '/${MODULE_NAME}/list';
        }
        else layer.msg('添加失败', {icon: 6});
      },
      error: function () {
        layer.msg('添加失败', {icon: 6});
      }
    });
  }
</script>
</html>