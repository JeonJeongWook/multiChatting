CREATE SCHEMA `multichat` DEFAULT CHARACTER SET utf8 ;

create table user(
	u_idx int not null auto_increment primary key,
    id varchar(20) not null,
    pw varchar(20) not null
);
alter table user drop name;
select * from user;