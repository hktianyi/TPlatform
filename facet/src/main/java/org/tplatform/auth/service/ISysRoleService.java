package org.tplatform.auth.service;

import org.tplatform.auth.entity.SysRole;
import org.tplatform.core.fsm.StatusEnum;
import org.tplatform.core.service.IBaseService;

import java.util.Set;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface ISysRoleService extends IBaseService<SysRole> {

  Set<SysRole> findByUserId(Long userId, StatusEnum status);

  /**
   * 支持拖拽
   * @param id
   * @param pid
   * @return
   */
  public boolean updatePid(Long id, Long pid);
}
