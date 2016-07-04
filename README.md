#TPlatform（T台秀）
##J2EE基础开发平台

## 开发环境
### gradle
### tomcat8 + jdk8
### lombok

## 构件简介
### facet 接口+实体, 为分布式提供。 (本次业务开发不需要在此构件中增加接口)
### service 业务实现类
### admin 后台管理系统, *.admin 控制层包
### webApp 前台系统,  *.web 控制层包

## 包简介
### org.tplatform 基础开发平台

#### *.模块名.entity 实体类
#### *.模块名.service 业务类
#### *.模块名.mapper mybatis映射文件接口
#### *.模块名.admin 后台控制类
#### *.模块名.web 前台控制类

## 关键配置文件简介
### db.properties 数据库连接信息配置文件
### DataSourceConfig.java 数据源(druid)
### WebAppConfig.java 替代web.xml
### SpringConfig.java spring配置文件
### SpringMvcConfig.java springmvc配置文件