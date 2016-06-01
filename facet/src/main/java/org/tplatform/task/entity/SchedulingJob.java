package org.tplatform.task.entity;

import org.tplatform.core.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name = "sys_scheduler")
public class SchedulingJob extends BaseEntity {

  public static final String OWER_SHOP = "SHOP";      //任务前台
  public static final String OWER_ADMIN = "ADMIN";    //任务后台
  private static final long serialVersionUID = 1L;
  @Id
  private Long id;      //任务Id
  @Column(nullable = false, length = 50)
  private String jobName;      //任务名称
  @Column(nullable = false, length = 100)
  private String jobGroup;    //任务所属组的名称
  @Column(nullable = false, length = 10)
  private String ower;        //任务宿主
  @Column(nullable = false, length = 30)
  private String cronExpression;  //定时任务运行时间表达式
  private String description;    //任务描述

  private String strParam;    //任务运行时参数
  @Transient
  private String strParam2;    //任务运行时参数，手动调度临时入参

}
