-- liquibase formatted sql

-- changeset liquibase:3
CREATE TABLE SHOP_USER_STATUS
(
    ID_SHOP_USER_STATUS    INT             PRIMARY KEY,
    DESCRIPTION            VARCHAR(64)     DEFAULT NULL
);