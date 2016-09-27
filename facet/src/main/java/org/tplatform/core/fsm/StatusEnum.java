package org.tplatform.core.fsm;

/**
 * 通用状态机
 * Created by Tianyi on 2016/1/9.
 */
public enum StatusEnum {
  INIT(1),   // 数据新增
  COMMIT(2),   // 确认提交
  VALID(3),   // 有效
  INVALID(4), // 无效
  DELETE(5);  // 删除

  private int code; //状态码

  StatusEnum(int code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return String.valueOf(this.code);
  }
}
