/*GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' IDENTIFIED BY 'password' WITH GRANT OPTION;*/

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `javaee_idea_keeper` ;

CREATE SCHEMA IF NOT EXISTS `javaee_idea_keeper` DEFAULT CHARACTER SET utf8 ;
USE `javaee_idea_keeper`;


DROP TABLE IF EXISTS `clients` ;

CREATE TABLE IF NOT EXISTS `clients` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` CHAR(50),
  `password` CHAR(50),
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS `users` ;

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` CHAR(50),
  `password` CHAR(150),
  `name` CHAR(50),
  `surname` CHAR(50),
  `email` CHAR(50),
  `access_level` CHAR(50),

  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS `ideas` ;

CREATE TABLE IF NOT EXISTS `ideas` (
  `idea_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` CHAR(50),
  `description` CHAR(50),
  PRIMARY KEY (`idea_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS `activities0` ;

CREATE TABLE IF NOT EXISTS `activities0` (
  `activity_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`activity_id`, `user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS `attempts` ;

CREATE TABLE IF NOT EXISTS `attempts` (
  `attempt_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) UNSIGNED NOT NULL,
  `login` CHAR(50),
  `attempts_count` int(1),
  `last_modified` datetime,

  PRIMARY KEY (`attempt_id`, `user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS `tasklists` ;

CREATE TABLE IF NOT EXISTS `tasklists` (
  `tasklist_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) UNSIGNED NOT NULL,
  `task_id` INT(11) UNSIGNED NOT NULL,
  `created_date` datetime NOT NULL,
  `end_date` datetime,

  PRIMARY KEY (`tasklist_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
