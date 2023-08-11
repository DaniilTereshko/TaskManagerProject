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
    password character varying(250) NOT NULL,
    create_date timestamp(3) without time zone,
    update_date timestamp(3) without time zone,
    PRIMARY KEY (uuid),
    UNIQUE (email),
    UNIQUE (tg)
);

CREATE TABLE user_service.verification_tokens
(
    uuid uuid,
    token uuid NOT NULL,
    email character varying(255) NOT NULL,
    user_id uuid NOT NULL,
    PRIMARY KEY (uuid),
    UNIQUE (token),
    UNIQUE (email),
    UNIQUE (user_id),
    FOREIGN KEY (user_id)
        REFERENCES user_service.users (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS user_service.verification_tokens
    OWNER to root;

ALTER TABLE IF EXISTS user_service.users
    OWNER to root;