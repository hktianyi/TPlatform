package org.tplatform.auth;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.tplatform.common.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * Created by Colin on 2017/4/26.
 */
@Getter
@Setter
@Entity
@Table(name = "sys_auth_user")
@DynamicInsert
@DynamicUpdate
public class SysUser extends BaseEntity {

  @Id
  @GeneratedValue(generator = "assigned")
  @GenericGenerator(name = "assigned", strategy = "assigned")
  @Column(length = 16, updatable = false)
  private String username;// 登录名，唯一，不可修改
  @Column(length = 16)
  private String nickname;// 昵称
  @Column(length = 64, nullable = false)
  private String password;// 登录密码
  @Column(length = 16)
  private String mobile;  // 手机号
  @Column(length = 128)
  private String email;   // 邮箱
  @Column(length = 32)
  private String avatarUrl;  // 头像
  @Column(length = 1, nullable = false)
  private Integer status;  // 状态
  @Column(columnDefinition = "datetime", updatable = false)
  private Date createTime;  // 创建时间

  /**
   * 用户角色
   */
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  @JoinTable(name = "sys_auth_user_role", joinColumns = @JoinColumn(name = "username"),
      inverseJoinColumns = @JoinColumn(name = "role"))
  private Set<SysRole> roles;

  /**
   * 用户机构
   */
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  @JoinTable(name = "sys_auth_user_organ", joinColumns = @JoinColumn(name = "username"),
      inverseJoinColumns = @JoinColumn(name = "code"))
  private Set<SysOrgan> organs;
}
