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

 Date: 12/10/2019 22:30:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_rules
-- ----------------------------
DROP TABLE IF EXISTS `admin_rules`;
CREATE TABLE `admin_rules`  (
  `rule_id` int(11) NOT NULL AUTO_INCREMENT,
  `fine` double(255, 2) NOT NULL,
  `period` double(255, 2) NOT NULL,
  `deposit` double(255, 2) NOT NULL,
  `timestamp` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_rules
-- ----------------------------
INSERT INTO `admin_rules` VALUES (1, 1.00, 30.00, 300.00, NULL);
INSERT INTO `admin_rules` VALUES (2, 112.00, -1.00, -1.00, NULL);
INSERT INTO `admin_rules` VALUES (3, -1.00, 9897.00, -1.00, NULL);
INSERT INTO `admin_rules` VALUES (4, -1.00, -12.00, -1.00, NULL);
INSERT INTO `admin_rules` VALUES (5, -1.00, 86776868.00, -1.00, NULL);
INSERT INTO `admin_rules` VALUES (6, -1.00, -1.00, 139743.00, NULL);
INSERT INTO `admin_rules` VALUES (7, -1.00, -1.00, -990.00, NULL);
INSERT INTO `admin_rules` VALUES (8, -1.00, -1.00, 39274.00, NULL);
INSERT INTO `admin_rules` VALUES (9, -1.00, -1.00, 3782648.00, NULL);
INSERT INTO `admin_rules` VALUES (10, -1.00, -1.00, 234.00, NULL);
INSERT INTO `admin_rules` VALUES (11, 112.00, -1.00, -1.00, 'hfoesaoheohfoe');
INSERT INTO `admin_rules` VALUES (12, -1.00, 34567.00, -1.00, 'hfoesaoheohfoe');
INSERT INTO `admin_rules` VALUES (13, -1.00, -1.00, 234342.00, 'hfoesaoheohfoe');
INSERT INTO `admin_rules` VALUES (14, 3.00, -1.00, -1.00, 'hfoesaoheohfoe');
INSERT INTO `admin_rules` VALUES (15, 3.43, -1.00, -1.00, 'hfoesaoheohfoe');
INSERT INTO `admin_rules` VALUES (16, 3.43, -1.00, -1.00, 'hfoesaoheohfoe');

SET FOREIGN_KEY_CHECKS = 1;
