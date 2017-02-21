package org.tplatform.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.tplatform.util.Logger;
import org.tplatform.util.PropertyUtil;
import org.tplatform.util.SpringContextUtil;
import org.tplatform.util.StringUtil;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Hashtable;

/**
 * 数据源、事物配置
 * Created by Tianyi on 2016/3/2.
 */
@Configuration
@DependsOn("springContextUtil")
@EnableTransactionManagement(proxyTargetClass = true)
//@PropertySource("classpath:db.properties")
@ImportResource("classpath:spring-data-jpa.xml")
public class DataSourceConfig {

//  @Value("${jndiName}")
  private String jndiName = PropertyUtil.getProInfo("db", "jndiName");
//  @Value("${basePackage}")
  private String[] basePackages = (PropertyUtil.getProInfo("config", "basePackage")).split(",");

  // JNDI数据源(未使用)
  @Profile("TEST")
  @Bean(name = "dataSource")
  public DataSource dataSource() throws NamingException {
    Logger.i(" --- 测试环境 JNDI数据源: " + jndiName);
    JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
    jndiObjectFactoryBean.setJndiName(jndiName);
    jndiObjectFactoryBean.setResourceRef(true);
    jndiObjectFactoryBean.afterPropertiesSet();
    return (DataSource) jndiObjectFactoryBean.getObject();
  }

  /**
   * 生产环境,使用JNDI数据源
   * @return
   */
  @Profile("PROD")
  @Bean(name = "dataSource")
  public DataSource jndiDataSource() {
    Logger.i(" --- 生产环境 JNDI数据源: " + jndiName);
    if (StringUtil.isEmpty(jndiName)) return null;

    JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
    jndiDataSourceLookup.setResourceRef(true);
    return jndiDataSourceLookup.getDataSource(jndiName);
  }

  /**
   * 开发环境, 使用DruidDataSourceFactory创建数据源
   * @return
   */
  @Profile("DEV")
  @Bean(name = "dataSource")
  public DataSource embeddedDataSource() {
    try {
      return DruidDataSourceFactory.createDataSource(PropertyUtil.getProInfoMap("db"));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
    entityManagerFactory.setDataSource(SpringContextUtil.getBean(DataSource.class));
    entityManagerFactory.setPackagesToScan(basePackages);
    entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
    entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());

    // 设置jpaVendorAdapter
    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    jpaVendorAdapter.setGenerateDdl(false);
    jpaVendorAdapter.setShowSql(true);
    jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
    jpaVendorAdapter.setDatabase(Database.MYSQL);
    entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);

    // 设置配置参数
    Hashtable<String, String> prop = PropertyUtil.getProInfoMap("db");
    entityManagerFactory.setJpaPropertyMap(prop);
    return entityManagerFactory;
  }

  @Bean(name = "transactionManager")
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }

}
