/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2022-01-22 16:03:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `priceLevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of property
-- ----------------------------
INSERT INTO `property` VALUES ('1', '叫了个鸡', '1000', '500', '2');
INSERT INTO `property` VALUES ('2', '张三丰饺子馆', '2300', '1500', '3');
INSERT INTO `property` VALUES ('3', '永和大王', '580', '3000', '1');
INSERT INTO `property` VALUES ('4', '肯德基', '6000', '200', '4');
