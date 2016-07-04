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

  /**
   * URL
   */
  public final static String PRODUCES = "text/html;charset=UTF-8";
  public final static String FORWARD = "forward:";
  public final static String REDIRECT = "redirect:";
  public final static String FORWARD_500 = "/error/500.jsp";
  public final static String REDIRECT_500 = "/error/500.jsp";
  public final static String SESSION_LOSE_TO_PAGE_KEY = "loginTo";

  /**
   * 系统常量
   */
  public final static String SESSION_USER_KEY = "user";
  public final static String SYSTEM_APPLICATION_NAME = "APP_NAME";
  public final static String APP_MODULE_NAME = "MODULE_NAME";
  public final static String SYSTEM_SERVLET_PATH = "_PATH";
  public final static String SYSTEM_SERVLET_VERSION = "_VERSION";
  public final static String SYSTEM_SERVLET_NAME_SPRINGMVC = "spring-mvc";

  /**
   * 错误页面
   */
  public final static String PAGE_WAIT = "/h/wait.html";//敬请期待页面
  public final static String PAGE_404 = "2";//404
  public final static String PAGE_500 = "2";//500

  public final static String MENU_ROOT = "0";     //菜单根节点
  public final static String CHILDREN_HAS = "1";  //菜单根节点
  public final static String CHILDREN_NULL = "0"; //菜单根节点


  /*------------------------------------channel 表数据类型  开始-------------------------*/
  public final static String CHANNEL = "1";     //渠道数据
  public final static String TRANSFER = "2";     //换量数据
  /*------------------------------------channel 表数据类型  结束-------------------------*/

  /* -----------------------------------缓存KEY开始-------------------------------------- */
  public final static int CACHE_TIME_10MIN = 10 * 60;
  public final static int CACHE_TIME_1MIN = 60;
  public final static String CACHE_WC_TOKEN = "WC_TOKEN_";
  public final static String CACHE_CM_LOTTERY_TIMES = "CM_LOTTERY_TIMES_";
  public final static String CACHE_CM_LOTTERY_POSTAL_STATUS = "CM_LOTTERY_STATUS_";
  public final static String CACHE_CM_LOTTERY_COUPON_STATUS = "CM_LOTTERY_COUPON_STATUS_";
  public final static String CACHE_CM_MOBILE_CODE = "CM_MOBILE_CODE_";
  public final static String CACHE_WC_TICKET = "WC_TICKET_";
  /* -----------------------------------缓存KEY结束-------------------------------------- */
  
  
  public static final String TRANSFER_MODEL_ELENAME = "cooperateType_1"; //换量模式elename
  public static final String TRANSFER_RATIO_ELENAME = "transferRatioStar_1"; // 换量比例elename
  
  public static final String PLATFORM_ANDROID = "Android";
  public static final String PLATFORM_IOS = "IOS";
  public static final String PLATFORM_WECHAT = "WeChat";
  public static final String PLATFORM_OTHER = "Other";

  public static final String DF_AUTO_OPERATOR = "AUTO";


  //
  public static final String REG_EMAIL_SUBJECT = "艾菲注册激活";
  public static final String REG_EMAIL_TEXT = "";
  public static final String EMAIL_FROM = "support@effie-greaterchina.org";


  // 邮件类型
  public static final int REG_EMIAL = 0;
  public static final int FORGET_PASSWORD_EMAIL = 1;

}
