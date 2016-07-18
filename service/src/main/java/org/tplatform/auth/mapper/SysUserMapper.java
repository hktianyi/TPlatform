package org.tplatform.auth.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.tplatform.auth.entity.SysUser;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Tianyi on 2016/5/28.
 */
public interface SysUserMapper extends Mapper<SysUser> {

  /**
   * 修改密码
   * @param id
   * @param password
   * @return
   */
  @Update("UPDATE SET password = ${password} WHERE id = #{id}")
  int updatePassword(@Param("id") Long id, @Param("password") String password);
}
