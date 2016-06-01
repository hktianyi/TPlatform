package org.tplatform.core.service.impl;

import org.tplatform.framework.util.DateUtil;
import org.tplatform.impl.BaseService;
import org.tplatform.core.entity.DFElementRecord;
import org.tplatform.core.entity.DynamicForm;
import org.tplatform.core.mapper.DynamicFormMapper;
import org.tplatform.core.service.IDynamicFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Tianyi on 2015/1/6.
 */
@Service
public class DynamicFormService extends BaseService<DynamicForm> implements IDynamicFormService {

  @Autowired
  private DynamicFormMapper dynamicFormMapper;

  @Override
//  @Cacheable(value = "_SYS", key = "'_DF_FORMID_' + #formId")
  public DynamicForm findByFormId(String formId) {
    DynamicForm dynamicForm = dynamicFormMapper.findByFormId(formId);
    if (dynamicForm != null) dynamicForm.setElements(dynamicFormMapper.findElementsByFormId(formId));
    return dynamicForm;
  }

  @Override
//  @Cacheable(value = "_SYS", key = "'_DF_FORMID_' + #formId")
  public List<DFElementRecord> findElementsByFormId(String formId) {
    return dynamicFormMapper.findElementsByFormId(formId);
  }

  @Override
  public DynamicForm findRecords(String formId, String recordId) {
    DynamicForm dynamicForm = dynamicFormMapper.findByFormId(formId);
    if (dynamicForm != null) dynamicForm.setElements(dynamicFormMapper.findRecords(formId, recordId));
    return dynamicForm;
  }

  @Override
  public List<DFElementRecord> findRecordList(String formId, String recordId) {
    return dynamicFormMapper.findRecordList(formId, recordId);
  }

  @Override
  public Integer saveRecords(List<DFElementRecord> dfElementRecordList) {
    if (dfElementRecordList == null || dfElementRecordList.size() == 0) return 0;
    dfElementRecordList.stream().forEach(dfElementRecord -> {
      if (dfElementRecord.getId() == null || dfElementRecord.getId() == 0) {
        if (StringUtils.hasText(dfElementRecord.getEleName()) && StringUtils.hasText(dfElementRecord.getRecordId())) {
          dfElementRecord.setTimestamp(DateUtil.getTimeInMillis());
          dynamicFormMapper.saveRecord(dfElementRecord);
        }
      } else
        dynamicFormMapper.updateRecord(dfElementRecord);
    });
    return 1;
  }

  /**
   * 删除表单数据
   *
   * @param formId
   * @param recordId
   */
  public void deleteRecord(String formId, String recordId) {
    dynamicFormMapper.deleteRecord(formId, recordId);
  }

}
