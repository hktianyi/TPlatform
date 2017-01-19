package org.tplatform.auth.repo;//package org.tplatform.auth.repo;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.tplatform.auth.entity.SysResource;
//import org.tplatform.auth.fsm.SysResourceType;
//import org.tplatform.core.fsm.StatusEnum;
//import org.tplatform.core.repo.BaseRepo;
//
//import java.util.List;
//
///**
// * Created by tianyi on 2016/11/10.
// */
//public interface SysResourceRepo extends BaseRepo<SysResource> {
//
//  @Query(value = "select t1.id, t1.pid, t1.name, t1.action, t1.icon from sys_auth_resource t1, sys_auth_role_resource t2 " +
//      "where t1.id = t2.resource_id and t2.role_id in (:roleId) and t1.type = :type and t1.status = :status order by t1.pid, t1.sort", nativeQuery = true)
//  List<SysResource> findByRole(@Param("roleId") String roleId, @Param("type") SysResourceType type, @Param("status") StatusEnum status);
//}
