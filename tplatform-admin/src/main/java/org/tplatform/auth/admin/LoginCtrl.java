package org.tplatform.auth.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.theme.AbstractThemeResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.tplatform.auth.SysUser;
import org.tplatform.auth.SysUserService;
import org.tplatform.common.GlobalConstant;
import org.tplatform.util.StringUtil;

import javax.servlet.http.HttpSession;

/**
 * Created by Tianyi on 2015/12/5.
 */
@Controller
@RequestMapping
public class LoginCtrl {

  @Autowired
  private HttpSession session;

  @Autowired
  private AbstractThemeResolver themeResolver;

  @Autowired
  private SysUserService sysUserService;

  /**
   * 登录页
   *
   * @return
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    if (session.getAttribute(GlobalConstant.KEY_SESSION_USER) != null) {
      return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "main";
    }
    return getThemeName() + "/login.jsp";
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
      sysUser.getRoles().size();
      session.setAttribute(GlobalConstant.KEY_SESSION_USER, sysUser);
      // 登录跳转
      String loginTo = String.valueOf(session.getAttribute(GlobalConstant.KEY_SESSION_LOGIN_TO_PAGE));
      if (StringUtil.isNotEmpty(loginTo)) {
        session.removeAttribute(GlobalConstant.KEY_SESSION_LOGIN_TO_PAGE);
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + loginTo;
      } else {
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
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
  @RequestMapping(method = RequestMethod.GET)
  public String main(ModelMap modelMap) {
//    modelMap.put("title", "TPlatform");
//    modelMap.put("body", "/sys/dashboard.jsp");
    return getThemeName() + "/main.jsp";
//    return GlobalConstant.REDIRECT + "/doc/list";
  }

  /**
   * 退出登录
   *
   * @return
   */
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout() {
    session.removeAttribute(GlobalConstant.KEY_SESSION_USER);
    return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/login";
  }

  protected String getThemeName() {
    String themeName = (String) session.getAttribute(SessionThemeResolver.THEME_SESSION_ATTRIBUTE_NAME);
    return StringUtil.isEmpty(themeName) ? themeResolver.getDefaultThemeName() : themeName;
  }
}
