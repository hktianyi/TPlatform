package org.tplatform.auth.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.tplatform.auth.entity.SysUser;
import org.tplatform.auth.service.ISysRoleService;
import org.tplatform.auth.service.ISysUserService;
import org.tplatform.constant.GlobalConstant;
import org.tplatform.core.fsm.StatusEnum;
import org.tplatform.framework.util.StringUtil;

import javax.servlet.http.HttpSession;

/**
 * Created by Tianyi on 2015/12/5.
 */
@Controller
@RequestMapping
public class LoginCtrl {

  @Autowired
  protected HttpSession session;

  @Autowired
  private ISysUserService sysUserService;
  @Autowired
  private ISysRoleService sysRoleService;

  /**
   * 登录页
   *
   * @return
   */
  @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
  public String login() {
    if (session.getAttribute(GlobalConstant.SESSION_USER_KEY) != null) {
      return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "main.html";
    }
    return "/login.jsp";
  }

  /**
   * 用户登录
   *
   * @param username
   * @param password
   * @param modelMap
   * @return
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(String username, String password, ModelMap modelMap) {
    SysUser sysUser = sysUserService.findByUsername(username);
    if (sysUser != null && sysUser.getPassword().equals(password)) {
      sysUser.setRoles(sysRoleService.findByUserId(sysUser.getId(), StatusEnum.VALID));
      session.setAttribute(GlobalConstant.SESSION_USER_KEY, sysUser);
      // 登录跳转
      String loginTo = String.valueOf(session.getAttribute(GlobalConstant.SESSION_LOSE_TO_PAGE_KEY));
      if (StringUtil.isNotEmpty(loginTo)) {
        session.removeAttribute(GlobalConstant.SESSION_LOSE_TO_PAGE_KEY);
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + loginTo;
      } else {
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "main.html";
      }
    } else {
      modelMap.put("errorMsg", "用户名或密码错误！");
      return login();
    }
  }

  /**
   * 主页
   *
   * @return
   */
  @RequestMapping(value = "/main.html", method = RequestMethod.GET)
  public String main(ModelMap modelMap) {
    modelMap.put("title", "TPlatform");
    modelMap.put("body", "/sys/dashboard.jsp");
    return "/main.jsp";
  }

  /**
   * 退出登录
   *
   * @return
   */
  @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
  public String logout() {
    throw new RuntimeException("123");
//    session.removeAttribute(GlobalConstant.SESSION_USER_KEY);
//    return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/login";
  }

}
