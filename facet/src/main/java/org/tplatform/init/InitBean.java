//package org.tplatform.init;
//
//import org.tplatform.framework.log.Logger;
//import org.tplatform.framework.util.PropertyUtil;
//import org.tplatform.framework.util.StringUtil;
//import org.tplatform.task.service.ISchedulerService;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Created by Tianyi on 2014/8/8.
// */
//@Component
//public class InitBean implements InitializingBean {
//
//  @Autowired
//  private ISchedulerService schedulerService;
//
//  /**
//   * 项目启动时，初始化定时任务
//   */
//  public void afterPropertiesSet() throws Exception {
//    taskStart();
//  }
//
//  /**
//   * 初始化定时任务
//   */
//  private void taskStart() {
//    try {
//      //判断定时任务开关是否打开
//      if ("on".equals(PropertyUtil.getProInfo("config", "taskExecute"))) {
//        String taskOwer = PropertyUtil.getProInfo("config", "taskOwer");
//        if (StringUtil.isNotEmpty(taskOwer)) {
////          List<SchedulingJob> list = schedulerService.find("status", StatusEnum.VAL"status", StatusEnum.VA//LID, "ower", t.parallelStream().forEach(schedulingJob -> {
////            try {
////              schedulerService.startJob(schedulingJob);
////            } catch (ClassNotFoundException e) {
////              Logger.e("taskStart", e);
////            } catch (SchedulerException e) {
////              Logger.e("taskStart", e);
////            }
////          });
//          Logger.i(taskOwer + "定时任务初始化启动成功！");
//        } else {
//          Logger.i("taskOwer为空，定时任务无法启动！");
//        }
//      }
//    } catch (Exception e) {
//      Logger.e("taskStart:定时任务初始化启动失败！", e);
//    }
//  }
//}
