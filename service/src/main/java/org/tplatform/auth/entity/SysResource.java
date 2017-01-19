package org.tplatform.auth.entity;

import lombok.Data;
import org.tplatform.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.Set;

/**
 * 资源信息（包括菜单、按钮）
 * Created by Tianyi on 2015/3/13.
 */
@Data
@Entity
@Table(name = "SYS_AUTH_RESOURCE")
public class SysResource extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 10)
  protected Long id;

  @Column(length = 10)
  private Long pid;// 父编码
  @Column(length = 32)
  private String name;// 资源名
  @Column(length = 64)
  private String action;// 资源路径
  @Column(length = 64)
  private String type;// 资源类型 1：菜单；   2：按钮
  @Column(length = 16)
  private String icon;// 资源icon
  @Column(length = 2)
  private Integer sort;// 排序

  @Transient
  private Integer leaf;// 叶子节点

  @Transient
  private List<SysResource> children;

  @Transient
  private Set<SysRole> roles;
}
