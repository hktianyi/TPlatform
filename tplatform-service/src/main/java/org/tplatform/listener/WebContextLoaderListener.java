package org.tplatform.listener;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.tplatform.util.ThreadPoolUtil;

import javax.servlet.ServletContextEvent;

/**
 * Created by tianyi on 2017/3/2.
 */
public class WebContextLoaderListener extends ContextLoaderListener {
  public WebContextLoaderListener() {
    super();
  }

  public WebContextLoaderListener(WebApplicationContext context) {
    super(context);
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
    ThreadPoolUtil.getFixedThreadPool().shutdown();
    ThreadPoolUtil.getSchedulePool().shutdown();
    super.contextDestroyed(event);
  }
}
