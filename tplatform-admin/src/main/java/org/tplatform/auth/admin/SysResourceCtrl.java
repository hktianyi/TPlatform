package org.tplatform.auth.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.auth.SysResource;
import org.tplatform.auth.SysResourceService;
import org.tplatform.auth.SysRoleService;
import org.tplatform.common.BaseCtrl;
import org.tplatform.common.RespBody;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tianyi on 2015/12/5.
 */
@Controller
@RequestMapping("/sysResource")
public class SysResourceCtrl extends BaseCtrl<SysResource> {

  @Autowired
  private SysResourceService sysResourceService;
  @Autowired
  private SysRoleService sysRoleService;

  @Override
  public String list(ModelMap modelMap) {
    modelMap.put("roles", sysRoleService.findAll());
    return super.list(modelMap);
  }

  @Override
  public String edit(Serializable id, ModelMap modelMap) {
    modelMap.put("roles", sysRoleService.findAll());
    return super.edit(id, modelMap);
  }

  /**
   * Ajax加载数据
   *
   * @param pid
   * @return
   */
  @RequestMapping("/loadTree/{type}")
  @ResponseBody
  public List loadTree(@PathVariable(value = "type") String type, @RequestParam(value = "pid", required = false, defaultValue = "0") Long pid, @RequestParam(value = "selectedIds", required = false) String selectedIds) {
    SysResource sysResource = new SysResource();
    sysResource.setPid(pid);
    return sysResourceService.findForTree(pid, null, selectedIds, SysResource.class);
//    return sysResourceService.findForTree(pid, null, selectedIds, ("jstree".equalsIgnoreCase(type) ? JsTree.class : SysResource.class));
  }

  /**
   * 拖拽节点
   *
   * @param id
   * @param pid
   * @return
   */
  @RequestMapping("/updatePid")
  @ResponseBody
  public RespBody updatePid(@RequestParam Long id, @RequestParam(required = false, defaultValue = "0") Long pid) {
    return RespBody.ok(sysResourceService.updatePid(id, pid));
  }

//  /**
//   * 保存, status如果为空,则设置为无效(INVALID)
//   * @param sysResource
//   * @return
//   */
//  @RequestMapping(value = "/saveWithRole", method = RequestMethod.POST)
//  @ResponseBody
//  public RespBody saveWithRole(SysResource sysResource, @RequestParam("role[]") Long[] roles) {
//    if(sysResource.getStatus() == null) sysResource.setStatus(StatusEnum.INVALID.getCode());
//    return RespBody.ok(sysResourceService.saveWithRole(sysResource, roles));
//  }

  /**
   * 选择图标
   * @return
   */
  @RequestMapping(value = "/icons")
  public String icons() {
    return super.dir.concat("/icons.jsp");
  }
}
