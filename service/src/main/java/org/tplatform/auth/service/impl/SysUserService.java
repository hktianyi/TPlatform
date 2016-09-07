package org.tplatform.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tplatform.auth.entity.SysUser;
import org.tplatform.auth.mapper.SysUserMapper;
import org.tplatform.auth.service.ISysUserService;
import org.tplatform.impl.BaseService;

/**
 * 系统用户业务类
 *
 * Created by Tianyi on 2016/5/28.
 */
@Service
public class SysUserService extends BaseService<SysUser> implements ISysUserService {

  @Autowired
  private SysUserMapper sysUserMapper;

  public SysUser findByUsername(String username) {
    SysUser sysUser = new SysUser();
    sysUser.setUsername(username);
    return sysUserMapper.selectOne(sysUser);
  }

  public boolean updateAccount(SysUser sysUser) {
    return sysUserMapper.updateByPrimaryKeySelective(sysUser) > 0;
  }

  @Override
  public boolean saveWithRole(SysUser sysUser, Long[] roles) {
    super.saveOrUpdate(sysUser);
    if (sysUser.getId() == null) {
      sysUser = super.findOne(sysUser);
    }
    return sysUserMapper.saveWithRole(sysUser, roles) > 0;
  }
}
