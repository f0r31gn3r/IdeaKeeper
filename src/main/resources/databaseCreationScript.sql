/*GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' IDENTIFIED BY 'password' WITH GRANT OPTION;*/

use javaee_idea_keeper;

INSERT INTO `javaee_idea_keeper`.`users` VALUES (2, 'USER','email@email.lv', 'loginraa', 'nameaa', 'passwordaa','surnameaa');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (3, 'USER','email@email.lv', 'loginbb', 'namebb', 'passwordbb','surnamebb');
INSERT INTO `javaee_idea_keeper`.`users` VALUES (4, 'USER','email@email.lv', 'logincc', 'namecc', 'passwordcc','surnamecc');
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