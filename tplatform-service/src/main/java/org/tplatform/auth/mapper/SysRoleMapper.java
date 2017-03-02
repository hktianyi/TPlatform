package org.tplatform.auth.mapper;//package org.tplatform.auth.mapper;
//
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.SelectProvider;
//import org.apache.ibatis.annotations.Update;
//import org.tplatform.auth.entity.SysRole;
//import org.tplatform.common.StatusEnum;
//import tk.mybatis.repo.common.Mapper;
//
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created by Tianyi on 2016/5/28.
// */
//public interface SysRoleMapper extends Mapper<SysRole> {
//
//  /**
//   * 根据用户查询角色
//   *
//   * @param userId
//   * @param status
//   * @return
//   */
//  @SelectProvider(type = SQLProvider.class, method = "findByUserId")
//  Set<SysRole> findByUserId(@Param("userId") Long userId, @Param("status") StatusEnum status);
//
//  /**
//   * 根据菜单资源查询角色
//   *
//   * @param resourceId
//   * @return
//   */
//  @Select("select t1.id, t1.name from sys_auth_role t1, sys_auth_role_resource t2" +
//      " where t1.id = t2.role_id and t2.resource_id = ${resourceId}")
//  Set<SysRole> findByResourceId(@Param("resourceId") Long resourceId);
//
//  /**
//   * 更新父节点，拖拽支持
//   * @param id
//   * @param pid
//   * @return
//   */
//  @Update("UPDATE sys_auth_role SET pid = #{pid} WHERE id = #{id}")
//  int updatePid(@Param("id") Long id, @Param("pid") Long pid);
//
//  class SQLProvider {
//    public String findByUserId(Map param) {
//      String sql = "select t1.id, t1.name from sys_auth_role t1, sys_auth_user_role t2" +
//          " where t1.id = t2.role_id and t2.user_id = #{userId}";
//      if(param.get("status") != null) sql += " and t1.status = #{status}";
//      return sql;
//    }
//  }
//}
