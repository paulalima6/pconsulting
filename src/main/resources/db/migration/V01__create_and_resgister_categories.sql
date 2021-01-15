create table category(
	id bigint(20) primary key auto_increment,
	name varchar(50) not null
) engine = InnoDB default charset = utf8;

insert into category(name) values ('Lazer');
insert into category(name) values ('Alimentação');
insert into category(name) values ('Supermercado');
insert into category(name) values ('Farmácia');
insert into category(name) values ('Outros');
