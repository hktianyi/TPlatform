package org.tplatform.auth;

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
