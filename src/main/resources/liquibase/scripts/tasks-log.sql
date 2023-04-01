--liquibase formatted sql

--changeSet eosreign:1
CREATE TABLE socks
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    color       TEXT   NOT NULL,
    cotton_part INT4   NOT NULL
);

--changeSet eosreign:2
CREATE TABLE socks_batches
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    socks       INT8   NOT NULL,
    socks_count INT4   NOT NULL
);