package org.tplatform.constant;

/**
 * 常量类
 * Created by Tianyi on 2014/11/20.
 */
public class GlobalConstant {

  public final static String REDISHOST = "";

  public final static String ENCODE = "UTF-8";
  public static final String PROJECT_CLASS_PATH = GlobalConstant.class.getClassLoader().getResource("/").getFile();// 项目类的根路径
  public static final String PROJECT_ROOT_PATH = PROJECT_CLASS_PATH.replaceAll("WEB-INF/.*", "");// 项目根路径

  public final static String STREAM_DATA_NAME = "streamData";
  public final static String FILE_DOMAIN = "http://f.changdiyun.com";

  public final static String PRODUCES = "text/html;charset=UTF-8";
  public final static String FORWARD = "forward:";
  public final static String REDIRECT = "redirect:";

  /* -----------------------------------系统常量开始-------------------------------------- */
  public final static String SYSTEM_APPLICATION_FILE_DOMAIN = "FILE_DOMAIN";
  public final static String SYSTEM_APPLICATION_NAME = "APP_NAME";
  public final static String APP_MODULE_NAME = "_MODULE_NAME";
  public final static String SYSTEM_SERVLET_PATH = "_PATH";
  public final static String SYSTEM_SERVLET_VERSION = "_VERSION";
  public final static String SYSTEM_SERVLET_NAME_SPRINGMVC = "spring-mvc";
  public final static String SYSTEM_SERVLET_NAME_DRUIDSTATVIEW = "druid-stat-view";
  /* -----------------------------------系统常量结束-------------------------------------- */

  /* -----------------------------------session key开始-------------------------------------- */
  public final static String KEY_SESSION_LOGIN_TO_PAGE = "_LOGIN_TO";
  public final static String KEY_SESSION_USER = "_USER";
  public final static String KEY_SESSION_APPID = "_APP_ID";
  public final static String KEY_SESSION_OPENID = "_OPEN_ID";
  /* -----------------------------------session key结束-------------------------------------- */

  /* -----------------------------------缓存KEY开始-------------------------------------- */
  public final static String KEY_CACHE_SYS = "_SYS";
  public final static int CACHE_TIME_10MIN = 10 * 60;
  public final static int CACHE_TIME_1MIN = 60;
  public final static String CACHE_WC_TOKEN = "WC_TOKEN_";
  public final static String CACHE_CM_LOTTERY_TIMES = "CM_LOTTERY_TIMES_";
  public final static String CACHE_CM_LOTTERY_POSTAL_STATUS = "CM_LOTTERY_STATUS_";
  public final static String CACHE_CM_LOTTERY_COUPON_STATUS = "CM_LOTTERY_COUPON_STATUS_";
  public final static String CACHE_CM_MOBILE_CODE = "CM_MOBILE_CODE_";
  public final static String CACHE_WC_TICKET = "WC_TICKET_";
  /* -----------------------------------缓存KEY结束-------------------------------------- */

}
