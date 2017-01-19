<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="../../common/common.jsp" %>
  <link rel='stylesheet' href='${_PATH}/static/plugins/unitegallery/css/unite-gallery.css' type='text/css' />
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
            <a href="${_PATH}/order/list">订单管理</a>
            <i class="fa fa-angle-right"></i>
          </li>
          <li>
            <span>方案管理</span>
          </li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="portlet box blue">
            <div class="portlet-title">
              <div class="caption">
                <i class="fa fa-tag"></i>方案详情
              </div>
            </div>
            <div class="portlet-body form">
              <form class="form-horizontal" role="form">
                <input type="hidden" name="id" value="${data.id}"/>
                <input type="hidden" name="hotelId" value="${data.hotelId}"/>
                <%--<input type="hidden" name="orderId" value="${not empty data.orderId ? data.orderId : orderId}"/>--%>
                <%-- 计划删掉OrderSolution，暂时使用solutionId存放orderId --%>
                <input type="hidden" name="orderId" value="${not empty data.orderId ? data.orderId : orderId}"/>
                <div class="form-body">
                  <h3 class="form-section">基础信息</h3>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">封面图片:</label>
                        <div class="col-md-8">
                          <img id="imgUrl" src="${FILE_DOMAIN}${data.hotel.imgUrl}" style="width: 200px; height: 150px; cursor: pointer;" alt="方案封面"/>
                          <input type="hidden" name="imgUrl" value="${data.hotel.imgUrl}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">方案名:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="planName" value="${data.planName}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">酒店名:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="name" value="${data.hotel.name}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">最近装修时间:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="decorationDate" value="${data.hotel.extend.decorationDate}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">星级:</label>
                        <div class="col-md-8">
                          <select class="form-control" id="star" name="star">
                            <option value="">请选择</option>
                            <c:forEach items="${hotel_star}" var="l">
                              <option value="${l.value}" ${data.level eq l.value  ? 'selected' : ''}>${l.zhName}</option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">会场数量:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="venueNum" value="${data.hotel.venueNum}"/>
                        </div>
                      </div>
                    </div>
                    <%--<div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">是否推荐:</label>
                        <div class="col-md-8">
                          <select class="form-control" name="recommend">
                            <option value="1" <c:if test="${data.recommend eq '1'}">selected</c:if>>不推荐</option>
                            <option value="2" <c:if test="${data.recommend eq '2'}">selected</c:if>>推荐</option>
                          </select>
                        </div>
                      </div>
                    </div>--%>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2">地址:</label>
                        <div class="col-md-9">
                          <input class="form-control" name="street" value="${data.hotel.street}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2">酒店描述:</label>
                        <div class="col-md-9">
                          <textarea class="form-control" name="extend.remark" rows="8">${data.hotel.extend.remark}</textarea>
                        </div>
                      </div>
                    </div>
                  </div>

                  <h3 class="form-section">会场信息</h3>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">会场名:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="venueName" value="${data.venueName}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">总价:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="totalPrice" value="${data.totalPrice}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">状态:</label>
                        <div class="col-md-8">
                          <select class="form-control" name="status">
                            <option value="1" <c:if test="${data.status eq '1'}">selected</c:if>>有效</option>
                            <option value="0" <c:if test="${data.status eq '0'}">selected</c:if>>无效</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <table id="imgTable" class="table table-striped table-bordered table-hover">
                          <thead>
                          <tr>
                            <th class="hide" data-name="id"></th>
                            <th class="hide" data-name="planId"></th>
                            <th data-name="imgUrl">预览</th>
                            <th data-name="description">描述</th>
                            <th>操作<span class="table-add glyphicon glyphicon-plus" data-type="Img"></span></th>
                          </tr>
                          </thead>
                          <tbody>
                          <tr class="hide">
                            <td class="hide"></td>
                            <td class="hide">${data.id}</td>
                            <td><img src="#" class="center-block thumbnail"/></td>
                            <td contenteditable="true"></td>
                            <td>
                              <span class="table-remove glyphicon glyphicon-remove" data-type="Img"></span>
                              <%--<span class="table-up glyphicon glyphicon-arrow-up"></span>
                              <span class="table-down glyphicon glyphicon-arrow-down"></span>--%>
                            </td>
                          </tr>
                          <c:forEach items="${data.imgList}" var="img">
                            <tr>
                              <td class="hide">${img.id}</td>
                              <td class="hide">${img.solutionId}</td>
                              <td data-val="${img.imgUrl}"><img src="${FILE_DOMAIN}${img.imgUrl}" class="center-block thumbnail"></td>
                              <td contenteditable="true">${img.remark}</td>
                              <td>
                                <span class="table-remove glyphicon glyphicon-remove" data-id="${img.id}" data-type="Img"></span>
                                  <%--<span class="table-up glyphicon glyphicon-arrow-up"></span>
                                  <span class="table-down glyphicon glyphicon-arrow-down"></span>--%>
                              </td>
                            </tr>
                          </c:forEach>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>

                  <h3 class="form-section">报价明细</h3>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <table id="priceTable" class="table table-striped table-bordered table-hover">
                          <thead>
                          <tr>
                            <th class="hide" data-name="id"></th>
                            <th class="hide" data-name="planId"></th>
                            <th data-name="itemName">项目名称</th>
                            <th data-name="count">数量</th>
                            <th data-name="price">单价</th>
                            <th data-name="subtotal">小计</th>
                            <th>操作<span class="table-add glyphicon glyphicon-plus" data-type="Price"></span></th>
                          </tr>
                          </thead>
                          <tbody>
                          <tr class="hide">
                            <td class="hide"></td>
                            <td class="hide">${data.id}</td>
                            <td contenteditable="true"></td>
                            <td contenteditable="true"></td>
                            <td contenteditable="true"></td>
                            <td contenteditable="true"></td>
                            <td>
                              <span class="table-remove glyphicon glyphicon-remove" data-type="Price"></span>
                              <%--<span class="table-up glyphicon glyphicon-arrow-up"></span>
                              <span class="table-down glyphicon glyphicon-arrow-down"></span>--%>
                            </td>
                          </tr>
                          <c:forEach items="${data.priceList}" var="price">
                            <tr>
                              <td class="hide">${price.id}</td>
                              <td class="hide">${price.solutionId}</td>
                              <td contenteditable="true">${price.itemName}</td>
                              <td contenteditable="true">${price.count}</td>
                              <td contenteditable="true">${price.price}</td>
                              <td contenteditable="true">${price.subtotal}</td>
                              <td>
                                <span class="table-remove glyphicon glyphicon-remove" data-id="${price.id}" data-type="Price"></span>
                                  <%--<span class="table-up glyphicon glyphicon-arrow-up"></span>
                                  <span class="table-down glyphicon glyphicon-arrow-down"></span>--%>
                              </td>
                            </tr>
                          </c:forEach>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2">备注:</label>
                        <div class="col-md-9">
                          <textarea class="form-control" name="remark" rows="8">${data.remark}</textarea>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="form-actions">
                    <div class="row">
                      <div class="col-md-6">
                        <div class="row">
                          <div class="col-md-offset-3 col-md-9">
                            <button type="button" class="btn green" onclick="submitForm()">
                              <i class="fa fa-pencil"></i> 保存
                            </button>
                            <button type="button" class="btn default" onclick="history.back()">取消</button>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6"></div>
                    </div>
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
<div id="modal_hotel_gallery" class="modal container fade bs-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">请选择图片</h4>
      </div>
      <div class="modal-body" id="gallery">
        <c:forEach items="${data.hotel.pictures}" var="pic">
        <a href="javascript:;" class="thumbnail"><img src="${FILE_DOMAIN}${pic.url}" data-image="${FILE_DOMAIN}${pic.url}" alt="${pic.name}"></a>
        </c:forEach>
      </div>
      <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">关闭</button>
      </div>
    </div>
  </div>
