-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: booksystem
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

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

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `bookId` varchar(40) NOT NULL,
  `bookName` varchar(50) NOT NULL,
  `bookAuthor` varchar(50) NOT NULL,
  `bookPrice` decimal(10,0) NOT NULL,
  `publishName` varchar(30) NOT NULL,
  `now_num` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES ('0691b240-8693-4d96-9c3d-02f56e171d30','抽象圣经','孙笑川',8000,'抽象出版社',4,4),('6c8df092-24c8-41f7-bd1f-2b42f5505a4b','sjiaj','a d',111,'adad',11,11),('9200fae2-a4fa-4fd4-835d-d40ec586a020','xxx','xxx',3,'xxx',2,3),('a78a03c0-62d6-4c1a-b708-c75e6708e769','sjiaj','a d',111,'adad',11,11);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `borrow` (
  `borrowId` int(11) NOT NULL AUTO_INCREMENT,
  `bookId` varchar(40) NOT NULL,
  `userId` varchar(10) NOT NULL,
  `startTime` date NOT NULL,
  `finishTime` date NOT NULL,
  `flag` int(11) NOT NULL,
  PRIMARY KEY (`borrowId`),
  KEY `FK_BORROW_USERID` (`userId`),
  KEY `FK_BORROW_BOOKID` (`bookId`),
  CONSTRAINT `FK_BORROW_BOOKID` FOREIGN KEY (`bookId`) REFERENCES `books` (`bookId`),
  CONSTRAINT `FK_BORROW_USERID` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES (16,'9200fae2-a4fa-4fd4-835d-d40ec586a020','2016210787','2018-12-16','2019-01-16',4),(17,'0691b240-8693-4d96-9c3d-02f56e171d30','2016210787','2018-12-16','2019-01-16',4),(18,'9200fae2-a4fa-4fd4-835d-d40ec586a020','2016210787','2018-12-16','2019-01-16',1);
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL AUTO_INCREMENT,
  `bookId` varchar(40) NOT NULL,
  `userId` varchar(40) NOT NULL,
  `commentText` text,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`commentId`),
  KEY `FK_COMMENT_USERID` (`userId`),
  KEY `FK_COMMENT_BOOKID` (`bookId`),
  CONSTRAINT `FK_BOOKID` FOREIGN KEY (`bookId`) REFERENCES `books` (`bookId`),
  CONSTRAINT `FK_COMMENT_BOOKID` FOREIGN KEY (`bookId`) REFERENCES `books` (`bookId`),
  CONSTRAINT `FK_COMMENT_USERID` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  CONSTRAINT `FK_USERID` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (6,'9200fae2-a4fa-4fd4-835d-d40ec586a020','2016210787','xxx','2018-12-16 18:40:19');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userName` varchar(50) NOT NULL,
  `userId` varchar(10) NOT NULL,
  `userPassword` varchar(100) NOT NULL,
  `userEmail` varchar(30) NOT NULL,
  `userType` int(11) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Doggy Sun','2016210787','199883','123@qq.com',2),('qwe','2016210888','123','123@qq.com',1),('xxx','2016210889','123','123@qq.com',2),('xxx','2016210899','123456','123@qq.com',1),('sb','2016210979','123','123@qq.com',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-17  0:19:31
