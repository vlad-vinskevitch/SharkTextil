-- liquibase formatted sql

-- changeset liquibase:4
CREATE TABLE SHOP_USER
(
    ID_USER               INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ID_SHOP_USER_STATUS   INT REFERENCES SHOP_USER_STATUS (ID_SHOP_USER_STATUS),
    EMAIL                 VARCHAR(64)              NOT NULL,
    PASSWORD              VARCHAR(64)              NOT NULL,
    USER_NAME             VARCHAR(64)              DEFAULT NULL,
    USER_LAST_NAME        VARCHAR(64)              DEFAULT NULL,
    CREATION_DATE         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE           TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
