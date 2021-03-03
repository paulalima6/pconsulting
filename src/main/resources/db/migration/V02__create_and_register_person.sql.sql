create table person (
	id bigint(20) primary key auto_increment,
	name varchar(100) not null,
	active boolean not null,
	street varchar(100),
	number varchar(100) not null,
	additional varchar(100),
	neighborhood varchar(100) not null,
	zip_code varchar(100) not null,
	city varchar(100) not null,
	state varchar(100) not null	
) engine = InnoDB default charset = utf8;

insert into person(name, active, street, number, additional, neighborhood, zip_code, city, state) values ('Ana Paula', true, 'Estrada Normandia', '137', 'casa 13', 'Jardim Pioneiro', '06705-360', 'Cotia', 'São Paulo');
insert into person(name, active, street, number, neighborhood, zip_code, city, state) values ('Natalia Lima', true, 'Rua Alagoinhas', '137', 'Jardim Araruama', '06700-560', 'Cotia', 'São Paulo');
insert into person(name, active, street, number, additional, neighborhood, zip_code, city, state) values ('Tania Lima', true, 'Avenida Chucri Zaidan', '1231', 'apto 92', 'Morumbi', '03221-742', 'São Paulo', 'São Paulo');
insert into person(name, active, street, number, neighborhood, zip_code, city, state) values ('Divorlan de Almeida', true, 'Avenida Luigi Galvani', '623', 'Brooklin', '03456-221', 'São Paulo', 'São Paulo');
insert into person(name, active, street, number, additional, neighborhood, zip_code, city, state) values ('Ana Julia Lima', true, 'Alameda Rio Negro', '122', 'casa 4', 'Residencial Zero', '03221-740', 'Barueri', 'São Paulo');
insert into person(name, active, street, number, additional, neighborhood, zip_code, city, state) values ('Danilo Macedo', true, 'Rua de Alphaville', '12', 'apto 151', 'Alphaville', '06722-123', 'Barueri', 'São Paulo');
insert into person(name, active, street, number, neighborhood, zip_code, city, state) values ('Cielo', true, 'Alameda Xingu', '512', 'Alphaville', '05221-922', 'Barueri', 'São Paulo');
insert into person(name, active, street, number, additional, neighborhood, zip_code, city, state) values ('Felipe', true, 'Avenida Tals', '720', 'apto 156', 'Jardim Não Lembro', '08332-992', 'Taboão da Serra', 'São Paulo');
