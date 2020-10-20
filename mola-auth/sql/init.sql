DROP TABLE IF EXISTS `t_auth_login_user`;
CREATE TABLE `t_auth_login_user` (
	`id` bigint NOT NULL COMMENT 'id',
	`username` VARCHAR (32) NOT NULL COMMENT '登陆账号',
	`password` VARCHAR (128) NOT NULL COMMENT '登陆密码',
	`tag` TINYINT (2) NOT NULL DEFAULT '0' COMMENT '标签（0:正常数据,1:删除数据,9:异常数据）',
	`remark` VARCHAR (256) DEFAULT NULL COMMENT '备注',
	`created_at` datetime NOT NULL COMMENT '创建时间',
	`created_by` VARCHAR (32) NOT NULL COMMENT '创建人',
	`updated_at` datetime NOT NULL COMMENT '更新时间',
	`updated_by` VARCHAR (32) NOT NULL COMMENT '更新人',
	PRIMARY KEY (`id`),
	UNIQUE KEY `seq_username` (`username`) USING BTREE
) ENGINE = INNODB COMMENT = '登陆用户表';

INSERT INTO `t_auth_login_user` (`id`, `username`, `password`, `tag`, `remark`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES ('1', 'admin', '$2a$10$BLXYkRrXIhAixLBD2zDKXuHhS.LnOM4keQYPagn.C2JQqcyO.H4Sm', '0', 'admin', '2020-08-07 16:14:36', '1', '2020-08-07 16:14:40', '1');

DROP TABLE IF EXISTS `t_auth_user_info`;
CREATE TABLE `t_auth_user_info` (
	`id` bigint NOT NULL COMMENT 'id',
	`login_user_id` VARCHAR (32) NOT NULL COMMENT '登陆用户ID',
	`name` VARCHAR (25) NOT NULL COMMENT '用户姓名',
	`sex` VARCHAR (2) NOT NULL COMMENT '用户性别',
	`age` integer (3) NOT NULL COMMENT '用户年龄',
	`tag` TINYINT (2) NOT NULL DEFAULT '0' COMMENT '标签（0:正常数据,1:删除数据,9:异常数据）',
	`remark` VARCHAR (256) DEFAULT NULL COMMENT '备注',
	`created_at` datetime NOT NULL COMMENT '创建时间',
	`created_by` VARCHAR (32) NOT NULL COMMENT '创建人',
	`updated_at` datetime NOT NULL COMMENT '更新时间',
	`updated_by` VARCHAR (32) NOT NULL COMMENT '更新人',
	PRIMARY KEY (`id`),
	UNIQUE KEY `seq_login_user_id` (`login_user_id`) USING BTREE
) ENGINE = INNODB COMMENT = '用户信息表';

INSERT INTO `t_auth_user_info` (`id`, `login_user_id`, `name`, `sex`, `age`, `tag`, `remark`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES ('11', '1', '许黑炭', '男', '31', '0', 'admin', '2020-08-13 18:14:23', '1', '2020-08-13 18:14:29', '1');
