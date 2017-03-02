package org.tplatform.auth.repo;//package org.tplatform.auth.form;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.tplatform.auth.entity.SysRole;
//import org.tplatform.auth.repo.SysRoleRepo;
//import org.tplatform.common.StatusEnum;
//
//import java.util.Set;
//
///**
// * 角色业务类
// * <p>
// * Created by Tianyi on 2016/5/28.
// */
//@Service
//public class SysRoleService extends BaseService<SysRole> implements SysRoleService {
//
//  @Autowired
//  private SysRoleRepo sysRoleRepo;
//
//  /**
//   * 根据用户查找角色
//   * @param userId
//   * @param status
//   * @return
//   */
//  public Set<SysRole> findByUserId(Long userId, StatusEnum status) {
////    return sysRoleRepo.findByUserIdAndStatus(userId, status);
//    return null;
//  }
//
//  /**
//   * 根据菜单资源查找角色
//   * @param resourceId
//   * @return
//   */
//  public Set<SysRole> findByResourceId(Long resourceId) {
////    return sysRoleRepo.findByResourceId(resourceId);
//    return null;
//  }
//
//  /**
//   * 支持拖拽
//   * @param id
//   * @param pid
//   * @return
//   */
//  public boolean updatePid(Long id, Long pid) {
//    return false;
//  }
//}
