-- liquibase formatted sql

-- changeset liquibase:1
CREATE OR REPLACE FUNCTION UPDATE_UPDATED_DATE() RETURNS TRIGGER AS
'
    BEGIN
        NEW.UPDATE_DATE = now();
        RETURN NEW;
    END;
' LANGUAGE plpgsql;
