# --- First database schema

# --- !Ups

set ignorecase true;

create table vocabulary (
  id                    serial primary key,
  title                 varchar(255) not null,
  sourceLanguage        varchar(255) not null,
  targetLanguage        varchar(255) not null,
  userId                int not null
)
;


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists vocabulary;