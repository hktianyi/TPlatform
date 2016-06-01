package org.tplatform.auth.fsm;

/**
 * 资源类型
 * Created by Tianyi on 2016/3/16.
 */
public enum SysResourceType {
  MENU("菜单"),
  BUTTON("按钮");

  private String name;

  SysResourceType(String name) {
    this.name = name;
  }
}
