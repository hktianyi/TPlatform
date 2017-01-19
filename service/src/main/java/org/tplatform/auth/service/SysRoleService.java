package org.tplatform.auth.service;

import org.springframework.data.jpa.repository.Query;
import org.tplatform.auth.entity.SysRole;
import org.tplatform.common.BaseRepo;

import java.util.Set;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface SysRoleService extends BaseRepo<SysRole> {

  /**
   * 根据用户查找角色
   * @param userId
   * @return
   */
  @Query(value = "select t1.id, t1.name from sys_auth_role t1, sys_auth_user_role t2 where t1.id = t2.role_id and t2.user_id = :userId", nativeQuery = true)
  Set<SysRole> findByUserId(Long userId);

  /**
   * 根据菜单资源查找角色
   * @param resourceId
   * @return
   */
  @Query(value = "select t1.id, t1.name from sys_auth_role t1, sys_auth_role_resource t2 where t1.id = t2.role_id and t2.resource_id = :resourceId", nativeQuery = true)
  Set<SysRole> findByResourceId(Long resourceId);

//  /**
//   * 支持拖拽
//   * @param id
//   * @param pid
//   * @return
//   */
//  boolean updatePid(Long id, Long pid);
}
