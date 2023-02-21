-- liquibase formatted sql

-- changeset liquibase:12
CREATE TABLE STAFF_STATUS
(
    STATUS_ID            INT           PRIMARY KEY,
    DESCRIPTION          VARCHAR(64)   DEFAULT NULL
);