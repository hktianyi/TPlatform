package org.tplatform.plugin.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.tplatform.common.BaseRepo;
import org.tplatform.common.BaseRepoImpl;
import org.tplatform.common.GlobalConstant;
import org.tplatform.util.SpringContextUtil;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 动态表单业务类
 * Created by tianyi on 2017/2/22.
 */
public interface DFService extends BaseRepo<DFElementRecord> {

  /**
   * 根据表单ID和记录ID查找动态表单对象
   *
   * @param formId   表单ID
   * @param recordId 记录ID
   * @return DynamicForm
   */
  @Cacheable(value = GlobalConstant.KEY_CACHE_SYS, key = "'_DF_findRecords_' + #formId + '_' + #recordId")
  DynamicForm findRecords(String formId, String recordId);

  /**
   * 保存表单记录
   *
   * @param dfElementRecordList 表单元素集合
   * @return Integer
   */
//  @CacheEvict(value = GlobalConstant.KEY_CACHE_SYS, key = "'_DF_'")
  @Transactional
  Integer saveRecords(List<DFElementRecord> dfElementRecordList);
}


class DFServiceImpl extends BaseRepoImpl<DFElementRecord> {

  @Autowired
  private DynamicFormService dynamicFormService;

  public DynamicForm findRecords(String formId, String recordId) {
    DynamicForm dynamicForm = dynamicFormService.findByFormId(formId);
    if (dynamicForm != null) {
      String sql = "select t1.id eleId, t1.formId, t1.eleType, t1.eleAttr, t1.eleName, t1.eleClass, t1.zhName, t1.remark, t1.sort, " +
          " (select id from sys_df_record t0 where t0.eleName = t1.eleName and t0.recordId = ?0) id, " +
          " (select elevalue from sys_df_record t2 where t2.eleName = t1.eleName and t2.recordId = ?1) eleValue " +
          " from sys_df_element t1 where t1.formId = ?2 order by t1.sort";
      dynamicForm.setElements(findWithSql(sql, recordId, recordId, formId));
    }
    return dynamicForm;
  }

  public int saveRecords(List<DFElementRecord> dfElementRecordList) {
    if (dfElementRecordList == null || dfElementRecordList.size() == 0)
      throw new IllegalArgumentException("表单元素为空，拒绝保存");

    Long operator = SpringContextUtil.getOperator();
    String formId = dfElementRecordList.get(0).getFormId(), recordId = dfElementRecordList.get(0).getRecordId();
    // 删除旧数据
    super.executeSql("DELETE FROM sys_df_record WHERE formId = ?0 and recordId = ?1", formId, recordId);

    // 准备新数据
    StringBuilder sql = new StringBuilder();
    sql.append("INSERT INTO sys_df_record (operator, formId, recordId, eleName, eleValue) VALUES");
    DFElementRecord dfElementRecord;
    for (int i = 0, size = dfElementRecordList.size(); i < size; i++) {
      dfElementRecord = dfElementRecordList.get(i);
      sql.append("('").append(operator).append("',")
          .append("'").append(formId).append("',")
          .append("'").append(recordId).append("',")
          .append("'").append(dfElementRecord.getEleName()).append("',")
          .append("'").append(dfElementRecord.getEleValue()).append("'")
          .append("),");
    }

    return super.executeSql(sql.substring(0, sql.length() - 1), formId);
  }
}
