drop table user;
create table user(id int auto_increment not null primary key, username varchar(50) not null,password varchar(50) not null,createtime varchar(30) not null ,sex int,email varchar(30),uuid varchar(40),status int) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table content;
create table content(id bigint auto_increment not null primary key,title varchar(200) character set utf8 not null ,parentId bigint ,iconPath varchar(300) character set utf8,audioPath varchar(300) character set utf8, createtime varchar(30)  ,content varchar(1000) character set utf8,summary varchar(3000) character set utf8,contentPath varchar(300) character set utf8, contenttype int,userid int,startIndex int , contentLength int ,reader int , recomment int,leiBie int,fromwhere varchar(300) character set utf8,author varchar(50) character set utf8) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table titleTip;
create table titleTip(id int auto_increment not null primary key,title varchar(300) character set utf8 not null );

drop table comment;
create table comment(id int auto_increment not null primary key ,type int , userId int ,recomment int , email varchar(100) character set utf8 not null , contentId bigint , content varchar(300) character set utf8 not null,createdate varchar(100),username varchar(100) character set utf8 not null);

drop table userlearne;
create table userlearne(id bigint not null primary key,userId bigint , learnEId bigint);

drop table catalog;
create table catalog(id bigint not null primary key,parentId bigint ,createtime varchar(30)  , name varchar(30) , description varchar(300));

drop table catalogandcontent;
create table catalogandcontent(id bigint not null primary key,catalogId bigint,contentId bigint ,createtime varchar(30) );
drop table relationship;
create table relationship(id bigint not null primary key,sourceId bigint,targetId bigint ,relationshipType int ,createtime varchar(30) );
#alter table content modify column summary varchar(1000) ;