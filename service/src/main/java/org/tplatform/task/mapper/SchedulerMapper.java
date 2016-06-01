package org.tplatform.task.mapper;

import org.tplatform.task.entity.SchedulingJob;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by tianyi on 16/1/13.
 */
public interface SchedulerMapper extends Mapper<SchedulingJob> {
  /**
   * 根据所有者查询可用的定时器
   * @param ower
   * @return
   */
  @Select("select * from sys_scheduler where status = 'VALID' and ower = #{ower}")
  List<SchedulingJob> findByOwer(String ower);
}

