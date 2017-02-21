package org.tplatform.auth.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.auth.entity.SysRole;
import org.tplatform.auth.service.SysRoleService;
import org.tplatform.common.BaseCtrl;

import java.util.List;

/**
 * Created by Tianyi on 2015/12/5.
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleCtrl extends BaseCtrl<SysRole> {

  @Autowired
  private SysRoleService sysRoleService;

  /**
   * Ajax加载数据
   *
   * @param pid
   * @return
   */
  @RequestMapping("/loadTree")
  @ResponseBody
  public List<SysRole> loadTree(@RequestParam(value = "id", required = false, defaultValue = "0") Long pid) {
    SysRole sysRole = new SysRole();
    sysRole.setPid(pid);
    return sysRoleService.findAll(Example.of(sysRole));
  }

//  /**
//   * 拖拽节点
//   *
//   * @param id
//   * @param pid
//   * @return
//   */
//  @RequestMapping("/updatePid")
//  @ResponseBody
//  public RespBody updatePid(@RequestParam Long id, @RequestParam(required = false, defaultValue = "0") Long pid) {
//    return RespBody.ok(sysRoleService.updatePid(id, pid));
//  }
}
