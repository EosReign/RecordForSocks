--liquibase formatted sql

--changeSet eosreign:1
CREATE TABLE socks
(
    id          BIGSERIAL     NOT NULL PRIMARY KEY,
    color       VARCHAR(16)   NOT NULL,
    cotton_part INT4          NOT NULL,
    socks_count INT4          NOT NULL
);