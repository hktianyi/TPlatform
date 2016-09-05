package org.tplatform.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tplatform.framework.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 视图拦截器
 * Created by tianyi on 15/12/23.
 */
//@Configuration
//@EnableAspectJAutoProxy
//@Aspect
//@Component
public class ViewInterceptor {

  @Pointcut("execution(String org..*Ctrl.*(..))")
  public void setViewName() {
  }

  /**
   * 视图拦截器，强制返回装饰页（参数 layer = 1 时返回弹层layer.jsp， 否则返回 main.jsp）
   *
   * @param pjp
   * @return
   * @throws Throwable
   */
  @Around("setViewName()")
  public Object after(ProceedingJoinPoint pjp) throws Throwable {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String viewName = String.valueOf(pjp.proceed());
    if (viewName != null && !"/login.html".equals(viewName) && !"/main.jsp".equals(viewName) && viewName.matches("^/.*\\.jsp$")) {
      request.setAttribute("body", viewName);
      String layer = request.getParameter("layer");
      if (StringUtil.isEmpty(layer)) viewName = "/main.jsp";
      else if ("1".equals(layer)) viewName = "/layer.jsp";
    }
    return viewName;
  }

}
