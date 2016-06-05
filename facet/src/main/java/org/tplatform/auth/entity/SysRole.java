package org.tplatform.auth.entity;

import lombok.Data;
import org.tplatform.core.entity.BaseEntity;

import javax.persistence.Table;

/**
 * 角色表
 * Created by Tianyi on 2015/3/13.
 */
@Data
@Table(name = "sys_auth_role")
public class SysRole extends BaseEntity {
  private Long pid;
  private String name;
  private String icon;
}
