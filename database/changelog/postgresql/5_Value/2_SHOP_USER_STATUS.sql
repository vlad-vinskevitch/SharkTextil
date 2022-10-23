-- liquibase formatted sql

-- changeset liquibase:8
INSERT INTO SHOP_USER_STATUS
VALUES ('1', 'ACTIVE');

-- changeset liquibase:9
INSERT INTO SHOP_USER_STATUS
VALUES ('2', 'INACTIVE');

-- changeset liquibase:10
INSERT INTO SHOP_USER_STATUS
VALUES ('3', 'DELETED');