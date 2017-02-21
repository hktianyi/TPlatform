package org.tplatform.auth.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.tplatform.auth.entity.SysResource;
import org.tplatform.auth.fsm.SysResourceType;
import org.tplatform.auth.service.SysResourceService;
import org.tplatform.core.entity.JsTree;
import org.tplatform.core.fsm.StatusEnum;
import org.tplatform.util.StringUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 系统资源（菜单、按钮）业务类
 * Created by Tianyi on 2016/5/28.
 */
@Service
public class SysResourceServiceImpl {

  @Autowired
  private EntityManager em;
  @Autowired
  private SysResourceService sysResourceService;

//  /**
//   * 查询菜单
//   *
//   * @param pid
//   * @return
//   */
//  public List<SysResource> findMenus(Long pid, StatusEnum statusEnum) {
//    SysResource sysResource = new SysResource();
//    sysResource.setPid(pid);
//    sysResource.setType(SysResourceType.MENU);
//    sysResource.setStatus(statusEnum.VALID.getCode());
//    return find(sysResource);
//  }

//  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_MENU_TREE_' + #roleId + #status + #parentCode")
  public List<SysResource> findMenuTree(String roleId, StatusEnum status, Long parentCode) {
    List<SysResource> result = sysResourceService.findByRole(roleId, SysResourceType.MENU.toString(), status.toString());
//    Query query = em.createNativeQuery("select t1.* from sys_auth_resource t1, sys_auth_role_resource t2 " +
//        "where t1.id = t2.resource_id and t2.role_id in (:roleId) and t1.type = :type and t1.status = :status order by t1.pid, t1.sort", SysResource.class);
//    query.setParameter("roleId", roleId);
//    query.setParameter("type", SysResourceType.MENU.toString());
//    query.setParameter("status", status.toString());
//    List<SysResource> result = query.getResultList();
    result.stream().forEach(parent -> result.stream().forEach(child -> {
      if (child.getPid() == parent.getId()) {
        if (parent.getChildren() == null)
          parent.setChildren(new ArrayList<>());
        parent.getChildren().add(child);
      }
    }));
    return result.stream().filter(sysResource -> sysResource.getPid() == 0).collect(Collectors.toList());
  }

  public List findForTree(Long pid, StatusEnum status, String selectedIds, Class<?> clazz) {
    SysResource sysResource = new SysResource();
    sysResource.setPid(pid);
    List<SysResource> sysResourceList = sysResourceService.findAll(Example.of(sysResource));
    if(JsTree.class.equals(clazz)) {
      // 解析选中的标签
      Set<Long> selectedId = (StringUtil.isEmpty(selectedIds) || "0".equals(selectedIds)) ? null : Arrays.stream(selectedIds.split(",")).map(Long::valueOf).collect(Collectors.toSet());

      // 循环sysResourceList, 生成List<JsTree>
      List list = sysResourceList.stream().map(temp -> {
        JsTree jsTree = new JsTree();
        jsTree.setId(temp.getId());
        jsTree.setText(temp.getName());
        jsTree.setIcon("fa fa-" + temp.getIcon());
        jsTree.setState("loaded", temp.getLeaf() < 1);

        if(selectedId!=null) {
          // 判断当前level是否有选中的节点
          for (Long id : selectedId) {
            if(Objects.equals(temp.getId(), id)) {
              selectedId.remove(id);
              jsTree.setState("selected", "true");
            }
          }
        }
        return jsTree;
      }).collect(Collectors.toList());

//          // 未移除,需要循环层级
//          // TODO 只处理了二级关联
//          if(moved[0]) {
//            SysResource child = super.find(id);
//            sysResourceList.parallelStream().filter(temp -> Objects.equals(child.getPid(), temp.getId())).forEach(temp -> {
//              sysResource.setPid(temp.getId());
//              temp.setChildren(super.find(sysResource));
//            });
//          }
      return list;
    }
    return sysResourceList;
  }


//  /**
//   * 支持拖拽
//   * @param id
//   * @param pid
//   * @return
//   */
//  public boolean updatePid(Long id, Long pid) {
//    return sysResourceMapper.updatePid(id, pid) > 0;
//  }
//
//  @Override
////  @CacheEvict(value = GlobalConstant.KEY_CACHE_SYS, key = "'_MENU_TREE_*'")
//  public boolean saveWithRole(SysResource sysResource, Long[] roles) {
//    super.saveOrUpdate(sysResource);
//    return sysResourceMapper.saveWithRole(sysResource, roles) > 0;
//  }
}
