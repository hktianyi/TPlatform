package org.tplatform.auth.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.tplatform.auth.entity.SysRole;
import org.tplatform.core.fsm.StatusEnum;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

/**
 * Created by Tianyi on 2016/5/28.
 */
public interface SysRoleMapper extends Mapper<SysRole> {

  /**
   * 根据用户查询角色
   * @param userId
   * @param status
   * @return
   */
  @Select("select t1.id, t1.name from sys_auth_role t1, sys_auth_user_role t2" +
      " where t1.id = t2.role_id and t2.user_id = ${userId} and t1.status = #{status}")
  Set<SysRole> findByUserId(@Param("userId") Long userId, @Param("status") StatusEnum status);
}
