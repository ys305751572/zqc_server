/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.11 : Database - aal
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`leoman_velocity` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `leoman_velocity`;

DROP TABLE IF EXISTS `t_admin`;

/** 系统管理员表 **/
CREATE TABLE `t_admin` (
  `id` bigint(32) NOT NULL auto_increment,
  `username` varchar(50) not null unique comment '登录名',
  `password` varchar(50) not null,
  `create_date` bigint,
  `modify_date` bigint,
  `last_login_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理员表';

  insert into `t_admin` (`username`,`password`) values ("admin","21232F297A57A5A743894A0E4A801FC3");

DROP TABLE IF EXISTS `t_role`;

/** 角色表 **/
CREATE TABLE `t_role` (
  `id` bigint(32) NOT NULL auto_increment,
  `name` varchar(50) not null unique comment '角色',
  `admin_id` bigint comment '操作员',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

DROP TABLE IF EXISTS `t_module`;

/** 模块表 **/
CREATE TABLE `t_module` (
  `id` bigint(32) NOT NULL auto_increment,
  `name` varchar(50) not null unique comment '模块名称',
  `url` bigint comment '模块url',
  `description`varchar(2000) comment '描述',
  `admin_id` bigint comment '操作员',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块表';

DROP TABLE IF EXISTS `t_user_role`;

/** 用户角色关联关系表 **/
CREATE TABLE `t_user_role` (
  `id` bigint(32) NOT NULL auto_increment,
  `admin_id` bigint(32) comment '管理员ID',
  `role_id` bigint(32) comment '角色ID', 
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联关系表';


DROP TABLE IF EXISTS `t_role_module`;

/** 角色模块关联关系表 **/
CREATE TABLE `t_role_module` (
  `id` bigint(32) NOT NULL auto_increment,
  `role_id` bigint(32) comment '角色ID', 
  `module_id` bigint (32) comment '模块ID',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色模块关联关系表';


DROP TABLE IF EXISTS `t_user_login`;

/** 用户登录信息表 **/
CREATE TABLE `t_user_login` (
  `id` bigint(32) NOT NULL auto_increment,
  `username` varchar(50) not null unique comment '登录名',
  `password` varchar(50) not null,
  `create_date` bigint,
  `modify_date` bigint,
  `last_login_date` bigint,
  `ip_address` varchar(20) comment '登录IP地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录信息表';

DROP TABLE IF EXISTS `t_log`;

/** 日志表 **/
CREATE TABLE `t_log` (
  `id` bigint(32) NOT NULL auto_increment,
  `message` LONGTEXT comment '日志消息',
  `user_type` int(2) DEFAULT 0 COMMENT '用户类别 0:后台管理员 1:APP用户',
  `url` VARCHAR(500) DEFAULT '' COMMENT '访问路径',
  `params` VARCHAR(500) DEFAULT '' COMMENT '访问参数',
  `log_type` INT(2) DEFAULT 0 COMMENT '日志类型 0:信息 1:错误',
  `user_id` bigint COMMENT '操作用户ID',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

DROP TABLE IF EXISTS `t_module_relation`;

/** 模块关联表 **/
CREATE TABLE `t_module_relation` (
  `id` bigint(32) NOT NULL auto_increment,
  `parent_id` bigint not null,
  `child_id` bigint not null,
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块关联表';

DROP TABLE IF EXISTS `t_dd`;
/** 多选项设置表 **/
CREATE TABLE `t_dd` (
  `id` bigint(32) NOT NULL auto_increment,
  `name` VARCHAR(20) NOT NULL COMMENT '选项名字',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典表';

DROP TABLE IF EXISTS `t_ddsub`;
/** 多选项至表 **/
CREATE TABLE `t_ddsub` (
  `id` bigint(32) NOT NULL auto_increment,
  `dd_id` VARCHAR(20) NOT NULL COMMENT '选项ID',
  `key1` VARCHAR(20) NOT NULL COMMENT 'value值',
  `value1` VARCHAR(20) NOT NULL COMMENT 'text值',
  `create_date` bigint,
  `modify_date` bigint,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典子表';
