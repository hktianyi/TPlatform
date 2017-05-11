package org.tplatform.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.tplatform.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Tianyi on 2015/12/13.
 */
@Data
@Entity
@Table(name = "sys_dict")
@DynamicUpdate
public class Dict extends BaseEntity {

  /**
   * 顶级节点PID值
   */
  public static final long KING_PID = 0;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 10)
  protected Long id;

  @Column(length = 10)
  private Long pid;//父ID
  @Column(length = 32, nullable = false)
  private String dicType;//类型
  @Column(length = 32)
  private String zhName;//中文名称
  @Column(length = 32)
  private String value;//取值
  @Column(length = 4)
  private Integer sort;//排序索引
  @Column(length = 2)
  private Integer status;//状态 0 禁用，1 启用（行政区划：1省，2 市，3 县，4 直辖市，5 虚拟[市辖区、县]）
}
