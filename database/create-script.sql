--------------------------------------------
--  Authentication and Authorization
--------------------------------------------
drop table if exists users, user_roles;

create table users (
    username varchar(255) primary key,
    password varchar(255)
);

create table user_roles (
    username varchar(255),
    role varchar(255),
    primary key (username, role)
);

insert into users (username, password) values ('admin', '$2a$10$kRbQq1xPISiteFw/LMEoi.Cid/tKI4.flGJB.05hhtPpgIYu.LPbS');
insert into user_roles (username, role) values ('admin', 'ADMIN');

--------------------------------------------
--  Application
--------------------------------------------
drop table if exists todos;
create table todos (
    id serial primary key,
    title varchar(255),
    completed boolean default false
);

insert into todos (title) values ('Wash dishes');
insert into todos (title) values ('Feed Loki');
insert into todos (title) values ('Feed Cat');