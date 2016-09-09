package org.tplatform.core.entity;

import lombok.Data;
import org.tplatform.core.fsm.StatusEnum;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 映射实体基础类
 * Created by Tianyi on 2014/12/3.
 */
@Data
public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(length = 20)
  @GeneratedValue(generator = "JDBC")
  protected Long id;
  // 创建时间
  @Column(columnDefinition = "datetime")
  protected Date createTime;
  // 操作员
  protected String operator;
  // 状态
  protected StatusEnum status;
  // 机构层级（数据权限）
  @Transient
  protected String hierarchy;

}
