DROP TABLE if exists birth_death_rate;
CREATE TABLE if not exists birth_death_rate (
    `id` INT NOT NULL AUTO_INCREMENT,
    `period` INT,
    `birth_or_death` VARCHAR(10),
    `region` VARCHAR(100),
    `count` INT,
    PRIMARY KEY (`id`)
);