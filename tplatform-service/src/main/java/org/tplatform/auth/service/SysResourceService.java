package org.tplatform.auth.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tplatform.auth.entity.SysResource;
import org.tplatform.auth.fsm.SysResourceType;
import org.tplatform.common.BaseRepo;
import org.tplatform.core.fsm.StatusEnum;

import java.util.List;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface SysResourceService extends BaseRepo<SysResource> {

  /**
   * 根据角色查询菜单树
   *
   * @param roleId
   * @param status
   * @return
   */
  List<SysResource> findMenuTree(String roleId, StatusEnum status, Long parentCode);

  @Query(value = "select t1.* from sys_auth_resource t1, sys_auth_role_resource t2 " +
      "where t1.id = t2.resource_id and t2.role_id in (:roleId) and t1.type = :type and t1.status = :status order by t1.pid, t1.sort", nativeQuery = true)
  List<SysResource> findByRole(@Param("roleId") String roleId, @Param("type") String type, @Param("status") String status);

//  /**
//   * 查询菜单
//   *
//   * @param pid
//   * @return
//   */
//  List<SysResource> findMenus(Long pid, StatusEnum statusEnum);
//
//  /**
//   * 查询jstree
//   *
//   * @param pid
//   * @param selectedIds
//   * @param status
//   * @return
//   */
//  List findForTree(Long pid, StatusEnum status, String selectedIds, Class<?> clazz);
//
//  /**
//   * 支持拖拽
//   * @param id
//   * @param pid
//   * @return
//   */
//  public boolean updatePid(Long id, Long pid);
//
//  /**
//   * 保存
//   * @param sysResource
//   * @param roles
//   * @return
//   */
//  public boolean saveWithRole(SysResource sysResource, Long[] roles);
}
