package org.tplatform.config;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.tplatform.common.LogInterceptor;
import org.tplatform.common.PlatformMappingExceptionResolver;
import org.tplatform.util.PropertyUtil;
import org.tplatform.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * spring配置文件
 * Created by Tianyi on 2016/3/1.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.tplatform",
    includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, RestController.class, ControllerAdvice.class, RestControllerAdvice.class})},
    excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Service.class, Repository.class, Configuration.class})})
public class MvcServlet extends WebMvcConfigurerAdapter {

  // 视图解析器
  @Bean
  public InternalResourceViewResolver internalResourceViewResolver() {
    InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
    String prefix = PropertyUtil.getProInfo("config", "web.prefix");
    String suffix = PropertyUtil.getProInfo("config", "web.suffix");
    internalResourceViewResolver.setPrefix(StringUtil.isBlank(prefix) ? "/WEB-INF/template/" : prefix);
    internalResourceViewResolver.setSuffix(StringUtil.isBlank(suffix) ? "" : suffix);
    internalResourceViewResolver.setOrder(1);
    return internalResourceViewResolver;
  }

  @Bean
  public ResourceBundleThemeSource resourceBundleThemeSource() {
    ResourceBundleThemeSource resourceBundleThemeSource = new ResourceBundleThemeSource();
    resourceBundleThemeSource.setBasenamePrefix("theme-");
    return resourceBundleThemeSource;
  }

  @Bean
  public SessionThemeResolver sessionThemeResolver() {
    SessionThemeResolver sessionThemeResolver = new SessionThemeResolver();
    String themeName = PropertyUtil.getProInfo("config", "theme.name");
    sessionThemeResolver.setDefaultThemeName(StringUtil.isBlank(themeName) ? "" : themeName);
    return sessionThemeResolver;
  }

  // 文件上传
  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
    commonsMultipartResolver.setDefaultEncoding("UTF-8");
    String MaxUploadSize = PropertyUtil.getProInfo("config", "web.upload.maxSize");
    String MaxInMemorySize = PropertyUtil.getProInfo("config", "web.upload.maxInMemorySize");
    commonsMultipartResolver.setMaxUploadSize(StringUtil.isBlank(MaxUploadSize) ? 10485760000L : Long.valueOf(MaxUploadSize));
    commonsMultipartResolver.setMaxInMemorySize(StringUtil.isBlank(MaxInMemorySize) ? 40960 : Integer.valueOf(MaxInMemorySize));
    return commonsMultipartResolver;
  }

  // 静态资源映射器
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  // 解决Spring MVC @ResponseBody返回中文字符串乱码问题
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
    List<MediaType> list = new ArrayList<>();
    list.add(MediaType.valueOf("text/plain;charset=GBK"));
    stringHttpMessageConverter.setSupportedMediaTypes(list);
  }

  // 拦截器
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LogInterceptor());
    super.addInterceptors(registry);
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat sdf = new SimpleDateFormat();
    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
  }

  @Override
  public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
    PlatformMappingExceptionResolver exceptionResolver = new PlatformMappingExceptionResolver();
    Properties mappings = new Properties();
    mappings.put(Exception.class, "/base/error");
    exceptionResolver.setExceptionMappings(mappings);
    exceptionResolvers.add(exceptionResolver);
    super.extendHandlerExceptionResolvers(exceptionResolvers);
  }

}
