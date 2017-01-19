package org.tplatform.auth.mapper;//package org.tplatform.auth.mapper;
//
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.InsertProvider;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.SelectKey;
//import org.apache.ibatis.annotations.Update;
//import org.tplatform.auth.entity.SysUser;
//import tk.mybatis.repo.common.Mapper;
//
//import java.util.Map;
//
///**
// * Created by Tianyi on 2016/5/28.
// */
//public interface SysUserMapper extends Mapper<SysUser> {
//
//  @InsertProvider(type = SQLProvider.class, method = "saveWithRole")
//  int saveWithRole(@Param("sysUser") SysUser sysUser, @Param("roles") Long[] roles);
//
//  class SQLProvider {
//
//    public String saveWithRole(Map param) {
//      Long[] roles = (Long[]) param.get("roles");
//      StringBuilder sql = new StringBuilder();
//      sql.append("DELETE FROM sys_auth_user_role WHERE user_id = #{sysUser.id};\n");
//      sql.append("INSERT INTO sys_auth_user_role (user_id, role_id) VALUES");
//      for (int i = 0, size = roles.length; i < size; i++) {
//        sql.append("(#{sysUser.id},")
//            .append(roles[i])
//            .append("),");
//      }
//      return sql.substring(0, sql.length() - 1);
//    }
//  }
//}
