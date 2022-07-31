DROP TABLE if exists birth_death_rate;
CREATE TABLE if not exists birth_death_rate (
    `id` INT NOT NULL AUTO_INCREMENT,
    `period` INT NOT NULL,
    `birth_or_death` VARCHAR(10) NOT NULL,
    `region` VARCHAR(100) NOT NULL,
    `count` INT NOT NULL,
    PRIMARY KEY (`id`)
);