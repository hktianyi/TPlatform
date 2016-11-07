package org.tplatform.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tplatform.auth.entity.SysResource;
import org.tplatform.auth.fsm.SysResourceType;
import org.tplatform.auth.mapper.SysResourceMapper;
import org.tplatform.core.entity.JsTree;
import org.tplatform.core.fsm.StatusEnum;
import org.tplatform.framework.util.StringUtil;
import org.tplatform.impl.BaseService;

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
public class SysResourceService extends BaseService<SysResource> implements ISysResourceService {

  @Autowired
  private SysResourceMapper sysResourceMapper;

  /**
   * 查询菜单
   *
   * @param pid
   * @return
   */
  public List<SysResource> findMenus(Long pid, StatusEnum statusEnum) {
    SysResource sysResource = new SysResource();
    sysResource.setPid(pid);
    sysResource.setType(SysResourceType.MENU);
    sysResource.setStatus(statusEnum.VALID.getCode());
    return sysResourceMapper.select(sysResource);
  }

  @Override
//  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_MENU_TREE_' + #roleId + #status + #parentCode")
  public List<SysResource> findMenuTree(String roleId, StatusEnum status, Long parentCode) {
    List<SysResource> result = sysResourceMapper.findByRole(roleId, SysResourceType.MENU, status, parentCode);
    result.stream().forEach(parent -> result.stream().forEach(child -> {
      if (child.getPid() == parent.getId()) {
        if (parent.getChildren() == null)
          parent.setChildren(new ArrayList<>());
        parent.getChildren().add(child);
      }
    }));
    return result.stream().filter(sysResource -> sysResource.getPid() == 0).collect(Collectors.toList());
  }

  @Override
  public List findForTree(Long pid, StatusEnum status, String selectedIds, Class<?> clazz) {
    SysResource sysResource = new SysResource();
    sysResource.setPid(pid);
    List<SysResource> sysResourceList = super.find(sysResource);
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


  /**
   * 支持拖拽
   * @param id
   * @param pid
   * @return
   */
  public boolean updatePid(Long id, Long pid) {
    return sysResourceMapper.updatePid(id, pid) > 0;
  }

  @Override
//  @CacheEvict(value = GlobalConstant.KEY_CACHE_SYS, key = "'_MENU_TREE_*'")
  public boolean saveWithRole(SysResource sysResource, Long[] roles) {
    super.saveOrUpdate(sysResource);
    return sysResourceMapper.saveWithRole(sysResource, roles) > 0;
  }
}
