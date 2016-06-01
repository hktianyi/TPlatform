package org.tplatform.task.service.impl;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public class JobFactory extends SpringBeanJobFactory implements
    ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext)
      throws BeansException {
    this.applicationContext = applicationContext;
  }

  @Override
  protected Object createJobInstance(TriggerFiredBundle bundle)
      throws Exception {
    Object obj = super.createJobInstance(bundle);
    if (obj != null) {
      applicationContext.getAutowireCapableBeanFactory().autowireBean(obj);
    }
    return obj;
  }

}
