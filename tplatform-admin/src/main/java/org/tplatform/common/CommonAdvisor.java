package org.tplatform.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by tianyi on 2017/3/25.
 */
@ControllerAdvice
public class CommonAdvisor {

  @ExceptionHandler(NoHandlerFoundException.class)
  public String handle(Exception e) {
    return "404.jsp";
  }
}
