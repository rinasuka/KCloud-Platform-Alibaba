CREATE TABLE `gen_field_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `column_type` varchar(200) DEFAULT NULL COMMENT '字段类型',
  `attr_type` varchar(200) DEFAULT NULL COMMENT '属性类型',
  `package_name` varchar(200) DEFAULT NULL COMMENT '属性包名',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
  `editor` bigint(20) DEFAULT NULL COMMENT '编辑人',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` tinyint(1) NOT NULL COMMENT '1已删除 0未删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `column_type` (`column_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字段类型管理';

INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1391677542887788567, 'datetime', 'Date', 'java.util.Date', 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:45', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535858679453085698, 'date', 'Date', 'java.util.Date', 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:45', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535859148908949506, 'tinyint', 'Integer', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:45', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535859326311231489, 'smallint', 'Integer', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:46', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535859588534923265, 'mediumint', 'Integer', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535878154046939137, 'int', 'Integer', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535881096963563522, 'integer', 'Integer', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535881356595175426, 'bigint', 'Long', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535886982654205953, 'float', 'Float', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535887129341599746, 'double', 'Double', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535887276649750530, 'decimal', 'BigDecimal', 'java.math.BigDecimal', 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535887450809835521, 'bit', 'Boolean', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535887779873955841, 'char', 'String', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535887940687765505, 'varchar', 'String', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535888146045083649, 'tinytext', 'String', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1535888341252186114, 'text', 'String', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:47', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1537444981390794754, 'mediumtext', 'String', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1537447512825225218, 'longtext', 'String', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1538469199368712193, 'timestamp', 'Date', 'java.util.Date', 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1539250224424394753, 'NUMBER', 'Integer', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1539251440843857922, 'BINARY_INTEGER', 'Integer', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1539265093588545538, 'BINARY_FLOAT', 'Float', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1539396453854629890, 'BINARY_DOUBLE', 'Double', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1539402478934646785, 'VARCHAR2', 'String', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1539615028590673921, 'NVARCHAR', 'String', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1539989085181972481, 'NVARCHAR2', 'String', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1540000886082768897, 'CLOB', 'String', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1540001496240754689, 'int8', 'Long', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1540001809446211586, 'int4', 'Integer', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1540001992288505857, 'int2', 'Integer', NULL, 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);
INSERT INTO `gen_field_type`(`id`, `column_type`, `attr_type`, `package_name`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`) VALUES (1545035717690912769, 'numeric', 'BigDecimal', 'java.math.BigDecimal', 1341620898007281665, 1341620898007281665, '2022-07-25 10:16:54', '2022-07-25 10:21:48', 0);