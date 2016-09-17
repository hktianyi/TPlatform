package org.tplatform.core.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tplatform.constant.GlobalConstant;
import org.tplatform.core.entity.Dictionary;
import org.tplatform.core.mapper.DictionaryMapper;
import org.tplatform.core.service.IDictionaryService;
import org.tplatform.impl.BaseService;

import java.util.List;

/**
 * Created by Tianyi on 2015/1/6.
 */
@Service
public class DictionaryService extends BaseService<Dictionary> implements IDictionaryService {
  @Override
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dictionary_findByDicTypeEnName_' + #dicTypeEnName")
  public List<Dictionary> findByDicTypeEnName(String dicTypeEnName) {
    return ((DictionaryMapper) super.mapper).findByDicTypeEnName(dicTypeEnName);
  }
  @Override
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dictionary_findByDicTypeMenuType_' + #menuType")
  public List<Dictionary> findByDicTypeMenuType(String menuType) {
    return ((DictionaryMapper) super.mapper).findByDicTypeMenuType(menuType);
  }
}
