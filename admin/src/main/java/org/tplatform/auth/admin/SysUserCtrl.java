package org.tplatform.auth.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tplatform.auth.entity.SysUser;
import org.tplatform.common.BaseCtrl;

/**
 * Created by Tianyi on 2015/12/5.
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserCtrl extends BaseCtrl<SysUser> {

}
