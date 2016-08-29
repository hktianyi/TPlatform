package org.tplatform.task.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.common.BaseCtrl;
import org.tplatform.framework.log.Logger;
import org.tplatform.task.entity.SchedulingJob;
import org.tplatform.task.service.ISchedulerService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/timer")
public class TimerCtrl extends BaseCtrl<SchedulingJob> {

  @Autowired
  private ISchedulerService schedulerService;

  /**
   * 查询定时器
   */
  @RequestMapping("loadData")
  @ResponseBody
  public Map<String, Object> loadData(SchedulingJob entity) {
    Map<String, Object> jsonMap = new HashMap<>();
    try {
      jsonMap.put("dataList", this.schedulerService.queryAll(entity));
//      Long count = schedulerService.count();
//      jsonMap.put("recordsTotal", count);
//      jsonMap.put("recordsFiltered", count);
    } catch (Exception e) {
      Logger.e("loadData", e);
    }
    return jsonMap;
  }

  /**
   * 立即执行一次
   *
   * @param schedulingJob
   * @return
   */
  @RequestMapping("/trigger")
  @ResponseBody
  public Map<String, Object> triggerJob(SchedulingJob schedulingJob) {
    Map<String, Object> rtnMap = new HashMap<>();
    try {
      schedulerService.triggerJob(schedulingJob);
      rtnMap.put("statusCode", 200);
    } catch (Exception e) {
      Logger.e(getClass(), "手动触发定时任务异常：" + schedulingJob.getId(), e);
      rtnMap.put("statusCode", 500);
      rtnMap.put("info", "操作失败！");
    }
    return rtnMap;
  }
}
