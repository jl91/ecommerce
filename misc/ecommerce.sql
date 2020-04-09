CREATE DATABASE  IF NOT EXISTS `ecommerce` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ecommerce`;
-- MySQL dump 10.13  Distrib 8.0.18, for Linux (x86_64)
--
-- Host: 172.18.0.2    Database: ecommerce
-- ------------------------------------------------------
-- Server version	5.7.28

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
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned NOT NULL,
  `cart_id` int(10) unsigned NOT NULL,
  `product_snapshot` json NOT NULL,
  `value` float(10,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cart_items_cart1_idx` (`cart_id`),
  KEY `fk_cart_items_products1_idx` (`product_id`),
  CONSTRAINT `fk_cart_items_cart1` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_items_products1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` VALUES (1,3,1,'{\"id\": 3, \"sku\": \"2fdgdf\", \"name\": \"3ssd\", \"value\": 123.22, \"createdAt\": {\"hour\": 0, \"nano\": 0, \"year\": 2020, \"month\": \"APRIL\", \"minute\": 24, \"second\": 33, \"dayOfWeek\": \"MONDAY\", \"dayOfYear\": 97, \"chronology\": {\"id\": \"ISO\", \"calendarType\": \"iso8601\"}, \"dayOfMonth\": 6, \"monthValue\": 4}, \"deletedAt\": null, \"updatedAt\": null, \"description\": \"sdsd\"}',246.44,2,'2020-04-07 01:56:53',NULL,NULL),(2,33,1,'{\"id\": 33, \"sku\": \"2fdgdf\", \"name\": \"3ssd\", \"value\": 123.22, \"createdAt\": {\"hour\": 13, \"nano\": 0, \"year\": 2020, \"month\": \"APRIL\", \"minute\": 20, \"second\": 17, \"dayOfWeek\": \"MONDAY\", \"dayOfYear\": 97, \"chronology\": {\"id\": \"ISO\", \"calendarType\": \"iso8601\"}, \"dayOfMonth\": 6, \"monthValue\": 4}, \"deletedAt\": null, \"updatedAt\": null, \"description\": \"sdsd\"}',246.44,2,'2020-04-07 01:57:29',NULL,NULL),(3,3,2,'{\"id\": 3, \"sku\": \"2fdgdf\", \"name\": \"3ssd\", \"value\": 123.22, \"createdAt\": {\"hour\": 0, \"nano\": 0, \"year\": 2020, \"month\": \"APRIL\", \"minute\": 24, \"second\": 33, \"dayOfWeek\": \"MONDAY\", \"dayOfYear\": 97, \"chronology\": {\"id\": \"ISO\", \"calendarType\": \"iso8601\"}, \"dayOfMonth\": 6, \"monthValue\": 4}, \"deletedAt\": null, \"updatedAt\": null, \"description\": \"sdsd\"}',246.44,2,'2020-04-07 02:31:31',NULL,NULL),(4,34,1,'{\"id\": 34, \"sku\": \"2fdgdf\", \"name\": \"3ssd\", \"value\": 123.22, \"createdAt\": {\"hour\": 23, \"nano\": 0, \"year\": 2020, \"month\": \"APRIL\", \"minute\": 33, \"second\": 35, \"dayOfWeek\": \"MONDAY\", \"dayOfYear\": 97, \"chronology\": {\"id\": \"ISO\", \"calendarType\": \"iso8601\"}, \"dayOfMonth\": 6, \"monthValue\": 4}, \"deletedAt\": null, \"updatedAt\": null, \"description\": \"sdsd\"}',246.44,2,'2020-04-07 02:48:34',NULL,NULL),(5,34,6,'{\"id\": 34, \"sku\": \"2fdgdf\", \"name\": \"3ssd\", \"value\": 123.22, \"createdAt\": {\"hour\": 23, \"nano\": 0, \"year\": 2020, \"month\": \"APRIL\", \"minute\": 33, \"second\": 35, \"dayOfWeek\": \"MONDAY\", \"dayOfYear\": 97, \"chronology\": {\"id\": \"ISO\", \"calendarType\": \"iso8601\"}, \"dayOfMonth\": 6, \"monthValue\": 4}, \"deletedAt\": null, \"updatedAt\": null, \"description\": \"sdsd\"}',246.44,2,'2020-04-07 03:07:53',NULL,NULL),(6,35,6,'{\"id\": 35, \"sku\": \"2fdgdf\", \"name\": \"3ssd\", \"value\": 123.22, \"createdAt\": {\"hour\": 23, \"nano\": 0, \"year\": 2020, \"month\": \"APRIL\", \"minute\": 36, \"second\": 57, \"dayOfWeek\": \"MONDAY\", \"dayOfYear\": 97, \"chronology\": {\"id\": \"ISO\", \"calendarType\": \"iso8601\"}, \"dayOfMonth\": 6, \"monthValue\": 4}, \"deletedAt\": null, \"updatedAt\": null, \"description\": \"sdsd\"}',246.44,2,'2020-04-07 03:12:06','2020-04-07 03:21:21','2020-04-07 03:21:21'),(7,34,7,'{\"id\": 34, \"sku\": \"2fdgdf\", \"name\": \"3ssd\", \"value\": 123.22, \"createdAt\": {\"hour\": 23, \"nano\": 0, \"year\": 2020, \"month\": \"APRIL\", \"minute\": 33, \"second\": 35, \"dayOfWeek\": \"MONDAY\", \"dayOfYear\": 97, \"chronology\": {\"id\": \"ISO\", \"calendarType\": \"iso8601\"}, \"dayOfMonth\": 6, \"monthValue\": 4}, \"deletedAt\": null, \"updatedAt\": null, \"description\": \"sdsd\"}',246.44,2,'2020-04-07 03:20:38',NULL,NULL),(8,34,8,'{\"id\": 34, \"sku\": \"2fdgdf\", \"name\": \"3ssd\", \"value\": 123.22, \"createdAt\": {\"hour\": 23, \"nano\": 0, \"year\": 2020, \"month\": \"APRIL\", \"minute\": 33, \"second\": 35, \"dayOfWeek\": \"MONDAY\", \"dayOfYear\": 97, \"chronology\": {\"id\": \"ISO\", \"calendarType\": \"iso8601\"}, \"dayOfMonth\": 6, \"monthValue\": 4}, \"deletedAt\": null, \"updatedAt\": null, \"description\": \"sdsd\"}',246.44,2,'2020-04-07 03:20:41',NULL,NULL),(9,35,6,'{\"id\": 35, \"sku\": \"2fdgdf\", \"name\": \"3ssd\", \"value\": 123.22, \"createdAt\": {\"hour\": 23, \"nano\": 0, \"year\": 2020, \"month\": \"APRIL\", \"minute\": 36, \"second\": 57, \"dayOfWeek\": \"MONDAY\", \"dayOfYear\": 97, \"chronology\": {\"id\": \"ISO\", \"calendarType\": \"iso8601\"}, \"dayOfMonth\": 6, \"monthValue\": 4}, \"deletedAt\": null, \"updatedAt\": null, \"description\": \"sdsd\"}',246.44,2,'2020-04-07 03:22:04','2020-04-07 03:22:37','2020-04-07 03:22:37');
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `status` enum('opened','closed','canceled') NOT NULL,
  `total` float(10,0) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cart_users1_idx` (`user_id`),
  CONSTRAINT `fk_cart_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (1,1,'opened',493,'2020-04-07 01:56:53','2020-04-07 02:48:34','2020-04-07 02:07:42'),(2,1,'opened',246,'2020-04-07 02:31:31','2020-04-07 02:31:31',NULL),(6,1,'opened',246,'2020-04-07 03:07:53','2020-04-07 03:22:37',NULL),(7,1,'opened',246,'2020-04-07 03:20:38','2020-04-07 03:20:38',NULL),(8,1,'opened',246,'2020-04-07 03:20:41','2020-04-07 03:20:41',NULL);
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (49),(49),(49),(49),(49),(49),(49),(49);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_inventory`
--

