package org.tplatform.core.service;

import org.tplatform.core.entity.DFElementRecord;
import org.tplatform.core.entity.DynamicForm;

import java.util.List;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface IDynamicFormService extends IBaseService<DynamicForm> {

  /**
   * 根据表单ID查询表单
   * @param formId 表单ID
   * @return
   */
  DynamicForm findByFormId(String formId);


  /**
   * 根据表单ID，查询元素
   * @param formId
   * @return
     */
  List<DFElementRecord> findElementsByFormId(String formId);

  /**
   * 查询表单数据
   * @param formId 表单ID
   * @param recordId 记录ID
   * @return DynamicForm
   */
  DynamicForm findRecords(String formId, String recordId);

  /**
   * 查询表单数据
   * @param formId 表单ID
   * @param recordId 记录ID
   * @return List<DFElementRecord>
   */
  List<DFElementRecord> findRecordList(String formId, String recordId);

  /**
   * 查询表单数据
   * @param dfElementRecordList
   * @return
   */
  Integer saveRecords(List<DFElementRecord> dfElementRecordList);

}
