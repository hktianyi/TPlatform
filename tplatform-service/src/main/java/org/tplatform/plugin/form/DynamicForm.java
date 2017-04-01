package org.tplatform.plugin.form;

import lombok.Data;
import org.tplatform.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 动态表单
 * Created by Tianyi on 2015/4/13.
 */
@Data
@Entity
@Table(name = "SYS_DF")
public class DynamicForm extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 20)
  protected Long id;

  @Column(length = 10, nullable = false, unique = true)
  private String enName;//英文名
  private String zhName;//表单名

  @OneToMany(mappedBy = "formId", fetch = FetchType.EAGER)
  @OrderBy("sort")
  private List<DFElement> elements; // 元素集合

  @Transient
  private List<DFElementRecord> records; // 数据

}
