CREATE SCHEMA `multichat` DEFAULT CHARACTER SET utf8 ;

create table user(
	u_idx int not null auto_increment primary key,
    id varchar(20) not null unique,
    pw varchar(20) not null
);
select * from user;