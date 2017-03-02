package org.tplatform.plugin.form;

import org.tplatform.common.BaseRepo;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface DynamicFormService extends BaseRepo<DynamicForm> {

  /**
   * 根据表单ID查询表单
   * @param formId 表单ID
   * @return
   */
  DynamicForm findByFormId(String formId);

}
