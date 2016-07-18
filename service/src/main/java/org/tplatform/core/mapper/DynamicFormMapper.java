package org.tplatform.core.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.tplatform.core.entity.DFElementRecord;
import org.tplatform.core.entity.DynamicForm;
import org.tplatform.framework.util.SpringContextUtil;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by tianyi on 16/1/13.
 */
public interface DynamicFormMapper extends Mapper<DynamicForm> {

  @Select("select id, formId, zhName from sys_df where formId = #{formId}")
  DynamicForm findByFormId(@Param("formId") String formId);

  @Select("select * from sys_df_element where formId = #{formId} order by sort")
  List<DFElementRecord> findElementsByFormId(@Param("formId") String formId);

  @Select("select t1.id eleId, t1.formId, t1.eleType, t1.eleAttr, t1.eleName, t1.eleClass, t1.zhName, t1.remark, t1.sort, " +
      " (select id from sys_df_record t0 where t0.eleName = t1.eleName and t0.recordId = #{recordId}) id, " +
      " (select elevalue from sys_df_record t2 where t2.eleName = t1.eleName and t2.recordId = #{recordId}) eleValue " +
      " from sys_df_element t1 where t1.formId = #{formId} order by t1.sort")
  List<DFElementRecord> findRecords(@Param("formId") String formId, @Param("recordId") String recordId);

  @Select("select * from sys_df_record where formId = #{formId} and recordId = #{recordId}")
  List<DFElementRecord> findRecordList(@Param("formId") String formId, @Param("recordId") String recordId);

  /**
   * 批量插入
   *
   * @param list
   */
  @InsertProvider(type = SQLProvider.class, method = "insertRecords")
  void insertRecords(@Param("list") List<DFElementRecord> list);

  /**
   * 动态SQL生成
   */
  class SQLProvider {

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    public String insertRecords(List<DFElementRecord> list) {
      String operator = SpringContextUtil.getOperator();
      StringBuilder sql = new StringBuilder();
      sql.append("DELETE FROM sys_df_record WHERE formId = #{list[0].formId} and recordId = #{list[0].recordId};\n");
      sql.append("INSERT INTO sys_df_record (operator, status, formId, recordId, eleName, eleValue) VALUES");
      for (int i = 0, size = list.size(); i < size; i++) {
        sql.append("('").append(operator).append("',")
            .append("#{list[").append(i).append("].status},")
            .append("#{list[").append(i).append("].formId},")
            .append("#{list[").append(i).append("].recordId},")
            .append("#{list[").append(i).append("].eleName},")
            .append("#{list[").append(i).append("].eleValue}")
            .append("),");
      }
      return sql.substring(0, sql.length() - 1);
    }
  }
}
