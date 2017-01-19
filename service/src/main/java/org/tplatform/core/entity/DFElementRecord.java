package org.tplatform.core.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表单记录
 * Created by Tianyi on 2015/4/13.
 */
@Data
@Entity
@Table(name = "SYS_DF_RECORD")
public class DFElementRecord extends DFElement {

  @Id
  @GeneratedValue
  @Column(length = 20)
  protected Long id;

  private Long eleId;       // 元素ID
  private String recordId;  // 记录ID
  private String eleValue;  // 取值
}
