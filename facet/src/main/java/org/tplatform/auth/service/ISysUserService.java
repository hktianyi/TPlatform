package org.tplatform.auth.service;

import org.tplatform.auth.entity.SysUser;
import org.tplatform.core.service.IBaseService;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface ISysUserService extends IBaseService<SysUser> {

  SysUser findByUsername(String username);

  boolean updateAccount(SysUser sysUser);
}
