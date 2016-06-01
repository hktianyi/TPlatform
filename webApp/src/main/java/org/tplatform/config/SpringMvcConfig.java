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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * spring配置文件
 * Created by Tianyi on 2016/3/1.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.tplatform", basePackageClasses = {Controller.class, ControllerAdvice.class},
    includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*.admin.*")})
//@ImportResource("classpath:dubbo-consumer.xml")
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

  // Apache Tiles
  @Bean
  public TilesConfigurer tilesConfigurer() {
    TilesConfigurer tilesConfigurer = new TilesConfigurer();
    tilesConfigurer.setDefinitions("");
    return tilesConfigurer;
  }

  // 视图解析器
  @Bean
  public UrlBasedViewResolver urlBasedViewResolver() {
    UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
    urlBasedViewResolver.setViewClass(TilesView.class);
    urlBasedViewResolver.setOrder(2);
    return urlBasedViewResolver;
  }

  // 视图解析器
  @Bean
  public InternalResourceViewResolver internalResourceViewResolver() {
    InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
    internalResourceViewResolver.setPrefix("/WEB-INF/views/");
    internalResourceViewResolver.setOrder(1);
    return internalResourceViewResolver;
  }

  // 文件上传
  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
    commonsMultipartResolver.setDefaultEncoding("UTF-8");
    commonsMultipartResolver.setMaxUploadSize(10485760000L);
    commonsMultipartResolver.setMaxInMemorySize(40960);
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
    super.addInterceptors(registry);
  }

  // 异常处理
  @Override
  public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
    ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();
    System.out.println("==================configureHandlerExceptionResolvers异常================\n" + exceptionResolvers);
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat sdf = new SimpleDateFormat();
    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
  }

}
