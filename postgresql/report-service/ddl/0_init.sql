CREATE SCHEMA report_service
    AUTHORIZATION root;

CREATE TABLE report_service.reports
(
    uuid uuid,
    status character varying(35) NOT NULL,
    type character varying(40) NOT NULL,
    description character varying(155) NOT NULL,
    file character varying(55),
    param jsonb,
    create_date timestamp(3) without time zone,
    update_date timestamp(3) without time zone,
    PRIMARY KEY (uuid)
);

ALTER TABLE IF EXISTS report_service.reports
    OWNER to root;