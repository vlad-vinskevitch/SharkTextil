-- liquibase formatted sql

-- changeset liquibase:13
CREATE TABLE STAFF
(
    ID_STUFF              INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ID_STAFF_TYPE         INT REFERENCES STAFF_TYPE (TYPE_ID),
    ID_STAFF_STATUS       INT REFERENCES STAFF_STATUS (STATUS_ID),
    NAME                  VARCHAR(64)              NOT NULL,
    DESCRIPTION           VARCHAR(64)              NOT NULL,
    SIZE                  VARCHAR(64)              DEFAULT NULL,
    PRICE                 DOUBLE PRECISION         DEFAULT NULL,
    COLOR                 INT                      DEFAULT NULL,
    CREATION_DATE         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE           TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);