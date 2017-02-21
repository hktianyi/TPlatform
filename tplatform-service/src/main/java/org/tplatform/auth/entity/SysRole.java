package org.tplatform.auth.entity;

import lombok.Data;
import org.tplatform.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色表
 * Created by Tianyi on 2015/3/13.
 */
@Data
@Entity
@Table(name = "SYS_AUTH_ROLE")
public class SysRole extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 10)
  protected Long id;

  @Column(length = 10)
  private Long pid;
  @Column(length = 32)
  private String name;
  @Column(length = 16)
  private String icon;
}
