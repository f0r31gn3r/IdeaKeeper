/*GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' IDENTIFIED BY 'password' WITH GRANT OPTION;*/

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema javaee_idea_keeper
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `javaee_idea_keeper` ;

-- -----------------------------------------------------
-- Schema javaee_idea_keeper
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `javaee_idea_keeper` DEFAULT CHARACTER SET utf8 ;
USE `javaee_idea_keeper` ;

-- -----------------------------------------------------
-- Table `javaee_idea_keeper`.`activities0`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `javaee_idea_keeper`.`activities0` ;

CREATE TABLE IF NOT EXISTS `javaee_idea_keeper`.`activities0` (
  `activity_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`activity_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `javaee_idea_keeper`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `javaee_idea_keeper`.`users` ;

CREATE TABLE IF NOT EXISTS `javaee_idea_keeper`.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `access_level` CHAR(50) NULL DEFAULT NULL,
  `email` CHAR(50) NULL DEFAULT NULL,
  `login` CHAR(50) NULL DEFAULT NULL,
  `name` CHAR(50) NULL DEFAULT NULL,
  `password` CHAR(150) NULL DEFAULT NULL,
  `surname` CHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

INSERT INTO `javaee_idea_keeper`.`users` VALUES (2, 'USER','email@email.lv', 'user', 'name', 'user','surname');



-- -----------------------------------------------------
-- Table `javaee_idea_keeper`.`attempts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `javaee_idea_keeper`.`attempts` ;

CREATE TABLE IF NOT EXISTS `javaee_idea_keeper`.`attempts` (
  `attempt_id` INT(11) NOT NULL AUTO_INCREMENT,
  `attempts_count` INT(1) NULL DEFAULT NULL,
  `last_modified` DATETIME NULL DEFAULT NULL,
  `login` CHAR(50) NULL DEFAULT NULL,
  `user_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`attempt_id`),
  INDEX `FK_rx9t2dq47ubovynv4m75fiuls` (`user_id` ASC),
  CONSTRAINT `FK_rx9t2dq47ubovynv4m75fiuls`
    FOREIGN KEY (`user_id`)
    REFERENCES `javaee_idea_keeper`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `javaee_idea_keeper`.`clients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `javaee_idea_keeper`.`clients` ;

CREATE TABLE IF NOT EXISTS `javaee_idea_keeper`.`clients` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` CHAR(50) NOT NULL,
  `password` CHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `javaee_idea_keeper`.`ideas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `javaee_idea_keeper`.`ideas` ;

CREATE TABLE IF NOT EXISTS `javaee_idea_keeper`.`ideas` (
  `idea_id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` CHAR(50) NULL DEFAULT NULL,
  `title` CHAR(50) NULL DEFAULT NULL,
  `user_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idea_id`),
  INDEX `FK_n2wlf9nq1cc1nnvxthv2jtx4s` (`user_id` ASC),
  CONSTRAINT `FK_n2wlf9nq1cc1nnvxthv2jtx4s`
    FOREIGN KEY (`user_id`)
    REFERENCES `javaee_idea_keeper`.`users` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `javaee_idea_keeper`.`tasklists`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `javaee_idea_keeper`.`tasklists` ;

CREATE TABLE IF NOT EXISTS `javaee_idea_keeper`.`tasklists` (
  `tasklist_id` INT(11) NOT NULL AUTO_INCREMENT,
  `created_date` DATETIME NOT NULL,
  `end_date` DATETIME NULL DEFAULT NULL,
  `task_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`tasklist_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

use javaee_idea_keeper;

INSERT INTO `javaee_idea_keeper`.`users` VALUES (2, 'USER','email@email.lv', 'user1', 'name', 'user','surname');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (3, 'USER','email@email.lv', 'user2', 'name', 'user','surname');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (4, 'USER','email@email.lv', 'user3', 'name', 'user','surname');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (1, 'ADMIN','admin@admin.lv', 'admin', 'admin', 'admin','admin');


INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (1, 'title1','description1', 2);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (2, 'title2','description2', 2);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (3, 'title3','description3', 3);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (4, 'title4','description4', 3);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (5, 'title5','description5', 4);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (6, 'title6','description6', 4);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (7, 'title7','description7', 4);



select * from attempts;
select * from ideas;
select * from users;