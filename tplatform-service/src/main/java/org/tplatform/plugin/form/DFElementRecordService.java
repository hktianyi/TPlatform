package org.tplatform.plugin.form;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.tplatform.common.BaseRepo;

import java.util.List;

/**
 * Created by Tianyi on 2016/3/18.
 */
public interface DFElementRecordService extends BaseRepo<DFElementRecord> {

  List<DFElementRecord> findByRecordId(String recordId);

  @Modifying
  @Query("delete from DFElementRecord where recordId = ?1")
  int deleteByRecordId(String recordId);
}
