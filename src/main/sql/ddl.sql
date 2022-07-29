create user 'board'@'localhost' identified by 'board';

create database board character set = utf8;

grant all privileges on board.* to 'board'@'localhost';

create table board.USER
(
    NO       int auto_increment primary key,
    ID       varchar(30),
    PASSWORD varchar(100),
    NICKNAME varchar(100),
    REGDATE  datetime,
    unique key (ID)
) engine = InnoDB
  character set = utf8;

create table board.BOARD
(
    NO       int auto_increment primary key,
    TITLE    varchar(255),
    OPENDATE datetime,
    unique key (TITLE)
) engine = InnoDB
  character set = utf8;

create table board.POST
(
    NO       int auto_increment primary key,
    BOARD    varchar(255),
    TITLE    varchar(255),
    CONTENT  text,
    AUTHOR   varchar(30),
    POSTDATE datetime,
    VIEWS    long,
    unique key (NO),
    foreign key (BOARD) references board.BOARD (TITLE),
    foreign key (AUTHOR) references board.USER (ID)
) engine = InnoDB
  character set = utf8;