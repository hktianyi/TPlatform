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
<div class="page-content-wrapper">
  <div class="page-content">
    <div class="row">
      <div class="col-md-12">
        <form class="form-horizontal" role="form">
              <%--<input type="hidden" name="id" value="${data.id}">--%>
              <input type="hidden" name="memberId" value="${data.memberId}">
              <div class="form-body">
                <div class="row">
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">订单号:</label>
                      <div class="col-md-8">
                        <input class="form-control" name="id" value="${data.id}" readonly/>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">录入人:</label>
                      <div class="col-md-8">
                        <input class="form-control" name="userId" value="${data.userId}" readonly/>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">订单日期:</label>
                      <div class="col-md-8">
                        <input class="form-control" value="<fmt:formatDate value="${data.createTime}" type="both"/>" readonly/>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">客户名:</label>
                      <div class="col-md-8">
                        <input class="form-control" name="customerName" value="${data.member.username}"/>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">*联系人:</label>
                      <div class="col-md-8">
                        <input class="form-control" name="contacts" value="${data.member.nickname}"/>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">状态:</label>
                      <div class="col-md-8">
                        <select class="form-control" name="status">
                          <option value="1" <c:if test="${data.status eq '1'}">selected</c:if>>新订单</option>
                          <option value="2" <c:if test="${data.status eq '2'}">selected</c:if>>跟进中</option>
                          <option value="3" <c:if test="${data.status eq '3'}">selected</c:if>>已成单</option>
                          <option value="4" <c:if test="${data.status eq '4'}">selected</c:if>>未成单</option>
                        </select>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">联系方式:</label>
                      <div class="col-md-8">
                        <input class="form-control" name="tel" value="${data.member.mobile}" readonly/>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">邮箱:</label>
                      <div class="col-md-8">
                        <input class="form-control" name="email" value="${data.member.email}" readonly/>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">QQ:</label>
                      <div class="col-md-8">
                        <input class="form-control" name="qq" value="${data.member.qq}" readonly/>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">开始日期:</label>
                      <div class="col-md-8">
                        <input class="form-control date-picker" name="startDate" value="<fmt:formatDate value="${data.startDate}" type="date" dateStyle="default"/>"/>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">结束日期:</label>
                      <div class="col-md-8">
                        <input class="form-control date-picker" name="endDate" value="<fmt:formatDate value="${data.endDate}" type="date" dateStyle="default"/>"/>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">微信:</label>
                      <div class="col-md-8">
                        <input class="form-control" name="wechat" value="${data.member.wechat}" readonly/>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">推荐人:</label>
                      <div class="col-md-8">
                        <input class="form-control" name="referrer" value="${data.member.referrer}"/>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">活动要求:</label>
                      <div class="col-md-8">
                        <textarea class="form-control" name="need">${data.need}</textarea>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="form-group">
                      <label class="control-label col-md-4">备注:</label>
                      <div class="col-md-8">
                        <textarea class="form-control" name="remark">${data.remark}</textarea>
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
                          <button type="button" class="btn default" onclick="layerClose()">取消</button>
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
<%@include file="/WEB-INF/common/footerWithoutDisInfo.jsp" %>
<script type="text/javascript">
  $(function () {
    $.validator.addMethod("selected", function () {
      if ($("input[name='memberId']").val()) return true;
      else return false;
    }, '请选择客户!');
    $('form').validate({
      rules: {
        contacts: "selected"
      },
      submitHandler: function (form, event) {
        event.preventDefault();
        $.ajax(_MODULE_NAME + '/save', {
          type: "POST",
          data: $('form').serialize(),
          success: function (resp) {
            if (resp.statusCode === 200)
              layerClose('保存成功');
            else
              alert("保存失败！");
          },
          error: function (res) {
            alert(res.responseText);
          }
        })
      }
    });
    $("input[name='contacts']").autocomplete({
      serviceUrl: _PATH + "/member/findSuggest",
      paramName: "nickname",
      dataType: "json",
      transformResult: function(response) {
        return {
          suggestions: $.map(response.data, function(item) {
            return { value: item.nickname, data: item.id };
          })
        };
      },
      onSelect: function (suggestion) {
        autocomplete(suggestion.data);
      }
    });
  });
  function autocomplete(id, isInit) {
    if(id) {
      $.getJSON(_PATH + "/member/"+id, function(resp) {
        if(resp.statusCode === 200) {
          var data = resp.data;
          if(!isInit) {
            //酒店信息
            $("input[name='memberId']").val(data.id);
            $("input[name='customerName']").val(data.username);
            $("input[name='tel']").val(data.mobile || data.phone);
            $("input[name='email']").val(data.email);
            $("input[name='qq']").val(data.qq);
            $("input[name='wechat']").val(data.wechat);
            $("input[name='contacts']").valid();
          }
        }
      });
    }
  }
</script>
</body>
</html>