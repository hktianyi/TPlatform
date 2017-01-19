package org.tplatform.domain;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by tianyi on 2016/12/21.
 */
public class DictServiceImpl {

  @Autowired
  private DictService dictService;

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
