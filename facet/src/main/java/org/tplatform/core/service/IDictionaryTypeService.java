package org.tplatform.core.service;

import org.tplatform.core.entity.DictionaryType;

import java.util.List;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface IDictionaryTypeService extends IBaseService<DictionaryType> {

  /**
   * 根据菜单查找字典
   * @param menuType 菜单类型
   * @return
   */
  List<DictionaryType> findByMenuType(String menuType);
}
