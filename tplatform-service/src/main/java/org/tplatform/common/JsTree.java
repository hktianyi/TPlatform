package org.tplatform.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jsTree实体
 * Created by Tianyi on 2015/1/6.
 */
@Data
public class JsTree {

  private Serializable id;
  private String text;
  private String icon;
  private List<Object> children;
  private Map<String, Object> state;// 是否已加载

  public void setState(String key, Object value) {
    if(this.state == null)
      this.state = new HashMap<>();
    this.state.put(key, value);
  }
}
