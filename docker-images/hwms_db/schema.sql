CREATE USER IF NOT EXISTS 'hwms_owner'@'%' IDENTIFIED BY 'helloworld';
CREATE USER IF NOT EXISTS 'hwms_owner'@'localhost' IDENTIFIED BY 'helloworld';
CREATE DATABASE IF NOT EXISTS hwms;
use hwms
GRANT ALL PRIVILEGES ON hwms TO 'hwms_owner'@'%';
GRANT ALL PRIVILEGES ON hwms TO 'hwms_owner'@'localhost';

CREATE TABLE users (
	id MEDIUMINT not null auto_increment primary key,
	name text not null,
	email text not null
);
