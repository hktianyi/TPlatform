package org.tplatform.core.service.impl;

import org.tplatform.impl.BaseService;
import org.tplatform.core.entity.DictionaryType;
import org.tplatform.core.mapper.DictionaryTypeMapper;
import org.tplatform.core.service.IDictionaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tianyi on 2015/1/6.
 */
@Service
public class DictionaryTypeService extends BaseService<DictionaryType> implements IDictionaryTypeService {

  @Autowired
  private DictionaryTypeMapper dictionaryTypeMapper;

  @Override
  public List<DictionaryType> findByMenuType(String menuType) {
    return dictionaryTypeMapper.findByMenuType(menuType);
  }
}
