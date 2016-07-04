package org.tplatform.core.mapper;

import org.tplatform.core.entity.Dictionary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by tianyi on 16/1/13.
 */
public interface DictionaryMapper extends Mapper<Dictionary> {

  @Select("SELECT t1.* FROM sys_dictionary t1, sys_dictionary_type t2 WHERE t1.dicTypeId = t2.id AND t2.enName = #{dicTypeEnName} order by sort")
  List<Dictionary> findByDicTypeEnName(@Param("dicTypeEnName") String dicTypeEnName);

  @Select("SELECT t1.* FROM sys_dictionary t1 LEFT JOIN sys_dictionary_type t2 ON t1.dicTypeId = t2.id WHERE t2.menuType = #{menuType} order by sort")
  List<Dictionary> findByDicTypeMenuType(@Param("menuType") String menuType);

  @Select("select zhName from sys_dictionary where dicTypeId = #{codeType} and value = #{codeValue}")
  String findDicNameByCode(@Param("codeType") String codeType,@Param("codeValue")String codeValue);
}
