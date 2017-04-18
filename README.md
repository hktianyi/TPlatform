#TPlatform（T台秀）
##J2EE基础开发平台

## 开发环境
### gradle
### tomcat8 + jdk8
### lombok（idea需要下载lombok插件，并且开启annotation processing）
### Spring全家桶（SpringMVC、Spring、SpringJPA、Spring Security）

## 关键配置文件简介
### db.properties 数据库连接信息配置文件(开发环境使用，生成、测试环境使用JNDI数据源)
### DataSourceConfig.java 数据源(druid)
### WebAppConfig.java 替代web.xml
### SpringConfig.java spring配置文件
### SpringMvcConfig.java springmvc配置文件