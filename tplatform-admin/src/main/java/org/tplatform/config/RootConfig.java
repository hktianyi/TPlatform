package org.tplatform.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Tianyi on 2016/3/2.
 */
@Configuration
@Import({DataSourceConfig.class})
//@EnableScheduling
//@EnableCaching
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "org.tplatform",
includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Service.class, Component.class, Repository.class})},
excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, RestController.class, ControllerAdvice.class, RestControllerAdvice.class, EnableWebMvc.class})})
public class RootConfig {
}
