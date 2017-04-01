package org.tplatform.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.tplatform.common.BaseRepo;
import org.tplatform.common.BaseRepoImpl;
import org.tplatform.common.GlobalConstant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典业务类
 * Created by tianyi on 2016/11/20.
 */
public interface DictService extends BaseRepo<Dict> {

  /**
   * 查询字典类型
   * @return
   */
  @Query(value = "select dicType from SYS_DICT group by dicType", nativeQuery = true)
  List<String> groupByDicType();

  /**
   * 根据字典类型查找字典
   *
   * @param dicType 字典类型
   * @return List
   */
//  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_Type_' + #dicType")
  List<Dict> findByDicType(String dicType);

  /**
   * 根据字典类型、父ID查找字典
   *
   * @param dicType 字典类型
   * @param pid     父级ID
   * @return List
   */
  List<Dict> findByDicTypeAndPid(String dicType, Long pid);

  List<Dict> findByDicTypeAndPidOrderBySort(String dicType, Long pid);

  /**
   * 根据字典类型生成字典Map
   * @param dicType
   * @return
   */
  Map<String, String> findForDictMap(String dicType);
  Map<String, String> findForDictMap(String dicType, boolean reverse);

  /**
   * 根据字典类型、状态查找字典
   *
   * @param dicType 字典类型
   * @param status  状态
   * @return List
   */
  List<Dict> findByDicTypeAndStatus(String dicType, Integer status);

  List<Dict> findByDicTypeAndStatusOrderBySort(String dicType, Integer status);

  /**
   * 根据字典类型、父ID、状态查找字典
   *
   * @param dicType 字典类型
   * @param pid     父级ID
   * @param status  状态
   * @return List
   */
  List<Dict> findByDicTypeAndPidAndStatus(String dicType, Long pid, Integer status);

  List<Dict> findByDicTypeAndPidAndStatusOrderBySort(String dicType, Long pid, Integer status);

  //  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_Name_' + #id")
  @Query(value = "select zhName from sys_dict where id = ?1", nativeQuery = true)
  String getNameById(Long id);


  /* ***************************** 查询省市信息开始 ******************************* */

  /**
   * 查询省份
   *
   * @return List
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_ProvinceList'")
  List<Dict> findProvinceList();

  /**
   * 查询直辖市
   *
   * @return List
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_MunicipalityList'")
  List<Dict> findMunicipalityList();

  /**
   * 根据省份查询城市
   *
   * @param pid 父级ID
   * @return List
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_CityList_' + #pid")
  List<Dict> findCityList(Long pid);

  /**
   * 根据城市查询区县
   *
   * @param pid 父级ID
   * @return List
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_Dict_CountyList_' + #pid")
  List<Dict> findCountyList(Long pid);
  /* ***************************** 查询省市信息结束 ******************************* */
}


class DictServiceImpl extends BaseRepoImpl<Dict> {

  @Autowired
  private DictService dictService;


  /**
   * 根据字典类型生成字典Map
   * @param dicType
   * @return
   */
  Map<String, String> findForDictMap(String dicType) {
    return findForDictMap(dicType, false);
  }
  Map<String, String> findForDictMap(String dicType, boolean reverse) {
    Map<String, String> dictMap = new HashMap<>();
    List<Dict> list = dictService.findByDicType(dicType);
    if (reverse) {
      list.parallelStream().forEach(dict -> dictMap.put(dict.getZhName(), dict.getValue()));
    } else {
      list.parallelStream().forEach(dict -> dictMap.put(dict.getValue(), dict.getZhName()));
    }
    return dictMap;
  }

  /* ***************************** 查询省市信息开始 ******************************* */
  public List<Dict> findProvinceList() {
    return dictService.findByDicTypeAndPidOrderBySort(AreaType.行政区划.getCode(), Dict.KING_PID);
  }

  public List<Dict> findMunicipalityList() {
    return dictService.findByDicTypeAndPidAndStatusOrderBySort(AreaType.行政区划.getCode(), Dict.KING_PID, AreaType.直辖市.getStatus());
  }

  public List<Dict> findCityList(Long pid) {
    List<Dict> list;
    if (pid == null || pid <= 0) {
      list = dictService.findByDicTypeAndStatusOrderBySort(AreaType.行政区划.getCode(), AreaType.市.getStatus());
      list.addAll(dictService.findByDicTypeAndStatusOrderBySort(AreaType.行政区划.getCode(), AreaType.直辖市.getStatus()));
    } else {
      list = dictService.findByDicTypeAndPid(AreaType.行政区划.getCode(), pid);
    }
    return list;
  }

  public List<Dict> findCountyList(Long pid) {
    return dictService.findByDicTypeAndPidOrderBySort(AreaType.行政区划.getCode(), pid);
  }
  /* ***************************** 查询省市信息结束 ******************************* */
}