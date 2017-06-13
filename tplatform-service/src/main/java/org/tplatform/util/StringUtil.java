package org.tplatform.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * Created by Tianyi on 2014/11/20.
 */
public class StringUtil extends StringUtils {
  //工具类禁止实例化
  private StringUtil() {
  }

  /**
   * 判断字符是否非空
   *
   * @param str
   * @return
   */
  public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }

  /**
   * 判断字符是否为空
   *
   * @param str
   * @return
   */
  public static boolean isEmpty(String str) {
    return null == str || str.length() == 0 || "null".equals(str.toLowerCase());
  }

  /**
   * 字符串转换unicode
   */
  public static String string2Unicode(String str) {

    if (isEmpty(str)) return null;

    StringBuffer unicode = new StringBuffer();
    char[] chars = str.toCharArray();
    for (char c : chars)
      unicode.append("\\u" + Integer.toHexString(c));

    return unicode.toString();
  }

  /**
   * unicode 转字符串
   */
  public static String unicode2String(String unicode) {

    if (isEmpty(unicode)) return null;

    StringBuffer sbf = new StringBuffer();
    String[] hex = unicode.split("\\\\u");
    for (int i = 1; i < hex.length; i++)
      sbf.append((char) Integer.parseInt(hex[i], 16));

    return sbf.toString();
  }

  /**
   * 获取请求IP地址
   *
   * @param request 请求
   * @return IP地址
   */
  public static String getIp(HttpServletRequest request) {
    String ip = null;
    try {
      ip = request.getHeader("X-Real-IP");
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("x-forwarded-for");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-Ip");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-Ip");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
      }
      if ("0:0:0:0:0:0:0:1".equals(ip)) {
        ip = "127.0.0.1";
      }
    } catch (Exception ex) {
      ip = "-.-.-.-";
    }
    return ip;
  }

  /**
   * 缩略字符串（不区分中英文字符）
   * @param str 目标字符串
   * @param length 截取长度
   * @return
   */
  public static String abbr(String str, int length) {
    if (str == null) {
      return "";
    }
    try {
      StringBuilder sb = new StringBuilder();
      int currentLength = 0;
      for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
        currentLength += String.valueOf(c).getBytes("GBK").length;
        if (currentLength <= length - 3) {
          sb.append(c);
        } else {
          sb.append("...");
          break;
        }
      }
      return sb.toString();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * 替换掉HTML标签方法
   */
  public static String replaceHtml(String html) {
    if (isBlank(html)){
      return "";
    }
    String regEx = "<.+?>";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(html);
    String s = m.replaceAll("");
    return s;
  }

  /**
   * 获得用户远程地址
   */
  public static String getRemoteAddr(HttpServletRequest request){
    String remoteAddr = request.getHeader("X-Real-IP");
    if (isNotBlank(remoteAddr)) {
      remoteAddr = request.getHeader("X-Forwarded-For");
    }else if (isNotBlank(remoteAddr)) {
      remoteAddr = request.getHeader("Proxy-Client-IP");
    }else if (isNotBlank(remoteAddr)) {
      remoteAddr = request.getHeader("WL-Proxy-Client-IP");
    }
    return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
  }

  public static void main(String[] args) {
    int i = 100000;
    Long start = DateUtil.getTimeInMillis();
    while (i-- > 0) {
      unicode2String("\\u2460");
    }
    Long end = DateUtil.getTimeInMillis();
    System.out.println("================================>" + (end - start));
  }

}
