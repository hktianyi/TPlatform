package org.tplatform.core.mapper;

import org.tplatform.constant.GlobalConstant;
import org.tplatform.core.entity.DFElementRecord;
import org.tplatform.core.entity.DynamicForm;
import org.apache.ibatis.annotations.*;
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

  @Select("select t1.formId, t1.eleType, t1.eleAttr, t1.eleName, t1.eleClass, t1.zhName, t1.remark, " +
      " (select id from b_df_record t0 where t0.eleName = t1.eleName and t0.recordId = #{recordId}) id, " +
      " (select elevalue from b_df_record t2 where t2.eleName = t1.eleName and t2.recordId = #{recordId}) eleValue, " +
      " (select `maxValue` from b_df_record t3 where t3.eleName = t1.eleName and t3.recordId = #{recordId}) `maxValue`," +
      " (select `minValue` from b_df_record t4 where t4.eleName = t1.eleName and t4.recordId = #{recordId}) `minValue`," +
      " (select `avgValue` from b_df_record t5 where t5.eleName = t1.eleName and t5.recordId = #{recordId}) `avgValue`," +
      " (select `score` from b_df_record t6 where t6.eleName = t1.eleName and t6.recordId = #{recordId}) `score`" +
      " from sys_df_element t1 where t1.formId = #{formId} order by t1.sort")
  List<DFElementRecord> findRecords(@Param("formId") String formId, @Param("recordId") String recordId);

  @Select("select * from b_df_record where formId = #{formId} and recordId = #{recordId}")
  List<DFElementRecord> findRecordList(@Param("formId") String formId, @Param("recordId") String recordId);


  @Insert("insert into b_df_record (timestamp, operator, status, formId, recordId, eleName, eleValue, `maxValue`, `minValue`, `avgValue`, `score`) " +
      "values (now(), #{operator}, #{status}, #{formId}, #{recordId}, #{eleName}, #{eleValue}, #{maxValue}, #{minValue}, #{avgValue}, #{score})")
  void saveRecord(DFElementRecord dfElementRecord);

  @Update("update b_df_record set eleValue = #{eleValue}, score = #{score} where id = #{id}")
  void updateRecord(DFElementRecord dfElementRecord);

  @Delete("delete from b_df_record where formId = #{formId} and recordId = #{recordId} and operator = '" + GlobalConstant.DF_AUTO_OPERATOR + "'")
  void deleteRecord(@Param("formId") String formId, @Param("recordId") String recordId);
}
