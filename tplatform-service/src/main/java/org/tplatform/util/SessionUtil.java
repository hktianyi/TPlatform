package org.tplatform.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tplatform.auth.SysRole;
import org.tplatform.auth.SysUser;
import org.tplatform.auth.SysUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户会话工具类
 */
@Component
public class SessionUtil {

  /**
   * SpringMvc下获取request
   *
   * @return
   */
  public static HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }

  /**
   * SpringMvc下获取session
   *
   * @return
   */
  public static HttpSession getSession() {
    return getRequest().getSession();
  }

  public static String getAuthenticatedUsername() {
    String username;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof User) {
      username = ((User) principal).getUsername();
    } else if (principal instanceof SysUser) {
      username = ((SysUser) principal).getUsername();
    } else {
      username = (String)principal;
    }
    return username;
  }

  public static SysUser getUser() {
    SysUser sysUser;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof User) {
      sysUser = SpringContextUtil.getBean(SysUserService.class).findOne(((User) principal).getUsername());
    } else if (principal instanceof SysUser) {
      sysUser = (SysUser) principal;
    } else {
      sysUser = null;
    }
    return sysUser;
  }

  /**
   * 获取所属机构ID
   * @return Long
   */
  public static Long getOrganId() {
    Long organId;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof User) {
      organId = SpringContextUtil.getBean(SysUserService.class).findOne(((User) principal).getUsername()).getOrganId();
    } else if (principal instanceof SysUser) {
      organId = ((SysUser) principal).getOrganId();
    } else {
      organId = null;
    }
    return organId;
  }

  /**
   * 获取角色数据类型
   * @return Integer
   */
  public static Integer getDataAuthType() {
    SysUser sysUser = getUser();
    SysRole role = sysUser.getRoles().parallelStream().min((o1, o2) -> o1.getDataAuthType() - o1.getDataAuthType()).orElse(null);
    if (role == null) {
      return null;
    } else {
      return role.getDataAuthType();
    }
  }

}
