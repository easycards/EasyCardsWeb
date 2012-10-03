# --- First database schema

# --- !Ups

create table users (
  id                    serial,
  email                 varchar(255) not null UNIQUE,
  name                  varchar(255) not null,
  password              varchar(255) not null
);


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists users;