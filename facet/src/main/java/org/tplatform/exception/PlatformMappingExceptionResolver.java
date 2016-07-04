package org.tplatform.exception;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.tplatform.framework.log.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by tianyi on 16/7/3.
 */
public class PlatformMappingExceptionResolver extends SimpleMappingExceptionResolver {
  @Override
  protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
    String viewName = determineViewName(e, request);
    // vm方式返回
    if (viewName != null) {
      if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
          .getHeader("X-Requested-With") != null && request
          .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
        // 非异步方式返回
        Integer statusCode = determineStatusCode(request, viewName);
        if (statusCode != null) {
          applyStatusCodeIfPossible(request, response, statusCode);
        }
        // 跳转到提示页面
        return getModelAndView(viewName, e, request);
      } else {
        // 异步方式返回
        try {
          PrintWriter writer = response.getWriter();
          writer.write(e.getMessage());
          response.setStatus(404, e.getMessage());
          //将异常栈信息记录到日志中
          Logger.e(this.getClass(), "统一异常处理", e);
          writer.flush();
        } catch (Exception ex) {
          Logger.e(this.getClass(), "统一异常处理", ex);
        }
        // 不进行页面跳转
        return null;
      }
    } else {
      return null;
    }
  }
}
