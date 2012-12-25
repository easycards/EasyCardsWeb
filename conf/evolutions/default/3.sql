# --- First database schema

# --- !Ups

create table definition (
  id                    serial primary key,
  source                varchar(255) not null,
  target                varchar(255) not null,
  sourceLanguage        varchar(10) not null,
  targetLanguage        varchar(10) not null,
  vocabularyId          int not null
)
;


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists definition;