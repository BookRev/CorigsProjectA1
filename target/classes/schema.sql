drop database if exists bookReviewRS;
create database bookReviewRS;
use bookReviewRS;

create table if not exists USER(
userID int not null auto_increment,
username varchar(20),
email varchar(100),
password varchar(20),
phone bigint(10),
primary key (userID)
);

create table if not exists BOOK(
isbn bigint(13) not null,
title varchar(100),
author varchar(50),
primary key(isbn)
);

create table if not exists WEBSITE(
webName varchar(50),
URL varchar(255),
isbn bigint(13),
foreign key (isbn) references BOOK(isbn)
);

create table if not exists REVIEW(
rating float(3),
summary varchar(5000),
isbn bigint(13),
foreign key (isbn) references BOOK(isbn)
);

create table if not exists SEARCHISTORY(
userID int,
isbn bigint(13),
favorite bool not null default 0,
accessTime DATETIME NOT NULL DEFAULT current_timestamp,
foreign key (userID) references USER(userID),
foreign key (isbn) references BOOK(isbn)
);