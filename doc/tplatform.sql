/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : tplatform

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-04-27 15:30:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for b_member
-- ----------------------------
DROP TABLE IF EXISTS `b_member`;
CREATE TABLE `b_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `useremail` varchar(100) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `appellation` int(11) DEFAULT NULL,
  `fullname` varchar(30) DEFAULT NULL,
  `job` varchar(50) DEFAULT NULL,
  `telphone` varchar(15) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `address_a` varchar(100) DEFAULT NULL,
  `address_b` varchar(100) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `city` varchar(15) DEFAULT NULL,
  `zipcode` varchar(8) DEFAULT NULL,
  `country` varchar(8) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifyTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `operator` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`useremail`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_member
-- ----------------------------

-- ----------------------------
-- Table structure for sys_auth_organ
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_organ`;
CREATE TABLE `sys_auth_organ` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(10) DEFAULT NULL COMMENT '机构简称',
  `organ_code` varchar(16) NOT NULL COMMENT '机构编码',
  `organ_name` varchar(32) NOT NULL COMMENT '机构全称',
  `organ_type` varchar(6) NOT NULL COMMENT '机构类型',
  `parent_code` varchar(16) NOT NULL COMMENT '父级节点',
  `sort` int(2) NOT NULL COMMENT '排序号',
  `is_last` enum('') NOT NULL COMMENT '是否末级',
  `hierarchy` varchar(128) NOT NULL COMMENT '组织层级',
  `operator` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `status` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ckmdmw26b3dikk08vljw5ryrs` (`organ_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织信息表';

-- ----------------------------
-- Records of sys_auth_organ
-- ----------------------------

-- ----------------------------
-- Table structure for sys_auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_resource`;
CREATE TABLE `sys_auth_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon` varchar(16) CHARACTER SET utf8mb4 DEFAULT NULL,
  `name` varchar(16) NOT NULL,
  `pid` varchar(16) NOT NULL,
  `permission` varchar(16) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` varchar(8) NOT NULL,
  `type` varchar(4) NOT NULL,
  `action` varchar(255) NOT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `operator` varchar(32) DEFAULT NULL,
  `hierarchy` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_auth_resource
-- ----------------------------
INSERT INTO `sys_auth_resource` VALUES ('1', 'gear', '系统管理', '0', null, '91', 'VALID', 'MENU', '', '1464769959482', 'superadmin', '/0');
INSERT INTO `sys_auth_resource` VALUES ('2', 'tag', '权限管理', '0', null, '92', 'VALID', 'MENU', '', '1464769959482', 'superadmin', '/0');
INSERT INTO `sys_auth_resource` VALUES ('3', 'commenting-o', '消息管理', '0', null, '93', 'VALID', 'MENU', '', '1464769959482', 'superadmin', '/0');
INSERT INTO `sys_auth_resource` VALUES ('4', 'sort-alpha-asc', '字典管理', '1', null, '14', 'VALID', 'MENU', '/dictionary/list/o', '1464769959482', 'superadmin', '/0');
INSERT INTO `sys_auth_resource` VALUES ('11', 'tag', '系统信息', '1', null, '11', 'VALID', 'MENU', '/sysInfo/dashboard', '1464769959482', 'superadmin', '/0');
INSERT INTO `sys_auth_resource` VALUES ('12', 'camera', '日志管理', '1', null, '12', 'VALID', 'MENU', '/dictionary/list/mxjg', '1464769959482', 'superadmin', '/0');
INSERT INTO `sys_auth_resource` VALUES ('13', 'eye', '数据池监控', '1', null, '12', 'VALID', 'MENU', '/dictionary/list/o', '1464769959482', 'superadmin', '/0');
INSERT INTO `sys_auth_resource` VALUES ('14', 'list', '菜单管理', '2', null, '14', 'VALID', 'MENU', '/sysResource/list', '1464769959482', 'superadmin', '/0');
INSERT INTO `sys_auth_resource` VALUES ('15', 'user', '用户管理', '2', null, '2', 'VALID', 'MENU', '/sysUser/list', '1464769959482', 'superadmin', null);
INSERT INTO `sys_auth_resource` VALUES ('16', 'black-tie', '角色管理', '2', null, '3', 'VALID', 'MENU', '/sysRole/list', '1464774138154', 'superadmin', null);
INSERT INTO `sys_auth_resource` VALUES ('17', 'sitemap', '组织机构管理', '17', null, '4', 'VALID', 'MENU', '/sysOrgan/list', '1464774322242', 'superadmin', null);
INSERT INTO `sys_auth_resource` VALUES ('19', 'users', '会员管理', '0', null, '2', 'VALID', 'MENU', '/member/list', null, 'superadmin', null);

-- ----------------------------
-- Table structure for sys_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_role`;
CREATE TABLE `sys_auth_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `pid` bigint(11) unsigned zerofill NOT NULL,
  `name` varchar(16) NOT NULL COMMENT '角色名称',
  `icon` varchar(16) DEFAULT NULL,
  `hierarchy` varchar(128) DEFAULT NULL COMMENT '组织层级',
  `timestamp` bigint(20) DEFAULT NULL COMMENT '时间戳',
  `operator` varchar(32) DEFAULT NULL COMMENT '操作员',
  `status` varchar(8) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_auth_role
