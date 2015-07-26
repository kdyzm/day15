create database contacts character set UTF8;
use contacts;
create table users(
  id varchar(32) not null,
  name varchar(30),
  pwd varchar(32),
  constraint user_pk primary key(id)
);
create table contacts(
  id varchar(32) not null,
  name varchar(30),
  sex char(1),
  tel varchar(20),
  addr varchar(100),
  uid varchar(32),
  constraint c_pk primary key(id),
  constraint c_fk foreign key(uid) references users(id)
);
