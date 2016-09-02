package org.tplatform.task.service.impl;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tplatform.core.fsm.StatusEnum;
import org.tplatform.framework.log.Logger;
import org.tplatform.framework.util.ClassUtil;
import org.tplatform.impl.BaseService;
import org.tplatform.task.entity.SchedulingJob;
import org.tplatform.task.mapper.SchedulerMapper;
import org.tplatform.task.service.ISchedulerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("schedulerService")
@Transactional
public class SchedulerService extends BaseService<SchedulingJob> implements ISchedulerService {

  public static Map<String, String> jobGroupMap;
  @Autowired
  private SchedulerMapper schedulerMapper;
  @Autowired
  private Scheduler scheduler;

  static {
    jobGroupMap = new HashMap<>();
    Logger.i(SchedulerService.class.getPackage().getName() + ".classify");
    List<Class<?>> classList = ClassUtil.getClasses(SchedulerService.class.getPackage().getName() + ".classify");
    for (Class<?> clazz : classList) {
      TaskGroup taskGroup = clazz.getAnnotation(TaskGroup.class);
      if (taskGroup != null) {
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> c : interfaces) {
          if (Job.class.equals(c)) {
            Logger.i("This is a Job" + clazz.getName() + "    :    " + taskGroup.value());
            jobGroupMap.put(clazz.getName(), taskGroup.value());
            break;
          }
        }
      }
    }
  }

  /**
   * 查询定时任务分组名
   *
   * @return
   */
  public Map<String, String> queryAllJobGroup() {
    return jobGroupMap;
  }

  /**
   * 查询定时任务分组名
   *
   * @return
   */
  public List<SchedulingJob> queryAll(SchedulingJob schedulingJob) {
    return schedulerMapper.selectByExample(schedulingJob);
  }

  /**
   * 根据条件查询定时任务
   *
   * @return
   */
  public List<SchedulingJob> findByOwer(String ower) {
    return schedulerMapper.findByOwer(ower);
  }

  /**
   * 查询定时任务
   *
   * @param isByTrigger
   * @return
   * @throws SchedulerException
   */
  public List<SchedulingJob> queryAll(boolean isByTrigger) throws SchedulerException {
    /*List<SchedulingJob> list = new LinkedList<SchedulingJob>();//scheduler.getJobGroupNames();
    GroupMatcher paramGroupMatcher = isByTrigger?GroupMatcher.anyTriggerGroup():GroupMatcher.anyJobGroup();
		Set<? extends Key> set = isByTrigger?scheduler.getTriggerKeys(paramGroupMatcher):scheduler.getJobKeys(paramGroupMatcher);
		if(isByTrigger) {
			for(Key<TriggerKey> key : set)
				list.add((SchedulingJob)scheduler.getJobDetail(scheduler.getTrigger((TriggerKey)key).getJobKey()).getJobDataMap().get("schedulingJob"));
		}else {
			for(Key<JobKey> key : set)
				list.add((SchedulingJob)scheduler.getJobDetail((JobKey)key).getJobDataMap().get("schedulingJob"));
		}*/
    return schedulerMapper.selectAll();
  }

  /**
   * 手动触发一次任务
   *
   * @param schedulingJob
   * @throws SchedulerException
   */
  public void triggerJob(SchedulingJob schedulingJob) throws SchedulerException {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put("date", schedulingJob.getStrParam2());
    jobDataMap.put("strParam", schedulingJob.getStrParam());
    jobDataMap.put("isManual", true);
    schedulingJob = schedulerMapper.selectByPrimaryKey(schedulingJob.getId());
    scheduler.triggerJob(new JobKey(schedulingJob.getJobName(), schedulingJob.getJobGroup()), jobDataMap);
  }

  /**
   * 新建或者更新一个定时任务
   *
   * @throws java.sql.SQLException
   * @throws SchedulerException
   * @throws ClassNotFoundException
   */
  public synchronized void saveOrUpd(SchedulingJob schedulingJob) throws SchedulerException, ClassNotFoundException {

//    Session session = super.getSessionFactory().getCurrentSession();
//    Transaction transaction = session.beginTransaction();
//    try {
//      transaction.begin();
      if (schedulingJob.getId() != null) {
        schedulerMapper.updateByPrimaryKeySelective(schedulingJob);
        schedulingJob = schedulerMapper.selectByPrimaryKey(schedulingJob.getId());
        TriggerKey tiggerKey = TriggerKey.triggerKey(schedulingJob.getJobName(), schedulingJob.getJobGroup());
        Trigger trigger = scheduler.getTrigger(tiggerKey);
        scheduler.getJobDetail(scheduler.getTrigger(tiggerKey).getJobKey()).getJobDataMap().put("strParam", schedulingJob.getStrParam());
        scheduler.rescheduleJob(tiggerKey, trigger.getTriggerBuilder().withSchedule((ScheduleBuilder) CronScheduleBuilder.cronSchedule(schedulingJob.getCronExpression())).usingJobData("strParam", schedulingJob.getStrParam()).build());
      } else {
        //设置入参
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("strParam", schedulingJob.getStrParam());

        JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(schedulingJob.getJobGroup())).withIdentity(schedulingJob.getJobName(), schedulingJob.getJobGroup()).setJobData(jobDataMap).build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(TriggerKey.triggerKey(schedulingJob.getJobName(), schedulingJob.getJobGroup())).withSchedule(CronScheduleBuilder.cronSchedule(schedulingJob.getCronExpression())).usingJobData(jobDataMap).build();
        scheduler.scheduleJob(jobDetail, trigger);

        schedulingJob.setStatus(StatusEnum.VALID);
        schedulerMapper.insert(schedulingJob);
      }
      if (scheduler.isShutdown())
        scheduler.start();
