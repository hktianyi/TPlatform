package org.tplatform.auth.service;

import org.tplatform.auth.entity.SysUser;
import org.tplatform.common.BaseRepo;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface SysUserService extends BaseRepo<SysUser> {

  SysUser findByUsername(String username);

//  boolean updateAccount(SysUser sysUser);
//
//  boolean saveWithRole(SysUser sysUser, Long[] roles);
}
