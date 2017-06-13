package org.tplatform.common;

import com.google.common.base.Throwables;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.tplatform.util.DateUtil;
import org.tplatform.util.Logger;
import org.tplatform.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ForbiddenException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常处理
 * Created by tianyi on 2017/2/9.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  private ModelAndView handleError(HttpServletRequest req, HttpServletResponse rsp, Exception e, String viewName, HttpStatus status) throws Exception {
    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
      throw e;
    String errorMsg = e.getMessage();
    String errorStack = Throwables.getStackTraceAsString(e);

    Logger.e("Request: {" + req.getRequestURI() + "} raised {" + errorStack + "}");
    String requestType = req.getHeader("X-Requested-With");
    if (!StringUtil.isEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest")) {
      return handleAjaxError(rsp, errorMsg, status);
    } else {
      return handleViewError(req.getRequestURL().toString(), errorStack, errorMsg, viewName);
    }
  }

  private ModelAndView handleViewError(String url, String errorStack, String errorMessage, String viewName) {
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", errorStack);
    mav.addObject("url", url);
    mav.addObject("message", errorMessage);
    mav.addObject("timestamp", DateUtil.getCurrentDate());
    mav.setViewName(viewName);
    return mav;
  }

  private ModelAndView handleAjaxError(HttpServletResponse rsp, String errorMessage, HttpStatus status) throws IOException {
    rsp.setCharacterEncoding("UTF-8");
    rsp.setStatus(status.value());
    PrintWriter writer = rsp.getWriter();
    writer.write(errorMessage == null ? "" : errorMessage);
    writer.flush();
    return null;
  }

  //403的异常就会被这个方法捕获
  @ExceptionHandler(ForbiddenException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ModelAndView handle403Error(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
    return handleError(req, rsp, e, "../base/403.jsp", HttpStatus.FORBIDDEN);
  }

  //404的异常就会被这个方法捕获
  @ExceptionHandler({NoHandlerFoundException.class, NoSuchRequestHandlingMethodException.class})
  @ResponseStatus(HttpStatus.OK)
  public ModelAndView handle404Error(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
    return handleError(req, rsp, e, "../base/404.jsp", HttpStatus.NOT_FOUND);
  }

  //400的异常就会被这个方法捕获
  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ModelAndView handle400Error(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
    return handleError(req, rsp, e, "../base/400.jsp", HttpStatus.NOT_FOUND);
  }

  //405的异常就会被这个方法捕获
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public ModelAndView handle405Error(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
    return handleError(req, rsp, e, "../base/405.jsp", HttpStatus.NOT_FOUND);
  }

  //500的异常会被这个方法捕获
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ModelAndView handleError(HttpServletRequest req, HttpServletResponse rsp, Exception e) throws Exception {
    return handleError(req, rsp, e, "../base/500.jsp", HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