-- ----------------------------
INSERT INTO `sys_auth_role` VALUES ('1', '00000000000', '超级管理员', 'user', '/0', null, 'superadmin', 'VALID');
INSERT INTO `sys_auth_role` VALUES ('2', '00000000000', '管理员', 'user', '/0', null, 'superadmin', 'VALID');
INSERT INTO `sys_auth_role` VALUES ('3', '00000000000', '操作员', 'user', '/0', null, 'suepradmin', 'VALID');

-- ----------------------------
-- Table structure for sys_auth_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_role_resource`;
CREATE TABLE `sys_auth_role_resource` (
  `resource_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关系表';

-- ----------------------------
-- Records of sys_auth_role_resource
-- ----------------------------
INSERT INTO `sys_auth_role_resource` VALUES ('1', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('2', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('3', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('4', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('10', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('11', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('12', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('13', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('14', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('15', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('16', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('17', '1');
INSERT INTO `sys_auth_role_resource` VALUES ('19', '2');
INSERT INTO `sys_auth_role_resource` VALUES ('20', '2');
INSERT INTO `sys_auth_role_resource` VALUES ('21', '2');
INSERT INTO `sys_auth_role_resource` VALUES ('19', '3');
INSERT INTO `sys_auth_role_resource` VALUES ('20', '3');
INSERT INTO `sys_auth_role_resource` VALUES ('21', '3');
INSERT INTO `sys_auth_role_resource` VALUES ('1', '2');
INSERT INTO `sys_auth_role_resource` VALUES ('4', '2');

-- ----------------------------
-- Table structure for sys_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_user`;
CREATE TABLE `sys_auth_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `timestamp` bigint(16) NOT NULL COMMENT '时间戳',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `salt` varchar(32) DEFAULT NULL COMMENT '加密盐值',
  `status` varchar(8) NOT NULL COMMENT '状态',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `operator` varchar(32) DEFAULT NULL COMMENT '操作人',
  `nickname` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL,
  `hierarchy` varchar(128) DEFAULT NULL COMMENT '机构层级（数据权限）',
  `avatarUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kdemyqhyx1vqb74otf6cif0je` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='后台用户信息表';

-- ----------------------------
-- Records of sys_auth_user
-- ----------------------------
INSERT INTO `sys_auth_user` VALUES ('1', '1464769959482', '', null, '123456', null, 'VALID', 'superadmin', 'superadmin', '超级管理员', '/0', 'avatar3');
INSERT INTO `sys_auth_user` VALUES ('2', '1464769959482', '', null, '123456', null, 'VALID', 'admin', 'superadmin', '管理员', '/0', 'avatar3');
INSERT INTO `sys_auth_user` VALUES ('3', '1464769959482', '', null, '123456', null, 'VALID', 'test', 'superadmin', '操作员', '/0', 'avatar3');

-- ----------------------------
-- Table structure for sys_auth_user_organ
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_user_organ`;
CREATE TABLE `sys_auth_user_organ` (
  `organ_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`organ_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_auth_user_organ
-- ----------------------------

-- ----------------------------
-- Table structure for sys_auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth_user_role`;
CREATE TABLE `sys_auth_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  UNIQUE KEY `UK_f97haas0k1lb5jp4na07vq4nj` (`role_id`) USING BTREE,
  CONSTRAINT `sys_auth_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_auth_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_auth_user_role
-- ----------------------------
INSERT INTO `sys_auth_user_role` VALUES ('1', '1');
INSERT INTO `sys_auth_user_role` VALUES ('2', '2');
INSERT INTO `sys_auth_user_role` VALUES ('3', '3');

-- ----------------------------
-- Table structure for sys_conf
-- ----------------------------
DROP TABLE IF EXISTS `sys_conf`;
CREATE TABLE `sys_conf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `confKey` varchar(32) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `val` varchar(100) DEFAULT NULL,
  `operator` varchar(32) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `hierarchy` varchar(128) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s7nrsuyi0ntlrbpm3fh21e921` (`confKey`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_conf
-- ----------------------------

-- ----------------------------
-- Table structure for sys_df
-- ----------------------------
DROP TABLE IF EXISTS `sys_df`;
CREATE TABLE `sys_df` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `operator` varchar(32) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `formId` varchar(32) DEFAULT NULL COMMENT '表单ID',
  `zhName` varchar(32) DEFAULT NULL COMMENT '中文名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_df
-- ----------------------------

-- ----------------------------
-- Table structure for sys_df_element
-- ----------------------------
DROP TABLE IF EXISTS `sys_df_element`;
CREATE TABLE `sys_df_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `operator` varchar(32) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `formId` varchar(32) DEFAULT NULL COMMENT '表单ID',
  `eleType` varchar(32) DEFAULT NULL COMMENT '类型',
  `eleAttr` varchar(32) DEFAULT NULL COMMENT '属性',
  `eleName` varchar(32) DEFAULT NULL COMMENT '名称',
  `eleClass` varchar(32) DEFAULT NULL COMMENT 'class',
  `zhName` varchar(32) DEFAULT NULL COMMENT '中文名',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_df_element
-- ----------------------------

-- ----------------------------
-- Table structure for sys_df_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_df_record`;
CREATE TABLE `sys_df_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timestamp` bigint(11) DEFAULT NULL,
  `operator` varchar(32) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `formId` varchar(32) DEFAULT NULL COMMENT '表单ID',
  `recordId` varchar(64) DEFAULT NULL COMMENT '记录ID',
  `eleName` varchar(32) DEFAULT NULL COMMENT '元素名',
  `eleValue` varchar(255) DEFAULT NULL COMMENT '元素值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2394 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_df_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `dicTypeId` int(20) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `zhName` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `operator` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES ('1', null, '1', 'VALID', '1', '1', '品牌公益类', null, null);
INSERT INTO `sys_dictionary` VALUES ('2', null, '2', 'VALID', '1', '2', '品牌公益类2', null, null);
INSERT INTO `sys_dictionary` VALUES ('3', null, '1', 'VALID', '2', '1', '先生', null, null);
INSERT INTO `sys_dictionary` VALUES ('4', null, '2', 'VALID', '2', '2', '女士', null, null);
INSERT INTO `sys_dictionary` VALUES ('5', null, '0', 'VALID', '3', '0', '无', null, null);
INSERT INTO `sys_dictionary` VALUES ('6', null, '1', 'VALID', '3', '1', '价格变动', null, null);
INSERT INTO `sys_dictionary` VALUES ('7', null, '2', 'VALID', '3', '2', '优惠券', null, null);
INSERT INTO `sys_dictionary` VALUES ('8', null, '3', 'VALID', '3', '3', '分销', null, null);
INSERT INTO `sys_dictionary` VALUES ('9', null, '4', 'VALID', '3', '4', 'CRM/忠诚计划', null, null);
INSERT INTO `sys_dictionary` VALUES ('10', null, '5', 'VALID', '3', '5', '赠品/试用装', null, null);
INSERT INTO `sys_dictionary` VALUES ('11', null, '6', 'VALID', '3', '6', '其他在此运动同时的营销举措', null, null);
INSERT INTO `sys_dictionary` VALUES ('12', null, '7', 'VALID', '3', '_other', '其他', null, null);
INSERT INTO `sys_dictionary` VALUES ('13', null, '1', 'VALID', '4', '1', '较少', null, null);
INSERT INTO `sys_dictionary` VALUES ('14', null, '2', 'VALID', '4', '2', '相近', null, null);
INSERT INTO `sys_dictionary` VALUES ('15', null, '3', 'VALID', '4', '3', '较多', null, null);
INSERT INTO `sys_dictionary` VALUES ('16', null, '1', 'VALID', '5', '1', '减少', null, null);
INSERT INTO `sys_dictionary` VALUES ('17', null, '2', 'VALID', '5', '2', '持平', null, null);
INSERT INTO `sys_dictionary` VALUES ('18', null, '3', 'VALID', '5', '3', '增多', null, null);
INSERT INTO `sys_dictionary` VALUES ('19', null, '4', 'VALID', '5', '4', '无法提供', null, null);
INSERT INTO `sys_dictionary` VALUES ('20', null, '1', 'VALID', '6', '1', '0元', null, null);
INSERT INTO `sys_dictionary` VALUES ('21', null, '2', 'VALID', '6', '2', '100万以内', null, null);
INSERT INTO `sys_dictionary` VALUES ('22', null, '3', 'VALID', '6', '3', '101万-300万元', null, null);
INSERT INTO `sys_dictionary` VALUES ('23', null, '4', 'VALID', '6', '4', '301万 - 600万元', null, null);
INSERT INTO `sys_dictionary` VALUES ('24', null, '5', 'VALID', '6', '5', '601万 - 1200万元', null, null);
INSERT INTO `sys_dictionary` VALUES ('25', null, '6', 'VALID', '6', '6', '1201万 - 3000万元', null, null);
INSERT INTO `sys_dictionary` VALUES ('26', null, '7', 'VALID', '6', '7', '3001万 - 5000万元', null, null);
INSERT INTO `sys_dictionary` VALUES ('27', null, '8', 'VALID', '6', '8', '5000万 – 1亿元', null, null);
INSERT INTO `sys_dictionary` VALUES ('28', null, '9', 'VALID', '6', '9', '1亿 – 2亿元', null, null);
INSERT INTO `sys_dictionary` VALUES ('29', null, '10', 'VALID', '6', '10', '2亿 – 3亿元', null, null);
INSERT INTO `sys_dictionary` VALUES ('30', null, '11', 'VALID', '6', '11', '3亿元以上', null, null);
INSERT INTO `sys_dictionary` VALUES ('31', null, '1', 'VALID', '7', 'AU', '机动车-交通工具类', null, null);
INSERT INTO `sys_dictionary` VALUES ('32', null, '3', 'VALID', '7', 'PT', '交通服务类', null, null);
INSERT INTO `sys_dictionary` VALUES ('33', null, '4', 'VALID', '7', 'BE', '美容类', null, null);
INSERT INTO `sys_dictionary` VALUES ('34', null, '5', 'VALID', '7', 'PC', '个人护理类', null, null);
INSERT INTO `sys_dictionary` VALUES ('35', null, '6', 'VALID', '7', 'BA', '饮料-酒水类', null, null);
INSERT INTO `sys_dictionary` VALUES ('36', null, '7', 'VALID', '7', 'BC', '饮料-非酒水-碳酸类', null, null);
INSERT INTO `sys_dictionary` VALUES ('37', null, '8', 'VALID', '7', 'BN', '饮料-非酒水-非碳酸类', null, null);
INSERT INTO `sys_dictionary` VALUES ('38', null, '9', 'VALID', '7', 'PF', '包装食品、零食甜点类', null, null);
INSERT INTO `sys_dictionary` VALUES ('39', null, '10', 'VALID', '7', 'RS', '餐饮类', null, null);
INSERT INTO `sys_dictionary` VALUES ('40', null, '11', 'VALID', '7', 'RL', '零售类', null, null);
INSERT INTO `sys_dictionary` VALUES ('41', null, '13', 'VALID', '7', 'EL', '电子产品类', null, null);
INSERT INTO `sys_dictionary` VALUES ('42', null, '14', 'VALID', '7', 'CU', '文化与艺术类', null, null);
INSERT INTO `sys_dictionary` VALUES ('43', null, '15', 'VALID', '7', 'EA', '娱乐与运动类', null, null);
INSERT INTO `sys_dictionary` VALUES ('44', null, '16', 'VALID', '7', 'FA', '时尚风格与休闲生活类', null, null);
INSERT INTO `sys_dictionary` VALUES ('45', null, '17', 'VALID', '7', 'HC', '医疗保健类', null, null);
INSERT INTO `sys_dictionary` VALUES ('46', null, '18', 'VALID', '7', 'EN', '能量、营养与健康类', null, null);
INSERT INTO `sys_dictionary` VALUES ('47', null, '19', 'VALID', '7', 'HS', '家电与家居用品类', null, null);
INSERT INTO `sys_dictionary` VALUES ('48', null, '20', 'VALID', '7', 'AI', '农业、工业与建筑类', null, null);
INSERT INTO `sys_dictionary` VALUES ('49', null, '21', 'VALID', '7', 'IT', '网络、电信产品与服务类', null, null);
INSERT INTO `sys_dictionary` VALUES ('50', null, '22', 'VALID', '7', 'MN', '媒体和娱乐公司类', null, null);
INSERT INTO `sys_dictionary` VALUES ('51', null, '23', 'VALID', '7', 'OD', '办公&速递产品与服务类', null, null);
INSERT INTO `sys_dictionary` VALUES ('52', null, '24', 'VALID', '7', 'FN', '金融产品与服务类', null, null);
INSERT INTO `sys_dictionary` VALUES ('53', null, '25', 'VALID', '7', 'TR', '旅游观光类', null, null);
INSERT INTO `sys_dictionary` VALUES ('54', null, '26', 'VALID', '7', 'RT', '房地产类', null, null);
INSERT INTO `sys_dictionary` VALUES ('55', null, '27', 'VALID', '7', 'GV', '政府、机构类', null, null);
INSERT INTO `sys_dictionary` VALUES ('56', null, '1', 'VALID', '8', 'GB', '公益类-品牌公益类', null, null);
INSERT INTO `sys_dictionary` VALUES ('57', null, '3', 'VALID', '8', 'MI', '媒体类-媒体创意类', null, null);
INSERT INTO `sys_dictionary` VALUES ('58', null, '4', 'VALID', '8', 'MV', '媒体类-媒体创新类', null, null);
INSERT INTO `sys_dictionary` VALUES ('59', null, '5', 'VALID', '8', 'SM', '媒体类-单一媒体活化类', null, null);
INSERT INTO `sys_dictionary` VALUES ('60', null, '6', 'VALID', '8', 'SP', '小预算类-产品类', null, null);
INSERT INTO `sys_dictionary` VALUES ('61', null, '7', 'VALID', '8', 'SS', '小预算类-服务类', null, null);
INSERT INTO `sys_dictionary` VALUES ('62', null, '8', 'VALID', '8', 'SU', '持续成功类', null, null);
INSERT INTO `sys_dictionary` VALUES ('63', null, '9', 'VALID', '8', 'ST', '短效传播类', null, null);
INSERT INTO `sys_dictionary` VALUES ('64', null, '10', 'VALID', '8', 'SI', '单一影响互动传播', null, null);
INSERT INTO `sys_dictionary` VALUES ('65', null, '11', 'VALID', '8', 'YC', '青年营销类-儿童类', null, null);
INSERT INTO `sys_dictionary` VALUES ('66', null, '12', 'VALID', '8', 'YT', '青年营销类-青少年与青年类', null, null);
INSERT INTO `sys_dictionary` VALUES ('67', null, '13', 'VALID', '8', 'BE', '品牌体验类', null, null);
INSERT INTO `sys_dictionary` VALUES ('68', null, '14', 'VALID', '8', 'BT', 'B2B类', null, null);
INSERT INTO `sys_dictionary` VALUES ('69', null, '15', 'VALID', '8', 'SE', '赞助和事件营销类', null, null);
INSERT INTO `sys_dictionary` VALUES ('70', null, '16', 'VALID', '8', 'NP', '新产品与服务类', null, null);
INSERT INTO `sys_dictionary` VALUES ('71', null, '17', 'VALID', '8', 'LT', '下沉市场类', null, null);
INSERT INTO `sys_dictionary` VALUES ('72', null, '18', 'VALID', '8', 'DG', '以弱胜强类', null, null);
INSERT INTO `sys_dictionary` VALUES ('73', null, '19', 'VALID', '8', 'RT', '即效营销类', null, null);
INSERT INTO `sys_dictionary` VALUES ('74', null, '20', 'VALID', '8', 'SR', '单一零售商类', null, null);
INSERT INTO `sys_dictionary` VALUES ('75', null, '21', 'VALID', '8', 'SA', '季节营销类', null, null);
INSERT INTO `sys_dictionary` VALUES ('76', null, '22', 'VALID', '8', 'BC', '品牌内容类', null, null);
INSERT INTO `sys_dictionary` VALUES ('77', null, '1', 'VALID', '9', '1', '无', null, null);
INSERT INTO `sys_dictionary` VALUES ('78', null, '2', 'VALID', '9', '2', 'Bates CHI & Partners', null, null);
INSERT INTO `sys_dictionary` VALUES ('79', null, '3', 'VALID', '9', '3', 'BBDO', null, null);
INSERT INTO `sys_dictionary` VALUES ('80', null, '4', 'VALID', '9', '4', 'BBH', null, null);
INSERT INTO `sys_dictionary` VALUES ('81', null, '5', 'VALID', '9', '5', 'BlueFocus Advertising', null, null);
INSERT INTO `sys_dictionary` VALUES ('82', null, '6', 'VALID', '9', '6', 'BlueFocus Digital', null, null);
INSERT INTO `sys_dictionary` VALUES ('83', null, '7', 'VALID', '9', '7', 'BlueFocus Marketing Services', null, null);
INSERT INTO `sys_dictionary` VALUES ('84', null, '8', 'VALID', '9', '8', 'BlueFocus PR', null, null);
INSERT INTO `sys_dictionary` VALUES ('85', null, '9', 'VALID', '9', '9', 'Calling', null, null);
INSERT INTO `sys_dictionary` VALUES ('86', null, '10', 'VALID', '9', '10', 'Cheil Greater China', null, null);
INSERT INTO `sys_dictionary` VALUES ('87', null, '11', 'VALID', '9', '11', 'CMG', null, null);
INSERT INTO `sys_dictionary` VALUES ('88', null, '12', 'VALID', '9', '12', 'DAIKO Advertising', null, null);
INSERT INTO `sys_dictionary` VALUES ('89', null, '13', 'VALID', '9', '13', 'DDB', null, null);
INSERT INTO `sys_dictionary` VALUES ('90', null, '14', 'VALID', '9', '14', 'DeepFocus', null, null);
INSERT INTO `sys_dictionary` VALUES ('91', null, '15', 'VALID', '9', '15', 'Dentsu Aegis Network', null, null);
INSERT INTO `sys_dictionary` VALUES ('92', null, '16', 'VALID', '9', '16', 'Dentsu Inc.', null, null);
INSERT INTO `sys_dictionary` VALUES ('93', null, '17', 'VALID', '9', '17', 'DigitasLBi', null, null);
INSERT INTO `sys_dictionary` VALUES ('94', null, '18', 'VALID', '9', '18', 'DNA', null, null);
INSERT INTO `sys_dictionary` VALUES ('95', null, '19', 'VALID', '9', '19', 'FCB', null, null);
INSERT INTO `sys_dictionary` VALUES ('96', null, '20', 'VALID', '9', '20', 'Grey Group', null, null);
INSERT INTO `sys_dictionary` VALUES ('97', null, '21', 'VALID', '9', '21', 'GroupM', null, null);
INSERT INTO `sys_dictionary` VALUES ('98', null, '22', 'VALID', '9', '22', 'Hakuhodo', null, null);
INSERT INTO `sys_dictionary` VALUES ('99', null, '23', 'VALID', '9', '23', 'HAKUHODO DY Media Partners', null, null);
INSERT INTO `sys_dictionary` VALUES ('100', null, '24', 'VALID', '9', '24', 'Havas Creative Group', null, null);
INSERT INTO `sys_dictionary` VALUES ('101', null, '25', 'VALID', '9', '25', 'Havas Media Group', null, null);
INSERT INTO `sys_dictionary` VALUES ('102', null, '26', 'VALID', '9', '26', 'IPG Mediabrands', null, null);
INSERT INTO `sys_dictionary` VALUES ('103', null, '27', 'VALID', '9', '27', 'Ipsos', null, null);
INSERT INTO `sys_dictionary` VALUES ('104', null, '28', 'VALID', '9', '28', 'J.Walter Thompson', null, null);
INSERT INTO `sys_dictionary` VALUES ('105', null, '29', 'VALID', '9', '29', 'Kantar Group', null, null);
INSERT INTO `sys_dictionary` VALUES ('106', null, '30', 'VALID', '9', '30', 'KBS', null, null);
INSERT INTO `sys_dictionary` VALUES ('107', null, '31', 'VALID', '9', '31', 'Leo Burnett Group', null, null);
INSERT INTO `sys_dictionary` VALUES ('108', null, '32', 'VALID', '9', '32', 'LEO Digital Network', null, null);
INSERT INTO `sys_dictionary` VALUES ('109', null, '33', 'VALID', '9', '33', 'McCann Worldgroup', null, null);
INSERT INTO `sys_dictionary` VALUES ('110', null, '34', 'VALID', '9', '34', 'MDC PARTNERS', null, null);
INSERT INTO `sys_dictionary` VALUES ('111', null, '35', 'VALID', '9', '35', 'MSLGROUP Global', null, null);
INSERT INTO `sys_dictionary` VALUES ('112', null, '36', 'VALID', '9', '36', 'MullenLowe Group', null, null);
INSERT INTO `sys_dictionary` VALUES ('113', null, '37', 'VALID', '9', '37', 'Ogilvy Group', null, null);
INSERT INTO `sys_dictionary` VALUES ('114', null, '38', 'VALID', '9', '38', 'OMG', null, null);
INSERT INTO `sys_dictionary` VALUES ('115', null, '39', 'VALID', '9', '39', 'Publicis Worldwide', null, null);
INSERT INTO `sys_dictionary` VALUES ('116', null, '40', 'VALID', '9', '40', 'Razorfish Global', null, null);
INSERT INTO `sys_dictionary` VALUES ('117', null, '41', 'VALID', '9', '41', 'Re:Sources', null, null);
INSERT INTO `sys_dictionary` VALUES ('118', null, '42', 'VALID', '9', '42', 'RFI', null, null);
INSERT INTO `sys_dictionary` VALUES ('119', null, '43', 'VALID', '9', '43', 'Saatchi & Saatchi Worldwide', null, null);
INSERT INTO `sys_dictionary` VALUES ('120', null, '44', 'VALID', '9', '44', 'Starcom MediaVest Group', null, null);
INSERT INTO `sys_dictionary` VALUES ('121', null, '45', 'VALID', '9', '45', 'Synovate', null, null);
INSERT INTO `sys_dictionary` VALUES ('122', null, '46', 'VALID', '9', '46', 'TBWA', null, null);
INSERT INTO `sys_dictionary` VALUES ('123', null, '47', 'VALID', '9', '47', 'Tenth Avenue', null, null);
INSERT INTO `sys_dictionary` VALUES ('124', null, '48', 'VALID', '9', '48', 'Text 100', null, null);
INSERT INTO `sys_dictionary` VALUES ('125', null, '49', 'VALID', '9', '49', 'Thunder Communications', null, null);
INSERT INTO `sys_dictionary` VALUES ('126', null, '50', 'VALID', '9', '50', 'VivaKi', null, null);
INSERT INTO `sys_dictionary` VALUES ('127', null, '51', 'VALID', '9', '51', 'Y&R Group', null, null);
INSERT INTO `sys_dictionary` VALUES ('128', null, '52', 'VALID', '9', '52', 'YOMIKO Advertising', null, null);
INSERT INTO `sys_dictionary` VALUES ('129', null, '53', 'VALID', '9', '53', 'ZenithOptimedia Group', null, null);
INSERT INTO `sys_dictionary` VALUES ('130', null, '99', 'VALID', '9', '99', 'Others', null, null);
INSERT INTO `sys_dictionary` VALUES ('131', null, '1', 'VALID', '10', '1', '无', null, null);
INSERT INTO `sys_dictionary` VALUES ('132', null, '2', 'VALID', '10', '2', 'ACXIOM', null, null);
INSERT INTO `sys_dictionary` VALUES ('133', null, '3', 'VALID', '10', '3', 'APCO', null, null);
INSERT INTO `sys_dictionary` VALUES ('134', null, '4', 'VALID', '10', '4', 'ASATSU-DK', null, null);
INSERT INTO `sys_dictionary` VALUES ('135', null, '5', 'VALID', '10', '5', 'BAC GROUP', null, null);
INSERT INTO `sys_dictionary` VALUES ('136', null, '6', 'VALID', '10', '6', 'BLUEFOCUS', null, null);
INSERT INTO `sys_dictionary` VALUES ('137', null, '7', 'VALID', '10', '7', 'CHARM', null, null);
INSERT INTO `sys_dictionary` VALUES ('138', null, '8', 'VALID', '10', '8', 'CHEIL', null, null);
INSERT INTO `sys_dictionary` VALUES ('139', null, '9', 'VALID', '10', '9', 'DENTSU AEGIS NETWORK', null, null);
INSERT INTO `sys_dictionary` VALUES ('140', null, '10', 'VALID', '10', '10', 'DMG', null, null);
INSERT INTO `sys_dictionary` VALUES ('141', null, '11', 'VALID', '10', '11', 'EDELMAN', null, null);
INSERT INTO `sys_dictionary` VALUES ('142', null, '12', 'VALID', '10', '12', 'ENGINE', null, null);
INSERT INTO `sys_dictionary` VALUES ('143', null, '13', 'VALID', '10', '13', 'EPSILON', null, null);
INSERT INTO `sys_dictionary` VALUES ('144', null, '14', 'VALID', '10', '14', 'FRED & FARID', null, null);
INSERT INTO `sys_dictionary` VALUES ('145', null, '15', 'VALID', '10', '15', 'GIMC', null, null);
INSERT INTO `sys_dictionary` VALUES ('146', null, '16', 'VALID', '10', '16', 'HAKUHODO DY', null, null);
INSERT INTO `sys_dictionary` VALUES ('147', null, '17', 'VALID', '10', '17', 'HAVAS', null, null);
INSERT INTO `sys_dictionary` VALUES ('148', null, '18', 'VALID', '10', '18', 'ICLP', null, null);
INSERT INTO `sys_dictionary` VALUES ('149', null, '19', 'VALID', '10', '19', 'INNOCEAN', null, null);
INSERT INTO `sys_dictionary` VALUES ('150', null, '20', 'VALID', '10', '20', 'IPG', null, null);
INSERT INTO `sys_dictionary` VALUES ('151', null, '21', 'VALID', '10', '21', 'IPSOS', null, null);
INSERT INTO `sys_dictionary` VALUES ('152', null, '22', 'VALID', '10', '22', 'JUNG VON MATT', null, null);
INSERT INTO `sys_dictionary` VALUES ('153', null, '23', 'VALID', '10', '23', 'LEAGAS DELANEY', null, null);
INSERT INTO `sys_dictionary` VALUES ('154', null, '24', 'VALID', '10', '24', 'LEO GROUP', null, null);
INSERT INTO `sys_dictionary` VALUES ('155', null, '25', 'VALID', '10', '25', 'M&C SAATCHI', null, null);
INSERT INTO `sys_dictionary` VALUES ('156', null, '26', 'VALID', '10', '26', 'MDC PARTNERS', null, null);
INSERT INTO `sys_dictionary` VALUES ('157', null, '27', 'VALID', '10', '27', 'NEXT FIFTEEN', null, null);
INSERT INTO `sys_dictionary` VALUES ('158', null, '28', 'VALID', '10', '28', 'NIELSEN', null, null);
INSERT INTO `sys_dictionary` VALUES ('159', null, '29', 'VALID', '10', '29', 'OMNICOM', null, null);
INSERT INTO `sys_dictionary` VALUES ('160', null, '30', 'VALID', '10', '30', 'PUBLICIS GROUPE', null, null);
INSERT INTO `sys_dictionary` VALUES ('161', null, '31', 'VALID', '10', '31', 'RACEPOINT', null, null);
INSERT INTO `sys_dictionary` VALUES ('162', null, '32', 'VALID', '10', '32', 'RUDER FINN', null, null);
INSERT INTO `sys_dictionary` VALUES ('163', null, '33', 'VALID', '10', '33', 'SH-ADV', null, null);
INSERT INTO `sys_dictionary` VALUES ('164', null, '34', 'VALID', '10', '34', 'SIMEI', null, null);
INSERT INTO `sys_dictionary` VALUES ('165', null, '35', 'VALID', '10', '35', 'SPEARHEAD', null, null);
INSERT INTO `sys_dictionary` VALUES ('166', null, '36', 'VALID', '10', '36', 'THE HOFFMAN AGENCY', null, null);
INSERT INTO `sys_dictionary` VALUES ('167', null, '37', 'VALID', '10', '37', 'WAGGENER EDSTROM', null, null);
INSERT INTO `sys_dictionary` VALUES ('168', null, '38', 'VALID', '10', '38', 'WIEDEN+KENNEDY', null, null);
INSERT INTO `sys_dictionary` VALUES ('169', null, '39', 'VALID', '10', '39', 'WPP', null, null);
INSERT INTO `sys_dictionary` VALUES ('170', null, '99', 'VALID', '10', '99', 'Others', null, null);
INSERT INTO `sys_dictionary` VALUES ('173', null, '1', 'VALID', '11', '1', '广告设计费', null, null);
INSERT INTO `sys_dictionary` VALUES ('174', null, '2', 'VALID', '11', '2', '广告制作费', null, null);
INSERT INTO `sys_dictionary` VALUES ('175', null, '3', 'VALID', '11', '3', '广告发布费', null, null);
INSERT INTO `sys_dictionary` VALUES ('176', null, '4', 'VALID', '11', '4', '会务服务费', null, null);
INSERT INTO `sys_dictionary` VALUES ('177', null, '5', 'VALID', '11', '5', '活动策划费', null, null);
INSERT INTO `sys_dictionary` VALUES ('178', null, '6', 'VALID', '11', '6', '信息咨询费', null, null);
INSERT INTO `sys_dictionary` VALUES ('179', null, '7', 'VALID', '11', '7', '搭建运营费', null, null);
INSERT INTO `sys_dictionary` VALUES ('180', null, '8', 'VALID', '11', '8', '服务费', null, null);
INSERT INTO `sys_dictionary` VALUES ('182', null, '2', 'VALID', '8', 'GN', '公益类-非营利机构公益类', null, null);
INSERT INTO `sys_dictionary` VALUES ('183', null, '2', 'VALID', '7', 'AM', '机动车-汽车后市场类', null, null);
INSERT INTO `sys_dictionary` VALUES ('184', null, '12', 'VALID', '7', 'CR', '企业声誉与专业服务类', null, null);
INSERT INTO `sys_dictionary` VALUES ('185', null, '1', 'VALID', '12', 'payee', '上海勃飞文化传播有限公司', null, '2');
INSERT INTO `sys_dictionary` VALUES ('186', null, '2', 'VALID', '12', 'bankName', '平安银行上海五角场支行', null, '2');
INSERT INTO `sys_dictionary` VALUES ('187', null, '3', 'VALID', '12', 'bankAddress', '上海市黄兴路1855号', null, '2');
INSERT INTO `sys_dictionary` VALUES ('188', null, '4', 'VALID', '12', 'bankAccount', '11014696191000', null, '2');
INSERT INTO `sys_dictionary` VALUES ('189', null, '5', 'VALID', '12', 'swiftCode', 'SZDBCNBS', null, '2');
INSERT INTO `sys_dictionary` VALUES ('190', null, '6', 'VALID', '12', 'cnaps', '307290002241', null, '2');

-- ----------------------------
-- Table structure for sys_dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary_type`;
CREATE TABLE `sys_dictionary_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `operator` varchar(32) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL COMMENT '字典类型',
  `enName` varchar(32) DEFAULT NULL COMMENT '英文名',
  `zhName` varchar(16) DEFAULT NULL COMMENT '中文名',
  `menuType` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dictionary_type
-- ----------------------------
INSERT INTO `sys_dictionary_type` VALUES ('1', null, null, 'VALID', 'SELECT', 'proandsupport', '产品与服务类', 'o');
INSERT INTO `sys_dictionary_type` VALUES ('2', null, null, 'VALID', 'SELECT', 'projectTitle', '称谓', 'o');
INSERT INTO `sys_dictionary_type` VALUES ('3', null, null, 'VALID', 'CHECKBOX', 'plan_mtfl_yxjc', '媒体附录 - 营销举措', 'o');
INSERT INTO `sys_dictionary_type` VALUES ('4', null, null, 'VALID', 'RADIO', 'plan_mtfl_dsbj', '媒体附录 - 对手比较', 'o');
INSERT INTO `sys_dictionary_type` VALUES ('5', null, null, 'VALID', 'RADIO', 'plan_mtfl_wnbj', '媒体附录 - 往年比较', 'o');
INSERT INTO `sys_dictionary_type` VALUES ('6', null, null, 'VALID', 'RADIO', 'plan_mtfl_zcze', '媒体附录 - 支付总额', 'o');
INSERT INTO `sys_dictionary_type` VALUES ('7', null, null, 'VALID', 'SELECT', 'caseTypeA', '参赛类别', 'o');
INSERT INTO `sys_dictionary_type` VALUES ('8', null, null, 'VALID', 'SELECT', 'caseTypeB', '参赛类别2', 'o');
INSERT INTO `sys_dictionary_type` VALUES ('9', null, null, 'VALID', 'SELECT', 'network', '代理公司网络', 'o');
INSERT INTO `sys_dictionary_type` VALUES ('10', null, null, 'VALID', 'SELECT', 'holdingGroup', '控股集团', 'o');
INSERT INTO `sys_dictionary_type` VALUES ('11', null, null, 'VALID', 'SELECT', 'invoice_type', '发票项目', 'o');
INSERT INTO `sys_dictionary_type` VALUES ('12', null, '2', 'VALID', 'RADIO', 'merchant_info', '商户信息', 'o');

-- ----------------------------
-- Table structure for sys_scheduler
-- ----------------------------
DROP TABLE IF EXISTS `sys_scheduler`;
CREATE TABLE `sys_scheduler` (
  `id` bigint(20) NOT NULL,
  `cronExpression` varchar(30) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `jobGroup` varchar(100) NOT NULL,
  `jobName` varchar(50) NOT NULL,
  `ower` varchar(10) NOT NULL,
  `status` varchar(2) NOT NULL,
  `strParam` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_scheduler
-- ----------------------------
