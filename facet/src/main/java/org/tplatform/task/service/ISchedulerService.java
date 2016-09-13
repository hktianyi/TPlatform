package org.tplatform.task.service;

import org.quartz.SchedulerException;
import org.tplatform.core.service.IBaseService;
import org.tplatform.task.entity.SchedulingJob;

import java.util.List;
import java.util.Map;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface ISchedulerService extends IBaseService<SchedulingJob> {

  /**
   * 查询定时任务分组名
   *
   * @return
   */
  public Map<String, String> queryAllJobGroup();

  /**
   * 查询定时任务分组名
   *
   * @return
   */
  public List<SchedulingJob> queryAll(SchedulingJob schedulingJob);

  /**
   * 根据条件查询定时任务
   *
   * @return
   */
  public List<SchedulingJob> findByOwer(String ower);

  /**
   * 查询定时任务
   *
   * @param isByTrigger
   * @return
   * @throws SchedulerException
   */
  public List<SchedulingJob> queryAll(boolean isByTrigger) throws SchedulerException;

  /**
   * 手动触发一次任务
   *
   * @param schedulingJob
   * @throws SchedulerException
   */
  public void triggerJob(SchedulingJob schedulingJob) throws SchedulerException;

  /**
   * 新建或者更新一个定时任务
   *
   * @throws SchedulerException
   * @throws ClassNotFoundException
   */
  public void saveOrUpd(SchedulingJob schedulingJob) throws SchedulerException, ClassNotFoundException;

  /**
   * 删除一个任务
   *
   * @throws SchedulerException
   */
  public void delete(Long id) throws SchedulerException;

  /**
   * 暂停一个任务
   *
   * @throws SchedulerException
   */
  public void pauseJob(String... names) throws SchedulerException;

  /**
   * 继续执行一个任务
   *
   * @throws SchedulerException
   */
  public void resumeJob(String... names) throws SchedulerException;

  /**
   * 暂停一个定时器
   *
   * @throws SchedulerException
   */
  public void pauseTrigger(String... names)
      throws SchedulerException;

  /**
   * 继续执行一个定时器
   *
   * @throws SchedulerException
   */
  public void resumeTrigger(String... names) throws SchedulerException;
}
