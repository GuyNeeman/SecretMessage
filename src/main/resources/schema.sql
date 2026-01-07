DROP TABLE IF EXISTS message;

CREATE TABLE `message` (
                           `id` INT(11) NOT NULL AUTO_INCREMENT,
                           `uuid` VARCHAR(64) NOT NULL,
                           `message` TEXT NOT NULL,
                           `password` VARCHAR(255),
                           `expiresat` INT(10),
                           `selfdelete` BOOLEAN NOT NULL,
                           `language` TEXT,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
