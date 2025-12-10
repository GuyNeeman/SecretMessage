DROP TABLE IF EXISTS message;

CREATE TABLE `message` (
                           `id` INT(11) NOT NULL AUTO_INCREMENT,
                           `uuid` VARCHAR(64) NOT NULL,
                           `message` TEXT NOT NULL,
                           `password` VARCHAR(255),
                           `expiration_day` INT(2),
                           `expiration_hour` INT(2),
                           `expiration_minute` INT(3),
                           `selfdelete` BOOLEAN NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
