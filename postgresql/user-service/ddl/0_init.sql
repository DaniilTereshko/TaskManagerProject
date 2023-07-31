CREATE SCHEMA user_service
    AUTHORIZATION root;
CREATE TABLE user_service.users
(
    uuid uuid,
    fio character varying(255) NOT NULL,
    role character varying(35),
    user_status character varying(55) NOT NULL,
    email character varying(255) NOT NULL,
    tg character varying(20),
    notification_method character varying(20),
    password character varying(45) NOT NULL,
    create_date timestamp(3) without time zone,
    update_date timestamp(3) without time zone,
    PRIMARY KEY (uuid),
    UNIQUE (email),
    UNIQUE (tg)
);

ALTER TABLE IF EXISTS user_service.users
    OWNER to root;