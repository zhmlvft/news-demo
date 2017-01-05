DROP TABLE IF EXISTS user_info;
create table user_info(
   id INT NOT NULL AUTO_INCREMENT,
   username VARCHAR(10) NOT NULL,
   email VARCHAR(255) NOT NULL ,
   password VARCHAR(32) NOT NULL,
   mobile VARCHAR(15) NOT NULL,
   urls VARCHAR(255) NOT NULL,
   role VARCHAR(255) NOT NULL,
   PRIMARY KEY ( id )
)engine=innodb default charset=utf8mb4 auto_increment=1;

DROP TABLE IF EXISTS news_type;
create table news_type(
   id INT NOT NULL AUTO_INCREMENT,
   channelId VARCHAR(255) NOT NULL,
   name VARCHAR(255) NOT NULL ,
   PRIMARY KEY ( id )
)engine=innodb default charset=utf8mb4 auto_increment=1;

DROP TABLE IF EXISTS news;
create table news(
   id INT NOT NULL AUTO_INCREMENT,
   title VARCHAR(255) NOT NULL,
   description VARCHAR(255) NOT NULL ,
   pubDate VARCHAR(255) NOT NULL ,
   picUrl VARCHAR(255),
   link VARCHAR(255) NOT NULL ,
   content MEDIUMTEXT,
   html MEDIUMTEXT,
   source VARCHAR(255),
   channelId VARCHAR(255) NOT NULL ,
   channelName VARCHAR(255) NOT NULL ,
   PRIMARY KEY ( id )
)engine=innodb default charset=utf8mb4 auto_increment=1;
