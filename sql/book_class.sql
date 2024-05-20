/*
 Navicat Premium Data Transfer

 Source Server         : 36.112.150.185
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 36.112.150.185:3305
 Source Schema         : geo-db

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 20/05/2024 11:11:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_class
-- ----------------------------
DROP TABLE IF EXISTS `book_class`;
CREATE TABLE `book_class`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `class1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一级标题',
  `class2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二级标题',
  `class3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '三级标题',
  `class4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '四级标题',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `updata_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_class
-- ----------------------------
INSERT INTO `book_class` VALUES (38, '基础矿床学', '第一章  概  论‌', '第一节  矿产、矿业经济、我国矿产资源', NULL, NULL, '2024-05-20 10:21:35', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
