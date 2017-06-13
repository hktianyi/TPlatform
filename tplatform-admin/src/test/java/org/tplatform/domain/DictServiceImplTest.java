package org.tplatform.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tplatform.common.BaseTest;

import java.util.List;

/**
 * DictServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 21, 2016</pre>
 */
public class DictServiceImplTest extends BaseTest {

  @Autowired
  private DictService dictService;

  @Before
  public void before() throws Exception {
  }

  @After
  public void after() throws Exception {
  }

  /**
   * Method: findProvinceList()
   */
  @Test
  public void testFindProvinceList() throws Exception {
    List list = dictService.findProvinceList();
    System.out.println(list);
  }
  @Test
  public void testFindMunicipalityList() throws Exception {
    List list = dictService.findMunicipalityList();
    System.out.println(list);
  }

  /**
   * Method: findCityList(Long pid)
   */
  @Test
  public void testFindCityList() throws Exception {
    List<Dict> provinceList = dictService.findProvinceList();
    provinceList.parallelStream().forEach(province -> {
//      List<Dict> cityList = dictService.findWithSql("", province.getId());
//      System.out.println(cityList);
    });
  }

  /**
   * Method: findCountyList(Long pid)
   */
  @Test
  public void testFindCountyList() throws Exception {
//TODO: Test goes here...
  }


}
