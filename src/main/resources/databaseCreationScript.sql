/*GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' IDENTIFIED BY 'password' WITH GRANT OPTION;*/

use javaee_idea_keeper;

INSERT INTO `javaee_idea_keeper`.`users` VALUES (2, 'USER','email@email.lv', 'loginaa', 'nameaa', 'passwordaa','surnameaa');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (3, 'USER','email@email.lv', 'loginbb', 'namebb', 'passwordbb','surnamebb');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (4, 'USER','email@email.lv', 'logincc', 'namecc', 'passwordcc','surnamecc');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (1, 'ADMIN','admin@admin.lv', 'admin', 'admin', 'admin','admin');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (5, 'USER','email@admin.lv', 'logindd', 'namedd', 'passworddd','surnamedd');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (6, 'USER','email@admin.lv', 'loginee', 'nameee', 'passwordee','surnameee');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (7, 'USER','email@admin.lv', 'loginff', 'nameff', 'passwordff','surnameff');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (8, 'USER','email@admin.lv', 'logingg', 'namegg', 'passwordgg','surnamegg');


INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (1,'description1', 'title1', 2);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (2,'description2', 'title2', 2);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (3,'description3', 'title3', 3);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (4,'description4', 'title4', 3);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (5,'description5', 'title5', 4);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (6,'description6', 'title6', 4);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (7,'description7', 'title7', 4);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (8,'description7', 'title7', 5);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (9,'description7', 'title7', 5);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (10,'description7', 'title7', 6);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (11,'description7', 'title7', 6);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (12,'description7', 'title7', 6);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (13,'description7', 'title7', 7);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (14,'description7', 'title7', 2);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (15,'description7', 'title7', 2);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (16,'description1', 'title1', 2);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (17,'description1', 'title2', 2);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (18,'description1', 'title2', 2);
INSERT INTO `javaee_idea_keeper`.`ideas` VALUES (19,'description5', 'title3', 2);



select * from attempts;
select * from ideas;
select * from users;