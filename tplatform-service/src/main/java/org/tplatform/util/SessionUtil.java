package org.tplatform.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tplatform.auth.SysUser;
import org.tplatform.common.GlobalConstant;

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

  /**
   * SpringMvc下获取操作员登录名
   *
   * @return
   */
//  public static Long getOperatorId() {
////    SysUser sysUser = getBean(SysUserService.class).findByUsername(getAuthenticatedUsername());
////    if (sysUser != null) {
////      return sysUser.getId();
////    }
////      try {
////        Field field = object.getClass().getDeclaredField("id");
////        field.setAccessible(true);
////        return Long.valueOf(String.valueOf(field.get(object)));
////      } catch (NoSuchFieldException e) {
////        if (object.getClass().getGenericSuperclass() != null) {
////          try {
////            Field field = object.getClass().getSuperclass().getDeclaredField("id");
////            field.setAccessible(true);
////            return Long.valueOf(String.valueOf(field.get(object)));
////          } catch (NoSuchFieldException e1) {
////            e1.printStackTrace();
////          } catch (IllegalAccessException e1) {
////            e1.printStackTrace();
////          }
////        }
////        e.printStackTrace();
////      } catch (IllegalAccessException e) {
////        e.printStackTrace();
////      }
//    return null;
//  }
}
