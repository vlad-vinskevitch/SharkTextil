-- liquibase formatted sql

-- changeset liquibase:2
CREATE TABLE SHOP_USER_ROLE
(
    ID_USER_ROLE         INT           PRIMARY KEY,
    DESCRIPTION          VARCHAR(64)   DEFAULT NULL
);