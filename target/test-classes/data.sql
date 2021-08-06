-- auto-generated definition
create table gift_certificate
(
    id               serial
        constraint gift_certificate_pk
            primary key,
    name             varchar,
    description      varchar,
    price            integer,
    duration         integer,
    create_date      timestamp,
    last_update_date timestamp,
    tags             integer
);

alter table gift_certificate
    owner to giftrole;

