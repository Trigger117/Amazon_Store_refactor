-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: amazon
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENfT=@@CHARACTER_SET_CLIENT */;
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
-- Current Database: `amazon`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sys`;

--
-- Table structure for table `ruoli_utenti`
--

DROP TABLE IF EXISTS `ruoli_utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ruoli_utenti` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `ruolo` varchar(20) NOT NULL,
                                `last_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                `user_modify` int(11) NOT NULL DEFAULT '1',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `id_UNIQUE` (`id`),
                                UNIQUE KEY `ruolo_UNIQUE` (`ruolo`),
                                KEY `fk_utenti_ruoli_idx` (`user_modify`),
                                CONSTRAINT `fk_utenti_ruoli` FOREIGN KEY (`user_modify`) REFERENCES `utenti` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruoli_utenti`
--

LOCK TABLES `ruoli_utenti` WRITE;
/*!40000 ALTER TABLE `ruoli_utenti` DISABLE KEYS */;
INSERT INTO `ruoli_utenti` VALUES (1,'Amministratore','2019-10-03 07:57:16',1),(2,'Utente','2019-10-03 07:57:16',1);
/*!40000 ALTER TABLE `ruoli_utenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utenti` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `nome` varchar(30) NOT NULL,
                          `cognome` varchar(30) NOT NULL,
                          `username` varchar(20) NOT NULL,
                          `password` varchar(250) NOT NULL,
                          `indirizzo` varchar(100) NOT NULL,
                          `stato_attivo` tinyint(1) NOT NULL DEFAULT '1',
                          `ruolo` int(11) NOT NULL,
                          `last_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `id_UNIQUE` (`id`),
                          UNIQUE KEY `username_UNIQUE` (`username`),
                          KEY `fk_ruoli_utenti_idx` (`ruolo`),
                          CONSTRAINT `fk_ruoli_utenti` FOREIGN KEY (`ruolo`) REFERENCES `ruoli_utenti` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
INSERT INTO `utenti` VALUES (1,'admin','admin','admin','$2a$10$oH5xqoZfgbCumc/BXquiCeC7lWNRT/J3pv1lCQ4vVEul5byhgEqlm','Lungo Dora Pietro Colletta 81, 10153 Torino TO',1,1,'2021-04-28 07:57:16'),(2,'user','user','user','$2a$10$U21cGxutcAIYRGroRXwEZOxKH1PvglBtO/nhFPdS38MwRZE6BeT.q','Viale dell\'Industria, 62, 35129 Padova PD',1,2,'2021-04-28 07:57:16');


