/*
 Navicat Premium Data Transfer

 Source Server         : zyh
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : nhs

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 25/04/2025 10:48:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for backdown
-- ----------------------------
DROP TABLE IF EXISTS `backdown`;
CREATE TABLE `backdown`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remarks` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` int NOT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `customer_id` int NOT NULL COMMENT '客户ID',
  `retreattime` date NOT NULL COMMENT '退住时间',
  `retreattype` int NOT NULL COMMENT '退住类型 0-正常退住  1-死亡退住 2-保留床位',
  `retreatreason` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '退住原因',
  `auditstatus` int NOT NULL COMMENT '审批状态  0-已提交 1-同意  2-拒绝',
  `auditperson` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '审批人',
  `audittime` date NULL DEFAULT NULL COMMENT '审批时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of backdown
-- ----------------------------
INSERT INTO `backdown` VALUES (1, '暂无', 0, 40, '2023-12-17', 2, '临时出院', 1, '1', '2023-12-18');
INSERT INTO `backdown` VALUES (2, NULL, 1, 39, '2023-12-19', 0, '康复出院', 0, NULL, NULL);

-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_no` int NOT NULL COMMENT '房间编号',
  `bed_status` int NOT NULL COMMENT '房间状态 1：空闲  2：有人  3：外出',
  `remarks` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `bed_no` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '床位编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bed
-- ----------------------------
INSERT INTO `bed` VALUES (6, 1001, 2, NULL, '1001-1');
INSERT INTO `bed` VALUES (7, 1001, 1, NULL, '1001-2');
INSERT INTO `bed` VALUES (8, 1002, 1, NULL, '1002-1');
INSERT INTO `bed` VALUES (9, 1002, 2, NULL, '1002-2');
INSERT INTO `bed` VALUES (10, 1003, 1, NULL, '1003-1');
INSERT INTO `bed` VALUES (11, 1004, 1, NULL, '1004-1');
INSERT INTO `bed` VALUES (12, 1005, 1, NULL, '1005-1');
INSERT INTO `bed` VALUES (13, 1005, 1, NULL, '1005-2');
INSERT INTO `bed` VALUES (14, 1006, 1, NULL, '1006-1');
INSERT INTO `bed` VALUES (15, 1006, 2, NULL, '1006-2');
INSERT INTO `bed` VALUES (16, 1007, 1, NULL, '1007-1');
INSERT INTO `bed` VALUES (17, 1007, 1, NULL, '1007-2');
INSERT INTO `bed` VALUES (18, 1008, 1, NULL, '1008-1');
INSERT INTO `bed` VALUES (19, 1009, 1, NULL, '1009-1');
INSERT INTO `bed` VALUES (21, 1009, 1, NULL, '1009-2');
INSERT INTO `bed` VALUES (22, 1010, 2, NULL, '1010-1');
INSERT INTO `bed` VALUES (23, 1010, 1, NULL, '1010-2');
INSERT INTO `bed` VALUES (24, 1011, 1, NULL, '1011-1');
INSERT INTO `bed` VALUES (25, 1012, 1, NULL, '1012-1');
INSERT INTO `bed` VALUES (26, 1012, 2, NULL, '1012-2');
INSERT INTO `bed` VALUES (27, 1013, 1, NULL, '1013-1');
INSERT INTO `bed` VALUES (28, 1014, 2, NULL, '1014-1');
INSERT INTO `bed` VALUES (29, 1015, 1, NULL, '1015-1');
INSERT INTO `bed` VALUES (30, 1015, 1, NULL, '1015-2');
INSERT INTO `bed` VALUES (31, 1016, 1, NULL, '1016-1');
INSERT INTO `bed` VALUES (32, 1017, 2, NULL, '1017-1');
INSERT INTO `bed` VALUES (33, 1018, 1, NULL, '1018-1');
INSERT INTO `bed` VALUES (34, 2001, 1, NULL, '2001-1');
INSERT INTO `bed` VALUES (35, 2002, 2, NULL, '2002-1');
INSERT INTO `bed` VALUES (36, 2003, 1, NULL, '2003-1');
INSERT INTO `bed` VALUES (37, 2004, 1, NULL, '2004-1');
INSERT INTO `bed` VALUES (38, 2005, 2, NULL, '2005-1');
INSERT INTO `bed` VALUES (39, 2006, 1, NULL, '2006-1');
INSERT INTO `bed` VALUES (40, 2007, 1, NULL, '2007-1');
INSERT INTO `bed` VALUES (41, 2008, 1, NULL, '2008-1');
INSERT INTO `bed` VALUES (42, 2009, 1, NULL, '2009-1');
INSERT INTO `bed` VALUES (43, 2009, 1, NULL, '2009-2');
INSERT INTO `bed` VALUES (44, 2010, 1, NULL, '2010-1');
INSERT INTO `bed` VALUES (45, 2011, 1, NULL, '2011-1');
INSERT INTO `bed` VALUES (46, 2012, 1, NULL, '2012-1');
INSERT INTO `bed` VALUES (47, 2013, 1, NULL, '2013-1');
INSERT INTO `bed` VALUES (48, 2014, 2, NULL, '2014-1');
INSERT INTO `bed` VALUES (49, 2015, 1, NULL, '2015-1');
INSERT INTO `bed` VALUES (50, 2016, 2, NULL, '2016-1');
INSERT INTO `bed` VALUES (51, 2017, 2, NULL, '2017-1');
INSERT INTO `bed` VALUES (52, 2018, 2, NULL, '2018-1');

-- ----------------------------
-- Table structure for beddetails
-- ----------------------------
DROP TABLE IF EXISTS `beddetails`;
CREATE TABLE `beddetails`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `start_date` date NULL DEFAULT NULL COMMENT '床位起始日期',
  `end_date` date NULL DEFAULT NULL COMMENT '床位结束日期',
  `bed_details` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '床位详情信息',
  `customer_id` int NULL DEFAULT NULL COMMENT '顾客ID',
  `bed_id` int NULL DEFAULT NULL COMMENT '床位ID',
  `is_deleted` int NULL DEFAULT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of beddetails
-- ----------------------------
INSERT INTO `beddetails` VALUES (45, '2023-11-17', '2025-11-30', '606#1001-1', 27, 6, 0);
INSERT INTO `beddetails` VALUES (46, '2023-11-17', '2024-11-30', '606#1002-2', 28, 9, 0);
INSERT INTO `beddetails` VALUES (49, '2023-11-17', '2023-11-23', '606#2001-1', 30, 34, 1);
INSERT INTO `beddetails` VALUES (50, '2023-11-17', '2025-11-27', '606#2002-1', 29, 35, 0);
INSERT INTO `beddetails` VALUES (51, '2023-11-17', '2025-11-29', '606#2017-1', 31, 51, 0);
INSERT INTO `beddetails` VALUES (52, '2023-11-17', '2023-11-21', '606#2015-1', 32, 49, 1);
INSERT INTO `beddetails` VALUES (53, '2023-11-17', '2024-11-29', '606#2014-1', 33, 48, 0);
INSERT INTO `beddetails` VALUES (54, '2023-11-17', '2025-01-30', '606#1006-2', 34, 15, 0);
INSERT INTO `beddetails` VALUES (55, '2023-11-17', '2024-11-30', '606#1017-1', 35, 32, 0);
INSERT INTO `beddetails` VALUES (56, '2023-11-17', '2025-11-26', '606#1010-1', 36, 22, 0);
INSERT INTO `beddetails` VALUES (57, '2023-11-17', '2025-11-20', '606#1012-2', 37, 26, 0);
INSERT INTO `beddetails` VALUES (58, '2023-11-17', '2025-11-29', '606#2005-1', 38, 38, 0);
INSERT INTO `beddetails` VALUES (59, '2023-11-17', '2024-11-30', '606#1014-1', 39, 28, 0);
INSERT INTO `beddetails` VALUES (60, '2023-11-17', '2025-11-29', '606#2012-1', 40, 46, 0);
INSERT INTO `beddetails` VALUES (61, '2023-11-21', '2027-11-12', '606#2016-1', 32, 50, 0);
INSERT INTO `beddetails` VALUES (62, '2023-11-23', '2025-12-31', '606#2018-1', 30, 52, 0);

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `customer_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '客户姓名',
  `customer_age` int NULL DEFAULT NULL COMMENT '年龄',
  `customer_sex` int NULL DEFAULT NULL COMMENT '性别  0：男  1：女',
  `idcard` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `room_no` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '房间号',
  `building_no` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '所属楼房',
  `checkin_date` date NULL DEFAULT NULL COMMENT '入住时间',
  `expiration_date` date NULL DEFAULT NULL COMMENT '合同到期时间',
  `contact_tel` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `bed_id` int NULL DEFAULT NULL COMMENT '床号',
  `psychosomatic_state` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '身心状况',
  `attention` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '注意事项',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `height` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '身高',
  `weight` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '体重',
  `blood_type` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '血型',
  `filepath` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像路径',
  `user_id` int NULL DEFAULT NULL COMMENT '关联系统健康管家(护工)  无管家设置  -1',
  `level_id` int NULL DEFAULT NULL COMMENT '护理等级',
  `family_member` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '家属',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `nurselevel_customer_fk`(`level_id` ASC) USING BTREE,
  INDEX `user_customer_fk`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (27, 0, '刘茂元', 76, 0, '610104194707211657', '1001', '606', '2023-11-17', '2025-11-30', '18176756743', 6, '', NULL, '1947-07-21', NULL, NULL, 'O', '@/assets/tou.png', -1, 1, '刘其元');
INSERT INTO `customer` VALUES (28, 0, '高廉武', 83, 0, '610404194103010017', '1002', '606', '2023-11-17', '2024-11-30', '13767675481', 9, '', NULL, '1941-03-01', NULL, NULL, 'A', '@/assets/tou.png', 5, 2, '高校蓝');
INSERT INTO `customer` VALUES (29, 0, '刘玉珍', 72, 1, '610402194112240335', '2002', '606', '2023-11-17', '2025-11-27', '13534786754', 35, '', NULL, '1951-12-24', NULL, NULL, 'B', '@/assets/tou.png', -1, 1, '刘宇');
INSERT INTO `customer` VALUES (30, 0, '石秀英', 73, 1, '610422195102220029', '2018', '606', '2023-11-17', '2025-12-31', '13867541321', 52, '', NULL, '1951-02-22', NULL, NULL, 'O', '@/assets/tou.png', 5, 1, '石杰颖');
INSERT INTO `customer` VALUES (31, 0, '何风云', 62, 1, '610402196111166507', '2017', '606', '2023-11-17', '2025-11-29', '18765652312', 51, '', NULL, '1961-11-16', NULL, NULL, 'O', '@/assets/tou.png', 3, 3, '何方');
INSERT INTO `customer` VALUES (32, 0, '毛兰香', 73, 1, '61040419501227002x', '2016', '606', '2023-11-17', '2027-11-12', '15816164587', 50, '', NULL, '1950-12-27', NULL, NULL, 'O', '@/assets/tou.png', -1, 4, '毛参');
INSERT INTO `customer` VALUES (33, 0, '李艾青', 83, 1, '622801194104150240', '2014', '606', '2023-11-17', '2024-11-29', '18176572345', 48, '', NULL, '1941-04-14', NULL, NULL, 'B', '@/assets/tou.png', 2, 2, '李天');
INSERT INTO `customer` VALUES (34, 0, '刘海云', 64, 0, '510522196005042710', '1006', '606', '2023-11-17', '2025-01-30', '18798986545', 15, '', NULL, '1960-05-04', NULL, NULL, 'O', '@/assets/tou.png', -1, 2, '刘高');
INSERT INTO `customer` VALUES (35, 0, '王金堂', 58, 0, '610402196511056517', '1017', '606', '2023-11-17', '2024-11-30', '13423786754', 32, '', NULL, '1965-11-05', NULL, NULL, 'AB', '@/assets/tou.png', 3, 4, '王田雨');
INSERT INTO `customer` VALUES (36, 0, '刘玉亭', 72, 0, '610123195204158510', '1010', '606', '2023-11-17', '2025-11-26', '18767560987', 22, '', NULL, '1952-04-15', NULL, NULL, 'O', '@/assets/tou.png', 5, 2, '刘晓');
INSERT INTO `customer` VALUES (37, 0, '陈泉', 68, 0, '61040219560313030x', '1012', '606', '2023-11-17', '2025-11-20', '18567546712', 26, '', NULL, '1956-03-13', NULL, NULL, 'O', '@/assets/tou.png', -1, 5, '陈荣光');
INSERT INTO `customer` VALUES (38, 0, '苗月兰', 67, 1, '622725195701081429', '2005', '606', '2023-11-17', '2025-11-29', '18367541987', 38, '', NULL, '1957-01-08', NULL, NULL, 'A', '@/assets/tou.png', 3, 3, '苗存义');
INSERT INTO `customer` VALUES (39, 0, '张帆', 77, 0, '610402194701286518', '1014', '606', '2023-11-17', '2024-11-30', '17745189064', 28, '', NULL, '1947-01-28', NULL, NULL, 'O', '@/assets/tou.png', 2, 3, '张明');
INSERT INTO `customer` VALUES (40, 0, '孙瑞英', 68, 1, '610404195603010027', '2012', '606', '2023-11-17', '2025-11-29', '18167564213', 46, '', NULL, '1956-03-01', NULL, NULL, 'O', '@/assets/tou.png', 2, 2, '孙石');

-- ----------------------------
-- Table structure for customernurseitem
-- ----------------------------
DROP TABLE IF EXISTS `customernurseitem`;
CREATE TABLE `customernurseitem`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_id` int NOT NULL COMMENT '护理项目编号',
  `custormer_id` int NOT NULL COMMENT '客户编号',
  `level_id` int NULL DEFAULT NULL COMMENT '护理级别编号',
  `nurse_number` int NOT NULL COMMENT '购买数量',
  `is_deleted` int NULL DEFAULT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `buy_time` date NOT NULL COMMENT '服务购买日期',
  `maturity_time` date NOT NULL COMMENT '服务到期日',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customernurseitem
-- ----------------------------
INSERT INTO `customernurseitem` VALUES (58, 1, 40, 2, -5, 0, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` VALUES (59, 2, 40, 2, 56, 0, '2023-10-17', '2023-11-16');
INSERT INTO `customernurseitem` VALUES (60, 3, 40, 2, 2, 0, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` VALUES (61, 4, 40, 2, 80, 0, '2023-10-17', '2023-11-28');
INSERT INTO `customernurseitem` VALUES (62, 1, 39, 3, 44, 0, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` VALUES (63, 2, 39, 3, 100, 0, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` VALUES (64, 3, 39, 3, 76, 0, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` VALUES (65, 5, 39, 3, 80, 1, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` VALUES (66, 6, 39, 3, 70, 1, '2023-10-17', '2024-01-17');
INSERT INTO `customernurseitem` VALUES (67, 1, 36, NULL, 40, 0, '2023-10-21', '2024-01-21');
INSERT INTO `customernurseitem` VALUES (68, 3, 36, NULL, 70, 0, '2023-10-21', '2024-01-21');
INSERT INTO `customernurseitem` VALUES (69, 4, 36, NULL, 70, 1, '2023-10-21', '2024-01-21');
INSERT INTO `customernurseitem` VALUES (70, 4, 36, NULL, 19, 0, '2023-10-21', '2024-01-21');
INSERT INTO `customernurseitem` VALUES (71, 20, 40, NULL, 17, 0, '2023-10-23', '2024-01-23');
INSERT INTO `customernurseitem` VALUES (72, 17, 40, NULL, 16, 0, '2023-10-23', '2024-01-23');
INSERT INTO `customernurseitem` VALUES (73, 10, 40, NULL, 10, 0, '2023-10-23', '2024-01-23');
INSERT INTO `customernurseitem` VALUES (74, 12, 40, NULL, 17, 0, '2023-10-23', '2024-01-23');
INSERT INTO `customernurseitem` VALUES (75, 1, 35, 4, 98, 0, '2023-10-24', '2024-01-24');
INSERT INTO `customernurseitem` VALUES (76, 2, 35, 4, 99, 0, '2023-10-24', '2024-01-24');
INSERT INTO `customernurseitem` VALUES (77, 3, 35, 4, 100, 0, '2023-10-24', '2024-01-24');
INSERT INTO `customernurseitem` VALUES (78, 5, 35, 4, 100, 0, '2023-10-24', '2024-01-24');

-- ----------------------------
-- Table structure for customerpreference
-- ----------------------------
DROP TABLE IF EXISTS `customerpreference`;
CREATE TABLE `customerpreference`  (
  `id` int NOT NULL,
  `customer_id` int NULL DEFAULT NULL,
  `preferences` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `attention` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `remark` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `is_deleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customerpreference
-- ----------------------------
INSERT INTO `customerpreference` VALUES (1, 40, '甜食', '肥胖', '少吃糖', 0);
INSERT INTO `customerpreference` VALUES (2, 40, '油炸食物', '肥胖', '少油', 0);
INSERT INTO `customerpreference` VALUES (3, 39, '清淡', '免疫力低下', '多吃蛋白质', 0);

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `id` int NOT NULL,
  `food_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `food_type` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `is_halal` int NULL DEFAULT NULL,
  `food_img` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES (1, '小米粥', '主食', 12.00, 0, 'https://t12.baidu.com/it/u=2696151017,220713238&fm=30&app=106&f=JPEG?w=608&h=392&s=760864A6E95951C66622AE790300503B');
INSERT INTO `food` VALUES (2, '米饭', '主食', 3.00, 0, 'https://p3-tt.byteimg.com/origin/pgc-image/b3833f4ccd464b2d8ad474c994b60d4b?from=pc');
INSERT INTO `food` VALUES (3, '馒头', '主食', 2.00, 0, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2Fc8bf815e-38e3-4436-a87a-90b26403cc22%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1715759064&t=07e947be315a90bcacbf4b1ab7c01954');
INSERT INTO `food` VALUES (4, '饺子', '主食', 16.00, 0, 'https://img0.baidu.com/it/u=2548554196,561734897&fm=253&fmt=auto&app=138&f=JPEG?w=667&h=500');
INSERT INTO `food` VALUES (5, '面条', '主食', 18.00, 0, 'https://img2.baidu.com/it/u=490462899,2942472874&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=1067');
INSERT INTO `food` VALUES (6, '包子', '主食', 14.00, 0, 'https://pic.rmb.bdstatic.com/bjh/240413/dump/3e5fc87879243cf2143d0724b1481586.png');

-- ----------------------------
-- Table structure for meal
-- ----------------------------
DROP TABLE IF EXISTS `meal`;
CREATE TABLE `meal`  (
  `id` int NOT NULL,
  `week_day` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `food_id` int NULL DEFAULT NULL,
  `meal_type` int NULL DEFAULT NULL,
  `taste` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `is_deleted` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meal
-- ----------------------------
INSERT INTO `meal` VALUES (1, '周一', 1, 1, '甜口', 0);
INSERT INTO `meal` VALUES (2, '周二', 2, 1, '东北大米', 0);
INSERT INTO `meal` VALUES (3, '周三', 3, 1, '山东', 0);
INSERT INTO `meal` VALUES (4, '周一', 2, 2, '香', 0);
INSERT INTO `meal` VALUES (5, '周一', 3, 3, '香', 0);
INSERT INTO `meal` VALUES (6, '周二', 3, 2, '山东', 0);
INSERT INTO `meal` VALUES (7, '周三', 1, 3, '清淡', 0);
INSERT INTO `meal` VALUES (8, '周二', 5, 3, '清淡', 0);
INSERT INTO `meal` VALUES (9, '周三', 4, 2, '清淡', 0);
INSERT INTO `meal` VALUES (10, '周一', 6, 1, '咸口', 0);
INSERT INTO `meal` VALUES (11, '周一', 5, 2, '西红柿鸡蛋', 0);
INSERT INTO `meal` VALUES (12, '周一', 4, 3, '白菜', 0);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menus_index` varchar(5) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '一级菜单索引',
  `title` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单标题',
  `icon` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单图标',
  `path` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '1', '床位管理', 'tree', '/bed', 0);
INSERT INTO `menu` VALUES (2, '2', '客户管理', 'user', '/customer', 0);
INSERT INTO `menu` VALUES (4, '4', '护理管理', 'shopping', '/nurse', 0);
INSERT INTO `menu` VALUES (5, '5', '健康管家', 'druid', '/health', 0);
INSERT INTO `menu` VALUES (6, '6', '用户管理', 'peoples', '/user', 0);
INSERT INTO `menu` VALUES (7, NULL, '床位示意图', 'table', '/bedMap', 1);
INSERT INTO `menu` VALUES (8, NULL, '床位管理', 'tree-table', '/bedManage', 1);
INSERT INTO `menu` VALUES (9, NULL, '入住登记', 'eye-open', '/checkIn', 2);
INSERT INTO `menu` VALUES (11, NULL, '退住登记', 'eye', '/checkOut', 2);
INSERT INTO `menu` VALUES (12, NULL, '外出登记', 'logininfor', '/outRecords', 2);
INSERT INTO `menu` VALUES (17, NULL, '护理级别', 'online', '/nurseLevel', 4);
INSERT INTO `menu` VALUES (18, NULL, '护理项目', 'list', '/nurseItem', 4);
INSERT INTO `menu` VALUES (19, NULL, '客户护理设置', 'dict', '/nurseSetting', 4);
INSERT INTO `menu` VALUES (20, NULL, '护理记录', 'log', '/nurseRecords', 4);
INSERT INTO `menu` VALUES (21, NULL, '设置服务对象', 'people', '/serviceCustomerSetting', 5);
INSERT INTO `menu` VALUES (22, '2', '健康管家', 'Coin', NULL, 0);
INSERT INTO `menu` VALUES (23, NULL, '日常护理', 'Help', '/dailyNurse', 22);
INSERT INTO `menu` VALUES (24, NULL, '服务关注', 'example', '/serviceCare', 5);
INSERT INTO `menu` VALUES (25, NULL, '基础数据维护', 'system', '/listUser', 6);
INSERT INTO `menu` VALUES (26, '1', '客户管理', 'User', NULL, 0);
INSERT INTO `menu` VALUES (27, NULL, '外出登记', 'Location', '/outRecords', 26);
INSERT INTO `menu` VALUES (28, NULL, '退住登记', 'DArrowLeft', '/checkOut', 26);
INSERT INTO `menu` VALUES (29, NULL, '护理记录', 'ScaleToOriginal', '/nurseRecordsToUser', 22);
INSERT INTO `menu` VALUES (30, NULL, '膳食日历', 'date', '/meal', 2);
INSERT INTO `menu` VALUES (31, NULL, '客户喜好', 'post', '/preference', 2);

-- ----------------------------
-- Table structure for nursecontent
-- ----------------------------
DROP TABLE IF EXISTS `nursecontent`;
CREATE TABLE `nursecontent`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `serial_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '编号',
  `nursing_name` varchar(55) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
  `service_price` varchar(55) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '价格',
  `message` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT ' 描述',
  `status` int NOT NULL DEFAULT 2 COMMENT '状态 1：启用；2：停用',
  `execution_cycle` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '执行周期',
  `execution_times` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '执行次数',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of nursecontent
-- ----------------------------
INSERT INTO `nursecontent` VALUES (1, 'HLXM0001', '吸氧', '60元/次', '吸氧氧123', 1, '每天', '2', 0);
INSERT INTO `nursecontent` VALUES (2, 'HLXM0002', '协助服药', '免费', '', 1, '每天', '3', 0);
INSERT INTO `nursecontent` VALUES (3, 'HLXM0003', '测量血压', '免费', '前台血压计', 1, '每天', '1', 0);
INSERT INTO `nursecontent` VALUES (4, 'HLXM0004', '协助穿衣', '10元/次', NULL, 1, '每天', '2', 0);
INSERT INTO `nursecontent` VALUES (5, 'HLXM0005', '床上洗头', '20元/次', NULL, 1, '每周', '3', 0);
INSERT INTO `nursecontent` VALUES (6, 'HLXM0006', '健身', '10元/小时', '', 1, '每天', '1', 0);
INSERT INTO `nursecontent` VALUES (7, 'HLXM0007', '清洁床铺', '10元/次', '', 1, '每周', '2', 0);
INSERT INTO `nursecontent` VALUES (8, 'HLXM0008', '如厕服务', '20元/次', '', 1, '每天', '2', 0);
INSERT INTO `nursecontent` VALUES (9, 'HLXM0009', '口腔清洁', '50元/次', '', 1, '每月', '2', 0);
INSERT INTO `nursecontent` VALUES (10, 'HLXM0010', '假牙清洁', '20元/次', '', 1, '每周', '2', 0);
INSERT INTO `nursecontent` VALUES (12, 'HLXM0012', '擦拭身体', '20元/次', '', 1, '每天', '1', 0);
INSERT INTO `nursecontent` VALUES (14, 'HLXM0014', '体温测量', '免费', '体温枪测量额温', 1, '每天', '1', 0);
INSERT INTO `nursecontent` VALUES (15, 'HLXM0015', '协助饮食', '50元/次', '', 2, '每天', '3', 0);
INSERT INTO `nursecontent` VALUES (16, 'HLXM0016', '血压测量', '免费', '', 2, '每天', '1', 1);
INSERT INTO `nursecontent` VALUES (17, 'HLXM0017', '剪指甲', '12/次', '', 1, '每月', '2', 0);
INSERT INTO `nursecontent` VALUES (20, 'HLXM0019', '协助外出', '100/次', '', 1, '每月', '4', 0);
INSERT INTO `nursecontent` VALUES (21, 'HLXM0020', '瑜伽辅导', '80/次', '', 2, '每周', '2', 0);
INSERT INTO `nursecontent` VALUES (22, 'test', '测试', '111', NULL, 1, '1', '1', 1);
INSERT INTO `nursecontent` VALUES (23, 'tses2', 'test2', '222', 'tset2', 2, '2', '2', 1);
INSERT INTO `nursecontent` VALUES (24, 'HLXM0000', 'HLXM0000', '00', '******', 2, '0', '0', 0);
INSERT INTO `nursecontent` VALUES (27, '12313213', '1231231', '123123', '123123', 2, 'e21dwdq', '2casca', 1);
INSERT INTO `nursecontent` VALUES (28, '123123213', '2123', '1dasadadwadw1122222222222222222222222', 'qwd', 2, 'qwd', 'qwdqw', 1);
INSERT INTO `nursecontent` VALUES (29, 'qdw', 'qdqs', 'dqd', 'dqsd', 1, 'qsd', 'qd', 1);
INSERT INTO `nursecontent` VALUES (30, 'qdw', 'qdqs', '123123', 'dqsd', 1, 'qsd', 'qd', 1);
INSERT INTO `nursecontent` VALUES (31, 'qdwdq', 'dqwdqwd', 'qsdq', 'sqdqsd', 2, 'dqwdwq', 'dqwd', 1);
INSERT INTO `nursecontent` VALUES (32, 'qdwdq', 'dqwdqwd', '13213213', 'sqdqsd', 2, 'dqwdwq', 'dqwd', 1);
INSERT INTO `nursecontent` VALUES (33, 'a1ewewd', '1we1e', '1e2e1', 'dqwd', 2, 'dqwdq', 'dsqdqs', 1);
INSERT INTO `nursecontent` VALUES (34, 'adewfw', 'dwqw1d', '12d1', 'd12d', 2, '12d12', 'd12d', 1);

-- ----------------------------
-- Table structure for nurselevel
-- ----------------------------
DROP TABLE IF EXISTS `nurselevel`;
CREATE TABLE `nurselevel`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `level_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '未命名' COMMENT '护理级别',
  `level_status` int NULL DEFAULT 1 COMMENT '级别状态 1：启用；2：停用',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of nurselevel
-- ----------------------------
INSERT INTO `nurselevel` VALUES (1, '一级', 1, 0);
INSERT INTO `nurselevel` VALUES (2, '二级', 1, 0);
INSERT INTO `nurselevel` VALUES (3, '三级', 1, 0);
INSERT INTO `nurselevel` VALUES (4, '四级', 1, 0);
INSERT INTO `nurselevel` VALUES (5, '五级', 2, 0);
INSERT INTO `nurselevel` VALUES (7, '六级', 2, 0);
INSERT INTO `nurselevel` VALUES (8, 'x', 2, 0);
INSERT INTO `nurselevel` VALUES (9, '八级', 1, 0);
INSERT INTO `nurselevel` VALUES (10, '999', 1, 1);
INSERT INTO `nurselevel` VALUES (11, 'xxx', 1, 0);
INSERT INTO `nurselevel` VALUES (16, 'test', 1, 1);

-- ----------------------------
-- Table structure for nurselevelitem
-- ----------------------------
DROP TABLE IF EXISTS `nurselevelitem`;
CREATE TABLE `nurselevelitem`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `level_id` int NOT NULL COMMENT '关联护理级别',
  `item_id` int NOT NULL COMMENT '关联护理项目',
  `is_deleted` int NULL DEFAULT 0 COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `nurselevel_id_fk`(`level_id` ASC) USING BTREE,
  INDEX `nurseItem_id_fk`(`item_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of nurselevelitem
-- ----------------------------
INSERT INTO `nurselevelitem` VALUES (23, 1, 2, 0);
INSERT INTO `nurselevelitem` VALUES (25, 1, 4, 0);
INSERT INTO `nurselevelitem` VALUES (28, 2, 1, 0);
INSERT INTO `nurselevelitem` VALUES (29, 2, 2, 0);
INSERT INTO `nurselevelitem` VALUES (39, 3, 2, 0);
INSERT INTO `nurselevelitem` VALUES (40, 3, 3, 0);
INSERT INTO `nurselevelitem` VALUES (41, 3, 5, 0);
INSERT INTO `nurselevelitem` VALUES (53, 4, 1, 0);
INSERT INTO `nurselevelitem` VALUES (54, 4, 2, 0);
INSERT INTO `nurselevelitem` VALUES (55, 4, 3, 0);
INSERT INTO `nurselevelitem` VALUES (63, 1, 5, 0);
INSERT INTO `nurselevelitem` VALUES (71, 4, 5, 0);
INSERT INTO `nurselevelitem` VALUES (72, 3, 1, 0);
INSERT INTO `nurselevelitem` VALUES (73, 3, 6, 0);
INSERT INTO `nurselevelitem` VALUES (74, 1, 6, 0);
INSERT INTO `nurselevelitem` VALUES (76, 8, 2, 0);
INSERT INTO `nurselevelitem` VALUES (77, 8, 3, 0);
INSERT INTO `nurselevelitem` VALUES (78, 8, 14, 0);
INSERT INTO `nurselevelitem` VALUES (79, 9, 2, 0);
INSERT INTO `nurselevelitem` VALUES (80, 9, 4, 0);
INSERT INTO `nurselevelitem` VALUES (82, 9, 1, 0);
INSERT INTO `nurselevelitem` VALUES (83, 2, 3, 0);
INSERT INTO `nurselevelitem` VALUES (87, 2, 5, 0);
INSERT INTO `nurselevelitem` VALUES (91, 1, 1, 0);
INSERT INTO `nurselevelitem` VALUES (92, 1, 3, 0);
INSERT INTO `nurselevelitem` VALUES (93, 1, 7, 0);

-- ----------------------------
-- Table structure for nurserecord
-- ----------------------------
DROP TABLE IF EXISTS `nurserecord`;
CREATE TABLE `nurserecord`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `customer_id` int NOT NULL COMMENT '客户ID',
  `item_id` int NOT NULL COMMENT '护理项目ID',
  `nursing_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '护理时间',
  `nursing_content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '护理内容',
  `nursing_count` int NOT NULL COMMENT '护理数量',
  `user_id` int NOT NULL COMMENT '护理人员ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of nurserecord
-- ----------------------------
INSERT INTO `nurserecord` VALUES (9, 0, 40, 3, '2024-11-24 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (10, 0, 40, 17, '2023-12-24 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (11, 1, 40, 20, '2023-11-25 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` VALUES (12, 0, 35, 2, '2023-11-24 13:38:37', '按照医嘱进行喂药', 1, 3);
INSERT INTO `nurserecord` VALUES (13, 0, 35, 1, '2024-01-24 13:39:22', '仪器型号：XY09,吸氧服务', 1, 3);
INSERT INTO `nurserecord` VALUES (14, 0, 39, 1, '2023-11-24 18:05:24', '吸氧护理', 1, 2);
INSERT INTO `nurserecord` VALUES (15, 0, 40, 3, '2025-03-20 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (16, 0, 40, 17, '2022-09-25 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (17, 0, 40, 20, '2023-11-24 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` VALUES (18, 0, 40, 2, '2023-05-13 13:38:37', '按照医嘱进行喂药', 1, 3);
INSERT INTO `nurserecord` VALUES (19, 0, 40, 1, '2025-03-26 05:39:22', '仪器型号：XY09,吸氧服务', 1, 3);
INSERT INTO `nurserecord` VALUES (20, 0, 40, 3, '2023-11-24 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (21, 0, 40, 17, '2023-10-04 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (22, 0, 40, 20, '2025-04-09 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` VALUES (23, 0, 40, 2, '2023-11-24 13:38:37', '按照医嘱进行喂药', 1, 3);
INSERT INTO `nurserecord` VALUES (24, 0, 40, 1, '2023-07-30 13:39:22', '仪器型号：XY09,吸氧服务', 1, 3);
INSERT INTO `nurserecord` VALUES (25, 0, 33, 3, '2025-02-15 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (26, 0, 33, 17, '2023-11-24 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (27, 0, 33, 20, '2022-05-12 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` VALUES (28, 0, 33, 2, '2023-11-24 13:38:37', '按照医嘱进行喂药', 1, 3);
INSERT INTO `nurserecord` VALUES (29, 0, 33, 1, '2023-06-16 13:39:22', '仪器型号：XY09,吸氧服务', 1, 3);
INSERT INTO `nurserecord` VALUES (30, 0, 33, 1, '2023-11-24 18:05:24', '吸氧护理', 1, 2);
INSERT INTO `nurserecord` VALUES (31, 0, 33, 3, '2025-04-23 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (32, 0, 27, 17, '2023-11-24 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (33, 0, 27, 20, '2025-01-31 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` VALUES (34, 0, 33, 3, '2024-07-27 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (35, 0, 33, 17, '2023-11-24 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (36, 0, 33, 20, '2024-10-15 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` VALUES (37, 0, 33, 2, '2023-11-24 13:38:37', '按照医嘱进行喂药', 1, 3);
INSERT INTO `nurserecord` VALUES (38, 0, 33, 1, '2023-03-04 13:39:22', '仪器型号：XY09,吸氧服务', 1, 3);
INSERT INTO `nurserecord` VALUES (39, 0, 33, 1, '2023-11-24 18:05:24', '吸氧护理', 1, 2);
INSERT INTO `nurserecord` VALUES (40, 0, 33, 3, '2023-11-24 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (41, 0, 27, 17, '2023-11-24 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (42, 0, 27, 20, '2023-11-24 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` VALUES (43, 0, 27, 2, '2023-11-24 13:38:37', '按照医嘱进行喂药', 1, 3);
INSERT INTO `nurserecord` VALUES (44, 0, 27, 1, '2023-11-24 13:39:22', '仪器型号：XY09,吸氧服务', 1, 3);
INSERT INTO `nurserecord` VALUES (45, 0, 27, 1, '2023-11-24 18:05:24', '吸氧护理', 1, 2);
INSERT INTO `nurserecord` VALUES (46, 0, 27, 3, '2023-11-24 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (47, 0, 27, 17, '2023-11-24 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (48, 0, 35, 20, '2023-11-24 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` VALUES (49, 0, 35, 2, '2023-11-24 13:38:37', '按照医嘱进行喂药', 1, 3);
INSERT INTO `nurserecord` VALUES (50, 0, 35, 1, '2023-11-24 13:39:22', '仪器型号：XY09,吸氧服务', 1, 3);
INSERT INTO `nurserecord` VALUES (51, 0, 35, 1, '2023-11-24 18:05:24', '吸氧护理', 1, 2);
INSERT INTO `nurserecord` VALUES (52, 0, 28, 3, '2023-11-24 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (53, 0, 28, 17, '2023-11-24 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (54, 0, 28, 3, '2023-11-24 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (55, 0, 28, 17, '2023-11-24 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (56, 0, 28, 20, '2023-11-24 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` VALUES (57, 0, 29, 2, '2023-11-24 13:38:37', '按照医嘱进行喂药', 1, 3);
INSERT INTO `nurserecord` VALUES (58, 0, 31, 1, '2023-11-24 13:39:22', '仪器型号：XY09,吸氧服务', 1, 3);
INSERT INTO `nurserecord` VALUES (59, 0, 31, 1, '2023-11-24 18:05:24', '吸氧护理', 1, 2);
INSERT INTO `nurserecord` VALUES (60, 0, 31, 3, '2023-11-24 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (61, 0, 28, 17, '2023-11-24 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (62, 0, 28, 20, '2023-11-24 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` VALUES (63, 0, 28, 2, '2023-11-24 13:38:37', '按照医嘱进行喂药', 1, 3);
INSERT INTO `nurserecord` VALUES (64, 0, 28, 1, '2023-11-24 13:39:22', '仪器型号：XY09,吸氧服务', 1, 3);
INSERT INTO `nurserecord` VALUES (65, 0, 28, 1, '2023-11-24 18:05:24', '吸氧护理', 1, 2);
INSERT INTO `nurserecord` VALUES (66, 0, 34, 3, '2023-11-24 13:36:56', '日常血压测量', 1, 2);
INSERT INTO `nurserecord` VALUES (67, 0, 34, 17, '2023-11-24 13:37:18', '指甲修剪金和保养', 1, 2);
INSERT INTO `nurserecord` VALUES (68, 0, 34, 20, '2023-11-24 13:37:55', '陪同外出购物', 1, 2);
INSERT INTO `nurserecord` VALUES (69, 0, 34, 2, '2023-11-24 13:38:37', '按照医嘱进行喂药', 1, 3);
INSERT INTO `nurserecord` VALUES (70, 0, 34, 1, '2023-11-24 13:39:22', '仪器型号：XY09,吸氧服务', 1, 3);
INSERT INTO `nurserecord` VALUES (71, 0, 34, 1, '2023-11-24 18:05:24', '吸氧护理', 1, 2);
INSERT INTO `nurserecord` VALUES (72, 1, 27, 24, '2025-04-25 10:29:23', '123', 1, 2);
INSERT INTO `nurserecord` VALUES (73, 0, 37, 24, '2025-04-24 07:00:00', '测试内容', 1, 2);
INSERT INTO `nurserecord` VALUES (74, 1, 37, 1, '2025-04-25 10:29:01', '测试时间', 1, 2);
INSERT INTO `nurserecord` VALUES (75, 1, 27, 24, '2025-04-25 10:28:50', '测试时间2', 2, 6);
INSERT INTO `nurserecord` VALUES (77, 1, 27, 24, '2025-04-25 10:29:16', '空时间测试', 0, 1);
INSERT INTO `nurserecord` VALUES (78, 1, 27, 24, '2025-04-25 10:29:15', '空时间测试2', 0, 1);
INSERT INTO `nurserecord` VALUES (79, 1, 27, 24, '2025-04-25 10:29:13', '空时间测试3', 3, 1);
INSERT INTO `nurserecord` VALUES (80, 1, 27, 24, '2025-04-25 10:29:09', '空时间测试4', 4, 1);
INSERT INTO `nurserecord` VALUES (81, 1, 27, 24, '2025-04-25 10:29:07', '空时间测试5', 5, 1);
INSERT INTO `nurserecord` VALUES (82, 1, 27, 24, '2025-04-25 10:29:06', '空时间测试6', 6, 1);
INSERT INTO `nurserecord` VALUES (83, 1, 27, 24, '2025-04-25 10:29:04', '空时间测试', 0, 1);
INSERT INTO `nurserecord` VALUES (84, 1, 27, 24, '2025-04-25 10:27:48', '空时间测试', 0, 1);

-- ----------------------------
-- Table structure for outward
-- ----------------------------
DROP TABLE IF EXISTS `outward`;
CREATE TABLE `outward`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `remarks` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` int NOT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `customer_id` int NOT NULL COMMENT '客户ID',
  `outgoingreason` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '外出事由',
  `outgoingtime` date NOT NULL COMMENT '外出时间',
  `expectedreturntime` date NOT NULL COMMENT '预计回院时间',
  `actualreturntime` date NULL DEFAULT NULL COMMENT '实际回院时间',
  `escorted` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '陪同人',
  `relation` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '与老人关系',
  `escortedtel` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '陪同人电话',
  `auditstatus` int NOT NULL COMMENT '审批状态  0-已提交 1-同意  2-拒绝',
  `auditperson` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '审批人',
  `audittime` date NULL DEFAULT NULL COMMENT '审批时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of outward
-- ----------------------------
INSERT INTO `outward` VALUES (1, NULL, 0, 40, '想家', '2023-12-17', '2023-12-20', NULL, '女儿', '母女', '13312341234', 1, '1', '2023-12-18');
INSERT INTO `outward` VALUES (2, NULL, 0, 33, '出门', '2023-12-17', '2023-12-19', NULL, '儿子', NULL, '13812341234', 2, NULL, NULL);
INSERT INTO `outward` VALUES (3, NULL, 0, 33, '出门', '2023-12-17', '2023-12-20', '2023-12-29', '儿子', NULL, '13812341234', 0, NULL, NULL);
INSERT INTO `outward` VALUES (4, NULL, 1, 33, '出门', '2023-12-18', '2023-12-20', NULL, '儿子', NULL, '13312341234', 0, NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime(6) NOT NULL COMMENT '创建时间',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int NULL DEFAULT NULL COMMENT '更新人',
  `is_deleted` int NOT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '系统角色名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '2023-11-01 17:13:16.000000', NULL, NULL, 0, '系统管理员');
INSERT INTO `role` VALUES (2, '2023-11-01 17:13:13.000000', NULL, NULL, 0, '健康管家');

-- ----------------------------
-- Table structure for rolemenu
-- ----------------------------
DROP TABLE IF EXISTS `rolemenu`;
CREATE TABLE `rolemenu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int NOT NULL COMMENT '角色编号',
  `menu` int NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of rolemenu
-- ----------------------------
INSERT INTO `rolemenu` VALUES (1, 1, 1);
INSERT INTO `rolemenu` VALUES (2, 1, 2);
INSERT INTO `rolemenu` VALUES (3, 1, 3);
INSERT INTO `rolemenu` VALUES (4, 1, 4);
INSERT INTO `rolemenu` VALUES (5, 1, 5);
INSERT INTO `rolemenu` VALUES (6, 1, 6);
INSERT INTO `rolemenu` VALUES (7, 2, 26);
INSERT INTO `rolemenu` VALUES (8, 2, 22);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `room_floor` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '房间楼层',
  `room_no` int NOT NULL COMMENT '房间编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, '一楼', 1001);
INSERT INTO `room` VALUES (2, '一楼', 1002);
INSERT INTO `room` VALUES (3, '一楼', 1003);
INSERT INTO `room` VALUES (4, '一楼', 1004);
INSERT INTO `room` VALUES (5, '一楼', 1005);
INSERT INTO `room` VALUES (6, '一楼', 1006);
INSERT INTO `room` VALUES (7, '一楼', 1007);
INSERT INTO `room` VALUES (8, '一楼', 1008);
INSERT INTO `room` VALUES (9, '一楼', 1009);
INSERT INTO `room` VALUES (10, '一楼', 1010);
INSERT INTO `room` VALUES (11, '一楼', 1011);
INSERT INTO `room` VALUES (12, '一楼', 1012);
INSERT INTO `room` VALUES (13, '一楼', 1013);
INSERT INTO `room` VALUES (14, '一楼', 1014);
INSERT INTO `room` VALUES (15, '一楼', 1015);
INSERT INTO `room` VALUES (16, '一楼', 1016);
INSERT INTO `room` VALUES (17, '一楼', 1017);
INSERT INTO `room` VALUES (18, '一楼', 1018);
INSERT INTO `room` VALUES (19, '二楼', 2001);
INSERT INTO `room` VALUES (20, '二楼', 2002);
INSERT INTO `room` VALUES (21, '二楼', 2003);
INSERT INTO `room` VALUES (22, '二楼', 2004);
INSERT INTO `room` VALUES (23, '二楼', 2005);
INSERT INTO `room` VALUES (24, '二楼', 2006);
INSERT INTO `room` VALUES (25, '二楼', 2007);
INSERT INTO `room` VALUES (26, '二楼', 2008);
INSERT INTO `room` VALUES (27, '二楼', 2009);
INSERT INTO `room` VALUES (28, '二楼', 2010);
INSERT INTO `room` VALUES (29, '二楼', 2011);
INSERT INTO `room` VALUES (30, '二楼', 2012);
INSERT INTO `room` VALUES (31, '二楼', 2013);
INSERT INTO `room` VALUES (32, '二楼', 2014);
INSERT INTO `room` VALUES (33, '二楼', 2015);
INSERT INTO `room` VALUES (34, '二楼', 2016);
INSERT INTO `room` VALUES (35, '二楼', 2017);
INSERT INTO `room` VALUES (36, '二楼', 2018);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` date NOT NULL COMMENT '创建时间',
  `create_by` int NOT NULL COMMENT '创建者',
  `update_time` date NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int NULL DEFAULT NULL COMMENT '更新者',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `nickname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '真实姓名',
  `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '系统账号',
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `sex` int NOT NULL COMMENT '性别（0：女  1：男）',
  `email` varchar(254) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '电话号码',
  `role_id` int NOT NULL COMMENT '系统角色编号（1-管理员 2-健康管家）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2023-11-02', 0, '2023-11-02', 0, 0, 'admin', 'admin', 'admin', 1, 'zhangxing@s.cq', '13545453412', 1);
INSERT INTO `user` VALUES (2, '2023-11-03', 0, '2023-11-03', 0, 0, '吴伟', 'wuwei', 'wuwei', 1, 'wuwei@s.cq', '13517178987', 2);
INSERT INTO `user` VALUES (3, '2023-11-21', 0, '2023-11-21', 0, 0, '陈小明', 'chenxiaoming', 'chenxiaoming', 0, 'chenxiaoming@s.cq', '18767890987', 2);
INSERT INTO `user` VALUES (4, '2023-11-21', 0, '2023-11-21', 0, 0, '张兴', 'zhangxing', 'zhangxing', 1, 'zhangxing@s.cq', '18878675643', 2);
INSERT INTO `user` VALUES (5, '2023-11-21', 0, '2023-11-21', 0, 0, '王年华', 'wangnianhua', 'wangnianhua', 0, 'wangnianhua@s.cq', '13412128976', 2);
INSERT INTO `user` VALUES (6, '2023-11-21', 0, '2023-11-21', 0, 0, '吴优', 'wuyou', 'wuyou', 0, 'wuyou@s.cq', '13567452789', 2);

SET FOREIGN_KEY_CHECKS = 1;
