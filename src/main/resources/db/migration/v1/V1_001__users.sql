CREATE TABLE sample.users
(
    id   BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(150)      NOT NULL,
    surname VARCHAR(150)    NOT NULL,
    username VARCHAR(150)   UNIQUE  NOT NULL,
    password VARCHAR(255)   NOT NULL
)