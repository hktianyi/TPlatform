package org.tplatform.core.mapper;

import org.tplatform.core.entity.DictionaryType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by tianyi on 16/1/13.
 */
public interface DictionaryTypeMapper extends Mapper<DictionaryType> {

  @Select("select * from SYS_DICTIONARY_TYPE where menuType = #{menuType}")
  List<DictionaryType> findByMenuType(@Param("menuType") String menuType);
}
