package org.tplatform.auth.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.auth.entity.SysOrgan;
import org.tplatform.auth.service.ISysOrganService;
import org.tplatform.common.BaseCtrl;
import org.tplatform.core.entity.JsTree;

import java.util.List;

/**
 * Created by Tianyi on 2015/12/5.
 */
@Controller
@RequestMapping("/sysOrgan")
public class SysOrganCtrl extends BaseCtrl<SysOrgan> {

  @Autowired
  private ISysOrganService sysOrganService;

  /**
   * Ajax加载数据
   *
   * @param pCode
   * @return
   */
  @RequestMapping("/loadTree/{type}")
  @ResponseBody
  public List loadTree(@PathVariable(value = "type") String type, @RequestParam(value = "pCode", required = false, defaultValue = "0") String pCode, @RequestParam(value = "selectedIds", required = false) String selectedIds) {
    SysOrgan sysOrgan = new SysOrgan();
    sysOrgan.setPCode(pCode);
    return sysOrganService.findForTree(pCode, null, selectedIds, ("jstree".equalsIgnoreCase(type) ? JsTree.class : SysOrgan.class));
  }
}
