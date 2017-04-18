package org.tplatform.common;

/**
 * 字典类型
 * Created by Tianyi on 2016/3/16.
 */
public enum DicType {
  SELECT("下拉框"),
  CHECKBOX("复选框"),
  RADIO("单选框");

  private String name;

  DicType(String name) {
    this.name = name;
  }
}
