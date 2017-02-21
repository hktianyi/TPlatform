<%@ page pageEncoding="UTF-8" language="java" %>
<form class="search-form open">
  <div class="input-group">
    <input type="hidden" id="qNames" name="qNames" value="<%=request.getParameter("qNames")%>">
    <input type="search" class="form-control" placeholder="搜索" id="q" name="q">
    <span class="input-group-btn">
                            <a href="javascript:getDataList();" class="btn submit">
                                <i class="icon-magnifier"></i>
                            </a>
                        </span>
  </div>
</form>
<script type="text/javascript">
  !(function () {
    $('form.search-form input[type="search"]').on('keydown', function (event) {
      if (event.keyCode == 13) {
        event.preventDefault;
        $(this).blur();
        getDataList();
      }
    });
    $('.search-advance-btn').on('click', function (e) {
      e.preventDefault();
      var btn = $(this);
      if (btn.hasClass('open')) {
        btn.removeClass('open');
        $('#search-advance').hide();
        $('.search-form').show();
        btn.find('i').removeClass('fa-search-minus').addClass('fa-search-plus');
      } else {
        btn.addClass('open');
        $('#search-advance').show();
        $('.search-form').hide();
        btn.find('i').removeClass('fa-search-plus').addClass('fa-search-minus');
      }
    })
  })(window);
</script>