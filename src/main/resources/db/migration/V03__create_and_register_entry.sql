create table entry (
	id bigint(20) primary key auto_increment,
	description varchar(100) not null,
	due_date date not null,
	pay_day date,
	value decimal(10,2) not null,
	observation varchar(100),
	type varchar(20) not null,
	id_category bigint(20) not null,
	id_person bigint(20) not null,
	foreign key (id_category) references category(id),
	foreign key (id_person) references person(id)	
) engine = InnoDB default charset = utf8;

insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Prolabore', '2020-07-01', '2020-07-03', 12000.00, 'Distribuição de Lucros', 'REVENUE', 1, 1);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Bahamas', '2020-07-02', null, 4000.00, null, 'EXPENSE', 2, 2);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Top Club', '2020-07-03', null, 350.33, null, 'REVENUE', 3, 3);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Cemig', '2020-07-04','2020-07-08', 1239.47, 'Geração', 'REVENUE', 3, 4);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('DMAE', '2020-07-05', '2020-07-07', 436.00, null, 'EXPENSE', 3, 5);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Extra', '2020-07-06', null, 745.87, null, 'REVENUE', 4, 6);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Cielo', '2020-07-07', '2020-07-14', 9876.22, null, 'REVENUE', 1, 7);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Despachante', '2020-07-08', '2020-07-09', 327.00, 'Licenciamento Porshe', 'EXPENSE', 4, 2);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Pneus', '2020-07-09', null, 2500.00, 'Pneus Novos', 'EXPENSE', 3, 3);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Café', '2020-07-10', '2020-07-12', 260.00, null, 'EXPENSE', 5, 5);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Eletronicos', '2020-07-11', '2020-07-17', 4700.00, 'Note Novo', 'REVENUE', 1, 5);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Instrumentos', '2020-07-12', '2020-07-22', 2400.00, 'Piano', 'REVENUE', 4, 3);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Almoço', '2020-07-13', '2020-07-14', 127.00, 'Coco Bambu', 'EXPENSE', 4, 2);
insert into entry(description, due_date, pay_day, value, observation, type, id_category, id_person) values ('Lanche', '2020-07-14', '2020-07-22', 37.00, null, 'EXPENSE', 4, 1);
