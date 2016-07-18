package org.tplatform.core.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 系统配置实体
 * Created by Tianyi on 2015/1/6.
 */
@Data
@Table(name = "sys_conf")
public class SysConf extends BaseEntity {

  @Column(length = 20)
  private Long pId;
  @Column(length = 10)
  private String type;
  @Column(name = "confKey", length = 32, unique = true)
  private String confKey;
  @Column(length = 20)
  private String name;
  @Column(length = 100)
  private String val;
  @Column(length = 100)
  private String remark;
  @Column(scale = 1)
  private Integer sort;
  @Column(scale = 1)
  private Integer level;
}
