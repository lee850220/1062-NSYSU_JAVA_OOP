CREATE SCHEMA `java_hw13` ;
CREATE TABLE `java_hw13`.`user` (
  `id` VARCHAR(12) NOT NULL,
  `password` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_UNIQUE` (`id` ASC));
CREATE TABLE `java_hw13`.`money` (
  `user` VARCHAR(12) NOT NULL,
  `balance` INT(8)  NOT NULL,
  PRIMARY KEY (`user`));
INSERT INTO `java_hw13`.`money` (`user`, `balance`) VALUES ('B043040035', '8000');
INSERT INTO `java_hw13`.`money` (`user`, `balance`) VALUES ('B123456789', '13000');
INSERT INTO `java_hw13`.`money` (`user`, `balance`) VALUES ('M043040001', '300');
INSERT INTO `java_hw13`.`money` (`user`, `balance`) VALUES ('produce101', '4000');
INSERT INTO `java_hw13`.`user` (`id`, `password`) VALUES ('B043040035', '123456');
INSERT INTO `java_hw13`.`user` (`id`, `password`) VALUES ('B123456789', '666666');
INSERT INTO `java_hw13`.`user` (`id`, `password`) VALUES ('M043040001', '000000');
INSERT INTO `java_hw13`.`user` (`id`, `password`) VALUES ('produce101', '111111');