</div>
<!-- END CONTAINER -->
<%@include file="/WEB-INF/common/footer.jsp" %>
<script type='text/javascript' src='${_PATH}/static/plugins/unitegallery/js/unitegallery.js'></script>
<script type='text/javascript' src='${_PATH}/static/plugins/unitegallery/themes/tiles/ug-theme-tiles.js'></script>
<script type="text/javascript">
  var galleyCallback, api_unitegallery, _FILEDOMAIN = '${FILE_DOMAIN}';
  $(function () {
    $('.table-add').click(function () {
      var $TABLE = $(this).parents("table");
      $TABLE.append($TABLE.find('tr.hide').clone(true).removeClass('hide'));
    });

    $('.table-remove').click(function () {
      var _this = $(this), id = _this.data("id");
      if (id)
        $.ajax(_PATH + '/solution/del' + _this.data("type") + '/' + id, {
          type:'DELETE',
          success: function (resp) {
            if (resp.statusCode === 200)
              _this.parents('tr').detach();
            else
              alert("删除失败！");
          }
        });
      else
        _this.parents('tr').detach();
    });

    $('.table-up').click(function () {
      var $row = $(this).parents('tr');
      if ($row.index() === 1) return; // Don't go above the header
      $row.prev().before($row.get(0));
    });

    $('.table-down').click(function () {
      var $row = $(this).parents('tr');
      if ($row.index() === $row.siblings('tr').length - 1) return; // Don't go above the header
      $row.next().after($row.get(0));
    });

    $('#imgTable').on('click', 'img', function () {
      var _this = this;
      $('#modal_hotel_gallery').modal();
      galleyCallback = function(imgUrl) {
        _this.src = _FILEDOMAIN + imgUrl;
        $(_this).parent().data("val", imgUrl);
      }
    });

//    $("#imgUrl").click(function() {
//      var _this = this;
//      $('#modal_hotel_gallery').modal();
//      galleyCallback = function(imgUrl) {
//        _this.src = _FILEDOMAIN + imgUrl;
//        $("input[name='imgUrl']").val(imgUrl);
//      }
//    });

    $('#modal_hotel_gallery').on('shown.bs.modal', function (e) {
      api_unitegallery.resize();
    });

    createUnitegallery();
  });
  $("input[name='name']").autocomplete({
    serviceUrl: _PATH + "/hotel/findSuggest",
    paramName: "hotelName",
    dataType: "json",
    transformResult: function(response) {
      return {
        suggestions: $.map(response.data, function(item) {
          return { value: item.name, data: item.id };
        })
      };
    },
    onSelect: function (suggestion) {
      autocomplete(suggestion.data);
    }
  });
  function autocomplete(id) {
    if(id) {
      $.getJSON(_PATH + "/hotel/"+id, function(resp) {
        if(resp.statusCode === 200) {
          var data = resp.data;
          //酒店信息
          $("#imgUrl").attr("src", _FILEDOMAIN + data.imgUrl);
          $("input[name='hotelId']").val(data.id);
          $("input[name='imgUrl']").val(data.imgUrl);
          $("input[name='decorationDate']").val(data.extend.decorationDate);
          $("input[name='street']").val(data.street);
          $("input[name='star']").val(data.star);
          $("input[name='venueNum']").val(data.venueNum);
          $("textarea[name='extend.remark']").val(data.extend.remark);
          //会场信息
          $("input[name='venueName']").val("");
          $("input[name='totalPrice']").val("");

          var gallery = '';
          for(var i in data.pictures) {
            gallery += '<a href="javascript:;"' +
                    ' class="thumbnail"><img src="' + _FILEDOMAIN + data.pictures[i].url +
                    '" data-image="' + _FILEDOMAIN + data.pictures[i].url +
                    '" alt="'+data.pictures[i].name+'"></a>';
          }
          $("#gallery").html(gallery);
          createUnitegallery();
        }
      });
    }
  }
  function createUnitegallery() {
    api_unitegallery = $("#gallery").unitegallery({
//            tile_as_link:true,
//            tile_enable_action:false,
      tile_enable_icons:false,
      tile_enable_textpanel:true,
      tile_textpanel_title_text_align: "center",
      tile_textpanel_always_on:true,
      tiles_type: "justified"
    });
    api_unitegallery.on('item_change',function(num, data){
      closeGallery(data.urlImage.replace(_FILEDOMAIN,''));
    });
  }
  //提交表单
  function submitForm() {
    layer.load(2, {shade:[0.6,'#393D49']});
    $.ajax(_PATH + '/solution/save', {
      type: "POST",
      contentType: 'application/json;charset=utf-8',
      data: JSON.stringify($.extend($("form").serializeJson(),
              {'imgList': $("#imgTable").serializeTable()}, {'priceList': $("#priceTable").serializeTable()})),
      success: function (resp) {
        if (resp.statusCode === 200)
          window.location.href = _PATH + "/solution/list/" + $("input[name='orderId']").val();
        else
          alert("保存失败！");
        layer.closeAll('loading');
      },
      error: function (res) {
        layer.closeAll('loading');
        alert(res.responseText);
      }
    })
  }

  function closeGallery(imgUrl) {
    if(galleyCallback) galleyCallback(imgUrl);
    $('#modal_hotel_gallery').modal('hide');
    galleyCallback = null;
    setTimeout(function () {
      $('.ug-lightbox').hide();
    }, 200);
  }
</script>
</body>
</html>