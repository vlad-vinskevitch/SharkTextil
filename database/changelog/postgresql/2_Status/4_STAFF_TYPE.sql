-- liquibase formatted sql

-- changeset liquibase:11
CREATE TABLE STAFF_TYPE
(
    TYPE_ID            INT           PRIMARY KEY,
    DESCRIPTION          VARCHAR(64)   DEFAULT NULL
);