//      transaction.commit();
//    } catch (Exception e) {
//      Logger.e("saveOrUpd", e);
//      transaction.rollback();
////		} finally {
////			session.close();
//    }
  }

  public synchronized void startJob(SchedulingJob schedulingJob) throws ClassNotFoundException, SchedulerException {
    JobDataMap jobDataMap = new JobDataMap();
    jobDataMap.put("strParam", schedulingJob.getStrParam());

    TriggerKey tiggerKey = TriggerKey.triggerKey(schedulingJob.getJobName(), schedulingJob.getJobGroup());
    JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(schedulingJob.getJobGroup())).withIdentity(schedulingJob.getJobName(), schedulingJob.getJobGroup()).setJobData(jobDataMap).build();
    Trigger trigger = TriggerBuilder.newTrigger().withIdentity(tiggerKey).withSchedule(CronScheduleBuilder.cronSchedule(schedulingJob.getCronExpression())).usingJobData(jobDataMap).build();
    scheduler.scheduleJob(jobDetail, trigger);
  }

  /**
   * 删除一个任务
   *
   * @throws SchedulerException
   */
  public synchronized void delete(Long id) throws SchedulerException {
    SchedulingJob schedulingJob = schedulerMapper.selectByPrimaryKey(id);
    TriggerKey triggerKey = TriggerKey.triggerKey(schedulingJob.getJobName(), schedulingJob.getJobGroup());
    scheduler.pauseTrigger(triggerKey);//停止触发器
    scheduler.unscheduleJob(triggerKey);//移除触发器
    scheduler.deleteJob(JobKey.jobKey(schedulingJob.getJobName(), schedulingJob.getJobGroup()));//删除任务
    schedulerMapper.deleteByPrimaryKey(schedulingJob.getId());
  }

  /**
   * 暂停一个任务
   *
   * @throws SchedulerException
   */
  public void pauseJob(String... names)
      throws SchedulerException {
    scheduler.pauseJob(JobKey.jobKey(names[0], names[1] == null ? null : names[1]));
  }

  /**
   * 继续执行一个任务
   *
   * @throws SchedulerException
   */
  public void resumeJob(String... names)
      throws SchedulerException {
    scheduler.resumeJob(JobKey.jobKey(names[0], names[1] == null ? null : names[1]));
  }

  /**
   * 暂停一个定时器
   *
   * @throws SchedulerException
   */
  public void pauseTrigger(String... names)
      throws SchedulerException {
    scheduler.pauseTrigger(TriggerKey.triggerKey(names[0], names[1] == null ? null : names[1]));
  }

  /**
   * 继续执行一个定时器
   *
   * @throws SchedulerException
   */
  public void resumeTrigger(String... names)
      throws SchedulerException {
    scheduler.resumeTrigger(TriggerKey.triggerKey(names[0], names[1] == null ? null : names[1]));
  }

}
