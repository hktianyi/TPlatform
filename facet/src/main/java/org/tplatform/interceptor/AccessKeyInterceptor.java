package org.tplatform.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by tianyi on 15/12/23.
 */
public class AccessKeyInterceptor extends HandlerInterceptorAdapter {

//  @Autowired
//  private IAccessService accessService;

  private String accessKeyParameterName="accessKey";
  private List<String> defaultAccessAllowedFrom;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String accessKey=request.getParameter(accessKeyParameterName);
    String referer = request.getHeader("Referer");
//    URL u = new URL(referer);
//    String host = u.getHost().toLowerCase();
//    if(accessKey==null){
//      Logger.e("====================================ILLEGAL ACCESS: ACCESS_KEY_MISSING!=======================");
//    }else{
////      IAccess access = accessService.getAccess(UserSessionUtils.getUserSession(request), accessKey);
////      if(access!=null){
////        defaultAccessAllowedFrom=access.getAccessAllowedFrom();
////      }else{
////        log.warn("======================================ACCESS KEY:"+accessKey+" DOES NOT EXIST!=================");
////      }
//    }
//    for(String s : defaultAccessAllowedFrom) {
//      if(host.matches(s)){
        response.setHeader("Access-Control-Allow-Origin", "*");
//        break;
//      }
//    }
    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    response.setHeader("Access-Control-Allow-Methods", "GET");
    response.setHeader("Allow", "GET");
    return true;
  }

  public String getAccessKeyParameterName() {
    return accessKeyParameterName;
  }

  public void setAccessKeyParameterName(String accessKeyParameterName) {
    this.accessKeyParameterName = accessKeyParameterName;
  }

  public List<String> getDefaultAccessAllowedFrom() {
    return defaultAccessAllowedFrom;
  }

  public void setDefaultAccessAllowedFrom(List<String> defaultAccessAllowedFrom) {
    this.defaultAccessAllowedFrom = defaultAccessAllowedFrom;
  }
}
