DROP DATABASE IF EXISTS ttschool;
CREATE DATABASE `ttschool`;
USE `ttschool`;

CREATE TABLE school (
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    year INT(5),
    UNIQUE KEY school (name,year),
    KEY year (year))
    ENGINE=INNODB DEFAULT CHARSET=utf8;
CREATE TABLE groups (
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    room VARCHAR(50),
    school_id INT(11) NOT NULL,
    KEY name (name),
    KEY room (room),
    FOREIGN KEY (school_id) REFERENCES school (id) ON UPDATE CASCADE ON DELETE CASCADE)
    ENGINE=INNODB DEFAULT CHARSET=utf8;
CREATE TABLE subject (
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    UNIQUE KEY name (name))
    ENGINE=INNODB DEFAULT CHARSET=utf8;
CREATE TABLE trainee (
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    rating INT(11) NOT NULL,
    group_id INT(11) DEFAULT NULL,
    KEY firstName (firstName),
    KEY lastName (lastName),
    KEY rating (rating),
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE SET NULL)
    ENGINE=INNODB DEFAULT CHARSET=utf8;
CREATE TABLE subject_group (
    id INT(11) NOT NULL AUTO_INCREMENT,
    group_id INT(11) NOT NULL,
    subject_id INT(11) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY subject_group (group_id,subject_id),
    KEY group_id (group_id),
    FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE,
    FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE)
    ENGINE=INNODB DEFAULT CHARSET=utf8;