/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : tplatform

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 03/23/2017 10:43:55 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_auth_organ`
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_organ`;
CREATE TABLE `sys_auth_organ` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `nickname` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `pCode` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `type` varchar(8) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `sys_auth_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_resource`;
CREATE TABLE `sys_auth_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `icon` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `type` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Records of `sys_auth_resource`
-- ----------------------------
BEGIN;
INSERT INTO `sys_auth_resource` VALUES ('1', '/main.html', 'tag', '首页', '0', '1', 'MENU', '1'), ('2', '', 'gear', '系统管理', '0', '91', 'MENU', '1'), ('3', '', 'tag', '权限管理', '0', '92', 'MENU', '1'), ('4', '/message/edit', 'commenting-o', '消息管理', '0', '50', 'MENU', '0'), ('5', '/dictionary/list/o', 'sort-alpha-asc', '字典管理', '2', '14', 'MENU', '1'), ('6', '/sysInfo/dashboard', 'tachometer', '系统信息', '2', '11', 'MENU', '1'), ('7', '/dictionary/list/mxjg', 'camera', '日志管理', '2', '12', 'MENU', '1'), ('8', '/sysInfo/druidStatView', 'eye', '数据池监控', '2', '12', 'MENU', '1'), ('9', '/sysResource/list', 'list', '菜单管理', '3', '14', 'MENU', '1'), ('10', '/sysUser/list', 'user', '用户管理', '3', '2', 'MENU', '1'), ('11', '/sysRole/list', 'black-tie', '角色管理', '3', '3', 'MENU', '1'), ('12', '/sysOrgan/list', 'sitemap', '组织机构管理', '17', '4', 'MENU', '1'), ('13', '/config/list', 'gear', '配置管理', '2', '15', 'MENU', '1'), ('14', '/sysOrgan/list', 'sitemap', '机构管理', '3', '4', 'MENU', '1'), ('15', '/doc/list', 'tag', '文章管理', '0', '3', 'MENU', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_auth_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_role`;
CREATE TABLE `sys_auth_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Records of `sys_auth_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_auth_role` VALUES ('1', 'user', '超级管理员', '0'), ('2', 'user', '管理员', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_auth_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_role_resource`;
CREATE TABLE `sys_auth_role_resource` (
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`),
  KEY `FKk8lt4k3rvkpclg6s2prtkaby3` (`resource_id`),
  CONSTRAINT `FKddtlfbq3vjcxb1gid2n9kn11a` FOREIGN KEY (`role_id`) REFERENCES `sys_auth_role` (`id`),
  CONSTRAINT `FKk8lt4k3rvkpclg6s2prtkaby3` FOREIGN KEY (`resource_id`) REFERENCES `sys_auth_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `sys_auth_role_resource`
-- ----------------------------
BEGIN;
INSERT INTO `sys_auth_role_resource` VALUES ('1', '1'), ('1', '2'), ('1', '3'), ('1', '4'), ('1', '5'), ('1', '6'), ('1', '7'), ('1', '8'), ('1', '9'), ('1', '10'), ('1', '11'), ('1', '12'), ('1', '13'), ('1', '14'), ('2', '15');
COMMIT;

-- ----------------------------
--  Table structure for `sys_auth_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_user`;
CREATE TABLE `sys_auth_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatarUrl` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `nickname` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Records of `sys_auth_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_auth_user` VALUES ('1', null, null, null, '超管', '123456', 'superadmin'), ('2', null, null, null, 'Admin', '123456', 'admin');
COMMIT;

-- ----------------------------
--  Table structure for `sys_auth_user_organ`
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_user_organ`;
CREATE TABLE `sys_auth_user_organ` (
  `user_id` bigint(20) NOT NULL,
  `organ_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`organ_id`),
  KEY `FK7pa21bmkvewirnpxfjjnhs14w` (`organ_id`),
  CONSTRAINT `FK7pa21bmkvewirnpxfjjnhs14w` FOREIGN KEY (`organ_id`) REFERENCES `sys_auth_organ` (`id`),
  CONSTRAINT `FKnllcn4ogrja441y3wv6ctt41d` FOREIGN KEY (`user_id`) REFERENCES `sys_auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `sys_auth_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_user_role`;
CREATE TABLE `sys_auth_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK7ji31jk11bbdnsgek8ksca1qc` (`role_id`),
  CONSTRAINT `FK6no5ekumv9gnqwi62u3bqkym` FOREIGN KEY (`user_id`) REFERENCES `sys_auth_user` (`id`),
  CONSTRAINT `FK7ji31jk11bbdnsgek8ksca1qc` FOREIGN KEY (`role_id`) REFERENCES `sys_auth_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Records of `sys_auth_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_auth_user_role` VALUES ('1', '1'), ('2', '2');
COMMIT;

-- ----------------------------
--  Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `confKey` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `pId` bigint(20) DEFAULT NULL,
  `remark` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `type` varchar(10) COLLATE utf8mb4_bin DEFAULT NULL,
  `val` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1r02nsyto93ayuasn7jtfk4ax` (`confKey`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Records of `sys_config`
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES ('1', 'ftpInfo', '服务器配置', null, null, null, 'FTP', ''), ('2', 'redisInfo', 'Redis服务器', null, null, null, 'Redis', ''), ('3', 'APP_NAME', '系统名称', null, null, null, 'Admin', 'TPlatform'), ('4', 'Email_Username', 'SMTP服务器登陆用户名', null, null, null, 'EMAIL', null), ('5', 'Email_Password', 'SMTP服务器登陆密码', null, null, null, 'EMAIL', null), ('6', 'FILE_DOMAIN', '域名', null, null, null, 'FILE_DM', null), ('7', 'redis.database', 'redis库', null, null, null, 'Redis', '8'), ('8', 'redis.maxIdle', 'redis配置', null, null, null, 'Redis', '300'), ('9', 'redis.maxWaitMillis', 'redis配置', null, null, null, 'Redis', '30000');
COMMIT;

-- ----------------------------
--  Table structure for `sys_df`
-- ----------------------------
DROP TABLE IF EXISTS `sys_df`;
CREATE TABLE `sys_df` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enName` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `zhName` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i3elt51rd8x938mhlkvsk3ftc` (`enName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `sys_df_element`
-- ----------------------------
DROP TABLE IF EXISTS `sys_df_element`;
CREATE TABLE `sys_df_element` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `eleAttr` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `eleClass` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `eleName` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `eleType` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `formId` bigint(20) NOT NULL,
  `remark` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `zhName` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKphsotqlhewi3j4stu0qu1ao4c` (`formId`),
  CONSTRAINT `FKphsotqlhewi3j4stu0qu1ao4c` FOREIGN KEY (`formId`) REFERENCES `sys_df` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `sys_df_record`
-- ----------------------------
DROP TABLE IF EXISTS `sys_df_record`;
CREATE TABLE `sys_df_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `eleId` bigint(20) NOT NULL,
  `eleValue` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `recordId` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkjucsl8df0c5jv8pqq5ia98f3` (`eleId`),
  CONSTRAINT `FKkjucsl8df0c5jv8pqq5ia98f3` FOREIGN KEY (`eleId`) REFERENCES `sys_df_element` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dicType` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `value` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `zhName` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `sys_dict_type`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `enName` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `menuType` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `zhName` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
--  Table structure for `t_doc`
-- ----------------------------
DROP TABLE IF EXISTS `t_doc`;
CREATE TABLE `t_doc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `context` longtext COLLATE utf8mb4_bin,
  `createTime` datetime DEFAULT NULL,
  `imgUrl` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `keyword` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `originAddress` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `summary` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `title` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `type` varchar(8) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
