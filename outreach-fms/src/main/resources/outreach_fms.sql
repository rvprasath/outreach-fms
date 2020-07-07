/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.20 : Database - outreach_fms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`outreach_fms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `outreach_fms`;

/*Table structure for table `m_role` */

DROP TABLE IF EXISTS `m_role`;

CREATE TABLE `m_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `m_role` */

insert  into `m_role`(`id`,`role`,`role_id`) values 
(1,'ADMIN',1),
(2,'PMO',2),
(3,'POC',3),
(4,'PARTICIPANTS',4);

/*Table structure for table `t_enrollment_not_attended` */

DROP TABLE IF EXISTS `t_enrollment_not_attended`;

CREATE TABLE `t_enrollment_not_attended` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` varchar(50) DEFAULT NULL,
  `event_name` varchar(50) DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  `beneficiary_name` varchar(250) DEFAULT NULL,
  `base_location` varchar(100) DEFAULT NULL,
  `event_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_enrollment_not_attended` */

/*Table structure for table `t_enrollment_unregistered` */

DROP TABLE IF EXISTS `t_enrollment_unregistered`;

CREATE TABLE `t_enrollment_unregistered` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` varchar(50) DEFAULT NULL,
  `event_name` varchar(50) DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  `beneficiary_name` varchar(250) DEFAULT NULL,
  `base_location` varchar(100) DEFAULT NULL,
  `event_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_enrollment_unregistered` */

/*Table structure for table `t_event_information` */

DROP TABLE IF EXISTS `t_event_information`;

CREATE TABLE `t_event_information` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `employee_name` varchar(100) DEFAULT NULL,
  `event_id` varchar(100) DEFAULT NULL,
  `event_name` varchar(100) DEFAULT NULL,
  `event_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `base_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `beneficiary_name` varchar(100) DEFAULT NULL,
  `council_name` varchar(100) DEFAULT NULL,
  `event_date` datetime DEFAULT NULL,
  `volunteer_hours` int DEFAULT NULL,
  `travel_hours` int DEFAULT NULL,
  `lives_impacted` int DEFAULT NULL,
  `business_unit` varchar(100) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  `iiep_category` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_event_information` */

/*Table structure for table `t_event_summary` */

DROP TABLE IF EXISTS `t_event_summary`;

CREATE TABLE `t_event_summary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `poc_id` int DEFAULT NULL,
  `poc_name` varchar(100) DEFAULT NULL,
  `event_id` varchar(100) DEFAULT NULL,
  `event_name` varchar(100) DEFAULT NULL,
  `month` varchar(10) DEFAULT NULL,
  `event_description` text,
  `base_location` varchar(255) DEFAULT NULL,
  `beneficiary_name` varchar(100) DEFAULT NULL,
  `venue_address` varchar(255) DEFAULT NULL,
  `council_name` varchar(100) DEFAULT NULL,
  `project` varchar(100) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `event_date` date DEFAULT NULL,
  `total_no_of_volunteer` int DEFAULT NULL,
  `volunteer_hours` int DEFAULT NULL,
  `total_travel_hours` int DEFAULT NULL,
  `overall_volunteering_hours` int DEFAULT NULL,
  `lives_impacted` int DEFAULT NULL,
  `activity_type` int DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `poc_contact_no` int DEFAULT NULL,
  `overall_rating` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_event_summary` */

/*Table structure for table `t_feedback_choice_answer` */

DROP TABLE IF EXISTS `t_feedback_choice_answer`;

CREATE TABLE `t_feedback_choice_answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `answer` text,
  `question` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `question` (`question`),
  CONSTRAINT `t_feedback_choice_answer_ibfk_1` FOREIGN KEY (`question`) REFERENCES `t_feedback_question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_feedback_choice_answer` */

/*Table structure for table `t_feedback_question` */

DROP TABLE IF EXISTS `t_feedback_question`;

CREATE TABLE `t_feedback_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` text,
  `question_type` char(5) DEFAULT NULL,
  `participant_type` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `rating_question` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rating_question` (`rating_question`),
  CONSTRAINT `t_feedback_question_ibfk_1` FOREIGN KEY (`rating_question`) REFERENCES `t_feedback_rating_question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_feedback_question` */

/*Table structure for table `t_feedback_rating_question` */

DROP TABLE IF EXISTS `t_feedback_rating_question`;

CREATE TABLE `t_feedback_rating_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rating_question` text,
  `like_question` text,
  `dislike_question` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_feedback_rating_question` */

/*Table structure for table `t_participant_feedback_status` */

DROP TABLE IF EXISTS `t_participant_feedback_status`;

CREATE TABLE `t_participant_feedback_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` varchar(100) DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  `employee_name` varchar(100) DEFAULT NULL,
  `employee_email` varchar(100) DEFAULT NULL,
  `event_status_code` int DEFAULT NULL,
  `event_status` varchar(25) DEFAULT NULL,
  `is_feedback_sent` tinyint(1) DEFAULT '0',
  `is_feedback_completed` tinyint(1) DEFAULT '0',
  `feedback_date` datetime DEFAULT NULL,
  `random_value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_participant_feedback_status` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `role` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`employee_id`,`name`,`password`,`email`,`role`) values 
(1,787958,'Admin','123','admin@test.com',1);

/*Table structure for table `user_feedback_answer` */

DROP TABLE IF EXISTS `user_feedback_answer`;

CREATE TABLE `user_feedback_answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int DEFAULT NULL,
  `event_id` varchar(100) DEFAULT NULL,
  `feedback_type` char(5) DEFAULT NULL,
  `question_id` int DEFAULT NULL,
  `choice_answer` text,
  `rating` int DEFAULT NULL,
  `like_answer` text,
  `dislike_answer` text,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user_feedback_answer` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
