-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ecommerce
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ecommerce` ;

-- -----------------------------------------------------
-- Schema ecommerce
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecommerce` DEFAULT CHARACTER SET utf8 ;
USE `ecommerce` ;

-- -----------------------------------------------------
-- Table `ecommerce`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`role` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`role` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `deleted_at` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`users` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` INT UNSIGNED NOT NULL,
  `username` VARCHAR(128) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `deleted_at` DATETIME NULL,
  PRIMARY KEY (`id`, `role_id`),
  CONSTRAINT `fk_users_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `ecommerce`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_users_role1_idx` ON `ecommerce`.`users` (`role_id` ASC) ;


-- -----------------------------------------------------
-- Table `ecommerce`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`products` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`products` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `sku` VARCHAR(45) NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `value` FLOAT(10,2) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `deleted_at` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecommerce`.`product_inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`product_inventory` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`product_inventory` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` INT UNSIGNED NOT NULL,
  `quantity` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `deleted_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_product_inventory_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `ecommerce`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_product_inventory_products1_idx` ON `ecommerce`.`product_inventory` (`product_id` ASC) ;


-- -----------------------------------------------------
-- Table `ecommerce`.`cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`cart` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`cart` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `status` ENUM('opened', 'closed', 'canceled') NOT NULL,
  `total` FLOAT(10,0) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `deleted_at` DATETIME NULL,
  PRIMARY KEY (`id`, `user_id`),
  CONSTRAINT `fk_cart_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ecommerce`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_cart_users1_idx` ON `ecommerce`.`cart` (`user_id` ASC) ;


-- -----------------------------------------------------
-- Table `ecommerce`.`purchase order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`purchase order` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`purchase order` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `cart_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `value` FLOAT(10,2) NOT NULL,
  `payment_method` JSON NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `deleted_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_purchase order history_cart1`
    FOREIGN KEY (`cart_id`)
    REFERENCES `ecommerce`.`cart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchase order history_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ecommerce`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_purchase order history_cart1_idx` ON `ecommerce`.`purchase order` (`cart_id` ASC) ;

CREATE INDEX `fk_purchase order history_users1_idx` ON `ecommerce`.`purchase order` (`user_id` ASC) ;


-- -----------------------------------------------------
-- Table `ecommerce`.`cart_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`cart_items` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`cart_items` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` INT UNSIGNED NOT NULL,
  `cart_id` INT UNSIGNED NOT NULL,
  `product_snapshot` JSON NOT NULL,
  `value` FLOAT(10,2) NOT NULL,
  `quantity` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `deleted_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cart_items_cart1`
    FOREIGN KEY (`cart_id`)
    REFERENCES `ecommerce`.`cart` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_items_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `ecommerce`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_cart_items_cart1_idx` ON `ecommerce`.`cart_items` (`cart_id` ASC) ;

CREATE INDEX `fk_cart_items_products1_idx` ON `ecommerce`.`cart_items` (`product_id` ASC) ;


-- -----------------------------------------------------
-- Table `ecommerce`.`purchase_order_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ecommerce`.`purchase_order_history` ;

CREATE TABLE IF NOT EXISTS `ecommerce`.`purchase_order_history` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `purchase_order_id` INT UNSIGNED NOT NULL,
  `status` ENUM('paid', 'unpaid', 'canceled') NOT NULL,
  `created_at` DATETIME NOT NULL,
  `deleted_at` DATETIME NULL,
  PRIMARY KEY (`id`, `purchase_order_id`),
  CONSTRAINT `fk_purchase_order_history_purchase order1`
    FOREIGN KEY (`purchase_order_id`)
    REFERENCES `ecommerce`.`purchase order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_purchase_order_history_purchase order1_idx` ON `ecommerce`.`purchase_order_history` (`purchase_order_id` ASC) ;


-- -----------------------------------------------------
-- Data for table `ecommerce`.`role`
-- -----------------------------------------------------
START TRANSACTION;
USE `ecommerce`;
INSERT INTO `ecommerce`.`role` (`id`, `name`, `created_at`, `updated_at`, `deleted_at`) VALUES (1, 'master', now(), NULL, NULL);
INSERT INTO `ecommerce`.`role` (`id`, `name`, `created_at`, `updated_at`, `deleted_at`) VALUES (2, 'customer', now(), NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ecommerce`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `ecommerce`;
INSERT INTO `ecommerce`.`users` (`id`, `role_id`, `username`, `password`, `name`, `created_at`, `updated_at`, `deleted_at`) VALUES (1, 1, 'master', '$2y$10$.9GOG64fABTY.XFY1XK6k.idbhctYVMDffYj03ap/Cf5HpC5UNGhu', 'Usuário Master', now(), NULL, NULL);
INSERT INTO `ecommerce`.`users` (`id`, `role_id`, `username`, `password`, `name`, `created_at`, `updated_at`, `deleted_at`) VALUES (1, 2, 'customer', '$2y$10$aZ8B9KR7Jo0z2MztvHsYHuH7KzDYqSGAeERN5BLP740NuiCJNaGCS', 'Usuário Customer', now(), NULL, NULL);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
