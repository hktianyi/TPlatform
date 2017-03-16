package org.tplatform.plugin.form;

import lombok.Data;
import org.tplatform.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 动态表单元素
 * Created by Tianyi on 2015/4/13.
 */
@Data
@Entity
@Table(name = "SYS_DF_ELEMENT")
//@MappedSuperclass
public class DFElement extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 20)
  private Long id;
  @Column(length = 20, nullable = false)
  private Long formId;

  private String eleType;//元素类型
  private String eleAttr;//属性
  private String eleName;//元素名
  private String eleClass;
  private String zhName;//中文名
  private String remark;//备注
  private Integer sort; //排序
}
