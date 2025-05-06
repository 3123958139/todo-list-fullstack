drop table if exists users, user_roles;
create table users (
    username varchar(255) primary key,
    email varchar(255),
    password varchar(255)
);

create table user_roles (
    username varchar(255),
    role varchar(255),
    primary key (username, role)
);

insert into users (username, password, email) values ('admin', '$2a$10$/2cfdNAuBaJOK/GeCG7r6.eU8a2jdqivs81fhpYdHBFk1L0dfZN3W', 'admin@example.com');
insert into user_roles (username, role) values ('admin', 'ADMIN');

drop table if exists todos;
create table todos (
    id serial primary key,
    title varchar(255),
    completed boolean default false
);

insert into todos (title) values ('Wash dishes');
insert into todos (title) values ('Feed Loki');
insert into todos (title) values ('Feed Cat');