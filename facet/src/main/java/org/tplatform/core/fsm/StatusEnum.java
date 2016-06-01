package org.tplatform.core.fsm;

/**
 * 通用状态机
 * Created by Tianyi on 2016/1/9.
 */
public enum StatusEnum {
  VALID(1),   // 有效
  INVALID(2), // 无效
  DELETE(3);  // 删除

  private int code; //状态码

  StatusEnum(int code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return String.valueOf(this.code);
  }
}
