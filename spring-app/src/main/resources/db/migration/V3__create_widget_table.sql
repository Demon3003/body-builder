CREATE SEQUENCE public.widget_seq
    INCREMENT 20
    START 5
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

    CREATE TABLE widget_type (
        id bigint PRIMARY KEY,
        name character varying(255) not null,
        description character varying
    );

    CREATE TABLE widget (
        id bigint primary key,
        name character varying(255) not null,
        type_id int not null,
        data json not null,
        parent_id bigint references dashboard(id) not null,
        CONSTRAINT widget_type_fk FOREIGN KEY (type_id)
        REFERENCES widget_type(id)
        MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
    );