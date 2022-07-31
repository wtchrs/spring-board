create user 'board'@'localhost' identified by 'board';

create database board character set = utf8;

grant all privileges on board.* to 'board'@'localhost';

create table board.USER
(
    NO       int auto_increment primary key,
    ID       varchar(100) not null,
    PASSWORD varchar(100) not null,
    NICKNAME varchar(100) not null,
    REGDATE  datetime     not null,
    unique key (ID)
) engine = InnoDB
  character set = utf8;

create table board.BOARD
(
    NO       int auto_increment primary key,
    TITLE    varchar(255) not null,
    OPENDATE datetime     not null,
    unique key (TITLE)
) engine = InnoDB
  character set = utf8;

create table board.POST
(
    NO        int auto_increment primary key,
    BOARD_NO  int          not null,
    AUTHOR_NO int          not null,
    TITLE     varchar(255) not null,
    CONTENT   text         not null,
    POSTDATE  datetime     not null,
    VIEWS     int          not null,
    foreign key (BOARD_NO) references board.BOARD (NO),
    foreign key (AUTHOR_NO) references board.USER (NO)
) engine = InnoDB
  character set = utf8;