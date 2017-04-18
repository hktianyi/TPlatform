package org.tplatform.plugin.form;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tplatform.common.BaseTest;
import org.tplatform.util.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianyi on 2017/2/23.
 */
public class DFServiceTest extends BaseTest {

  @Autowired
  private DFService dfService;
  @Autowired
  private DynamicFormService dynamicFormService;

  @Test
  public void findByFormId() {
    String formId = "plan_mtfl_cbqd";
    DynamicForm dynamicForm = dynamicFormService.findByEnName(formId);
    Assert.assertNotNull(dynamicForm);
    Assert.assertEquals(formId, dynamicForm.getEnName());
  }

  @Test
  public void findRecords() {
    String enName = "plan_mtfl_cbqd";
    String recordId = "TEST";
    DynamicForm dynamicForm = dfService.findByEnNameAndRecordId(enName, recordId);
    Assert.assertNotNull(dynamicForm);
    Assert.assertNotNull(dynamicForm.getElements());
    Assert.assertEquals(enName, dynamicForm.getEnName());
  }

  @Test
  public void saveRecords() {
    Long formId = 1l;
    Map<String, Long> eleNames = dfService.findEleIdByFormId(formId);

    String recordId = "TEST";
    Long timestamp = DateUtil.getTimeInMillis();
    List<DFElementRecord> elements = new ArrayList<>();
    DFElementRecord dfElementRecord;
    for (int i = 0; i<9; i++) {
      dfElementRecord = new DFElementRecord();
      System.out.println("xshd" + (i+1) + ": " + eleNames.get("xshd" + (i+1)));
      dfElementRecord.setEleId(eleNames.get("xshd" + (i+1)));
      dfElementRecord.setRecordId(recordId);
      dfElementRecord.setEleValue("value:" + i);
      dfElementRecord.setTimestamp(timestamp);
      elements.add(dfElementRecord);
    }
    dfService.saveRecords(elements);
  }
}
