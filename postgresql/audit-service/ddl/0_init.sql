CREATE SCHEMA audit_service
    AUTHORIZATION root;
CREATE TABLE audit_service.audit
(
    uuid uuid,
    text text NOT NULL,
    type character varying(30) NOT NULL,
    id character varying(55) NOT NULL,
    user_id uuid NOT NULL,
    mail character varying(255) NOT NULL,
    fio character varying(255) NOT NULL,
    role character varying(35) NOT NULL,
    create_date timestamp(3) without time zone,
    PRIMARY KEY (uuid)
);

ALTER TABLE IF EXISTS audit_service.audit_service
    OWNER to root;
