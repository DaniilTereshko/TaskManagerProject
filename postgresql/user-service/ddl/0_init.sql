CREATE TABLE user_service.users
(
    uuid uuid,
    fio text NOT NULL,
    role text,
    user_status text NOT NULL,
    activation_code text,
    email text NOT NULL,
    tg text,
    notification_method text,
    password text NOT NULL,
    create_date timestamp(3) without time zone,
    update_date timestamp(3) without time zone,
    PRIMARY KEY (uuid),
    UNIQUE (email),
    UNIQUE (tg)
);

ALTER TABLE IF EXISTS user_service.users
    OWNER to postgres;