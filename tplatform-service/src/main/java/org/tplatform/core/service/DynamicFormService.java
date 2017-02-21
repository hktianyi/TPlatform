package org.tplatform.core.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tplatform.core.entity.DynamicForm;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface DynamicFormService extends JpaRepository<DynamicForm, Long> {

  /**
   * 根据表单ID查询表单
   * @param formId 表单ID
   * @return
   */
//  DynamicForm findByFormId(String formId);
//
//
//  /**
//   * 根据表单ID，查询元素
//   * @param formId
//   * @return
//     */
//  List<DFElementRecord> findElementsByFormId(String formId);
//
//  /**
//   * 查询表单数据
//   * @param formId 表单ID
//   * @param recordId 记录ID
//   * @return DynamicForm
//   */
//  DynamicForm findRecords(String formId, String recordId);
//
//  /**
//   * 查询表单数据
//   * @param formId 表单ID
//   * @param recordId 记录ID
//   * @return List&lt;DFElementRecord&gt;
//   */
//  List<DFElementRecord> findRecordList(String formId, String recordId);
//
//  /**
//   * 查询表单数据
//   * @param dfElementRecordList 动态元素
//   * @return
//   */
//  Integer saveRecords(List<DFElementRecord> dfElementRecordList);

}
