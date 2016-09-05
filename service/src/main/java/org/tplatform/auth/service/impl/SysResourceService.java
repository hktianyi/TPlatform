package org.tplatform.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tplatform.auth.entity.SysResource;
import org.tplatform.auth.fsm.SysResourceType;
import org.tplatform.auth.mapper.SysResourceMapper;
import org.tplatform.auth.service.ISysResourceService;
import org.tplatform.core.fsm.StatusEnum;
import org.tplatform.impl.BaseService;

import java.util.ArrayList;
import java.util.List;
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
    sysResource.setStatus(statusEnum);
    return sysResourceMapper.select(sysResource);
  }

  @Override
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


  /**
   * 支持拖拽
   * @param id
   * @param pid
   * @return
   */
  public boolean updatePid(Long id, Long pid) {
    return sysResourceMapper.updatePid(id, pid) > 0;
  }
}
