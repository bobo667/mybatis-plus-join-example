/*
 Navicat Premium Data Transfer

 Source Server         : 8.0
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : mpj_test

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 14/07/2022 18:38:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`
(
  `name`  bigint    NOT NULL DEFAULT '1' COMMENT '111',
  `name2` timestamp NOT NULL COMMENT '123'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
  `id`           int               DEFAULT NULL,
  `user_id`      bigint   NOT NULL AUTO_INCREMENT,
  `content_json` json              DEFAULT NULL COMMENT 'json内容',
  `user_name`    varchar(100)      DEFAULT NULL COMMENT '用户名',
  `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `age_id`       int               DEFAULT NULL COMMENT '年龄表id',
  PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users`(`id`, `user_id`, `content_json`, `user_name`, `create_time`, `age_id`) VALUES (1, 1, '{\"name\": \"123\", \"content\": \"牛蛙牛蛙\"}', '名字啊', '2021-10-28 18:17:50', 1);
INSERT INTO `users`(`id`, `user_id`, `content_json`, `user_name`, `create_time`, `age_id`) VALUES (2, 2, '{\"name\": \"456\", \"content\": \"呜呜呜\"}', '名字2', '2021-10-28 18:17:50', 1);
COMMIT;

-- ----------------------------
-- Table structure for users_age
-- ----------------------------
DROP TABLE IF EXISTS `users_age`;
CREATE TABLE `users_age`
(
  `id`               bigint   NOT NULL AUTO_INCREMENT,
  `age_doc`          varchar(255)      DEFAULT NULL,
  `content_json_age` json              DEFAULT NULL COMMENT 'json内容',
  `age_name`         varchar(100)      DEFAULT NULL,
  `create_time`      datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户年龄';
-- ----------------------------
-- Records of users_age
-- ----------------------------
BEGIN;
INSERT INTO `users_age`(`id`, `age_doc`, `content_json_age`, `age_name`, `create_time`) VALUES (1, '90', '{\"name\": \"456\", \"content\": \"呜呜呜\"}', '90', '2021-12-17 13:11:11');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
