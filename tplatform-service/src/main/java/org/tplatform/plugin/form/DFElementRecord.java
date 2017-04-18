package org.tplatform.plugin.form;

import lombok.Data;
import org.tplatform.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 表单记录
 * Created by Tianyi on 2015/4/13.
 */
@Data
@Entity
@Table(name = "SYS_DF_RECORD")
public class DFElementRecord extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 20)
  protected Long id;

  @Column(length = 20, nullable = false)
  private Long eleId;       // 元素ID
  private String recordId;  // 记录ID
  private String eleValue;  // 取值
  private Long timestamp;  // 时间戳

  @OneToOne
  @JoinColumn(name = "eleId", insertable = false, updatable = false)
  private DFElement element;
}
