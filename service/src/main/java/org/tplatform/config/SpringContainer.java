package org.tplatform.config;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.container.Container;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tianyi on 2016/3/23.
 */
public class SpringContainer implements Container {

  private static final Logger logger = LoggerFactory.getLogger(SpringContainer.class);

  static AnnotationConfigApplicationContext context;

  public static AnnotationConfigApplicationContext getContext() {
    return context;
  }

  public void start() {
    context = new AnnotationConfigApplicationContext(SpringConfig.class);
    context.start();
  }

  public void stop() {
    try {
      if (context != null) {
        context.stop();
        context.close();
        context = null;
      }
    } catch (Throwable e) {
      logger.error(e.getMessage(), e);
    }
  }
}
