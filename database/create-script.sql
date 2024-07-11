drop table if exists todos;

create table todos (
    id serial primary key,
    title varchar(255),
    completed boolean default false
);

insert into todos (title) values ('Wash dishes');
insert into todos (title) values ('Feed Loki');
insert into todos (title) values ('Feed Cat');

select * from todos;