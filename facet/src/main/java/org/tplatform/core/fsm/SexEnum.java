package org.tplatform.core.fsm;

/**
 * 性别
 * Created by Tianyi on 2016/1/9.
 */
public enum SexEnum {
  BOY('男'), // 无效
  GIRL('女');   // 有效

  private char name; //状态码

  SexEnum(char name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.valueOf(this.name);
  }
}
