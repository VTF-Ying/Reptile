/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2020-03-19 10:42:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book_content
-- ----------------------------
DROP TABLE IF EXISTS `book_content`;
CREATE TABLE `book_content` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) NOT NULL,
  `book_url` varchar(255) NOT NULL,
  `book_chapter_content` text NOT NULL,
  `enabled_flag` int(11) NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for details_book
-- ----------------------------
DROP TABLE IF EXISTS `details_book`;
CREATE TABLE `details_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_details_id` int(11) NOT NULL,
  `book_chapter` varchar(225) NOT NULL,
  `book_chapter_url` varchar(225) NOT NULL,
  `enabled_flag` int(11) NOT NULL,
  `up_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4759 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for index_book
-- ----------------------------
DROP TABLE IF EXISTS `index_book`;
CREATE TABLE `index_book` (
  `book_id` bigint(20) NOT NULL,
  `book_url` varchar(255) NOT NULL,
  `book_name` varchar(255) NOT NULL,
  `enabled_flag` int(11) NOT NULL,
  `book_update_date` varchar(30) NOT NULL,
  `book_type` varchar(10) NOT NULL,
  `book_author` varchar(30) NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for transition_book
-- ----------------------------
DROP TABLE IF EXISTS `transition_book`;
CREATE TABLE `transition_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_url` varchar(225) NOT NULL,
  `book_name` varchar(32) NOT NULL,
  `book_author` varchar(32) NOT NULL,
  `up_time` varchar(32) NOT NULL,
  `enabled_flag` int(11) NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
