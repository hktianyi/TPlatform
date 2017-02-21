package org.tplatform.auth.repo;//package org.tplatform.auth.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.tplatform.auth.entity.SysUser;
//import org.tplatform.auth.repo.SysUserRepo;
//
///**
// * 系统用户业务类
// *
// * Created by Tianyi on 2016/5/28.
// */
//@Service
//public class SysUserService extends BaseService<SysUser> implements SysUserService {
//
//  @Autowired
//  private SysUserRepo sysUserRepo;
//
//  public SysUser findByUsername(String username) {
//    return sysUserRepo.findByUsername(username);
//  }
//
//  public boolean updateAccount(SysUser sysUser) {
//    if (sysUser.getId() == null) return false;
//    sysUserRepo.save(sysUser);
//    return true;
//  }
//
//  @Override
//  public boolean saveWithRole(SysUser sysUser, Long[] roles) {
//    return false;
//  }
//}
