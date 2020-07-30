
-- 用户表
CREATE TABLE t_user(
	id INT(6) PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(60),
	PASSWORD VARCHAR(60),
	email VARCHAR(60)
	
);


-- 省份表
CREATE TABLE t_province(
	id INT(6) PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(60),
	tags VARCHAR(80),
	placecounts INT(4)

);

-- 景点表
CREATE TABLE t_place(
	id INT(6) PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(60),
	picpath VARCHAR(100),
	hottime TIMESTAMP,
	hotticket DOUBLE(7,2),
	dimticket DOUBLE(7,2),
	placedes VARCHAR(300),
	provinceid INT(6)
);

