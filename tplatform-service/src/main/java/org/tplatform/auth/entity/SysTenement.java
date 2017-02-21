package org.tplatform.auth.entity;

import lombok.Data;
import org.tplatform.common.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * 系统租户
 * Created by Tianyi on 2017/02/09.
 */
@Data
@Entity
@Table(name = "SYS_TENEMENT")
public class SysTenement extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 10)
  protected Long id;

  private String tenement;// 登录名，唯一，不可修改
  private String nickname;// 昵称
  private String domain;// 域
  private String mobile;  // 手机号
  private String email;   // 邮箱
  private String logoUrl;  // Logo

  @ManyToMany(targetEntity = SysRole.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinTable(name = "SYS_TENEMENT_USER", joinColumns = @JoinColumn(name = "tenement_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<SysUser> users;

}
