package org.tplatform.config;

import com.alibaba.druid.support.http.StatViewServlet;
import lombok.Setter;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;
import org.tplatform.common.GlobalConstant;
import org.tplatform.filters.AuthenticationFilter;
import org.tplatform.listener.SessionListener;
import org.tplatform.util.DateUtil;
import org.tplatform.util.PropertyUtil;
import org.tplatform.util.SpringContextUtil;
import org.tplatform.util.StringUtil;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * Web主配置文件，替代web.xml
 * Created by Tianyi on 2016/3/1.
 */
public abstract class WebAppConfig implements WebApplicationInitializer {

  @Setter
  private Class springMvcConfigClass = SpringMvcConfig.class;
  @Setter
  private static String urlRegex = null; // "^/(login|static)+.*$";
  @Setter
  private static boolean druidStatView = true;

  // 启动执行
  public void onStartup(ServletContext servletContext) throws ServletException {

    // spring环境配置文件,默认开发环境
    System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "DEV");

    // SpringMVC 入口
    AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
    mvcContext.register(springMvcConfigClass);
    ServletRegistration.Dynamic springMvc = servletContext.addServlet(GlobalConstant.SYSTEM_SERVLET_NAME_SPRINGMVC, new DispatcherServlet(mvcContext));
    springMvc.setLoadOnStartup(1);

    // 验证tomcat版本
    if (!springMvc.addMapping("/").isEmpty()) {
      throw new IllegalStateException("'" + GlobalConstant.SYSTEM_SERVLET_NAME_SPRINGMVC + "' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
    }

    servletContext.addListener(new ContextLoaderListener(mvcContext));
    // Spring 刷新Introspector防止内存泄露
    servletContext.addListener(new IntrospectorCleanupListener());
    servletContext.addListener(new SessionListener());

    // 字符集过滤
    FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
    encodingFilter.setInitParameter("forceEncoding", "true");
    encodingFilter.setInitParameter("encoding", "UTF-8");
    encodingFilter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, GlobalConstant.SYSTEM_SERVLET_NAME_SPRINGMVC);

    // EM过滤器，LAZY加载使用
    FilterRegistration.Dynamic emFilter = servletContext.addFilter("emFilter", new OpenEntityManagerInViewFilter());
    emFilter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, GlobalConstant.SYSTEM_SERVLET_NAME_SPRINGMVC);

    // 权限过滤
    if (StringUtil.isNotEmpty(urlRegex)) {
      FilterRegistration.Dynamic authenticationFilter = servletContext.addFilter("authenticationFilter", new AuthenticationFilter());
      authenticationFilter.setInitParameter("urlRegex", urlRegex);
      authenticationFilter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, GlobalConstant.SYSTEM_SERVLET_NAME_SPRINGMVC);
    }

    // druid数据源监控
    if (druidStatView) {
      ServletRegistration.Dynamic druid = servletContext.addServlet(GlobalConstant.SYSTEM_SERVLET_NAME_DRUIDSTATVIEW, new StatViewServlet());
      druid.addMapping("/sysInfo/druid/*");
      springMvc.setLoadOnStartup(2);
    }

    // 读取数据库配置
    new Thread(() -> {
      while (SpringContextUtil.getApplicationContext() == null) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      // 应用初始化配置参数
      servletContext.setAttribute(GlobalConstant.SYSTEM_APPLICATION_FILE_DOMAIN, PropertyUtil.getProInfo(GlobalConstant.SYSTEM_APPLICATION_FILE_DOMAIN));
      servletContext.setAttribute(GlobalConstant.SYSTEM_APPLICATION_NAME, PropertyUtil.getProInfo(GlobalConstant.SYSTEM_APPLICATION_NAME));
      servletContext.setAttribute(GlobalConstant.SYSTEM_SERVLET_PATH, servletContext.getContextPath());
      servletContext.setAttribute(GlobalConstant.SYSTEM_SERVLET_VERSION, DateUtil.getCurrentDate(DateUtil.FORMAT_DATETIME_SHORT));
      System.out.println("/* =========================================================\n" +
          " *\n" +
          " * TPlatform ® - V0.0.1\n" +
          " *\n" +
          " * =========================================================\n" +
          " *\n" +
          " * Copyright © 2017 By TPlatform (http://www.tplatform.org)\n" +
          " *\n" +
          " *\n" +
          " *                       _oo0oo_\n" +
          " *                      o8888888o\n" +
          " *                      88\" . \"88\n" +
          " *                      (| -_- |)\n" +
          " *                      0\\  =  /0\n" +
          " *                    ___/`---'\\___\n" +
          " *                  .' \\|     |// '.\n" +
          " *                 / \\|||  :  |||// \\\n" +
          " *                / _||||| -:- |||||- \\\n" +
          " *               |   | \\\\  -  /// |   |\n" +
          " *               | \\_|  ''\\---/''  |_/ |\n" +
          " *               \\  .-\\__  '-'  ___/-. /\n" +
          " *             ___'. .'  /--.--\\  `. .'___\n" +
          " *          .\"\" '<  `.___\\_<|>_/___.' >' \"\".\n" +
          " *         | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
          " *         \\  \\ `_.   \\_ __\\ /__ _/   .-` /  /\n" +
          " *     =====`-.____`.___ \\_____/___.-`___.-'=====\n" +
          " *                       `=---='\n" +
          " *\n" +
          " *     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
          " *\n" +
          " *                 Java 是世界上最好的语言！\n" +
          " *\n" +
          " * ========================================================= */");
    }).start();
  }

}