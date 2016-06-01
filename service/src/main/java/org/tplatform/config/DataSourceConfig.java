package org.tplatform.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.tplatform.util.PropertyUtil;
import tk.mybatis.mapper.code.Style;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据源、事物配置
 * Created by Tianyi on 2016/3/2.
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DataSourceConfig {

//  // JNDI数据源(未使用)
//  @Bean(name = "dataSource")
//  public DataSource dataSource() throws NamingException {
//    JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//    jndiObjectFactoryBean.setJndiName("java:comp/env/cdy");
//    jndiObjectFactoryBean.setResourceRef(true);
//    jndiObjectFactoryBean.afterPropertiesSet();
//    return (DataSource) jndiObjectFactoryBean.getObject();
//  }

//  // JNDI数据源名称
//  private static String jndiName = PropertyUtil.getProInfo("config", "jdbc.jndiName");
//
//  // JNDI数据源
//  @Bean(name = "dataSource")
//  public DataSource dataSource() {
//    if (StringUtil.isEmpty(this.jndiName)) return null;
//
//    JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
//    jndiDataSourceLookup.setResourceRef(true);
//    return jndiDataSourceLookup.getDataSource(this.jndiName);
//  }

  // JNDI数据源
  @Bean(name = "dataSource")
  public DataSource dataSource() {
    try {
      return DruidDataSourceFactory.createDataSource(PropertyUtil.getProInfoMap("db"));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  // sqlSession模板
  @Bean(name = "sqlSessionTemplate")
  public SqlSessionTemplate sqlSessionTemplate() throws Exception {
    SqlSessionFactory sqlSessionFactory = sqlSessionFactory();
    return sqlSessionFactory == null ? null : new SqlSessionTemplate(sqlSessionFactory());
  }

  // sqlSession工厂
  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource());
    sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/*.xml"));

    Properties properties = new Properties();
    properties.put("dialect", "mysql");
//    该参数默认为false
//    设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
//    和startPage中的pageNum效果一样-->
//    properties.put("offsetAsPageNum", true);
//    该参数默认为false
//    设置为true时，使用RowBounds分页会进行count查询
//    properties.put("rowBoundsWithCount", true);
//    设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果
//    （相当于没有执行分页查询，但是返回结果仍然是Page类型）-->
//    properties.put("pageSizeZero", true);
//    3.3.0版本可用 - 分页参数合理化，默认false禁用
//    启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
//    禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
    properties.put("reasonable", true);
//    3.5.0版本可用 - 为了支持startPage(Object params)方法
//    增加了一个`params`参数来配置参数映射，用于从Map或ServletRequest中取值
//    可以配置pageNum,pageSize,count,pageSizeZero,reasonable,orderBy,不配置映射的用默认值
//    不理解该含义的前提下，不要随便复制该配置
//    properties.put("params", "pageNum=start;pageSize=length;");
//    支持通过Mapper接口参数来传递分页参数
//    properties.put("supportMethodsArguments", false);
//    always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page
    properties.put("returnPageInfo", "always");

    PageHelper pageHelper = new PageHelper();
    pageHelper.setProperties(properties);

    sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
    return sqlSessionFactoryBean.getObject();
  }

  // 通用mapper
  @Bean
  @DependsOn("sqlSessionFactory")
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    mapperScannerConfigurer.setBasePackage("org.tplatform.**.mapper");
    mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");

    MapperHelper mapperHelper = new MapperHelper();
    Config config = new Config();
    config.setStyle(Style.camelhumpAndLowercase);
    mapperHelper.setConfig(config);
    mapperHelper.registerMapper(Mapper.class);
    mapperScannerConfigurer.setMapperHelper(mapperHelper);

    return mapperScannerConfigurer;
  }

  // 事物管理器
  @Bean(name = "transactionManager")
  public DataSourceTransactionManager transactionManager() throws NamingException {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSource());
    return dataSourceTransactionManager;
  }
}
