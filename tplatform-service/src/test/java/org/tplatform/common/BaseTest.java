package org.tplatform.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.tplatform.config.SpringRootConfig;

/**
 * Created by tianyi on 2017/2/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SpringRootConfig.class)
@ActiveProfiles("DEV")
public class BaseTest {

  public static void main(String[] args) {
    StringBuilder sbd = new StringBuilder();
    sbd.append("var ").append("{");
    sbd.append("a:'a',");
    int length = sbd.length();
    sbd.replace(length-1, length, "}");
    System.out.println(sbd);
  }
}
