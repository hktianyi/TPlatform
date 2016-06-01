package org.tplatform.auth.entity;

import lombok.Data;
import org.tplatform.auth.fsm.SysResourceType;
import org.tplatform.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 资源信息（包括菜单、按钮）
 * Created by Tianyi on 2015/3/13.
 */
@Data
@Table(name = "sys_auth_resource")
public class SysResource extends BaseEntity {

  @Column(length = 16)
  private Long pid;// 父编码
  @Column(length = 16)
  private String name;// 资源名
  @Column(length = 255)
  private String action;// 资源路径
  @Column(length = 4)
  private SysResourceType type;// 资源类型 1：菜单；   2：按钮
  @Column(length = 16)
  private String icon;// 资源icon
  @Column(length = 2)
  private Integer sort;// 排序

  @Transient
  private List<SysResource> children;
}
