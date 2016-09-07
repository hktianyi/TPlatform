package org.tplatform.auth.entity;

import lombok.Data;
import org.tplatform.auth.fsm.SysResourceType;
import org.tplatform.core.entity.BaseEntity;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.Set;

/**
 * 资源信息（包括菜单、按钮）
 * Created by Tianyi on 2015/3/13.
 */
@Data
@Table(name = "sys_auth_resource")
public class SysResource extends BaseEntity {

  private Long pid;// 父编码
  private String name;// 资源名
  private String action;// 资源路径
  private SysResourceType type;// 资源类型 1：菜单；   2：按钮
  private String icon;// 资源icon
  private Integer sort;// 排序

  @Transient
  private Integer leaf;// 叶子节点

  @Transient
  private List<SysResource> children;

  @Transient
  private Set<SysRole> roles;
}
