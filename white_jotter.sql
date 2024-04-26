/*
 Navicat Premium Data Transfer

 Source Server         : WJ
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : white_jotter

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 14/04/2022 20:22:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `date` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `press` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `abs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_book_category_on_cid`(`cid`) USING BTREE,
  CONSTRAINT `fk_book_category_on_cid` FOREIGN KEY (`cid`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '1', '1', '1', '1', '1', '1', 1);
INSERT INTO `book` VALUES (2, '2', '2', '2', '2', '2', '2', 1);
INSERT INTO `book` VALUES (3, '3', '3', '3', '3', '3', '3', 2);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '文学');
INSERT INTO `category` VALUES (2, '哲学');

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roomid` int NULL DEFAULT NULL,
  `seatid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_credit` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES (1, 'test', 101, '30', '下午12:02:30', '2022/3/22', '150', '正常', 100);
INSERT INTO `history` VALUES (8, 'test', 102, '3', '下午12:02:33', '2022/4/4', '130', '正常', 100);
INSERT INTO `history` VALUES (9, 'test', 203, '6', '下午12:13:58', '2022/4/4', '100', '正常', 100);
INSERT INTO `history` VALUES (10, 'test', 203, '6', '下午2:04:30', '2022/4/4', '30', '正常', 100);
INSERT INTO `history` VALUES (11, 'test', 203, '6', '下午2:25:21', '2022/4/4', '60', '违规', 94);
INSERT INTO `history` VALUES (28, 'test', 202, '8', '下午3:51:31', '2022/4/5', '1', '违规', 88);
INSERT INTO `history` VALUES (29, 'test', 202, '8', '下午10:26:28', '2022/4/5', '0', '正常', 90);
INSERT INTO `history` VALUES (30, 'test', 202, '1', '下午10:28:28', '2022/4/5', '0', '正常', 102);
INSERT INTO `history` VALUES (31, 'test', 202, '1', '下午10:35:02', '2022/4/5', '0', '正常', 102);
INSERT INTO `history` VALUES (32, 'test', 202, '1', '下午10:35:50', '2022/4/5', '0', '违规', 94);
INSERT INTO `history` VALUES (33, 'test2', 101, '5', '下午5:22:35', '2022/4/11', '1', '违规', 94);
INSERT INTO `history` VALUES (34, 'test', 202, '5', '上午10:02:32', '2022/4/12', '4', '正常', 102);
INSERT INTO `history` VALUES (35, 'test', 202, '5', '上午11:11:40', '2022/4/12', '1', '正常', 96);

-- ----------------------------
-- Table structure for monitor
-- ----------------------------
DROP TABLE IF EXISTS `monitor`;
CREATE TABLE `monitor`  (
  `roomid` int UNSIGNED NOT NULL,
  `monitor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roomid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of monitor
-- ----------------------------
INSERT INTO `monitor` VALUES (101, '\"../assets/img/demo.mp4\"');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `seat_num` int NULL DEFAULT NULL,
  `seat_used` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seat_violation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seat_leave` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `empty_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `empty_seat` int NULL DEFAULT NULL,
  `room_rows` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `room_cols` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (101, 76, '1,3,7,9,10,12,14,16,18,20,21,25,26,27,28,29,30,36,40,41,49,55,56,58,59,75,76,70,69,68,51,52,63,64,46,45,42,43,44,47,48,50,53,54,57,60,61,62,65,66,67,71,72,73,74,39', '0,4,27,4,4', '0,1,3', '71,72,79,80', 19, '1,2,3,4,5,6,7,8,9,10', '1,2,3,4,5,6,7,8');
INSERT INTO `room` VALUES (102, 62, '15,14,13,10,2,6,20,22,26,27,28,1,2,3,11', '0,11,12', '0,1,2,3', '1,2', 48, '1,2,3,4,5,6,7,8', '1,2,3,4,5,6,7,8');
INSERT INTO `room` VALUES (103, 48, '15,12,13,14,11', '0,11', '0', '37,38,43,44', 39, '1,2,3,4,5,6,7,8', '1,2,3,4,5,6');
INSERT INTO `room` VALUES (201, 56, '20,24,26,28,30,22,50', '0,50', '0', '33,34,41,42,49,50,57,58', 49, '1,2,3,4,5,6,7,8', '1,2,3,4,5,6,7,8');
INSERT INTO `room` VALUES (202, 64, '30,32,34,36,38,40,1,2', '0', '0,1,2', '55,56,63,64', 50, '1,2,3,4,5,6,7,8', '1,2,3,4,5,6,7,8');
INSERT INTO `room` VALUES (203, 76, '30,32,34,36,38,40,42,44,46,60,1,2,3,4,5,6', '0,60', '0', '7,8,15,16', 50, '1,2,3,4,5,6,7,8,9,10', '1,2,3,4,5,6,7,8');
INSERT INTO `room` VALUES (301, 60, '1,2,3,4,5,6,7,8,9,11', '0,11', '0', '1,2,3,4', 50, '1,2,3,4,5,6,7,8', '1,2,3,4,5,6,7,8');
INSERT INTO `room` VALUES (302, 78, '1,3,5,7,9,10,12,14,16,18,20,21,25,26,27,28,29,30,36,40,41,49,55,56,58,11,6', '0,11', '0', '1,2', 51, '1,2,3,4,5,6,7,8,9,10', '1,2,3,4,5,6,7,8');
INSERT INTO `room` VALUES (303, 56, '8,9,10,11,12,6,20,21', '0,12,6', '0', '3,4,5,6,11,12,13,14', 48, '1,2,3,4,5,6,7,8', '1,2,3,4,5,6,7,8');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456');
INSERT INTO `user` VALUES (2, 'root', '123456789');
INSERT INTO `user` VALUES (3, 'test', 'test');
INSERT INTO `user` VALUES (4, 'chen', 'chen');
INSERT INTO `user` VALUES (5, 'test2', 'test2');
INSERT INTO `user` VALUES (6, 'test3', 'test3');
INSERT INTO `user` VALUES (7, 'test4', 'test4');
INSERT INTO `user` VALUES (8, 'test5', 'test5');

-- ----------------------------
-- Table structure for userdata
-- ----------------------------
DROP TABLE IF EXISTS `userdata`;
CREATE TABLE `userdata`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `roomid` int NULL DEFAULT NULL,
  `seatid` int NULL DEFAULT NULL,
  `use_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `use_credit` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userdata
-- ----------------------------
INSERT INTO `userdata` VALUES ('admin', 102, 12, '0', '违规', 94);
INSERT INTO `userdata` VALUES ('test', 202, 5, '30', '正常', 100);
INSERT INTO `userdata` VALUES ('root', 103, 1, '0', '违规', 94);
INSERT INTO `userdata` VALUES ('chen', 201, 1, '0', '违规', 100);
INSERT INTO `userdata` VALUES ('test2', 101, 5, '30', '正常', 82);
INSERT INTO `userdata` VALUES ('test3', 103, 55, '0', '违规', 94);
INSERT INTO `userdata` VALUES ('test4', 202, 10, '0', '正常', 94);

-- ----------------------------
-- Table structure for violation
-- ----------------------------
DROP TABLE IF EXISTS `violation`;
CREATE TABLE `violation`  (
  `roomid` int NULL DEFAULT NULL,
  `seatid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of violation
-- ----------------------------
INSERT INTO `violation` VALUES (102, '12', 'admin');
INSERT INTO `violation` VALUES (201, '1', 'chen');
INSERT INTO `violation` VALUES (103, '1', 'root');

SET FOREIGN_KEY_CHECKS = 1;
