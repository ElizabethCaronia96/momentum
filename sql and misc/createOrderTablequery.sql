use momentum;



CREATE TABLE `orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `strategy` VARCHAR(45) NULL,
  `buy` TINYINT(1) NOT NULL,
  `price` DOUBLE NOT NULL,
  `num_stocks` INT(11) NOT NULL,
  `datetime` DATETIME NOT NULL,
  `status` BINARY(3) NOT NULL,
  PRIMARY KEY (`order_id`));
  