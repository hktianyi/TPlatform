package org.tplatform.domain;

import lombok.Data;
import org.tplatform.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统配置实体
 * Created by Tianyi on 2015/1/6.
 */
@Data
@Entity
@Table(name = "SYS_CONF")
public class Config extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 20)
  protected Long id;

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
