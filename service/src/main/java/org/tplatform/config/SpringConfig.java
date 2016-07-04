package org.tplatform.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Tianyi on 2016/3/2.
 */
@Configuration
@Import(DataSourceConfig.class)
//@ImportResource("classpath*:dubbo-provider.xml")
@EnableScheduling
@EnableCaching
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"org.tplatform", "org.effiechina"}, basePackageClasses = {Service.class, Component.class})
public class SpringConfig {

  @Bean
  public SchedulerFactoryBean scheduler() {
    SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//    schedulerFactoryBean.setJobFactory(new JobFactory());
    return schedulerFactoryBean;
  }
}
