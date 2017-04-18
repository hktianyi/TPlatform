package org.tplatform.plugin.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.tplatform.common.BaseRepo;
import org.tplatform.common.BaseRepoImpl;
import org.tplatform.common.GlobalConstant;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态表单业务类
 * Created by tianyi on 2017/2/22.
 */
public interface DFService extends BaseRepo<DFElementRecord> {

  /**
   * 根据表单ID和记录ID查找动态表单对象
   *
   * @param enName   表单ID
   * @param recordId 记录ID
   * @return DynamicForm
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_DF_findByEnNameAndRecordId_' + #enName + '_' + #recordId")
  DynamicForm findByEnNameAndRecordId(String enName, String recordId);

  /**
   * 根据记录ID查找动态表单记录
   *
   * @param recordId 记录ID
   * @return List
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_DF_findByRecordId_' + #recordId")
  List<DFElementRecord> findByRecordId(String recordId);

  /**
   * 根据记录ID查找动态表单记录
   *
   * @param formId 表单ID
   * @return List
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_DF_findByRecordId_' + #formId")
  Map<String, Long> findEleIdByFormId(Long formId);

  /**
   * 保存表单记录
   *
   * @param dfElementRecordList 表单元素集合
   */
//  @CacheEvict(value = GlobalConstant.KEY_CACHE_SYS, key = "'_DF_'")
  @Transactional
  void saveRecords(List<DFElementRecord> dfElementRecordList);
}


class DFServiceImpl extends BaseRepoImpl<DFElementRecord> {

  @Autowired
  private DynamicFormService dynamicFormService;
  @Autowired
  private DFElementRecordService dfElementRecordService;

  public DynamicForm findByEnNameAndRecordId(String enName, String recordId) {
    DynamicForm dynamicForm = dynamicFormService.findByEnName(enName);
    if (dynamicForm != null) {
      dynamicForm.setRecords(dfElementRecordService.findByRecordId(recordId));
    }
    return dynamicForm;
  }

  public List<DFElementRecord> findByRecordId(String recordId) {
    return dfElementRecordService.findByRecordId(recordId);
  }

  public Map<String, Long> findEleIdByFormId(Long formId) {
    DynamicForm dynamicForm = dynamicFormService.findOne(formId);
    List<DFElement> list = dynamicForm.getElements();
    if (list != null && list.size() > 0) {
      Map eleNameMap = new HashMap<>(list.size());
      list.parallelStream().forEach(element -> eleNameMap.put(element.getEleName(), element.getId()));
      return eleNameMap;
    }
    return null;
  }

  public void saveRecords(List<DFElementRecord> dfElementRecordList) {
    if (dfElementRecordList == null || dfElementRecordList.size() == 0)
      throw new IllegalArgumentException("表单元素为空，拒绝保存");

    String recordId = dfElementRecordList.get(0).getRecordId();
    dfElementRecordService.deleteByRecordId(recordId);
    dfElementRecordService.save(dfElementRecordList);
  }
}
