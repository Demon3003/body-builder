CREATE TABLE permission
(
    id bigint NOT NULL,
    permission_name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT permissions_pkey PRIMARY KEY (id),
    CONSTRAINT permission_name_unique UNIQUE (permission_name)
);

CREATE TABLE role
(
    id bigint NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id),
    CONSTRAINT role_name_unique UNIQUE (name)
);
CREATE TABLE public.role_permission
(
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL,
    CONSTRAINT role_perm_pk PRIMARY KEY (role_id, permission_id),
    CONSTRAINT permission_id_fk FOREIGN KEY (permission_id)
        REFERENCES public.permission (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT role_id_fk FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);
CREATE TABLE users
(
    id bigint NOT NULL,
    first_name character varying(30) COLLATE pg_catalog."default",
    last_name character varying(40) COLLATE pg_catalog."default",
    login character varying(20) UNIQUE COLLATE pg_catalog."default" NOT NULL,
    password character varying(200) COLLATE pg_catalog."default" NOT NULL,
    email character varying(40) UNIQUE COLLATE pg_catalog."default",
    image character varying(200) COLLATE pg_catalog."default",
    registration_date date DEFAULT now(),
    status_id integer NOT NULL default 1,
    role_id bigint NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_role_fk FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);
CREATE TABLE post
(
    id bigint NOT NULL,
    title character varying(255) COLLATE pg_catalog."default",
    text character varying(255) COLLATE pg_catalog."default",
    user_id bigint,
    CONSTRAINT post_pkey PRIMARY KEY (id),
    CONSTRAINT post_user_fk FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE post_comment
(
    id bigint NOT NULL,
    text character varying(255) COLLATE pg_catalog."default",
    post_id bigint,
    user_id bigint,
    CONSTRAINT post_comment_pkey PRIMARY KEY (id),
    CONSTRAINT user_id_fk FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT comment_post_id_fk FOREIGN KEY (post_id)
        REFERENCES public.post (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE post_details
(
    post_id bigint NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default",
    created_on timestamp without time zone,
    tags character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT post_details_pkey PRIMARY KEY (post_id),
    CONSTRAINT details_post_id_fk FOREIGN KEY (post_id)
        REFERENCES public.post (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
CREATE TABLE dashboard_type (
	id bigint primary key,
	name character varying(255) not null,
	description character varying

);
CREATE TABLE dashboard (
	id bigint primary key,
	name character varying(255) not null,
	display_name character varying(1000),
	description character varying,
	type_id int not null,
	parent_id bigint references dashboard(id),
	CONSTRAINT dashboard_type_fk FOREIGN KEY (type_id)
        REFERENCES dashboard_type(id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);