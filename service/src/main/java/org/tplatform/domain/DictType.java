package org.tplatform.domain;

import lombok.Data;
import org.tplatform.common.BaseEntity;
import org.tplatform.core.fsm.DicType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Tianyi on 2015/12/13.
 */
@Data
@Entity
@Table(name = "SYS_DICT_TYPE")
public class DictType extends BaseEntity {

  /**
   * 顶级节点PID值
   */
  public static final String AREA_CODE = "area_code";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 10)
  protected Long id;

  @Column(length = 10)
  private DicType type;//类型
  @Column(length = 32)
  private String enName;//英文名称
  @Column(length = 32)
  private String zhName;//中文名称
  @Column(length = 32)
  private String menuType;//菜单
  @Column(columnDefinition = "datetime", updatable = false)
  private Date createTime;//创建时间
}
