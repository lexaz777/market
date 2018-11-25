DROP TABLE IF EXISTS role;

CREATE TABLE role (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO role (name)
VALUES
('ROLE_CUSTOMER'),('ROLE_MANAGER'),('ROLE_ADMIN');

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  password char(80) NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  phone varchar(15) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO user (username,password,first_name,last_name,email,phone)
VALUES
('admin','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','Admin','Admin','vh1@mail.ru', '+790945456444');

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,

  PRIMARY KEY (user_id,role_id),

  KEY FK_ROLE_idx (role_id),

  CONSTRAINT FK_USER_05 FOREIGN KEY (user_id)
  REFERENCES user (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT FK_ROLE FOREIGN KEY (role_id)
  REFERENCES role (id)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(1, 3);

CREATE TABLE `categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `price` INT NULL,
  `photo_id` INT NULL,
  `category_id` INT NULL,
  `vendor_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT FK_CATEGORY FOREIGN KEY(category_id) REFERENCES categories(id)
  ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


