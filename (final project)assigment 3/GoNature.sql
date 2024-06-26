-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: gonature
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `canceledorders`
--

DROP TABLE IF EXISTS `canceledorders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `canceledorders` (
  `park_name` varchar(255) NOT NULL,
  `date_time` timestamp NULL DEFAULT NULL,
  `number_visitors` int DEFAULT NULL,
  `order_type` varchar(255) DEFAULT NULL,
  `cancel_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canceledorders`
--

LOCK TABLES `canceledorders` WRITE;
/*!40000 ALTER TABLE `canceledorders` DISABLE KEYS */;
INSERT INTO `canceledorders` VALUES ('Park A','2024-03-31 10:00:00',2,'GROUP','manual'),('Park A','2024-03-31 08:00:00',100,'SMALLGROUP','manual'),('Park A','2024-03-31 11:00:00',100,'SMALLGROUP','manual'),('Park A','2024-03-31 11:00:00',8,'GROUP','manual'),('Park A','2024-03-31 08:00:00',3,'SMALLGROUP','automatic'),('Park A','2024-03-30 09:00:00',5,'SMALLGROUP','notcancelednotcome'),('Park A','2024-04-01 08:00:00',100,'SMALLGROUP','manual'),('Park A','2024-04-01 08:00:00',5,'SMALLGROUP','manual'),('Park A','2024-04-01 09:00:00',5,'SMALLGROUP','manual'),('Park B','2024-04-01 09:00:00',100,'SMALLGROUP','manual'),('Park B','2024-04-01 09:00:00',4,'SMALLGROUP','manual');
/*!40000 ALTER TABLE `canceledorders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `ID` varchar(50) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `WorkerID` varchar(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Role` varchar(50) DEFAULT NULL,
  `UserName` varchar(50) DEFAULT NULL,
  `PassWord` varchar(50) DEFAULT NULL,
  `ParkID` varchar(20) DEFAULT NULL,
  `AuthorityLevel` varchar(50) DEFAULT NULL,
  `logged_in` int DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES ('1','John','Doe','W123','john.doe@example.com','Manager','johndoe','password123','Park A','parkmanager',0),('2','Jane','Smith','W456','jane.smith@example.com','Assistant','janesmith','password456','Park A','departmentmanager',1),('3','Mike','Johnson','W789','mike.johnson@example.com','Security','mikejohnson','password789','Park C','servicerepresntitive',0),('4','Pier','Mbariky','W101112','pier.m2001@gmail.com','manager','pierMbariky','12345','Park A','parkworker',0);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupleaderrequest`
--

DROP TABLE IF EXISTS `groupleaderrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groupleaderrequest` (
  `id` varchar(255) NOT NULL,
  `approved` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupleaderrequest`
--

LOCK TABLES `groupleaderrequest` WRITE;
/*!40000 ALTER TABLE `groupleaderrequest` DISABLE KEYS */;
INSERT INTO `groupleaderrequest` VALUES ('207718826',1),('222222222',1);
/*!40000 ALTER TABLE `groupleaderrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nonfulltimesreport`
--

DROP TABLE IF EXISTS `nonfulltimesreport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nonfulltimesreport` (
  `report_id` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `least_full` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nonfulltimesreport`
--

LOCK TABLES `nonfulltimesreport` WRITE;
/*!40000 ALTER TABLE `nonfulltimesreport` DISABLE KEYS */;
INSERT INTO `nonfulltimesreport` VALUES (2,'2024-02-26',' from 8:00 to 010:00 wasn\'t full',NULL),(2,'2024-02-26',' from 13:00 to 18:00 wasn\'t full',NULL),(2,'2024-02-27',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-02-28',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-02-29',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-01',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-02',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-03',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-04',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-05',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-06',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-07',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-08',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-09',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-10',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-11',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-12',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-13',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-14',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-15',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-16',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-17',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-18',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-19',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-20',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-21',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-22',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-23',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-24',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-25',' from 8:00 to 18:00 wasn\'t full',NULL),(2,'2024-03-26',' from 8:00 to 010:00 wasn\'t full',NULL),(2,'2024-03-26',' from 13:00 to 18:00 wasn\'t full',NULL),(3,'2024-02-26',' from 8:00 to 010:00 wasn\'t full',NULL),(3,'2024-02-26',' from 13:00 to 18:00 wasn\'t full',NULL),(3,'2024-02-27',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-02-28',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-02-29',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-01',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-02',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-03',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-04',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-05',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-06',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-07',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-08',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-09',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-10',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-11',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-12',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-13',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-14',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-15',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-16',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-17',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-18',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-19',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-20',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-21',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-22',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-23',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-24',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-25',' from 8:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-26',' from 8:00 to 010:00 wasn\'t full',NULL),(3,'2024-03-26',' from 13:00 to 18:00 wasn\'t full',NULL),(3,'2024-03-27',' from 8:00 to 18:00 wasn\'t full','31,31,31,29,29,31,31,31,31,31,31'),(5,'2024-02-26',' from 8:00 to 010:00 wasn\'t full',NULL),(5,'2024-02-26',' from 13:00 to 18:00 wasn\'t full',NULL),(5,'2024-02-27',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-02-28',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-02-29',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-01',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-02',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-03',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-04',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-05',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-06',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-07',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-08',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-09',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-10',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-11',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-12',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-13',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-14',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-15',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-16',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-17',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-18',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-19',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-20',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-21',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-22',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-23',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-24',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-25',' from 8:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-26',' from 8:00 to 010:00 wasn\'t full',NULL),(5,'2024-03-26',' from 13:00 to 18:00 wasn\'t full',NULL),(5,'2024-03-27',' from 8:00 to 18:00 wasn\'t full','31,31,31,29,29,31,31,31,31,31,31'),(6,'2024-02-26',' from 8:00 to 010:00 wasn\'t full',NULL),(6,'2024-02-26',' from 13:00 to 18:00 wasn\'t full',NULL),(6,'2024-02-27',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-02-28',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-02-29',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-01',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-02',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-03',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-04',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-05',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-06',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-07',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-08',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-09',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-10',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-11',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-12',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-13',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-14',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-15',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-16',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-17',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-18',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-19',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-20',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-21',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-22',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-23',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-24',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-25',' from 8:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-26',' from 8:00 to 010:00 wasn\'t full',NULL),(6,'2024-03-26',' from 13:00 to 18:00 wasn\'t full',NULL),(6,'2024-03-27',' from 8:00 to 18:00 wasn\'t full','31,31,31,29,29,31,31,31,31,31,31'),(7,'2024-02-26',' from 8:00 to 010:00 wasn\'t full',NULL),(7,'2024-02-26',' from 13:00 to 18:00 wasn\'t full',NULL),(7,'2024-02-27',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-02-28',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-02-29',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-01',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-02',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-03',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-04',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-05',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-06',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-07',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-08',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-09',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-10',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-11',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-12',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-13',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-14',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-15',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-16',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-17',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-18',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-19',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-20',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-21',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-22',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-23',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-24',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-25',' from 8:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-26',' from 8:00 to 010:00 wasn\'t full',NULL),(7,'2024-03-26',' from 13:00 to 18:00 wasn\'t full',NULL),(7,'2024-03-27',' from 8:00 to 18:00 wasn\'t full','31,31,31,29,29,31,31,31,31,31,31'),(8,'2024-02-26',' from 8:00 to 010:00 wasn\'t full',NULL),(8,'2024-02-26',' from 13:00 to 18:00 wasn\'t full',NULL),(8,'2024-02-27',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-02-28',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-02-29',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-01',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-02',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-03',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-04',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-05',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-06',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-07',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-08',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-09',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-10',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-11',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-12',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-13',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-14',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-15',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-16',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-17',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-18',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-19',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-20',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-21',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-22',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-23',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-24',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-25',' from 8:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-26',' from 8:00 to 010:00 wasn\'t full',NULL),(8,'2024-03-26',' from 13:00 to 18:00 wasn\'t full',NULL),(8,'2024-03-27',' from 8:00 to 18:00 wasn\'t full','31,31,31,29,29,31,31,31,31,31,31'),(10,'2024-02-29',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-01',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-02',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-03',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-04',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-05',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-06',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-07',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-08',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-09',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-10',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-11',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-12',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-13',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-14',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-15',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-16',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-17',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-18',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-19',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-20',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-21',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-22',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-23',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-24',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-25',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-26',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-27',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-28',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-29',' from 8:00 to 18:00 wasn\'t full',NULL),(10,'2024-03-30',' from 8:00 to 18:00 wasn\'t full','31,31,31,31,31,31,31,31,31,31,31'),(12,'2024-02-29',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-01',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-02',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-03',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-04',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-05',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-06',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-07',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-08',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-09',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-10',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-11',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-12',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-13',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-14',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-15',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-16',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-17',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-18',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-19',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-20',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-21',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-22',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-23',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-24',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-25',' from 8:00 to 10:00 wasn\'t full',NULL),(12,'2024-03-25',' from 14:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-26',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-27',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-28',' from 8:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-29',' from 8:00 to 10:00 wasn\'t full',NULL),(12,'2024-03-29',' from 11:00 to 18:00 wasn\'t full',NULL),(12,'2024-03-30',' from 8:00 to 18:00 wasn\'t full','31,31,29,30,30,30,31,31,31,31,0');
/*!40000 ALTER TABLE `nonfulltimesreport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `park_name` varchar(255) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `number_visitors` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `order_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parks`
--

DROP TABLE IF EXISTS `parks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parks` (
  `park_name` varchar(100) NOT NULL,
  `max_visitors` int DEFAULT NULL,
  `current_visitors` int DEFAULT NULL,
  `time_limit_visit` int DEFAULT NULL,
  `number_difference` int DEFAULT NULL,
  PRIMARY KEY (`park_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parks`
--

LOCK TABLES `parks` WRITE;
/*!40000 ALTER TABLE `parks` DISABLE KEYS */;
INSERT INTO `parks` VALUES ('Park A',1000,54,6,20),('Park B',150,30,5,30),('Park C',100,20,4,30);
/*!40000 ALTER TABLE `parks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `id` int NOT NULL AUTO_INCREMENT,
  `park_name` varchar(255) DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `report_type` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (12,'Park A','johndoe','nonfulltimeslast30days','2024-03-31 23:12:11'),(13,'Park A','johndoe','TotalVisitorsReport','2024-03-31 23:13:18');
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `requests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `park_name` varchar(255) NOT NULL,
  `sender` varchar(255) NOT NULL,
  `request_type` varchar(255) NOT NULL,
  `request_info` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `totalvisitorsreport`
--

DROP TABLE IF EXISTS `totalvisitorsreport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `totalvisitorsreport` (
  `report_id` int DEFAULT NULL,
  `individualv` varchar(255) DEFAULT NULL,
  `groupv` varchar(255) DEFAULT NULL,
  `familyv` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `totalvisitorsreport`
--

LOCK TABLES `totalvisitorsreport` WRITE;
/*!40000 ALTER TABLE `totalvisitorsreport` DISABLE KEYS */;
INSERT INTO `totalvisitorsreport` VALUES (1,'0,0,0,0,0,0,0','0,0,0,0,0,0,0','0,100,0,0,0,0,0'),(1,'0,0,0,0,0,0,0','0,0,0,0,0,0,0','0,100,0,0,0,0,0'),(4,'0,0,0,0,0,0,0','0,0,0,0,0,0,0','0,100,0,0,0,0,0'),(9,'0,0,0,0,0,0,0','0,0,0,0,0,0,0','0,100,0,0,0,0,0'),(11,'0,0,0,0,0,0,0','0,0,0,0,0,0,0','0,0,0,0,0,0,9'),(13,'0,0,0,0,0,0,0','0,0,0,0,100,0,0','100,0,0,0,0,0,0');
/*!40000 ALTER TABLE `totalvisitorsreport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `name` varchar(100) DEFAULT NULL,
  `id` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `logged_in` int DEFAULT '0',
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (NULL,'207718826','pierpier@gmail.com','0508526289',0,'regular'),(NULL,'208819926','mbarikypier48@gmail.com','0508526289',0,'regular'),(NULL,'209918822','pier.m2001@gmail.com','0508526289',0,'regular'),('pier','209918826','redangold@gmail.com','0508525290',0,'regular');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visits`
--

DROP TABLE IF EXISTS `visits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visits` (
  `id` int NOT NULL AUTO_INCREMENT,
  `park_name` varchar(255) DEFAULT NULL,
  `order_type` varchar(255) DEFAULT NULL,
  `number_visitors` int DEFAULT NULL,
  `entry_time` varchar(50) DEFAULT NULL,
  `exit_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visits`
--

LOCK TABLES `visits` WRITE;
/*!40000 ALTER TABLE `visits` DISABLE KEYS */;
INSERT INTO `visits` VALUES (95,'Park A','GROUP',100,'2024-03-29 10:30:45','2024-03-29 10:40:13'),(96,'Park A','SMALLGROUP',100,'2024-03-25 10:20:45','2024-03-25 13:00:00');
/*!40000 ALTER TABLE `visits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waitlist`
--

DROP TABLE IF EXISTS `waitlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `waitlist` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `orderer_id` int DEFAULT NULL,
  `park_name` varchar(255) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `number_visitors` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `order_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waitlist`
--

LOCK TABLES `waitlist` WRITE;
/*!40000 ALTER TABLE `waitlist` DISABLE KEYS */;
INSERT INTO `waitlist` VALUES (109,207718820,'Park B','2024-04-01 12:00:00','0508526289',7,'pier.m2001@gmail.com','GROUP');
/*!40000 ALTER TABLE `waitlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-01  0:31:57
