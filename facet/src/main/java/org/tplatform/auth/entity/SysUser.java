package org.tplatform.auth.entity;

import lombok.Data;
import org.tplatform.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

/**
 * 系统管理员
 * Created by Tianyi on 2014/11/21.
 */
@Data
@Table(name = "sys_auth_user")
public class SysUser extends BaseEntity {

  @Column(length = 32)
  private String username;// 登录名，唯一，不可修改
  @Column(length = 32)
  private String password;// 登录密码
  @Column(length = 16)
  private String mobile;  // 手机号
  @Column(length = 32)
  private String email;   // 邮箱
  @Column(length = 32)
  private String salt;    // 加密盐值
  @Column(length = 32)
  private String nickname;// 昵称
  @Column(length = 32)
  private String avatarUrl;  // 头像

  @Transient
  private Set<SysRole> roles;

}
