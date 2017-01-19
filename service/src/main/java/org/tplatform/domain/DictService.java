package org.tplatform.domain;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.tplatform.common.BaseRepo;
import org.tplatform.common.GlobalConstant;

import java.util.List;

/**
 * Created by tianyi on 2016/11/20.
 */
public interface DictService extends BaseRepo<Dict> {

  /**
   * 根据字典类型查找字典
   * @param dicType
   * @return
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_Type_' + #dicType")
  List<Dict> findByDicType(String dicType);

  /**
   * 根据字典类型、父ID查找字典
   * @param dicType
   * @param pid
   * @return
   */
  List<Dict> findByDicTypeAndPid(String dicType, Long pid);
  List<Dict> findByDicTypeAndPidOrderBySort(String dicType, Long pid);

  /**
   * 根据字典类型、状态查找字典
   * @param dicType
   * @param status
   * @return
   */
  List<Dict> findByDicTypeAndStatus(String dicType, Integer status);
  List<Dict> findByDicTypeAndStatusOrderBySort(String dicType, Integer status);

  /**
   * 根据字典类型、父ID、状态查找字典
   * @param dicType
   * @param status
   * @return
   */
  List<Dict> findByDicTypeAndPidAndStatus(String dicType, Long pid, Integer status);
  List<Dict> findByDicTypeAndPidAndStatusOrderBySort(String dicType, Long pid, Integer status);

//  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_Name_' + #id")
  @Query(value = "select zhName from sys_dict where id = ?1", nativeQuery = true)
  String getNameById(Long id);


  /* ***************************** 查询省市信息开始 ******************************* */

  /**
   * 查询省份
   * @return
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_ProvinceList'")
  List<Dict> findProvinceList();

  /**
   * 查询直辖市
   * @return
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_MunicipalityList'")
  List<Dict> findMunicipalityList();

  /**
   * 根据省份查询城市
   * @param pid
   * @return
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_CityList_' + #pid")
  List<Dict> findCityList(Long pid);

  /**
   * 根据城市查询区县
   * @param pid
   * @return
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_CountyList_' + #pid")
  List<Dict> findCountyList(Long pid);
  /* ***************************** 查询省市信息结束 ******************************* */
}
