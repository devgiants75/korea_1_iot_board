CREATE DATABASE IF NOT EXISTS `board_db`;

USE `board_db`;

CREATE TABLE `Users` (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    gender ENUM('M', 'F')
);

CREATE TABLE `Articles` (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author_id BIGINT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES `Users`(id) ON DELETE CASCADE
);

CREATE TABLE `Comments` (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    article_id BIGINT NOT NULL,
    commenter_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    FOREIGN KEY (article_id) REFERENCES `Articles`(id) ON DELETE CASCADE,
    FOREIGN KEY (commenter_id) REFERENCES `Users`(id) ON DELETE CASCADE
);





