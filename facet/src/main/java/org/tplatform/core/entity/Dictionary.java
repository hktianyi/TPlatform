package org.tplatform.core.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * Created by Tianyi on 2015/12/13.
 */
@Data
@Table(name = "sys_dictionary")
public class Dictionary extends BaseEntity {

  private Long dicTypeId;//类型ID
  private String zhName;//中文名称
  private String value;//取值
  private Integer sort;//排序索引
}
