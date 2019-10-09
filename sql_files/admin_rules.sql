/*
 Navicat MySQL Data Transfer

 Source Server         : first
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : mandarin

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 05/10/2019 21:11:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_rules
-- ----------------------------
DROP TABLE IF EXISTS `admin_rules`;
CREATE TABLE `admin_rules`  (
  `rule_id` int(11) NOT NULL AUTO_INCREMENT,
  `fine` double(255, 0) NOT NULL,
  `period` double(255, 0) NOT NULL,
  `deposit` double(255, 0) NOT NULL,
  PRIMARY KEY (`rule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_rules
-- ----------------------------
INSERT INTO `admin_rules` VALUES (1, 1, 30, 300);

SET FOREIGN_KEY_CHECKS = 1;
