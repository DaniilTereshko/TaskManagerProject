CREATE SCHEMA task_service
    AUTHORIZATION root;

CREATE TABLE task_service.projects
(
    uuid uuid,
    name character varying(155) NOT NULL,
    description text,
    manager uuid NOT NULL,
    status character varying(30) NOT NULL,
    create_date timestamp(3) without time zone,
    update_date timestamp(3) without time zone,
    PRIMARY KEY (uuid),
    UNIQUE (name)
);

CREATE TABLE task_service.tasks
(
    uuid uuid,
    project uuid NOT NULL,
    title character varying(155) NOT NULL,
    description text,
    status character varying(30),
    implementer uuid,
    create_date timestamp(3) without time zone,
    update_date timestamp(3) without time zone,
    PRIMARY KEY (uuid),
    FOREIGN KEY (project)
        REFERENCES task_service.projects (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);
CREATE TABLE task_service.projects_users
(
    id_project uuid NOT NULL,
    id_user uuid NOT NULL,
    FOREIGN KEY (id_project)
        REFERENCES task_service.projects (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE IF EXISTS task_service.projects_users
    OWNER to root;

ALTER TABLE IF EXISTS task_service.tasks
    OWNER to root;

ALTER TABLE IF EXISTS task_service.projects
    OWNER to root;