DROP TABLE IF EXISTS `product_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_inventory` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned NOT NULL,
  `quantity` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_inventory_products1_idx` (`product_id`),
  CONSTRAINT `fk_product_inventory_products1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_inventory`
--

LOCK TABLES `product_inventory` WRITE;
/*!40000 ALTER TABLE `product_inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sku` varchar(45) NOT NULL,
  `name` varchar(128) NOT NULL,
  `description` mediumtext NOT NULL,
  `value` float(10,2) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (3,'2fdgdf','3ssd','sdsd',123.22,'2020-04-06 03:24:33','2020-04-07 02:42:57','2020-04-07 02:42:57'),(33,'2fdgdf','3ssd','sdsd',123.22,'2020-04-06 16:20:17','2020-04-07 02:45:21','2020-04-07 02:45:21'),(34,'prod1','produto 1','lorem ipsum dolor sit amet',123.22,'2020-04-07 02:33:35',NULL,NULL),(35,'prod2','produto 2','lorem ipsum dolor sit amet',123.22,'2020-04-07 02:36:57',NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cart_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `value` float(10,2) NOT NULL,
  `payment_method` json DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_purchase order history_cart1_idx` (`cart_id`),
  KEY `fk_purchase order history_users1_idx` (`user_id`),
  CONSTRAINT `fk_purchase order history_cart1` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchase order history_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order`
--

LOCK TABLES `purchase_order` WRITE;
/*!40000 ALTER TABLE `purchase_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order_history`
--

DROP TABLE IF EXISTS `purchase_order_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order_history` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `purchase_order_id` int(10) unsigned NOT NULL,
  `status` enum('paid','unpaid','canceled') NOT NULL,
  `created_at` datetime NOT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_purchase_order_history_purchase order1_idx` (`purchase_order_id`),
  CONSTRAINT `fk_purchase_order_history_purchase order1` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order_history`
--

LOCK TABLES `purchase_order_history` WRITE;
/*!40000 ALTER TABLE `purchase_order_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_order_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_MASTER','2020-04-08 04:13:33',NULL,NULL),(2,'ROLER_CUSTOMER','2020-04-08 04:13:33',NULL,NULL);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(10) unsigned NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`role_id`),
  KEY `fk_users_role1_idx` (`role_id`),
  CONSTRAINT `fk_users_role1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'master','$2a$10$RJWvwoxEVsgXGoLV6QVYCe4c46FmrWlMNnVXvz3qqSmFiFMsQhu26','master','2020-04-08 04:13:33',NULL,NULL),(2,2,'customer','$2a$10$yhBiIaNV3GIWcPaxwe7oX.rJbp17cDBIhN1obSnW1WMukC.H5OW9S','customer','2020-04-08 04:13:33',NULL,NULL);
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

-- Dump completed on 2020-04-09  4:04:41
