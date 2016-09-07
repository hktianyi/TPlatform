package org.tplatform.auth.service;

import org.tplatform.auth.entity.SysRole;
import org.tplatform.core.fsm.StatusEnum;
import org.tplatform.core.service.IBaseService;

import java.util.Set;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface ISysRoleService extends IBaseService<SysRole> {

  /**
   * 根据用户查找角色
   * @param userId
   * @param status
   * @return
   */
  Set<SysRole> findByUserId(Long userId, StatusEnum status);

  /**
   * 根据菜单资源查找角色
   * @param resourceId
   * @return
   */
  Set<SysRole> findByResourceId(Long resourceId);

  /**
   * 支持拖拽
   * @param id
   * @param pid
   * @return
   */
  boolean updatePid(Long id, Long pid);
}
