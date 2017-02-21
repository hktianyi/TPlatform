package org.tplatform.core.repo;//package org.tplatform.core.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//import org.tplatform.common.GlobalConstant;
//import org.tplatform.core.entity.DFElementRecord;
//import org.tplatform.core.entity.DynamicForm;
//import org.tplatform.core.repo.DynamicFormMapper;
//
//import java.util.List;
//
///**
// * Created by Tianyi on 2015/1/6.
// */
//@Service
//public class DynamicFormService extends BaseService<DynamicForm> implements IDynamicFormService {
//
//  @Autowired
//  private DynamicFormMapper dynamicFormMapper;
//
//  @Override
//  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_DF_FORMID_' + #formId")
//  public DynamicForm findByFormId(String formId) {
//    DynamicForm dynamicForm = dynamicFormMapper.findByFormId(formId);
//    if (dynamicForm != null) dynamicForm.setElements(dynamicFormMapper.findElementsByFormId(formId));
//    return dynamicForm;
//  }
//
//  @Override
//  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_DF_FORMID_' + #formId")
//  public List<DFElementRecord> findElementsByFormId(String formId) {
//    return dynamicFormMapper.findElementsByFormId(formId);
//  }
//
//  @Override
//  public DynamicForm findRecords(String formId, String recordId) {
//    DynamicForm dynamicForm = dynamicFormMapper.findByFormId(formId);
//    if (dynamicForm != null) dynamicForm.setElements(dynamicFormMapper.findRecords(formId, recordId));
//    return dynamicForm;
//  }
//
//  @Override
//  public List<DFElementRecord> findRecordList(String formId, String recordId) {
//    return dynamicFormMapper.findRecordList(formId, recordId);
//  }
//
//  @Override
//  public Integer saveRecords(List<DFElementRecord> dfElementRecordList) {
//    if (dfElementRecordList == null || dfElementRecordList.size() == 0) return 0;
//    dynamicFormMapper.insertRecords(dfElementRecordList);
//    return 1;
//  }
//
//}
