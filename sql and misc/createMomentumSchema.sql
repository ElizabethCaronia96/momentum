
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: momentum_mysql
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `orders`
--
use momentum;
DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` varchar(10) NOT NULL,
  `strategy_type` varchar(10) NOT NULL,
  `strategy_id` int(11) NOT NULL,
  `datetime_added` datetime NOT NULL,
  `status` varchar(45) NOT NULL,
  `num_stocks` int default null,
  `entry_type` varchar(45) DEFAULT NULL,
  `entry_datetime` datetime DEFAULT NULL,
  `entry_price` double DEFAULT NULL,
  `exit_type` varchar(45) DEFAULT NULL,
  `exit_datetime` datetime DEFAULT NULL,
  `exit_price` double DEFAULT NULL,
  `profit_loss_percent` double DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strat_2ma`
--

DROP TABLE IF EXISTS `strat_2ma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strat_2ma` (
  `strategy_id` int(11) NOT NULL AUTO_INCREMENT,
  `long_avg_range` int(11) NOT NULL,
  `short_avg_range` int(11) NOT NULL,
  PRIMARY KEY (`strategy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strat_2ma`
--

LOCK TABLES `strat_2ma` WRITE;
/*!40000 ALTER TABLE `strat_2ma` DISABLE KEYS */;
/*!40000 ALTER TABLE `strat_2ma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strat_bb`
--

DROP TABLE IF EXISTS `strat_bb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strat_bb` (
  `strategy_id` int(11) NOT NULL AUTO_INCREMENT,
  `moving_avg_range` int(11) NOT NULL,
  `std_dev_multiple` double NOT NULL,
  PRIMARY KEY (`strategy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strat_bb`
--

LOCK TABLES `strat_bb` WRITE;
/*!40000 ALTER TABLE `strat_bb` DISABLE KEYS */;
/*!40000 ALTER TABLE `strat_bb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-30 19:29:53
