package org.tplatform.core.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.tplatform.core.entity.SysConf;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * Created by tianyi on 16/1/13.
 */
public interface SysConfMapper extends Mapper<SysConf> {

  @Select("select val from sys_conf where confkey = #{confKey}")
  String findVal(String confKey);

  @Update("update sys_conf set val = #{value} where confkey = #{confKey}")
  int updateVal(@Param("confKey") String confKey, @Param("value") String value);

  @Select("SELECT confKey, val FROM sys_conf")
  Map<String, Object> selectForMap();

  class SQLProvider {

  }
}
