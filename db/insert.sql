insert into room (name) values ('adminka');
insert into room (name) values ('flood');

insert into role (name) values ('admin');
insert into role (name) values ('user');

insert into person (login, password, role_id) VALUES ('admin', 'admin', 1);
insert into person (login, password, role_id) VALUES ('user', 'user', 2);

insert into message (description, author_id) VALUES ('rules', 1);