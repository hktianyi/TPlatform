package org.tplatform.core.entity;

import lombok.Data;
import org.tplatform.core.fsm.DicType;

import javax.persistence.Table;

/**
 * Created by Tianyi on 2015/12/13.
 */
@Data
@Table(name = "sys_dictionary_type")
public class DictionaryType extends BaseEntity {
  private DicType type;//类型
  private String enName;//英文名称
  private String zhName;//中文名称
  private String menuType;//菜单
}
