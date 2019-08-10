
--管理员登录表与信息
CREATE TABLE zx_user (
	id int(11) NOT NULL AUTO_INCREMENT,
	username varchar(50) NOT NULL COMMENT '用户名',
	password varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
	email    varchar(50) DEFAULT NULL COMMENT '邮箱',
	phone    varchar(20) DEFAULT NULL COMMENT '手机号码',
	question varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  answer   varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  role int(4) NOT NULL COMMENT '角色0-管理员,1-员工操作',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
insert into zx_user values ('1','admin','E10ADC3949BA59ABBE56E057F20F883E','caidl95@163.com', '13662925808', '问题','答案', '0', now(),now());

--商品表
CREATE TABLE zx_product (
	id int NOT NULL AUTO_INCREMENT COMMENT '商品表id',
 	category_id int(11) NOT NULL COMMENT '分类id,对应category表的主键',
	name varchar(50) NOT NULL COMMENT '商品名称',
	subtitle varchar(200) DEFAULT NULL COMMENT '商品副标题',
	main_image varchar(500) DEFAULT NULL COMMENT '产品主图,url相对地址前缀',
	detail text DEFAULT NULL COMMENT '商品详情',
	price decimal(20,2) NOT NULL COMMENT '价格,单位-元保留两位小数',
	stock int(11) NOT NULL COMMENT '库存数量',
	status int(6) DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除',
	place int(6) DEFAULT '1' COMMENT '商品存放位置 1-在仓库 2-在店面',
	create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--商品类别
CREATE TABLE zx_category (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  parent_id int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  name varchar(50) DEFAULT NULL COMMENT '类别名称',
  status tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  sort_order int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=100032 DEFAULT CHARSET=utf8;

--客户信息
CREATE TABLE zx_client (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '客户信息表id',
  name varchar(50) NOT NULL COMMENT '客户名称',
  phone varchar(20) NOT NULL COMMENT '手机号码',
  email varchar(50) DEFAULT NULL COMMENT '邮箱',
  addr varchar(60) DEFAULT NULL COMMENT '所住地址',
  is_default int DEFAULT '0' COMMENT '有效状态 0- 有效 ，1-删除',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--销售记录
CREATE TABLE zx_sell (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '销售表Id',
  client_id int(11) DEFAULT NULL COMMENT '用户信息表id',
  pay_type int(10) DEFAULT NULL COMMENT '支付平台:1-支付宝,2-微信,3-现金',
  payment decimal(20,2) DEFAULT NULL COMMENT '实际付款金额',
  end_time datetime DEFAULT NULL COMMENT '交易完成时间',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

insert into zx_sell values ('111','1','1','100', now(), now(),now());

--销售商品表
CREATE TABLE zx_sell_product(
    id int(11) NOT NULL AUTO_INCREMENT COMMENT '销售商品表Id',
    sell_id int(11) DEFAULT NULL COMMENT '销售表id',
    category_id int(11) DEFAULT NULL COMMENT '分类id,对应category表的主键',
    product_id int(11) DEFAULT NULL COMMENT '商品Id',
    product_name varchar(50) DEFAULT NULL COMMENT '商品名称',
    price decimal(20,2) DEFAULT NULL COMMENT '单价',
    num int DEFAULT NULL COMMENT '数量',
    place int(6) DEFAULT '1' COMMENT '商品存放位置 1-在仓库 2-在店面',
    PRIMARY KEY (id)
 )ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;

  insert into zx_sell_product values ('1','111','100003','4', '小银砖', '100.00','5', '2');
  insert into zx_sell_product values ('2','111','100003','7', '七大茶山', '380.00', '5','2');


DROP TABLE IF EXISTS `dict_version`;
CREATE TABLE `dict_version` (
  `version_id` char(36) NOT NULL COMMENT '版本主键',
  `version_number` varchar(64) NOT NULL COMMENT '版本编号',
  `version_code` int(11) NOT NULL COMMENT '版本代号',
  `version_content` text DEFAULT NULL COMMENT '更新内容',
  `version_url` varchar(128) DEFAULT NULL COMMENT '访问地址',
  `version_path` varchar(128) DEFAULT NULL COMMENT '物理地址',
  `version_type` varchar(36) DEFAULT NULL COMMENT '字典值（andriod_user：安卓用户端，ios_user：苹果用户端）',
  `version_status` varchar(36) DEFAULT NULL COMMENT '版本状态（启用：version_status_enabled，停用：version_status_disable）被停用后强制要求下载最新版',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` char(36) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` char(36) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`version_id`),
  KEY `version_number_index` (`version_number`) USING BTREE,
  KEY `version_code_index` (`version_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='版本更新表';


DROP TABLE IF EXISTS `user_operation_log`;
CREATE TABLE `user_operation_log` (
  `operation_log_id` char(36) NOT NULL COMMENT '操作日志主键',
  `user_id` char(36) DEFAULT NULL COMMENT '用户主键',
  `menu_name` varchar(64) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(128) DEFAULT NULL COMMENT '菜单路径',
  `ip_address` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `request_type` varchar(10) DEFAULT NULL COMMENT '请求类型（GET，POST）',
  `date` date DEFAULT NULL COMMENT '日期',
  `time` time DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`operation_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

-- ----------------------------
-- Records of user_operation_log
-- ----------------------------
INSERT INTO `user_operation_log` VALUES ('001b96f8ebab4a1cbc0fd37979b1f9e6', 'admin', null, '/YiDaoServer/serviceMsgRecord/selectMessageWindow.do', '192.168.1.19', 'POST', '2019-05-20', '15:25:28');
INSERT INTO `user_operation_log` VALUES ('002228ba6aa24103a92e0811f8564a3f', 'admin', 'H5登录', '/YiDaoServer/user/loginByH5.do', '192.168.1.19', 'GET', '2019-05-20', '11:06:02');
INSERT INTO `user_operation_log` VALUES ('0039e07e44b240c38d922ca11937246a', 'customerService_zhang', null, '/YiDaoServer/schedule/getTodaySchedule.do', '192.168.1.4', 'POST', '2019-05-20', '14:07:40');
INSERT INTO `user_operation_log` VALUES ('00450e2568144cad9e6d11e7b69cd9a4', 'assistantDoctor_li', null, '/YiDaoServer/schedule/getTodaySchedule.do', '192.168.1.26', 'POST', '2019-05-20', '10:58:09');
INSERT INTO `user_operation_log` VALUES ('00781ef48ef341d489b906eb05ab0f1f', 'admin', null, '/YiDaoServer/user/selectUserListByGroup.do', '192.168.1.4', 'POST', '2019-05-20', '11:05:56');
INSERT INTO `user_operation_log` VALUES ('0094e5d4d084485488934ad7702a61b8', 'admin', null, '/YiDaoServer/serviceMsgRecord/selectMessageWindow.do', '192.168.1.19', 'POST', '2019-05-20', '11:44:48');
INSERT INTO `user_operation_log` VALUES ('009bfc15bcdf4a8a980f05db8b1ef62e', '3cf0038277c04cda9d8bc8201c3849ea', null, '/YiDaoServer/user/getFourList.do', '192.168.1.9', 'POST', '2019-05-20', '16:55:29');
INSERT INTO `user_operation_log` VALUES ('00ac13e5e21f471795d920ed0d257a42', 'assistantDoctor_li', null, '/YiDaoServer/user/selectUserInfo.do', '192.168.1.26', 'POST', '2019-05-20', '11:12:43');
INSERT INTO `user_operation_log` VALUES ('01012e6884e3415a9825e5c7a07ebde9', 'admin', null, '/YiDaoServer/caseHistory/queryArchives.do', '192.168.1.19', 'POST', '2019-05-20', '14:07:23');
INSERT INTO `user_operation_log` VALUES ('0139bc5c66a849f4a539c4d363a49f61', 'ba2356b66b424acb8e226538bfa6ef01', '发送验证码', '/YiDaoServer/sms/sendCode.do', '192.168.1.20', 'POST', '2019-05-20', '16:20:34');
INSERT INTO `user_operation_log` VALUES ('014661867fc34b2d8d82232f31b38e2a', 'admin', null, '/YiDaoServer/caseHistory/saveCaseHistory.do', '192.168.1.23', 'POST', '2019-05-20', '15:56:28');
INSERT INTO `user_operation_log` VALUES ('0147007d16d241ee9fd3ecf9d7aa81a7', 'admin', null, '/YiDaoServer/memberInfo/selectMemberInfoByReservation.do', '192.168.1.23', 'POST', '2019-05-20', '11:41:47');
INSERT INTO `user_operation_log` VALUES ('015c29055fee4c37b1f6344f0065bad8', 'admin', null, '/YiDaoServer/vipPackage/getSelectorList.do', '192.168.1.19', 'POST', '2019-05-20', '15:29:30');
INSERT INTO `user_operation_log` VALUES ('015ce4966f40491bb044795b254ab896', 'admin', null, '/YiDaoServer/dict/getDictByType.do', '192.168.1.4', 'POST', '2019-05-20', '11:34:29');
INSERT INTO `user_operation_log` VALUES ('016936475d564794bf96a7f2cf1a307d', '3cf0038277c04cda9d8bc8201c3849ea', 'jsonSdk签名', '/YiDaoServer/getAutographData.do', '192.168.1.4', 'POST', '2019-05-20', '11:24:05');
INSERT INTO `user_operation_log` VALUES ('018f7e2d17344d03a5a122c44d01de54', 'admin', null, '/YiDaoServer/vipBatch/opened.do', '192.168.1.4', 'POST', '2019-05-20', '11:18:19');
INSERT INTO `user_operation_log` VALUES ('01a2d84363694192a814b7f750baace3', 'admin', null, '/YiDaoServer/serviceMsgRecord/selectMessageWindow.do', '192.168.1.19', 'POST', '2019-05-20', '11:47:10');
INSERT INTO `user_operation_log` VALUES ('01a9fdffa5844134b9ec11ce221da2f3', '3cf0038277c04cda9d8bc8201c3849ea', 'jsonSdk签名', '/YiDaoServer/getAutographData.do', '192.168.1.9', 'POST', '2019-05-20', '17:01:54');
INSERT INTO `user_operation_log` VALUES ('01b2f2e974c24be09c7c61fe547b35b5', '3cf0038277c04cda9d8bc8201c3849ea', null, '/YiDaoServer/serviceMsgRecord/selectMessageRecordAllAboutMe.do', '192.168.1.9', 'POST', '2019-05-20', '11:07:58');
INSERT INTO `user_operation_log` VALUES ('01df50cef4e842cba0bc980fcab3eae4', '3cf0038277c04cda9d8bc8201c3849ea', null, '/YiDaoServer/schedule/selectAvailableDates.do', '192.168.1.9', 'POST', '2019-05-20', '16:44:10');
INSERT INTO `user_operation_log` VALUES ('01f3cf4cd60d41ee9be01c7ec1a61ec4', '3cf0038277c04cda9d8bc8201c3849ea', null, '/YiDaoServer/serviceRecord/createServiceRecord.do', '192.168.1.4', 'POST', '2019-05-20', '11:27:18');
INSERT INTO `user_operation_log` VALUES ('01fea76337db4f48be33da54cc766a38', 'admin', null, '/YiDaoServer/vipPackage/getSelectorList.do', '192.168.1.19', 'POST', '2019-05-20', '11:36:33');
INSERT INTO `user_operation_log` VALUES ('0213726a4b8d4788a5ecbc12a41a1b63', 'admin', null, '/YiDaoServer/vipBatch/selectVipCardList.do', '192.168.1.10', 'POST', '2019-05-29', '19:57:18');
INSERT INTO `user_operation_log` VALUES ('0219786a15394598b8d3fa9fff10406a', 'customerService_zhang', null, '/YiDaoServer/dict/getDictByType.do', '192.168.1.4', 'POST', '2019-05-20', '16:51:29');
INSERT INTO `user_operation_log` VALUES ('023e7c9436a049219010e1b7e3baff55', 'admin', null, '/YiDaoServer/physicalExamination/findPhysicalToday.do', '192.168.1.23', 'POST', '2019-05-20', '15:25:56');
INSERT INTO `user_operation_log` VALUES ('023f9caea2ef4a069b8bf378296aabac', 'assistantDoctor_li', null, '/YiDaoServer/schedule/getTodaySchedule.do', '192.168.1.26', 'POST', '2019-05-20', '11:06:02');
INSERT INTO `user_operation_log` VALUES ('0247c83e97364886833b5e9e270835e8', 'admin', null, '/YiDaoServer/dict/getDictByType.do', '192.168.1.19', 'POST', '2019-05-20', '14:13:37');
INSERT INTO `user_operation_log` VALUES ('025cd9d01f7741da94b3a709b1bf57cf', '1fb8c150fcb24dbdb6a5b7230c40a19e', null, '/YiDaoServer/schedule/getTodaySchedule.do', '192.168.1.26', 'POST', '2019-05-20', '14:29:12');
INSERT INTO `user_operation_log` VALUES ('02720a20b6b44ccbad38785601ccc792', '3cf0038277c04cda9d8bc8201c3849ea', null, '/YiDaoServer/serviceMsgRecord/selectMessageRecordAllAboutMe.do', '192.168.1.9', 'POST', '2019-05-20', '11:09:38');
INSERT INTO `user_operation_log` VALUES ('027cbb264acb40d49513122f772ece96', '3cf0038277c04cda9d8bc8201c3849ea', null, '/YiDaoServer/schedule/getTodayScheduleVTwo.do', '192.168.1.9', 'POST', '2019-05-20', '16:44:29');
INSERT INTO `user_operation_log` VALUES ('0285291d64eb4943a5772d512d506c59', 'admin', null, '/YiDaoServer/memberInfo/selectMemberInfoByReservation.do', '192.168.1.4', 'POST', '2019-05-20', '11:35:33');
INSERT INTO `user_operation_log` VALUES ('029bf27dc09544f597c95d9635d85429', '3cf0038277c04cda9d8bc8201c3849ea', 'jsonSdk签名', '/YiDaoServer/getAutographData.do', '192.168.1.4', 'POST', '2019-05-20', '11:19:44');
INSERT INTO `user_operation_log` VALUES ('02b1b70f73394c69bf2ca4b898a51cc3', 'admin', null, '/YiDaoServer/caseHistory/selectHistoryById.do', '192.168.1.19', 'POST', '2019-05-20', '13:43:51');
INSERT INTO `user_operation_log` VALUES ('02efa8f5e37246d88fdbcc3b3dd5cfe7', 'admin', null, '/YiDaoServer/vaccine/findVaccineList.do', '192.168.1.19', 'POST', '2019-05-20', '11:18:27');
INSERT INTO `user_operation_log` VALUES ('02f1b20ad5ed460da47f6de4d1c7d0d9', 'assistantDoctor_li', null, '/YiDaoServer/schedule/getNowScheduleInfo.do', '192.168.1.26', 'POST', '2019-05-20', '10:58:10');
INSERT INTO `user_operation_log` VALUES ('02f6644237eb43eca213ae3ac7cc2c61', '3cf0038277c04cda9d8bc8201c3849ea', null, '/YiDaoServer/physicalExamination/insertPhysicalExaminationVTwo.do', '192.168.1.9', 'POST', '2019-05-20', '16:56:07');
-- ----------------------------
-- Table structure for user_tokens
-- ----------------------------
DROP TABLE IF EXISTS `user_tokens`;
CREATE TABLE `user_tokens` (
  `token_id` char(36) NOT NULL COMMENT '主键',
  `user_id` char(36) NOT NULL COMMENT '用户主键',
  `role_id` char(36) DEFAULT NULL COMMENT '角色主键',
  `user_token` varchar(64) NOT NULL COMMENT '令牌',
  `token_status` varchar(36) NOT NULL COMMENT '令牌状态（启用token_status_enabled，停用token_status_disable）',
  `start_date` date NOT NULL COMMENT '令牌开始时间',
  `end_date` date NOT NULL COMMENT '令牌结束日期',
  PRIMARY KEY (`token_id`),
  KEY `AK_unique` (`user_token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户授权token认证';

-- ----------------------------
-- Records of user_tokens
-- ----------------------------
INSERT INTO `user_tokens` VALUES ('02be4ad512d64f33bd18e23f0f26758b', 'ba2356b66b424acb8e226538bfa6ef01', null, '12f99490c64ee52b6668bba615914c57', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('04344fd2e6f74d6ebaddaf390b750b16', '3cf0038277c04cda9d8bc8201c3849ea', null, 'a0bcbd306df39c7103949048bfd9484b', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('064024f7bb064f559c882bb56d672d4e', '3cf0038277c04cda9d8bc8201c3849ea', null, 'a0bcbd306df39c7103949048bfd9484b', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('0b95cddd587a490ab26ee668f878bca0', '3cf0038277c04cda9d8bc8201c3849ea', null, 'a0bcbd306df39c7103949048bfd9484b', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('0dbc7c105d43474a987913c82cfeebd2', '3cf0038277c04cda9d8bc8201c3849ea', null, 'a0bcbd306df39c7103949048bfd9484b', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('0dcf75df90b6476eae47233bbfda67a8', '3cf0038277c04cda9d8bc8201c3849ea', null, 'a0bcbd306df39c7103949048bfd9484b', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('0e3f63dcecfb4d4ab7be128a678802ce', '3cf0038277c04cda9d8bc8201c3849ea', null, 'a0bcbd306df39c7103949048bfd9484b', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('10971b6aeafe44dfb5ff939f42801a60', 'admin', null, '9311591824abad708a90521f98cd9886', 'token_status_disable', '2019-05-20', '2019-06-19');
INSERT INTO `user_tokens` VALUES ('1cb628bda01b4aa19c4c925cc7cc222a', '3cf0038277c04cda9d8bc8201c3849ea', null, 'a0bcbd306df39c7103949048bfd9484b', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('1e7e7d1b55e547bc92b6345caad225a0', 'admin', null, '1ffa3dd17646cba31c2fbb5c63db154b', 'token_status_disable', '2019-05-20', '2019-06-19');
INSERT INTO `user_tokens` VALUES ('1f896d26d9fb4f8d83acad2b7c49e62f', '3cf0038277c04cda9d8bc8201c3849ea', null, 'a0bcbd306df39c7103949048bfd9484b', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('1fa475539aba4d5893821da1b881129a', '3cf0038277c04cda9d8bc8201c3849ea', null, 'a0bcbd306df39c7103949048bfd9484b', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('200f4088bf54476b941166a3ed0b1a02', '3cf0038277c04cda9d8bc8201c3849ea', null, 'a0bcbd306df39c7103949048bfd9484b', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('23182a23727e42fbad4cadb50cafb6e0', 'ba2356b66b424acb8e226538bfa6ef01', null, '12f99490c64ee52b6668bba615914c57', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('233583f5478140caaf0b40837f242eda', '3cf0038277c04cda9d8bc8201c3849ea', null, 'a0bcbd306df39c7103949048bfd9484b', 'token_status_enabled', '2019-05-20', '2019-05-21');
INSERT INTO `user_tokens` VALUES ('23631e1639ab4d2e802e6617842d1634', 'admin', null, '3e237e472ccb105a7e0d83eaa0203401', 'token_status_disable', '2019-05-20', '2019-06-19');

-- ----------------------------
-- Table structure for vip_detail
-- ----------------------------
DROP TABLE IF EXISTS `vip_detail`;
CREATE TABLE `vip_detail` (
  `detail_id` char(36) NOT NULL COMMENT '详情主键',
  `card_id` char(36) DEFAULT NULL COMMENT '会员卡主键',
  `user_id` char(36) DEFAULT NULL COMMENT '用户主键',
  `item_id` char(36) DEFAULT NULL COMMENT '服务项主键',
  `residue_degree` int(11) DEFAULT NULL COMMENT '剩余服务次数（-1代表无限制）',
  PRIMARY KEY (`detail_id`),
  KEY `AK_unique` (`card_id`,`item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='vip会员卡详情';

-- ----------------------------
-- Records of vip_detail
-- ----------------------------
INSERT INTO `vip_detail` VALUES ('07b42f9e014045af858e016353a3d716', 'e756ea4b7aaa11e9a1151866da87c4c1', 'ab1965fe9cba480293de01e4717fdff5', 'consultation_channel', '-1');
INSERT INTO `vip_detail` VALUES ('0925434d669a465482a2b706ffa26b2d', 'a756ea8a7aaa11e9a1151866da87c4c1', '2ed4cdde8d0545149885b10d280fb86e', 'Imported_vaccines', '-1');
INSERT INTO `vip_detail` VALUES ('2c07855a85e045759129e247fdece9a1', 'e756ea4b7aaa11e9a1151866da87c4c1', 'ab1965fe9cba480293de01e4717fdff5', 'leading_medical', '3');
INSERT INTO `vip_detail` VALUES ('36472e6de698444b83d440fb035b25fa', 'a756ea8a7aaa11e9a1151866da87c4c1', '2ed4cdde8d0545149885b10d280fb86e', 'comfortable_treatment', '-1');
INSERT INTO `vip_detail` VALUES ('3a3f5f773426442d970086d5c66cccab', 'e756ea4b7aaa11e9a1151866da87c4c1', 'ab1965fe9cba480293de01e4717fdff5', 'health_hotline', '-1');
INSERT INTO `vip_detail` VALUES ('43159ba29a8540f18f0839e7060fa873', 'e756ea4b7aaa11e9a1151866da87c4c1', 'ab1965fe9cba480293de01e4717fdff5', 'health_record', '-1');

-- ----------------------------
-- Table structure for user_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `user_operation_log`;
CREATE TABLE `user_operation_log` (
  `operation_log_id` char(36) NOT NULL COMMENT '操作日志主键',
  `user_id` char(36) DEFAULT NULL COMMENT '用户主键',
  `menu_name` varchar(64) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(128) DEFAULT NULL COMMENT '菜单路径',
  `ip_address` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `request_type` varchar(10) DEFAULT NULL COMMENT '请求类型（GET，POST）',
  `date` date DEFAULT NULL COMMENT '日期',
  `time` time DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`operation_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作日志表';

-- ----------------------------
-- Records of user_operation_log
-- ----------------------------
INSERT INTO `user_operation_log` VALUES ('001b96f8ebab4a1cbc0fd37979b1f9e6', 'admin', null, '/YiDaoServer/serviceMsgRecord/selectMessageWindow.do', '192.168.1.19', 'POST', '2019-05-20', '15:25:28');
INSERT INTO `user_operation_log` VALUES ('002228ba6aa24103a92e0811f8564a3f', 'admin', 'H5登录', '/YiDaoServer/user/loginByH5.do', '192.168.1.19', 'GET', '2019-05-20', '11:06:02');
INSERT INTO `user_operation_log` VALUES ('0039e07e44b240c38d922ca11937246a', 'customerService_zhang', null, '/YiDaoServer/schedule/getTodaySchedule.do', '192.168.1.4', 'POST', '2019-05-20', '14:07:40');
INSERT INTO `user_operation_log` VALUES ('00450e2568144cad9e6d11e7b69cd9a4', 'assistantDoctor_li', null, '/YiDaoServer/schedule/getTodaySchedule.do', '192.168.1.26', 'POST', '2019-05-20', '10:58:09');
INSERT INTO `user_operation_log` VALUES ('00781ef48ef341d489b906eb05ab0f1f', 'admin', null, '/YiDaoServer/user/selectUserListByGroup.do', '192.168.1.4', 'POST', '2019-05-20', '11:05:56');
INSERT INTO `user_operation_log` VALUES ('0094e5d4d084485488934ad7702a61b8', 'admin', null, '/YiDaoServer/serviceMsgRecord/selectMessageWindow.do', '192.168.1.19', 'POST', '2019-05-20', '11:44:48');

-- ----------------------------
-- Table structure for user_status_log
-- ----------------------------
DROP TABLE IF EXISTS `user_status_log`;
CREATE TABLE `user_status_log` (
  `status_log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT  '状态日志主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户主键',
  `status_type` varchar(36) DEFAULT NULL COMMENT '字典值（status_type_login：上线，status_type_logout：登出，status_type_auto_logout：自动离线）',
  `ip_address` varchar(64) DEFAULT NULL COMMENT 'ip地址',
  `login_date` date DEFAULT NULL COMMENT '上线时间',
  `login_time` time DEFAULT NULL COMMENT '上线时间',
  PRIMARY KEY (`status_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录日志表';

-- ----------------------------
-- Records of user_status_log
-- ----------------------------
INSERT INTO `user_status_log` VALUES ('023b4a2ceb24499e8e8384e65dbd6993', '3cf0038277c04cda9d8bc8201c3849ea', 'statusTypeLogin', '192.168.1.4', '2019-05-20', '11:50:34');
INSERT INTO `user_status_log` VALUES ('03b12367f4584fed8289d2e0b655c4f5', '3cf0038277c04cda9d8bc8201c3849ea', 'statusTypeLogin', '192.168.1.9', '2019-05-20', '15:58:15');
INSERT INTO `user_status_log` VALUES ('04f547c9ad13462db44f687513fe148c', 'ba2356b66b424acb8e226538bfa6ef01', 'statusTypeLogin', '192.168.1.20', '2019-05-20', '16:08:04');
INSERT INTO `user_status_log` VALUES ('0745f651d34a4c1788aaf3680c4ea89b', '3cf0038277c04cda9d8bc8201c3849ea', 'statusTypeLogin', '192.168.1.9', '2019-05-20', '16:48:17');
INSERT INTO `user_status_log` VALUES ('08195125cb8b45228a22306b34175ddb', 'admin', 'statusTypeLogin', '192.168.1.19', '2019-05-20', '10:56:25');
INSERT INTO `user_status_log` VALUES ('08b95c38252c4f419ef57b1970b05df0', '3cf0038277c04cda9d8bc8201c3849ea', 'statusTypeLogin', '192.168.1.9', '2019-05-20', '17:02:18');
INSERT INTO `user_status_log` VALUES ('0f7feb3a05764c19bb69a5b981ee6b36', 'admin', 'statusTypeLogin', '192.168.1.4', '2019-05-20', '11:05:54');
INSERT INTO `user_status_log` VALUES ('100b1407bd6b44f5a4eed611d78d9f70', '3cf0038277c04cda9d8bc8201c3849ea', 'statusTypeLogin', '192.168.1.4', '2019-05-20', '12:02:12');
INSERT INTO `user_status_log` VALUES ('117eab8eb9de42478a0659c262ed2588', 'ba2356b66b424acb8e226538bfa6ef01', 'statusTypeLogin', '192.168.1.20', '2019-05-20', '16:47:22');
INSERT INTO `user_status_log` VALUES ('11927260edea4be5993732466d7b6f9f', 'ba2356b66b424acb8e226538bfa6ef01', 'statusTypeLogin', '192.168.1.20', '2019-05-20', '16:46:03');