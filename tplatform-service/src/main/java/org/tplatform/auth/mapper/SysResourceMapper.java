package org.tplatform.auth.mapper;//package org.tplatform.auth.mapper;
//
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.InsertProvider;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.SelectKey;
//import org.apache.ibatis.annotations.Update;
//import org.tplatform.auth.SysResource;
//import org.tplatform.auth.fsm.SysResourceType;
//import org.tplatform.common.StatusEnum;
//import tk.mybatis.repo.common.Mapper;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Tianyi on 2016/5/28.
// */
//public interface SysResourceMapper extends Mapper<SysResource> {
//
//  @Override
//  @Select("SELECT t1.id, t1.pid, t1.name, t1.icon, t1.action, t1.type, t1.sort, t1.createTime, t1.status, (SELECT COUNT(1) FROM sys_auth_resource t2 WHERE t2.pid = t1.id) leaf FROM sys_auth_resource t1 WHERE t1.pid = #{record.pid}")
//  List<SysResource> select(@Param("record") SysResource record);
//
//  @Override
//  @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "record.id", before = false, resultType = Long.class)
//  @Insert("INSERT INTO sys_auth_resource ( id,createTime,operator,status,pid,name,action,type,icon,sort ) VALUES" +
//      "( #{record.id},#{record.createTime},#{record.operator},#{record.status},#{record.pid},#{record.name}," +
//      "#{record.action},#{record.type},#{record.icon},#{record.sort} ) ")
//  int insert(@Param("record") SysResource record);
//
//  /**
//   * 根据角色查询菜单
//   *
//   * @param roleId
//   * @param type
//   * @param status
//   * @return
//   */
//  @Select("select t1.id, t1.pid, t1.name, t1.action, t1.icon from sys_auth_resource t1, sys_auth_role_resource t2" +
//      " where t1.id = t2.resource_id and t2.role_id in (#{roleId}) and t1.type = #{type} and t1.status = #{status} order by t1.pid, t1.sort")
//  List<SysResource> findByRole(@Param("roleId") String roleId, @Param("type") SysResourceType type, @Param("status") StatusEnum status, @Param("parentCode") Long parentCode);
//
//
//  /**
//   * 更新父节点，拖拽支持
//   * @param id
//   * @param pid
//   * @return
//   */
//  @Update("UPDATE sys_auth_resource SET pid = #{pid} WHERE id = #{id}")
//  int updatePid(@Param("id") Long id, @Param("pid") Long pid);
//
//  @InsertProvider(type = SQLProvider.class, method = "saveWithRole")
//  int saveWithRole(@Param("sysResource") SysResource sysResource, @Param("roles") Long[] roles);
//
//  class SQLProvider {
//
//    public String saveWithRole(Map param) {
//      Long[] roles = (Long[]) param.get("roles");
//      StringBuilder sql = new StringBuilder();
//      sql.append("DELETE FROM sys_auth_role_resource WHERE resource_id = #{sysResource.id};\n");
//      sql.append("INSERT INTO sys_auth_role_resource (resource_id, role_id) VALUES");
//      for (int i = 0, size = roles.length; i < size; i++) {
//        sql.append("(#{sysResource.id},")
//            .append(roles[i])
//            .append("),");
//      }
//      return sql.substring(0, sql.length() - 1);
//    }
//  }
//}
