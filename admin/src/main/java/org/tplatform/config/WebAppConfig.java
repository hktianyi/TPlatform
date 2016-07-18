package org.tplatform.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;
import org.tplatform.constant.GlobalConstant;
import org.tplatform.filters.AuthenticationFilter;
import org.tplatform.framework.util.DateUtil;
import org.tplatform.listener.SessionListener;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * 替换web.xml
 * Created by Tianyi on 2016/3/1.
 */
public class WebAppConfig implements WebApplicationInitializer {

  // 启动执行
  public void onStartup(ServletContext servletContext) throws ServletException {

    // SpringMVC 入口
    AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
    mvcContext.register(SpringMvcConfig.class);
    ServletRegistration.Dynamic springMvc = servletContext.addServlet(GlobalConstant.SYSTEM_SERVLET_NAME_SPRINGMVC, new DispatcherServlet(mvcContext));
    springMvc.setLoadOnStartup(1);
    
    // 验证tomcat版本
    if (!springMvc.addMapping("/").isEmpty()) {
      throw new IllegalStateException("'" + GlobalConstant.SYSTEM_SERVLET_NAME_SPRINGMVC + "' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
    }

    // Spring 刷新Introspector防止内存泄露
    servletContext.addListener(new IntrospectorCleanupListener());
    servletContext.addListener(new SessionListener());

    // 字符集过滤
    FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
    encodingFilter.setInitParameter("forceEncoding", "true");
    encodingFilter.setInitParameter("encoding", "UTF-8");
    encodingFilter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, GlobalConstant.SYSTEM_SERVLET_NAME_SPRINGMVC);

    // 应用初始化配置参数
    servletContext.setAttribute(GlobalConstant.SYSTEM_APPLICATION_NAME, "TPlatform");
    servletContext.setAttribute(GlobalConstant.SYSTEM_SERVLET_PATH, servletContext.getContextPath());
    servletContext.setAttribute(GlobalConstant.SYSTEM_SERVLET_VERSION, DateUtil.getCurrentDate(DateUtil.FORMAT_DATETIME_SHORT));

    // 权限过滤
    FilterRegistration.Dynamic authenticationFilter = servletContext.addFilter("authenticationFilter", new AuthenticationFilter());
    authenticationFilter.setInitParameter("urlRegex", "^/(login|static)+.*$");
    authenticationFilter.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, GlobalConstant.SYSTEM_SERVLET_NAME_SPRINGMVC);

    // druid数据源监控
    ServletRegistration.Dynamic druid = servletContext.addServlet(GlobalConstant.SYSTEM_SERVLET_NAME_DRUIDSTATVIEW, new StatViewServlet());
    druid.addMapping("/sysInfo/druid/*");
    springMvc.setLoadOnStartup(2);
  }
}
