/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

---
--- Table structure for table `user_profiles`
---

DROP TABLE IF EXISTS `user_profiles`;
CREATE TABLE `user_profiles` (
  `uid` bigint(20) NOT NULL,
  `real_name` varchar(32),
  `nickname` varchar(16),
  `gender` int DEFAULT 0,
  `birthday` date DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `office_phone` varchar(32) DEFAULT NULL,
  `signature` varchar(128) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_profiles` WRITE;
INSERT INTO `user_profiles`
 VALUES (1, '管理员', '智能制造2025', 1, '1977-1-1', 'admin@xyz.com', '010-8888888', '中国智能制造2025崛起', -1);
UNLOCK TABLES;

--
-- Table structure for table `sequences`
--

DROP TABLE IF EXISTS `sequences`;
CREATE TABLE `sequences` (
  `sequence` varchar(255) DEFAULT NULL,
  `last_used` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sequences`
--

LOCK TABLES `sequences` WRITE;
INSERT INTO `sequences` VALUES ('users',10001);
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `avatar` longtext,
  `im_token` longtext,
  `nosalt_password` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `phone` varchar(128) NOT NULL,
  `registered_at` datetime NOT NULL,
  `salt` varchar(16) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `username` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_users_username` (`username`),
  UNIQUE KEY `UK_users_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
INSERT INTO `users`
 VALUES (1,'/images/avatar.jpg',NULL,
 '4db302395ab91fffc0cfbfae2dee01fd67712811a4657f978ff1c42e1b170f6e',
 '58b6747b7802e2902901d60827266fd2151de7385c2ef491f1f9988ae75bcf32cde1698c55b36034',
 'rPEVDZ8ykEcFguryLNj+QA==','2018-10-09 13:06:00','67d73285bfeddf54',0,1,'admin');
UNLOCK TABLES;

---
--- Table structure for table `roles`
---

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) NOT NULL,
  `show_name` varchar(32) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
INSERT INTO `roles`
 VALUES (-1,'ROLE_ADMIN','系统管理员','系统超级管理员','2018-10-25 13:06:00','2018-10-25 13:06:00');
UNLOCK TABLES;


---
--- Table structure for table `user_roles`
---

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
INSERT INTO `user_roles` (`user_id`,`role_id`)
 VALUES (1,-1); 
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
