package org.tplatform.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Json字符串处理工具
 * Created by Tianyi on 2014/11/20.
 */
public class JsonUtil {
  private final static ObjectMapper objectMapper = new ObjectMapper();
//  private JsonGenerator jsonGenerator = null;

  //工具类禁止实例化
  private JsonUtil() {
  }

  /**
   * json转Map
   *
   * @param json json字符串
   * @return map
   */
  public static Map<String, Object> json2Map(String json) {
    return fromJson(json, Map.class);
  }

  /**
   * json转list
   * @param json json字符串
   * @return list
   */
  public static List json2List(String json) {
    return fromJson(json, List.class);
  }

  /**
   * 把实体Bean、Map对象、数组、列表集合转换成Json格式的字符串
   * @param json json字符串
   * @param clazz 目标对象
   * @return clazz对象
   */
  public static <T> T fromJson(String json, Class<T> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 把实体Bean、Map对象、数组、列表集合转换成Json格式的字符串
   * @param obj 对象
   * @return json字符串
   */
  public static String obj2Json(Object obj) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}