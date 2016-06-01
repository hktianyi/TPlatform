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
}
