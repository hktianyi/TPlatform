package org.tplatform.plugin.form;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tplatform.common.BaseTest;

import java.util.ArrayList;
import java.util.List;

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
    DynamicForm dynamicForm = dynamicFormService.findByFormId(formId);
    Assert.assertNotNull(dynamicForm);
    Assert.assertEquals(formId, dynamicForm.getFormId());
  }

  @Test
  public void findRecords() {
    String formId = "plan_mtfl_cbqd";
    String recordId = "TEST";
    DynamicForm dynamicForm = dfService.findRecords(formId, recordId);
    Assert.assertNotNull(dynamicForm);
    Assert.assertNotNull(dynamicForm.getElements());
    Assert.assertEquals(formId, dynamicForm.getFormId());
  }

  @Test
  public void saveRecords() {
    String formId = "plan_mtfl_cbqd";
    String recordId = "TEST";
    List<DFElementRecord> elements = new ArrayList<>();
    DFElementRecord dfElementRecord;
    for (int i = 0; i<10; i++) {
      dfElementRecord = new DFElementRecord();
      dfElementRecord.setFormId(formId);
      dfElementRecord.setRecordId(recordId);
      dfElementRecord.setEleName("name:" + i);
      dfElementRecord.setEleValue("value:" + i);
      elements.add(dfElementRecord);
    }
    Assert.assertNotNull(dfService.saveRecords(elements));
  }
}
