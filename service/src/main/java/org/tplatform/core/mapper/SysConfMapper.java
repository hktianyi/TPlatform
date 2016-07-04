package org.tplatform.core.mapper;

import org.tplatform.core.entity.SysConf;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by tianyi on 16/1/13.
 */
public interface SysConfMapper extends Mapper<SysConf> {

  @Select("select val from sys_conf where confkey = #{confKey}")
  String findVal(String confKey);
}
