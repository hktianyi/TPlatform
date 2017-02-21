<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
  <title>${APP_NAME}</title>
  <%@include file="/WEB-INF/template/metronic/common/common.jsp" %>
</head>
<body class=" page-404-full-page">
<div class="row">
  <div class="col-md-12 page-404">
    <div class="number font-red"> 404</div>
    <div class="details">
      <h3>Oops! You're lost.</h3>
      <p> We can not find the page you're looking for.
        <br/>
        <a href="index.html"> Return home </a> or try the search bar below. </p>
      <form action="#">
        <div class="input-group input-medium">
          <input type="text" class="form-control" placeholder="keyword...">
          <span class="input-group-btn">
                                <button type="submit" class="btn red">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
        </div>
        <!-- /input-group -->
      </form>
    </div>
  </div>
</div>
<%@include file="/WEB-INF/template/metronic/common/footer.jsp" %>
</body>

</html>