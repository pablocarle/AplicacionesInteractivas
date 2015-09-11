drop table if exists clientes;
CREATE TABLE clientes (idCliente integer, nombre varchar(255), domicilio varchar(255), telefono varchar(255), email varchar(255), activo integer, autos integer, primary key (idCliente));
insert into clientes values(1, 'Juan Perez', 'Cochabamba 332', '467532463', 'email@localhost', 1, null);
insert into clientes values(2, 'Jorge Roque', 'San Juan 552', '7654335', 'email@demo', 1, null);
insert into clientes values(3, 'Pedro Perez', 'Av. Independencia 552', '157654335', 'email2@demo', 0, null);