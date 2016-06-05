package org.tplatform.sys.admin;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tplatform.common.BaseCtrl;
import org.tplatform.core.entity.RespBody;
import org.tplatform.sys.entity.SysInfo;
import org.tplatform.sys.service.ISysInfoService;

import java.lang.management.ManagementFactory;

/**
 * Created by Tianyi on 2016/6/2.
 */
@Controller
@RequestMapping("/sysInfo")
public class SysInfoCtrl extends BaseCtrl<SysInfo> {

  @Autowired
  private ISysInfoService sysInfoService;

  @RequestMapping("/dashboard")
  public String dashbord(ModelMap modelMap) {
    modelMap.put("data", ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class));
    return "/sys/dashboard.jsp";
  }


  @RequestMapping("/refresh")
  @ResponseBody
  public RespBody refresh() {
    return RespBody.ok(ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class));
  }

}
