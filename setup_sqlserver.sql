use db;

CREATE TABLE clientes (
	idCliente integer not null,
	nombre varchar(255),
	domicilio varchar(255), 
	telefono varchar(255),
	email varchar(255),
	activo bit,
	primary key (idCliente)
);


create table autos (
	idAuto integer not null,
	patente varchar(30) not null,
	marca varchar(30),
	modelo varchar(30),
	esgrande bit,
	constraint autos_pk primary key (idAuto)
);

create table clientes_autos (
	idCliente integer not null,
	idAuto integer not null,
	constraint clientes_autos_pk primary key (idCliente, idAuto),
	constraint clientes_autos_cliente_fk foreign key ( idCliente ) references clientes ( idCliente ),
	constraint clientes_autos_auto_fk foreign key ( idAuto ) references autos ( idAuto )
);

create table bancos (
    idBanco integer not null,
    nombre varchar(30) not null,
    constraint bancos_pk primary key (idBanco)
);

create table mediospago (
	idMedioPago integer not null,
	nombre varchar(30) not null,
	descripcion varchar(100),
	ftp_out varchar(255),
	ftp_in varchar(255),
	archivo varchar(255),
	idBanco integer not null,
	constraint mediospago_pk primary key ( idMedioPago ),
	constraint mediospago_bancos_fk foreign key (idBanco) references bancos (idBanco)
);

create table abonos (
	idAbono integer not null,
	nombre varchar(127) not null,
	dias integer not null,
	descuento float not null,
	constraint abonos_pk primary key (idAbono)
);

create unique index autos_patente_idx on autos ( patente );

create table contratos (
	idContrato integer not null,
	idCliente integer not null,
	idAuto integer not null,
	idMedioPago integer not null,
	idAbono integer not null,
	precio decimal(10,2),
	fechaInicio date,
	activo bit,
	constraint contratos_pk primary key ( idContrato ),
	CONSTRAINT contratos_clientesautos FOREIGN KEY ( idCliente, idAuto ) REFERENCES clientes_autos (idCliente, idAuto),
	constraint contratos_mediospago_fk foreign key ( idMedioPago ) references mediospago ( idMedioPago ),
	constraint contratos_abonos_fk foreign key ( idAbono ) references abonos ( idAbono )
);

create unique index contratos_idx on contratos ( idCliente, idAuto, activo );


insert into clientes values(1, 'Juan Perez', 'Cochabamba 332', '467532463', 'email@localhost', 1);
insert into clientes values(2, 'Jorge Roque', 'San Juan 552', '7654335', 'email@demo', 1);
insert into clientes values(3, 'Pedro Perez', 'Av. Independencia 552', '157654335', 'email2@demo', 1);

insert into bancos values(1, 'Banco Frances');
insert into bancos values(2, 'Banco Galicia');
insert into bancos values(3, 'Banco Santander');

