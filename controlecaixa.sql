CREATE DATABASE  IF NOT EXISTS `controlecaixa` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `controlecaixa`;
-- MySQL dump 10.13  Distrib 5.7.30, for Win32 (AMD64)
--
-- Host: localhost    Database: controlecaixa
-- ------------------------------------------------------
-- Server version	5.7.30-log

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
-- Table structure for table `caixa`
--

DROP TABLE IF EXISTS `caixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caixa` (
  `idcaixa` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  `saldoInicial` double DEFAULT NULL,
  PRIMARY KEY (`idcaixa`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caixa`
--

LOCK TABLES `caixa` WRITE;
/*!40000 ALTER TABLE `caixa` DISABLE KEYS */;
INSERT INTO `caixa` VALUES (1,'CAIXA MARIA',150),(2,'CAIXA FRANK',0);
/*!40000 ALTER TABLE `caixa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimentacao`
--

DROP TABLE IF EXISTS `movimentacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimentacao` (
  `idmovimentacao` int(11) NOT NULL AUTO_INCREMENT,
  `data` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`idmovimentacao`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimentacao`
--

LOCK TABLES `movimentacao` WRITE;
/*!40000 ALTER TABLE `movimentacao` DISABLE KEYS */;
INSERT INTO `movimentacao` VALUES (1,'01/01/2023','Entrada','TESTE',5),(2,'06/01/2023','Entrada','TESTE 2',40),(3,'06/01/2023','Entrada','TESTE 3',35),(4,'06/01/2023','Entrada','FORMATAÇÃO COMPUTADOR',50),(5,'06/01/2023','Saida','ESCOVA DE DENTE',30),(6,'06/01/2023','Saida','TESTANDO UPDATE',25),(7,'05/01/2023','Entrada','TESTANDO DATA FORMATADA',10);
/*!40000 ALTER TABLE `movimentacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimentacao_caixa`
--

DROP TABLE IF EXISTS `movimentacao_caixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimentacao_caixa` (
  `id_caixa` int(11) NOT NULL,
  `id_mov` int(11) NOT NULL,
  KEY `FK_Cod_Caixa_idx` (`id_caixa`),
  KEY `FK_Cod_Mov_idx` (`id_mov`),
  CONSTRAINT `FK_Cod_Caixa` FOREIGN KEY (`id_caixa`) REFERENCES `caixa` (`idcaixa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Cod_Mov` FOREIGN KEY (`id_mov`) REFERENCES `movimentacao` (`idmovimentacao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimentacao_caixa`
--

LOCK TABLES `movimentacao_caixa` WRITE;
/*!40000 ALTER TABLE `movimentacao_caixa` DISABLE KEYS */;
INSERT INTO `movimentacao_caixa` VALUES (1,1),(1,2),(2,3),(2,4),(2,5),(1,6),(1,7);
/*!40000 ALTER TABLE `movimentacao_caixa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'controlecaixa'
--

--
-- Dumping routines for database 'controlecaixa'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-06  9:08:25
