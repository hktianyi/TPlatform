package org.tplatform.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 视图拦截器
 * Created by tianyi on 15/12/23.
 */
@Configuration
@EnableAspectJAutoProxy
@Aspect
@Component
public class ViewInterceptor {

  @Pointcut("execution(String org.tplatform..*Ctrl.*(..))")
  public void setViewName() {
  }

  @Around("setViewName()")
  public Object after(ProceedingJoinPoint pjp) throws Throwable {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    String viewName = String.valueOf(pjp.proceed());
    if (viewName != null && !"/login.jsp".equals(viewName) && !"/main.jsp".equals(viewName) && viewName.matches("^/.*\\.jsp$")) {
      request.setAttribute("body", viewName);
      viewName = "1".equals(request.getParameter("layer")) ? "layer.jsp" : "/main.jsp";
    }
    return viewName;
  }

}
