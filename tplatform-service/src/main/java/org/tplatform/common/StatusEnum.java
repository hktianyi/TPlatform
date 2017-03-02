package org.tplatform.common;

import lombok.Getter;

/**
 * 通用状态机
 * Created by Tianyi on 2016/1/9.
 */
public enum StatusEnum {
  INIT("1"),   // 数据新增
  COMMIT("2"),   // 确认提交
  VALID("3"),   // 有效
  INVALID("4"), // 无效
  DELETE("5");  // 删除

  @Getter
  private String code; //状态码

  StatusEnum(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return String.valueOf(this.code);
  }
}
