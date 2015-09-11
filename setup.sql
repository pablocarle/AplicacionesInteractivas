drop table if exists clientes;
CREATE TABLE clientes (
	idCliente integer not null,
	nombre varchar(255),
	domicilio varchar(255), 
	telefono varchar(255),
	email varchar(255),
	activo boolean,
	primary key (idCliente)
);

drop table if exists contratos;
create table contratos (
	idContrato integer not null,
	idCliente integer not null,
	idAuto integer not null,
	idMedioPago integer not null,
	precio decimal(10,2),
	fechaInicio date,
	fechaFin date,
	constraint contratos_pk primary key ( idContrato )
);

drop table if exists autos;
create table autos (
	idAuto integer not null,
	patente varchar(30) not null,
	marca varchar(30),
	modelo varchar(30),
	constraint autos_pk primary key (idAuto)
);

drop table if exists clientes_autos;
create table clientes_autos (
	idCliente integer not null,
	idAuto integer not null,
	constraint clientes_autos_pk primary key (idCliente, idAuto),
	constraint clientes_autos_cliente_fk foreign key ( idCliente ) references clientes ( idCliente ),
	constraint clientes_autos_auto_fk foreign key ( idAuto ) references autos ( idAuto )
);

drop table if exists mediospago;
create table mediospago (
	idMedioPago integer not null,
	nombre varchar(30) not null,
	descripcion varchar(100),
	constraint mediospago_pk primary key ( idMedioPago )
);

create unique index autos_patente_idx on autos ( patente );

insert into clientes values(1, 'Juan Perez', 'Cochabamba 332', '467532463', 'email@localhost', null);
insert into clientes values(2, 'Jorge Roque', 'San Juan 552', '7654335', 'email@demo', null);
insert into clientes values(3, 'Pedro Perez', 'Av. Independencia 552', '157654335', 'email2@demo', null);