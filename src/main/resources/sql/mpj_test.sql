/*
 Navicat Premium Data Transfer

 Source Server         : 8.0
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3307
 Source Schema         : mpj_test

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 26/09/2021 18:21:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `age_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'test1', '2021-09-26 10:42:29', 1);
INSERT INTO `users` VALUES (2, 'test2', '2021-09-26 10:42:41', 1);
COMMIT;

-- ----------------------------
-- Table structure for users_age
-- ----------------------------
DROP TABLE IF EXISTS `users_age`;
CREATE TABLE `users_age` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age_doc` varchar(200) NOT NULL,
  `age_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users_age
-- ----------------------------
BEGIN;
INSERT INTO `users_age` VALUES (1, '95后', '95');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
