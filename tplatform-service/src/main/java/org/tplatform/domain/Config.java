package org.tplatform.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
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
@Setter
@Getter
@Entity
@Table(name = "SYS_CONFIG")
@DynamicUpdate
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
  @Column(length = 2, scale = 1)
  private Integer sort;

//  @JsonIgnore
//  @ManyToOne(optional = false)
//  private SysOrgan organ;
}
