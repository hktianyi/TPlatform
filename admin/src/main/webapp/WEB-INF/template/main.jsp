<%@ page pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh_cn" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh_cn" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh_cn">
<!--<![endif]-->
<head>
  <%@include file="/WEB-INF/common/common.jsp" %>
</head>
<!-- END HEAD -->
<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-full-width">
<%@include file="/WEB-INF/common/header.jsp" %>
<!-- BEGIN CONTAINER -->
<div class="page-container">
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <!-- BEGIN CONTENT BODY -->
    <div class="page-content">
      <!-- BEGIN PAGE HEADER-->
      <!-- BEGIN PAGE BAR -->
      <div class="page-bar">
        <ul class="page-breadcrumb">
          <li>
            <a href="${_PATH}/main.html">首页</a>
            <i class="fa fa-angle-right"></i>
          </li>
          <li>
            主面板
          </li>
        </ul>
        <div class="page-toolbar">
          <div class="btn-group pull-right">
            <button type="button" class="btn green btn-sm btn-outline dropdown-toggle" data-toggle="dropdown"> Actions
              <i class="fa fa-angle-down"></i>
            </button>
            <ul class="dropdown-menu pull-right" role="menu">
              <li>
                <a href="#">
                  <i class="icon-bell"></i> Action</a>
              </li>
              <li>
                <a href="#">
                  <i class="icon-shield"></i> Another action</a>
              </li>
              <li>
                <a href="#">
                  <i class="icon-user"></i> Something else here</a>
              </li>
              <li class="divider"></li>
              <li>
                <a href="#">
                  <i class="icon-bag"></i> Separated link</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <!-- END PAGE BAR -->
      <!-- BEGIN PAGE TITLE-->
      <h3 class="page-title"> Dark Menu Menu
        <small>dark mega menu option</small>
      </h3>
      <!-- END PAGE TITLE-->
      <!-- END PAGE HEADER-->
      <div id="notifyData"></div>
      <div id="embed-api-auth-container"></div>
      <div id="view-selector-container"></div>
      <div id="main-chart-container"></div>
      <div id="breakdown-chart-container"></div>
    </div>
    <!-- END CONTENT BODY -->
  </div>
  <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<%@include file="/WEB-INF/common/footer.jsp" %>
<script>
    (function (w, d, s, g, js, fs) {
        g = w.gapi || (w.gapi = {});
        g.analytics = {
            q: [], ready: function (f) {
                this.q.push(f);
            }
        };
        js = d.createElement(s);
        fs = d.getElementsByTagName(s)[0];
        js.src = 'https://apis.google.com/js/platform.js';
        fs.parentNode.insertBefore(js, fs);
        js.onload = function () {
            g.load('analytics');
        };
    }(window, document, 'script'));
</script>
<script>

    $(function () {
        $.getJSON(_PATH + '/placeWish/notifyData', function (resp) {
            if (resp.data) {
                $.each(resp.data, function (i, data) {
                    $('#notifyData').append('<div class="note note-warning" id="notePW'+data.id+'"><p> 人数：' +
                        (data.personNum || '-') + ', 会议类型：' + (data.meetingType || '-') + ', 预算：' + (data.budget || '-') + ', 会议时长：' + (data.duration || '-') + ', 开始日期：' + (data.startDate || '-') +
                        ', 电话：' + (data.tel || '-') + ', 咨询时间：' + moment(data.createTime).format('YYYY-MM-DD HH:mm') +
                        '</p><a class="btn blue" href="javascript:read(\''+data.id+'\');"> 知道了 </a></div>');
                });
            }
        })
    });

    function read(id) {
        $.post(_PATH + '/placeWish/read/' + id, function (resp) {
            console.log(resp);
            $('#notePW' + id).hide();
        })
    }

    gapi.analytics.ready(function () {

        /**
         * Authorize the user immediately if the user has already granted access.
         * If no access has been created, render an authorize button inside the
         * element with the ID "embed-api-auth-container".
         */
        gapi.analytics.auth.authorize({
            container: 'embed-api-auth-container',
            clientid: '133205233'
        });


        /**
         * Create a new ViewSelector instance to be rendered inside of an
         * element with the id "view-selector-container".
         */
        var viewSelector = new gapi.analytics.ViewSelector({
            container: 'view-selector-container'
        });

        // Render the view selector to the page.
        viewSelector.execute();

        /**
         * Create a table chart showing top browsers for users to interact with.
         * Clicking on a row in the table will update a second timeline chart with
         * data from the selected browser.
         */
        var mainChart = new gapi.analytics.googleCharts.DataChart({
            query: {
                'dimensions': 'ga:browser',
                'metrics': 'ga:sessions',
                'sort': '-ga:sessions',
                'max-results': '6'
            },
            chart: {
                type: 'TABLE',
                container: 'main-chart-container',
                options: {
                    width: '100%'
                }
            }
        });


        /**
         * Create a timeline chart showing sessions over time for the browser the
         * user selected in the main chart.
         */
        var breakdownChart = new gapi.analytics.googleCharts.DataChart({
            query: {
                'dimensions': 'ga:date',
                'metrics': 'ga:sessions',
                'start-date': '7daysAgo',
                'end-date': 'yesterday'
            },
            chart: {
                type: 'LINE',
                container: 'breakdown-chart-container',
                options: {
                    width: '100%'
                }
            }
        });


        /**
         * Store a refernce to the row click listener variable so it can be
         * removed later to prevent leaking memory when the chart instance is
         * replaced.
         */
        var mainChartRowClickListener;


        /**
         * Update both charts whenever the selected view changes.
         */
        viewSelector.on('change', function (ids) {
            var options = {query: {ids: ids}};

            // Clean up any event listeners registered on the main chart before
            // rendering a new one.
            if (mainChartRowClickListener) {
                google.visualization.events.removeListener(mainChartRowClickListener);
            }

            mainChart.set(options).execute();
            breakdownChart.set(options);

            // Only render the breakdown chart if a browser filter has been set.
            if (breakdownChart.get().query.filters) breakdownChart.execute();
        });


        /**
         * Each time the main chart is rendered, add an event listener to it so
         * that when the user clicks on a row, the line chart is updated with
         * the data from the browser in the clicked row.
         */
        mainChart.on('success', function (response) {

            var chart = response.chart;
            var dataTable = response.dataTable;

            // Store a reference to this listener so it can be cleaned up later.
            mainChartRowClickListener = google.visualization.events
                .addListener(chart, 'select', function (event) {

                    // When you unselect a row, the "select" event still fires
                    // but the selection is empty. Ignore that case.
                    if (!chart.getSelection().length) return;

                    var row = chart.getSelection()[0].row;
                    var browser = dataTable.getValue(row, 0);
                    var options = {
                        query: {
                            filters: 'ga:browser==' + browser
                        },
                        chart: {
                            options: {
                                title: browser
                            }
                        }
                    };

                    breakdownChart.set(options).execute();
                });
        });

    });
</script>
</body>
</html>