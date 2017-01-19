<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="../../common/common.jsp" %>
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
            <span>酒店管理</span>
          </li>
        </ul>
      </div>
      <div class="row">
        <div class="col-md-12">
          <div class="portlet box blue">
            <div class="portlet-title">
              <div class="caption">
                <i class="fa fa-tag"></i>酒店详情
              </div>
            </div>
            <div class="portlet-body form">
              <form class="form-horizontal" role="form">
                <input type="hidden" name="id" value="${data.id}">
                <div class="form-body">
                  <h3 class="form-section">基础信息</h3>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">封面图片:</label>
                        <div class="col-md-8">
                          <img id="imgUrl" src="${FILE_DOMAIN}${data.imgUrl}" style="width: 200px; height: 150px; cursor: pointer;" onerror="this.src='http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=选择图片'" alt="酒店封面"/>
                          <input type="hidden" name="imgUrl" value="${data.imgUrl}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">联系人:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="extend.contacts" value="${data.extend.contacts}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">联系方式:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="extend.phone" value="${data.extend.phone}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">酒店名称:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="name" value="${data.name}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">星级:</label>
                        <div class="col-md-8">
                          <select class="form-control" id="star" name="star">
                            <option value="">请选择</option>
                            <c:forEach items="${hotel_star}" var="l">
                              <option value="${l.value}" ${data.star eq l.value  ? 'selected' : ''}>${l.zhName}</option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">状态:</label>
                        <div class="col-md-8">
                          <select class="form-control" name="status">
                            <option value="1" <c:if test="${data.status eq 1}">selected</c:if>>有效</option>
                            <option value="0" <c:if test="${data.status eq 0}">selected</c:if>>无效</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">最近装修时间:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="extend.openDate" value="${data.extend.openDate}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">最大厅面积:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="venueAreaMax" value="${data.venueAreaMax}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">是否推荐:</label>
                        <div class="col-md-8">
                          <select class="form-control" name="grade">
                            <option value="1" <c:if test="${data.grade eq 1}">selected</c:if>>不推荐</option>
                            <option value="2" <c:if test="${data.grade eq 0}">selected</c:if>>推荐</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">省:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="province" value="${data.province}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">市:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="city" value="${data.city}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">区:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="county" value="${data.county}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2">详细地址:</label>
                        <div class="col-md-10">
                          <input class="form-control" name="street" value="${data.street}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">容纳人数:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="peopleNumMax" value="${data.peopleNumMax}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">会场数量:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="venueNum" value="${data.venueNum}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">房间数量:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="roomNum" value="${data.roomNum}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">标间价:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="roomPrice" value="${data.roomPrice}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">大床间价:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="roomKingBedPrice" value="${data.roomKingBedPrice}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">自助餐价:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="buffetPrice" value="${data.buffetPrice}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">桌餐价:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="foodPrice" value="${data.foodPrice}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">门市价:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="venuePrice" value="${data.venuePrice}"/>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label class="control-label col-md-4">销售价:</label>
                        <div class="col-md-8">
                          <input class="form-control" name="venueSalePrice" value="${data.venueSalePrice}"/>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2">酒店介绍:</label>
                        <div class="col-md-8">
                          <textarea class="form-control" name="extend.remark" rows="8">${data.extend.remark}</textarea>
                        </div>
                      </div>
                    </div>
                  </div>

                  <h3 class="form-section">设施和服务</h3>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2">酒店设施和服务:</label>
                        <div class="col-md-8">
                          <textarea class="form-control" name="extend.attr1">${data.extend.attr1}</textarea>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2">酒店餐饮设施:</label>
                        <div class="col-md-8">
                          <textarea class="form-control" name="extend.attr2">${data.extend.attr2}</textarea>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2">会议服务设施:</label>
                        <div class="col-md-8">
                          <textarea class="form-control" name="extend.attr3">${data.extend.attr3}</textarea>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label class="control-label col-md-2">客房设施和服务:</label>
                        <div class="col-md-8">
                          <textarea class="form-control" name="extend.attr4">${data.extend.attr4}</textarea>
                        </div>
                      </div>
                    </div>
                  </div>
                  <h3 class="form-section">会场信息</h3>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <table id="venueTable" class="table table-striped table-bordered table-hover">
                          <thead>
                          <tr>
                            <th class="hide" data-name="id"></th>
                            <th class="hide" data-name="hotelId"></th>
                            <th data-name="name">会场名</th>
                            <th data-name="area">面积</th>
                            <th data-name="tags">标签</th>
                            <th data-name="height">层高</th>
                            <th data-name="peopleNumMax">最大容客量</th>
                            <th data-name="layoutTheater">剧院容量</th>
                            <th data-name="layoutDesk">课桌容量</th>
                            <th data-name="layoutBanquet">宴会容量</th>
                            <th data-name="layoutU">U形容量</th>
                            <th data-name="layoutO">回形容量</th>
                            <th data-name="unitPrice">单价</th>
                            <th data-name="sellPrice">销售价</th>
                            <th>操作<span class="table-add glyphicon glyphicon-plus" data-type="Venue"></span></th>
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
                            <td contenteditable="true"></td>
                            <td contenteditable="true"></td>
                            <td contenteditable="true"></td>
                            <td contenteditable="true"></td>
                            <td contenteditable="true"></td>
                            <td contenteditable="true"></td>
                            <td contenteditable="true"></td>
                            <td contenteditable="true"></td>
                            <td>
                              <span class="table-remove glyphicon glyphicon-remove" data-type="Img"></span>
                              <%--<span class="table-up glyphicon glyphicon-arrow-up"></span>
                              <span class="table-down glyphicon glyphicon-arrow-down"></span>--%>
                            </td>
                          </tr>
                          <c:forEach items="${data.venues}" var="venue">
                            <tr>
                              <td class="hide">${venue.id}</td>
                              <td class="hide">${venue.hotelId}</td>
                              <td contenteditable="true">${venue.name}</td>
                              <td contenteditable="true">${venue.area}</td>
                              <td contenteditable="true">${venue.tags}</td>
                              <td contenteditable="true">${venue.height}</td>
                              <td contenteditable="true">${venue.peopleNumMax}</td>
                              <td contenteditable="true">${venue.layoutTheater}</td>
                              <td contenteditable="true">${venue.layoutDesk}</td>
                              <td contenteditable="true">${venue.layoutBanquet}</td>
                              <td contenteditable="true">${venue.layoutU}</td>
                              <td contenteditable="true">${venue.layoutO}</td>
                              <td contenteditable="true">${venue.unitPrice}</td>
                              <td contenteditable="true">${venue.sellPrice}</td>
                              <td>
                                <span class="table-remove glyphicon glyphicon-remove" data-id="${venue.id}"
                                      data-type="Venue"></span>
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

                  <h3 class="form-section">酒店图片</h3>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <table id="imgTable" class="table table-striped table-bordered table-hover">
                          <thead>
                          <tr>
                            <th class="hide" data-name="id"></th>
                            <th class="hide" data-name="hotelId"></th>
                            <th data-name="url">预览</th>
                            <th data-name="name">图片名称</th>
                            <th data-name="type">类型（0，1，2）</th>
                            <th>操作<span class="table-add glyphicon glyphicon-plus" data-type="Img"></span></th>
                          </tr>
                          </thead>
                          <tbody>
                          <tr class="hide">
                            <td class="hide"></td>
                            <td class="hide">${data.id}</td>
                            <td><img src="#" class="center-block thumbnail"/></td>
                            <td contenteditable="true"></td>
                            <td contenteditable="true"></td>
                            <td>
                              <span class="table-remove glyphicon glyphicon-remove" data-type="Img"></span>
                              <%--<span class="table-up glyphicon glyphicon-arrow-up"></span>
                              <span class="table-down glyphicon glyphicon-arrow-down"></span>--%>
                            </td>
                          </tr>
                          <c:forEach items="${data.pictures}" var="img">
                            <tr>
                              <td class="hide">${img.id}</td>
                              <td class="hide">${img.hotelId}</td>
                              <td data-val="${img.url}"><img src="${FILE_DOMAIN}${img.url}" class="center-block thumbnail"></td>
                              <td contenteditable="true">${img.name}</td>
                              <td contenteditable="true">${img.type}</td>
                              <td>
                                <span class="table-remove glyphicon glyphicon-remove" data-id="${img.id}"
                                      data-type="Img"></span>
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
<script type="text/javascript" src="${_PATH}/static/plugins/jquery.chained.remote.js"></script>
<script type="text/javascript" src="${_PATH}/static/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
  $(function () {
    $('.table-add').click(function () {
      var $TABLE = $(this).parents("table");
      var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide');
      var img = $clone.children().eq(2).find("img");
      if (img.length > 0) {
        readyUploader(img[0], '/hotel/upload', 'file', null, function (up, files) {
          previewImage(files[0], function (imgSrc) {
            img.prop('src', imgSrc);
          });
          up.start();
        }, function (up, file, resp) {
          img.prop('src', '${FILE_DOMAIN}' + resp.response);
          img.parent().data("val", resp.response);
        });
      }
      $TABLE.append($clone);
    });

    $('.table-remove').click(function () {
      var _this = $(this), id = _this.data("id");
      if (id)
        $.ajax(_PATH + '/hotel/del/' + _this.data("type") + '/' + id, {
          type: 'DELETE',
          success: function (resp) {
            if (resp.statusCode === 200)
              _this.parents('tr').detach();
            else
              alert("删除失败！");
          },
          error: function (resp) {
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

    $("#imgTable").find('tr:not(:hidden)').not(":first").each(function () {
      var img = $(this).children().eq(2).find("img");
      readyUploader(img[0], '/hotel/upload', 'file', null, function (up, files) {
        previewImage(files[0], function (imgSrc) {
          img.prop('src', imgSrc);
        });
        up.start();
      }, function (up, file, resp) {
        img.prop('src', '${FILE_DOMAIN}' + resp.response);
        img.parent().data("val", resp.response);
      });
    });

    // 封面图
    readyUploader('imgUrl', '/hotel/upload', 'file', null, function (up, files) {
      previewImage(files[0], function (imgSrc) {
        $("#imgUrl").prop('src', imgSrc);
      });
      up.start();
    }, function (up, file, resp) {
      $("input[name='imgUrl']").val(resp.response);
      $("#imgUrl").prop('src', '${FILE_DOMAIN}' + resp.response);
    });

    $('form').validate({
      rules: {
        name: "required",
        star: "required",
        province: "required",
//        city: "required",
        county: "required"
      },
      submitHandler: function (form, event) {
        event.preventDefault();
          $("[name^=extend]").serializeJson()
        $.ajax(_MODULE_NAME + '/save', {
          type: "POST",
          contentType: 'application/json;charset=utf-8',
          data: JSON.stringify($.extend($("form").serializeJson(),
              {'pictures': $("#imgTable").serializeTable()}, {'venues': $("#venueTable").serializeTable()})),
          success: function (resp) {
            if (resp.statusCode === 200)
              window.location.href = _MODULE_NAME + "/list";
            else
              alert("保存失败！");
          },
          error: function (res) {
            alert(res.responseText);
          }
        })
      }
    });
  });
</script>
</body>
</html>