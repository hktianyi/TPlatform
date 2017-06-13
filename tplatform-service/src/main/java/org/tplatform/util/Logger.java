package org.tplatform.util;

import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.tplatform.domain.Log;
import org.tplatform.domain.LogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by Tianyi on 2014/11/19.
 */
//@DependsOn("springContextUtil")
public final class Logger {

  private static LogService logService = SpringContextUtil.getBean(LogService.class);

//  private static String provider, to;
//
//  static {
//    ConfService confService = SpringContextUtil.getBean(ConfService.class);
//    provider = confService.findVal("Email_Provider");
//    to = confService.findVal("Email_Exception_Addressee");
//  }

  private Logger() {
  }

  private static String getClazzName() {
    return Thread.currentThread().getStackTrace()[3].getClassName();
  }

  /**
   * INFO记录
   *
   * @param msg
   */
  public static void d(String msg) {
    LoggerFactory.getLogger(getClazzName()).debug(msg);
  }

  /**
   * INFO记录
   *
   * @param msg
   */
  public static void d(String msg, Object... arguments) {
    LoggerFactory.getLogger(getClazzName()).debug(msg, arguments);
  }

  /**
   * INFO记录
   *
   * @param msg
   */
  public static void i(String msg) {
    LoggerFactory.getLogger(getClazzName()).info(msg);
  }

  /**
   * 警告
   *
   * @param msg
   */
  public static void w(String msg) {
    LoggerFactory.getLogger(getClazzName()).warn(msg);
  }

  /**
   * 错误
   *
   * @param msg
   */
  public static void e(String msg) {
    LoggerFactory.getLogger(getClazzName()).error(msg);
//    MailUtil.sendSimple("zhangyuyong@yunqiandai.com", "zhangyuyong@yunqiandai.com", msg + "[" + clazz.getName() + "]", msg);
  }

  /**
   * 带异常信息的错误日志
   *
   * @param msg
   * @param e
   */
  public static void e(String msg, Exception e) {
    String className = getClazzName();
    String text = logStyle(className, msg, e);
    LoggerFactory.getLogger(className).error(text);
//    if(StringUtil.isNotEmpty(to))
//      MailUtil.sendSimple("zhangyuyong@yunqiandai.com", "zhangyuyong@yunqiandai.com", msg + "[" + className + "]", text);
  }

  private static String logStyle(String className, String message, Exception e) {
    StringBuffer info = new StringBuffer("");
    info.append("\n错误位置:\n\t");
    info.append(className);
    info.append("\n描述:\n\t");
    info.append(message);
    info.append("\n类型:\n\t");
    info.append(e.getMessage());
    info.append("\n堆栈信息:\n");

    StackTraceElement[] message1 = e.getStackTrace();
    int cou = message1.length;

    for (int i = 0; i < cou; i++) {
      info.append("\t" + message1[i].toString() + "\n");
    }
    return info.toString();
  }

  /**
   * 保存日志
   */
  public static void saveLog(HttpServletRequest request, String title) {
    saveLog(request, null, null, title);
  }

  /**
   * 保存日志
   */
  public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title) {
    Log log = new Log();
    log.setTitle(title);
    log.setType(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
    log.setRemoteAddr(StringUtil.getRemoteAddr(request));
    log.setUserAgent(request.getHeader("user-agent"));
    log.setRequestUri(request.getRequestURI());
    log.setParams(request.getParameterMap());
    log.setMethod(request.getMethod());
    log.setOperator(SessionUtil.getAuthenticatedUsername());
    // 异步保存日志
    new SaveLogThread(log, handler, ex).start();
  }

  /**
   * 保存日志线程
   */
  public static class SaveLogThread extends Thread {

    private Log log;
    private Object handler;
    private Exception ex;

    public SaveLogThread(Log log, Object handler, Exception ex) {
      super(SaveLogThread.class.getSimpleName());
      this.log = log;
      this.handler = handler;
      this.ex = ex;
    }

    @Override
    public void run() {
      // 获取日志标题
      if (StringUtil.isBlank(log.getTitle())) {
        String permission = "";
        if (handler instanceof HandlerMethod) {
          Method m = ((HandlerMethod) handler).getMethod();
        }
        log.setTitle(log.getRequestUri());
      }
      // 如果有异常，设置异常信息
      if (ex != null)
        log.setException(ex.getMessage());
      // 如果无标题并无异常日志，则不保存信息
      if (StringUtil.isBlank(log.getTitle()) && StringUtil.isBlank(log.getException())) {
        return;
      }
      // 保存日志信息
      logService.save(log);
    }
  }

  /**
   * 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
   */
//  public static String getMenuNamePath(String requestUri, String permission){
//    String href = StringUtil.substringAfter(requestUri, Global.getAdminPath());
//    @SuppressWarnings("unchecked")
//    Map<String, String> menuMap = (Map<String, String>)CacheUtils.get(CACHE_MENU_NAME_PATH_MAP);
//    if (menuMap == null){
//      menuMap = Maps.newHashMap();
//      List<Menu> menuList = menuDao.findAllList(new Menu());
//      for (Menu menu : menuList){
//        // 获取菜单名称路径（如：系统设置-机构用户-用户管理-编辑）
//        String namePath = "";
//        if (menu.getParentIds() != null){
//          List<String> namePathList = Lists.newArrayList();
//          for (String id : StringUtil.split(menu.getParentIds(), ",")){
//            if (Menu.getRootId().equals(id)){
//              continue; // 过滤跟节点
//            }
//            for (Menu m : menuList){
//              if (m.getId().equals(id)){
//                namePathList.add(m.getName());
//                break;
//              }
//            }
//          }
//          namePathList.add(menu.getName());
//          namePath = StringUtil.join(namePathList, "-");
//        }
//        // 设置菜单名称路径
//        if (StringUtil.isNotBlank(menu.getHref())){
//          menuMap.put(menu.getHref(), namePath);
//        }else if (StringUtil.isNotBlank(menu.getPermission())){
//          for (String p : StringUtil.split(menu.getPermission())){
//            menuMap.put(p, namePath);
//          }
//        }
//
//      }
//      CacheUtils.put(CACHE_MENU_NAME_PATH_MAP, menuMap);
//    }
//    String menuNamePath = menuMap.get(href);
//    if (menuNamePath == null){
//      for (String p : StringUtil.split(permission)){
//        menuNamePath = menuMap.get(p);
//        if (StringUtil.isNotBlank(menuNamePath)){
//          break;
//        }
//      }
//      if (menuNamePath == null){
//        return "";
//      }
//    }
//    return menuNamePath;
//  }
}
