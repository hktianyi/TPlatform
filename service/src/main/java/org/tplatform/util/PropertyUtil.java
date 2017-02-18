package org.tplatform.util;

import org.springframework.context.annotation.DependsOn;
import org.tplatform.domain.ConfigService;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 属性文件解析、参数获取工具类
 */
@DependsOn("springContextUtil")
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

  /**
   * @param confKey : 数据库配置KEY
   * @return
   * 获取指定属性文件中对应的属性值
   */
  public static String getProInfo(String confKey) {
    if (StringUtil.isNotEmpty(confKey)) {
      return SpringContextUtil.getBean(ConfigService.class).getByKey(confKey);
    } else {
      return null;
    }
  }

  /**
   * 获取指定属性文件数据集,用于一次操作需要取多个属性
   * *
   */
  public static Hashtable<String, String> getProInfoMap(String fileName) {
    Hashtable<String, String> map = null;
    if (fileName == null || "".equals(fileName.trim())) {
      return null;
    } else {
      try {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName, Locale.getDefault());
        Enumeration<String> enumeration = resourceBundle.getKeys();
        String key = null;
        map = new Hashtable<>();
        while (enumeration.hasMoreElements()) {
          key = enumeration.nextElement();
          String value = resourceBundle.getString(key);
          map.put(key, value == null ? "" : value);
        }
      } catch (Exception e) {
        return null;
      }
    }
    return map;
  }

  public static ResourceBundle getBundle(String fileName) {
    return ResourceBundle.getBundle(fileName);
  }
}
