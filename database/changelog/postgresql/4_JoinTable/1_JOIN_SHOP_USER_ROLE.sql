-- liquibase formatted sql

-- changeset liquibase:8
CREATE TABLE JOIN_SHOP_USER_ROLE
(
    ID_SHOP_USER          INT REFERENCES SHOP_USER (ID_USER),
    ID_SHOP_USER_ROLE     INT REFERENCES SHOP_USER_ROLE (ID_USER_ROLE),
    UNIQUE (ID_SHOP_USER, ID_SHOP_USER_ROLE)
);