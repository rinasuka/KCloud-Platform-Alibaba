/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.30.133(老寇）
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : 192.168.30.133:3306
 Source Schema         : kcloud_platform_alibaba_tenant

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 12/09/2023 15:50:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for boot_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `boot_sys_dict`;
CREATE TABLE `boot_sys_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` bigint NOT NULL COMMENT '创建者',
  `editor` bigint NULL DEFAULT NULL COMMENT '编辑人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识 1已删除 0未删除',
  `version` int NOT NULL DEFAULT 0 COMMENT '版本号',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签',
  `value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '值',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型',
  `sort` int NOT NULL DEFAULT 1 COMMENT '排序',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1578676566256455695 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boot_sys_dict
-- ----------------------------
INSERT INTO `boot_sys_dict` VALUES (1578676566256455694, 1537114827246292998, 1537114827246292998, '2023-02-15 13:36:31', '2023-02-15 14:37:43', 0, 1, 0, 0, '多租户字典名称', '123', 'TENANT_TYPE', 10, '多租户字典测试');

-- ----------------------------
-- Table structure for boot_sys_message
-- ----------------------------
DROP TABLE IF EXISTS `boot_sys_message`;
CREATE TABLE `boot_sys_message`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` bigint NOT NULL COMMENT '创建人',
  `editor` bigint NULL DEFAULT NULL COMMENT '编辑人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识 1已删除 0未删除',
  `version` int NOT NULL DEFAULT 0 COMMENT '版本号',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `title` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `type` tinyint NOT NULL DEFAULT 0 COMMENT '类型 0通知 1提醒',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1587737320788005183 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boot_sys_message
-- ----------------------------
INSERT INTO `boot_sys_message` VALUES (1587737320788005179, 1537114827246292998, NULL, '2023-02-15 14:50:31', '2023-02-15 14:50:31', 0, 0, 0, 0, '测试', '## 二级标题测试', 0);
INSERT INTO `boot_sys_message` VALUES (1587737320788005182, 1537114827246293001, NULL, '2023-02-15 15:58:47', '2023-02-15 15:58:47', 0, 0, 0, 0, '测试', '测试', 0);

-- ----------------------------
-- Table structure for boot_sys_message_detail
-- ----------------------------
DROP TABLE IF EXISTS `boot_sys_message_detail`;
CREATE TABLE `boot_sys_message_detail`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` bigint NOT NULL COMMENT '创建人',
  `editor` bigint NULL DEFAULT NULL COMMENT '编辑人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识 1已删除 0未删除',
  `version` int NOT NULL DEFAULT 0 COMMENT '版本号',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `message_id` bigint NOT NULL COMMENT '消息ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `read_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '已读标识 0未读 1已读',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_read_flag_user_id`(`read_flag` ASC, `user_id` ASC) USING BTREE COMMENT '已读_用户编号_索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1587737321048052083 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息详情' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boot_sys_message_detail
-- ----------------------------
INSERT INTO `boot_sys_message_detail` VALUES (1587737321048052078, 1537114827246292998, NULL, '2023-02-15 14:50:32', '2023-02-15 14:50:32', 0, 1, 0, 0, 1587737320788005179, 1537114827246292998, 1);
INSERT INTO `boot_sys_message_detail` VALUES (1587737321048052082, 1537114827246293001, NULL, '2023-02-15 15:58:47', '2023-02-15 15:58:47', 0, 1, 0, 0, 1587737320788005182, 1537114827246293001, 1);

-- ----------------------------
-- Table structure for boot_sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `boot_sys_oss`;
CREATE TABLE `boot_sys_oss`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` bigint NOT NULL COMMENT '创建人',
  `editor` bigint NULL DEFAULT NULL COMMENT '编辑人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识 1已删除 0未删除',
  `version` int NOT NULL DEFAULT 0 COMMENT '版本号',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `endpoint` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '终端地址',
  `region` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区域',
  `access_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '访问密钥',
  `secret_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密钥',
  `bucket_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '桶名',
  `path_style_access_enabled` tinyint(1) NOT NULL COMMENT '路径样式访问 1已开启 0未启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1537444981390794757 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boot_sys_oss
-- ----------------------------
INSERT INTO `boot_sys_oss` VALUES (1537444981390794754, 1341620898007281665, 1341620898007281665, '2022-11-02 14:35:46', '2023-02-13 18:11:15', 0, 22, 0, 0, 'Aliyun OSS', 'https://oss-cn-shenzhen.aliyuncs.com', '', 'LTAIPIOiuCK4ZK3o', 'dtYg8khtfEUM3FlV2FvVH6bHyezdqi', 'koushenhai', 0);

-- ----------------------------
-- Table structure for boot_sys_oss_log
-- ----------------------------
DROP TABLE IF EXISTS `boot_sys_oss_log`;
CREATE TABLE `boot_sys_oss_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `creator` bigint NOT NULL COMMENT '创建人',
  `editor` bigint NULL DEFAULT NULL COMMENT '编辑人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识 1已删除 0未删除',
  `version` int NOT NULL DEFAULT 0 COMMENT '版本号',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `md5` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'md5标识',
  `size` bigint NOT NULL COMMENT '大小',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '存储日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of boot_sys_oss_log
-- ----------------------------

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history`  (
  `installed_rank` int NOT NULL,
  `version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `script` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `checksum` int NULL DEFAULT NULL,
  `installed_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`) USING BTREE,
  INDEX `flyway_schema_history_s_idx`(`success` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history` VALUES (1, '1.0', 'init', 'SQL', 'V1.0__init.sql', 941848137, 'root', '2023-06-30 22:59:57', 75, 1);

SET FOREIGN_KEY_CHECKS = 1;
