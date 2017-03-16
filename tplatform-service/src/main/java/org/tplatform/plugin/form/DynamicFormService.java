package org.tplatform.plugin.form;

import org.tplatform.common.BaseRepo;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface DynamicFormService extends BaseRepo<DynamicForm> {

  /**
   * 根据表单ID查询表单
   * @param enName 表单名称
   * @return
   */
  DynamicForm findByEnName(String enName);

}
