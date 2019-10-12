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

 Date: 12/10/2019 22:30:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for librarian
-- ----------------------------
DROP TABLE IF EXISTS `librarian`;
CREATE TABLE `librarian`  (
  `librarian_id` int(11) NOT NULL AUTO_INCREMENT,
  `librarian_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `librarian_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `librarian_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `librarian_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`librarian_id`) USING BTREE,
  UNIQUE INDEX `librarian_name`(`librarian_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of librarian
-- ----------------------------
INSERT INTO `librarian` VALUES (3, 'test123', '9', 'test', 'test');
INSERT INTO `librarian` VALUES (4, 'test1223456', '9', 'test', 'test');
INSERT INTO `librarian` VALUES (5, 'test123345', '9', 'test', 'test');
INSERT INTO `librarian` VALUES (6, 'test1233457689', '9', 'test', 'test');

SET FOREIGN_KEY_CHECKS = 1;
