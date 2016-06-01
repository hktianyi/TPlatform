package org.tplatform.core.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 动态表单
 * Created by Tianyi on 2015/4/13.
 */
@Data
@Table(name = "sys_df")
public class DynamicForm extends BaseEntity {

  @Column(length = 10, nullable = false, unique = true)
  private String formId;//表单ID
  private String zhName;//表单名

  @Transient
  private List<DFElementRecord> elements; // 元素集合

}
