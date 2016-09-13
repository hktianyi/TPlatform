package org.tplatform.framework.util;

import java.util.ResourceBundle;

/**
 * 属性文件解析、参数获取工具类
 */
public class PropertyUtil {

//  private static final String environment = ResourceBundle.getBundle("filter").getString("environment");
//  private static final Locale locale = StringUtil.isEmpty(environment) ? Locale.getDefault() : new Locale(environment);

  public static String getProInfo(String fileName, String proName) {
    try {
      if (StringUtil.isNotEmpty(fileName) || StringUtil.isNotEmpty(proName)) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName);
        return new String(resourceBundle.getString(proName).getBytes("ISO8859-1"), "UTF-8");
      } else {
        return null;
      }
    } catch (Exception e) {
      return null;
    }
  }

  public static ResourceBundle getBundle(String fileName) {
    return ResourceBundle.getBundle(fileName);
  }
}
