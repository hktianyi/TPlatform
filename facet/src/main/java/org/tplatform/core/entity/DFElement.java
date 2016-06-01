package org.tplatform.core.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * 动态表单元素
 * Created by Tianyi on 2015/4/13.
 */
@Data
@Table(name = "sys_df_element")
public class DFElement extends BaseEntity {

  private String formId;//表单ID
  private String eleType;//元素类型
  private String eleAttr;//属性
  private String eleName;//元素名
  private String eleClass;
  private String zhName;//中文名
  private String remark;//备注
  private Integer sort; //排序
}
