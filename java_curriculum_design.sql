/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : java_curriculum_design

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-12-13 10:50:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for institute
-- ----------------------------
DROP TABLE IF EXISTS `institute`;
CREATE TABLE `institute` (
  `instituteId` int(11) NOT NULL AUTO_INCREMENT COMMENT '二级学院主键',
  `instituteName` varchar(200) NOT NULL COMMENT '二级学院名称',
  PRIMARY KEY (`instituteId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of institute
-- ----------------------------
INSERT INTO `institute` VALUES ('1', '宝石与艺术设计学院');
INSERT INTO `institute` VALUES ('2', '外国语学院');
INSERT INTO `institute` VALUES ('3', '商学院');
INSERT INTO `institute` VALUES ('4', '机械化工学院');
INSERT INTO `institute` VALUES ('5', '教师教育学院');
INSERT INTO `institute` VALUES ('6', '大数据与软件工程学院');
INSERT INTO `institute` VALUES ('7', '电子与信息工程学院');
INSERT INTO `institute` VALUES ('8', '文学与传媒学院');
INSERT INTO `institute` VALUES ('9', '法学与公共管理学院');
INSERT INTO `institute` VALUES ('10', '马克思主义学院');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentNo` char(12) NOT NULL COMMENT '学生学号',
  `instituteId` int(11) DEFAULT NULL COMMENT '所属学院外键',
  `studentName` varchar(255) NOT NULL COMMENT '姓名',
  `sex` char(2) DEFAULT NULL COMMENT '性别',
  `politicsType` int(11) DEFAULT '3' COMMENT '政治面貌：1-党员，2-团员，3-无',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `score` int(11) DEFAULT NULL,
  `firstChooseId` int(11) DEFAULT NULL COMMENT '第一志愿外键',
  `secondChooseId` int(11) DEFAULT NULL COMMENT '第二志愿外键',
  `admissionSituation` int(11) DEFAULT NULL COMMENT '1-第一志愿被录取；2-第二志愿被录取',
  PRIMARY KEY (`studentNo`),
  KEY `firstChooseId` (`firstChooseId`),
  KEY `secondChooseId` (`secondChooseId`),
  KEY `instituteId` (`instituteId`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`firstChooseId`) REFERENCES `subject` (`subjectId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`secondChooseId`) REFERENCES `subject` (`subjectId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_3` FOREIGN KEY (`instituteId`) REFERENCES `institute` (`instituteId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('201700208203', '4', '黄结', '男', '1', '18376031350@163.com', '18376031350', '80', '1', '1', null);
INSERT INTO `student` VALUES ('201700208233', '6', '莫淋茸', '女', '2', 'mly@163.com', '125252525', '90', null, null, null);

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `subjectId` int(11) NOT NULL AUTO_INCREMENT COMMENT '题目编号',
  `teacherId` char(8) NOT NULL COMMENT '发布选题的教师工号',
  `subjectName` varchar(255) NOT NULL COMMENT '题目名称',
  `subjectNum` int(11) NOT NULL COMMENT '发布数量',
  `alreadyFillNum` int(11) DEFAULT '0' COMMENT '已被学生填报的数量',
  `subjectAsk` varchar(255) DEFAULT NULL COMMENT '要求',
  PRIMARY KEY (`subjectId`),
  KEY `teacherId` (`teacherId`),
  CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`teacherId`) REFERENCES `teacher` (`teacherId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('1', '20150528', '人事管理系统', '120', '0', '需要人事管理专业');
INSERT INTO `subject` VALUES ('2', '20150528', '食堂点餐系统', '100', '0', '无');
INSERT INTO `subject` VALUES ('3', '20150528', '教务系统', '20', '0', '三好学生');
INSERT INTO `subject` VALUES ('4', '20150528', '儿子', '22224444', '0', '1112222');
INSERT INTO `subject` VALUES ('5', '20150528', '44', '5566', '0', 'hhhhhh哈哈哈');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacherId` char(8) NOT NULL COMMENT '教师工号',
  `titleId` int(11) NOT NULL COMMENT '职称外键',
  `instituteId` int(11) NOT NULL COMMENT '所属学院外键',
  `teacherName` varchar(255) NOT NULL,
  `sex` char(2) DEFAULT NULL COMMENT '性别',
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `mySubjectNum` int(11) DEFAULT '0' COMMENT '个人已发布选题数',
  PRIMARY KEY (`teacherId`),
  KEY `titleId` (`titleId`),
  KEY `instituteId` (`instituteId`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`titleId`) REFERENCES `title` (`titleId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_ibfk_2` FOREIGN KEY (`instituteId`) REFERENCES `institute` (`instituteId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('20113344', '1', '6', '莫智懿', '男', '189898989', 'mzy@163.com', '0');
INSERT INTO `teacher` VALUES ('20150528', '1', '3', '黄结', '男', '18376031350', '583403411@qq.com', '5');

-- ----------------------------
-- Table structure for title
-- ----------------------------
DROP TABLE IF EXISTS `title`;
CREATE TABLE `title` (
  `titleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '职称Id',
  `titleName` varchar(255) NOT NULL,
  PRIMARY KEY (`titleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of title
-- ----------------------------
INSERT INTO `title` VALUES ('1', '教授');
INSERT INTO `title` VALUES ('2', '副教授');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` char(12) DEFAULT NULL COMMENT '使用学号或者工号直接登陆到个人',
  `PassWord` varchar(255) DEFAULT NULL,
  `userType` int(11) NOT NULL DEFAULT '3' COMMENT '1-管理员，2-老师，3-学生(默认)',
  `CreatedDate` date DEFAULT NULL,
  `LastmodifyDate` date DEFAULT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '1', '2018-12-02', '2018-12-02');
INSERT INTO `user` VALUES ('2', '20150528', 'admin', '2', '2018-12-06', '2018-12-06');
INSERT INTO `user` VALUES ('3', '201700208203', 'admin', '3', '2018-11-01', '2018-11-01');
INSERT INTO `user` VALUES ('4', '20113344', 'mzy1122', '2', '2018-12-13', '2018-12-13');
INSERT INTO `user` VALUES ('5', '201700208233', 'mly1122', '3', '2018-12-13', '2018-12-13');
