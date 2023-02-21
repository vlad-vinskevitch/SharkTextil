-- liquibase formatted sql

-- changeset liquibase:8
INSERT INTO STAFF_STATUS
VALUES ('1', 'ACTIVE');

-- changeset liquibase:9
INSERT INTO STAFF_STATUS
VALUES ('2', 'MANUAL_DELETED');