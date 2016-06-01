package org.tplatform.core.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * 表单记录
 * Created by Tianyi on 2015/4/13.
 */
@Data
@Table(name = "sys_df_record")
public class DFElementRecord extends DFElement {

  private Long eleId;       // 元素ID
  private String recordId;  // 记录ID
  private String eleValue;  // 取值
}
