<%@ page pageEncoding="UTF-8" language="java" %>
<div class="page-bar">
  <ul class="page-breadcrumb">
    <li>
      <a href="${_PATH}/main.html">首页</a>
      <i class="fa fa-angle-right"></i>
    </li>
    <li>
      <span>系统信息</span>
    </li>
  </ul>
  <div class="page-toolbar">
    <button type="button" class="btn btn-circle blue btn-outline btn-xs">&nbsp;?&nbsp;</button>
  </div>
</div>
<div class="row" style="margin-top: 6px;">
  <div class="col-md-6" id="gauge" style="height:300px">
  </div>
</div>
<script type="text/javascript" src="${_PATH}/static/plugins/echarts.min.js"></script>
<script type="text/javascript">
  $(function () {
    var echart = echarts.init(document.getElementById('gauge'));
    var option = {
      tooltip: {
        formatter: "{a} <br/>{c} {b}"
      },
      series: [
        {
          name: 'CPU',
          type: 'gauge',
          center: ['63%', '80%'],    // 默认全局居中
          z: 3,
          min: 0,
          max: 100,
          precision: 0,
          startAngle: 180,
          endAngle: 0,
          radius: '100%',
          axisLine: {            // 坐标轴线
            lineStyle: {       // 属性lineStyle控制线条样式
              width: 8
            }
          },
          axisTick: {            // 坐标轴小标记
            length: 15,        // 属性length控制线长
            lineStyle: {       // 属性lineStyle控制线条样式
              color: 'auto'
            }
          },
          splitLine: {           // 分隔线
            length: 20,         // 属性length控制线长
            lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
              color: 'auto'
            }
          },
          title: {
            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
              fontWeight: 'bolder',
              fontSize: 20,
              fontStyle: 'italic'
            }
          },
          detail: {
            show: false,
            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
              fontWeight: 'bolder'
            }
          },
          data: [{value: 0, name: 'CPU\n${data.arch}\n[${data.availableProcessors} Core]'}]
        },
        {
          name: '内存',
          type: 'gauge',
          center: ['25%', '60%'],    // 默认全局居中
          radius: '55%',
          min: 0,
          max: Math.round(${data.totalPhysicalMemorySize/1073741824}),
          splitNumber: 2,
          precision: 0,
          endAngle: 45,
          axisLine: {            // 坐标轴线
            lineStyle: {       // 属性lineStyle控制线条样式
              width: 5
            }
          },
          axisTick: {            // 坐标轴小标记
            length: 12,        // 属性length控制线长
            lineStyle: {       // 属性lineStyle控制线条样式
              color: 'auto'
            }
          },
          splitLine: {           // 分隔线
            length: 20,         // 属性length控制线长
            lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
              color: 'auto'
            }
          },
          pointer: {
            width: 5
          },
          title: {
            offsetCenter: [0, '-30%'],       // x, y，单位px
          },
          detail: {
            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
              fontWeight: 'bolder'
            }
          },
          data: [{value: ${(data.totalPhysicalMemorySize-data.freePhysicalMemorySize)/1073741824}, name: 'MEM\n(G)'}]
        }
      ]
    };

    r(echart, option);
  });
  function r(echart, option) {
    $.getJSON(_PATH + '/sysInfo/refresh', function (resp) {
      option.series[0].data[0].value = (resp.data.systemCpuLoad * 100).toFixed(2);
      option.series[1].data[0].value = ((resp.data.totalPhysicalMemorySize - resp.data.freePhysicalMemorySize) / 1073741824).toFixed(2);
      echart.setOption(option);

      setTimeout(function () {
        r(echart, option);
      }, 1000);
    });
  }
</script>