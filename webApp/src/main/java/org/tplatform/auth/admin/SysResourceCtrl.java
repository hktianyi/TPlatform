package org.tplatform.auth.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.auth.entity.SysResource;
import org.tplatform.auth.service.ISysResourceService;
import org.tplatform.common.BaseCtrl;
import org.tplatform.core.entity.RespBody;

/**
 * Created by Tianyi on 2015/12/5.
 */
@Controller
@RequestMapping("/sysResource")
public class SysResourceCtrl extends BaseCtrl<SysResource> {

  @Autowired
  private ISysResourceService sysResourceService;

  /**
   * Ajax加载数据
   *
   * @param pid
   * @return
   */
  @RequestMapping("/loadTree")
  @ResponseBody
  public RespBody loadTree(@RequestParam(value = "id", required = false, defaultValue = "0") Long pid) {
    SysResource sysResource = new SysResource();
    sysResource.setPid(pid);
    return RespBody.ok(sysResourceService.find(sysResource));
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
    SysResource sysResource = new SysResource();
    sysResource.setId(id);
    sysResource.setPid(pid);
    return RespBody.ok(sysResourceService.update(sysResource));
  }

}
