package org.tplatform.core.service;

import org.tplatform.core.entity.Dictionary;

import java.util.List;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface IDictionaryService extends IBaseService<Dictionary> {

  List<Dictionary> findByDicTypeEnName(String dicTypeEnName);

  List<Dictionary> findByDicTypeMenuType(String menuType);
}
