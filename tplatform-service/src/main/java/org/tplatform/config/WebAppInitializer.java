package org.tplatform.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.IntrospectorCleanupListener;
import org.tplatform.common.GlobalConstant;
import org.tplatform.listener.SessionListener;
import org.tplatform.util.DateUtil;
import org.tplatform.util.PropertyUtil;
import org.tplatform.util.SpringContextUtil;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Web主配置文件，替代web.xml
 * Created by Tianyi on 2016/3/1.
 */
@Order(3)
public abstract class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  protected Class<?>[] rootConfigClasses;
  protected Map<String, Class> servletConfig = new LinkedHashMap<>();

  public WebAppInitializer() {
    rootConfigClasses = new Class[]{RootConfig.class};
    servletConfig.put("/", MvcServlet.class);
    servletConfig.put("/druid/*", StatViewServlet.class);
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return rootConfigClasses;
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return servletConfig.values().toArray(new Class[servletConfig.values().size()]);
  }

  @Override
  protected String[] getServletMappings() {
    return servletConfig.keySet().toArray(new String[servletConfig.values().size()]);
  }

  @Override
  protected String getServletName() {
    return GlobalConstant.SYSTEM_SERVLET_NAME_SPRINGMVC;
  }

  @Override
  protected Filter[] getServletFilters() {
    // 字符集过滤
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    characterEncodingFilter.setForceEncoding(true);
    characterEncodingFilter.setForceRequestEncoding(true);
    characterEncodingFilter.setForceResponseEncoding(true);

    return new Filter[]{characterEncodingFilter, new OpenEntityManagerInViewFilter()};
  }

  @Override
  protected void registerContextLoaderListener(ServletContext servletContext) {
    servletContext.setInitParameter(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "DEV");
    super.registerContextLoaderListener(servletContext);
//    servletContext.addListener(new WebContextLoaderListener());
    // Spring 刷新Introspector防止内存泄露
    servletContext.addListener(new IntrospectorCleanupListener());
    servletContext.addListener(new SessionListener());
    // 读取数据库配置
    new ServletAttributeInit(servletContext);
  }
}

class ServletAttributeInit implements Runnable {

  private ServletContext servletContext;

  public ServletAttributeInit(ServletContext servletContext) {
    this.servletContext = servletContext;
    new Thread(this).start();
  }

  @Override
  public void run() {
    while (SpringContextUtil.getApplicationContext() == null) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    // 应用初始化配置参数
    servletContext.setAttribute(GlobalConstant.SYSTEM_SERVLET_PATH, servletContext.getContextPath());
    servletContext.setAttribute(GlobalConstant.SYSTEM_SERVLET_VERSION, DateUtil.getCurrentDate(DateUtil.FORMAT_DATETIME_SHORT));
    servletContext.setAttribute(GlobalConstant.SYSTEM_APPLICATION_FILE_DOMAIN, PropertyUtil.getProInfo(GlobalConstant.SYSTEM_APPLICATION_FILE_DOMAIN));
    servletContext.setAttribute(GlobalConstant.SYSTEM_APPLICATION_NAME, PropertyUtil.getProInfo(GlobalConstant.SYSTEM_APPLICATION_NAME));
    System.out.println("/* =========================================================\n" +
        " *\n" +
        " * Copyright © 2015 - " + DateUtil.getCurrentDate("yyyy") + " By TPlatform ® (http://www.tplatform.org)\n" +
        " *\n" +
        " * ========================================================= */"
    );
  }
}