package org.tplatform.auth.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.tplatform.auth.entity.SysOrgan;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Tianyi on 2016/6/1.
 */
public interface SysOrganMapper extends Mapper<SysOrgan> {

  @Override
  @Select("SELECT t1.id, t1.code, t1.pCode, t1.name, t1.nickname, t1.type, t1.sort, t1.createTime, t1.status, (SELECT COUNT(1) FROM sys_auth_organ t2 WHERE t2.pCode = t1.code) leaf FROM sys_auth_organ t1 WHERE t1.pCode = #{record.pCode}")
  List<SysOrgan> select(@Param("record") SysOrgan record);
}
