use optica

create table sucursal(
	id_sucursal int primary key auto_increment,
	nombre_sucursal varchar(300) not null,
	direccion_sucursal varchar(300) not null,
	telefono_sucursal varchar(9) not null,
	correo_sucursal varchar(150) not null
);

create table cargo(
	id_cargo int primary key auto_increment,
	nombre_cargo varchar(90) not null,
	descripcion_cargo varchar(500) not null
);

create table trabajador(
	dni_trabajador varchar(8) primary key,
	nombre_trabajador varchar(50) not null,
	apellido_paterno_trabajador varchar(50) not null,
	apellido_materno_trabajador varchar(50) not null,
	telefono_trabajador varchar(9) not null,
	email_trabajador varchar(250) default null,
	direccion_trabajador varchar(250) not null,
	fechaNacimiento_trabajador date not null,
	
	id_cargo_fk int, foreign key (id_cargo_fk) references cargo(id_cargo)
);

create table cliente(
	dni_cliente varchar(8) primary key,
	nombre_cliente varchar(50) not null,
	apellido_paterno_cliente varchar(50) not null,
	apellido_materno_cliente varchar(50) not null,
	telefono_cliente varchar(9) not null,
	email_cliente varchar(250) default null,
	fechaNacimiento_cliente date not null
);

create table direccionCliente(
	id_direccion int primary key auto_increment,
	direccion varchar(250) not null,
	referencia_direccion varchar(500) default null,
	
	dni_cliente_fk varchar(8), foreign key (dni_cliente_fk) references cliente(dni_cliente)
);

create table cita(
	id_cita int primary key auto_increment,
	decripcion_cita varchar(9999),
	fecha_cita date not null,
	hora_cita time not null,
	
	id_sucursal_fk int, foreign key(id_sucursal_fk) references sucursal(id_sucursal),
	dni_cliente_fk varchar(8), foreign key (dni_cliente_fk) references cliente(dni_cliente),
	dni_trabajador_fk varchar(8), foreign key (dni_trabajador_fk) references trabajador(dni_trabajador)
);

create table receta(
	id_receta int primary key auto_increment,
	detalle_receta varchar(9999),
	fecha_emision_receta date default current_date,
	hora_emision_receta time default current_time,
	
	dni_cliente_fk varchar(8), foreign key (dni_cliente_fk) references cliente(dni_cliente)
);

create table historialMedico(
	id_historialMedico int primary key auto_increment,
	url_historialMedico varchar(999),
	
	dni_cliente_fk varchar(8), foreign key (dni_cliente_fk) references cliente(dni_cliente)
);

create table categoria(
	id_categoria int primary key auto_increment,
	nombre_categoria varchar(90) not null,
	descripcion_categoria varchar(500) not null
);

create table estado(
	id_estado int primary key auto_increment,
	nombre_estado varchar(70) not null,
	descripcion_estado varchar(500) default null
);

-----------------------------------------------------------------

create table proveedor(
	ruc_proveedor varchar(11) primary key,
	razon_social_empresa varchar(450) not null,
	telefono_proveedor varchar(9) not null,
	email_proveedor varchar(250) default null,
	direccion_proveedor varchar(250) not null
);

create table producto(
	id_producto int primary key auto_increment,
	nombre_producto varchar(300) not null,
	precio_producto_venta float not null,
	precio_producto_compra float not null,
	descripcion_producto varchar(999),
	stock_producto_maximo int not null,
	stock_producto_actual int not null,
	stock_producto_minimo int not null,
	
	id_categoria_fk int, foreign key(id_categoria_fk) references categoria(id_categoria),
	id_estado_fk int, foreign key(id_estado_fk) references estado(id_estado)
);

create table productoProveedor(
	ruc_proveedor_fk varchar(11),
	id_producto_fk int,
	
	foreign key (ruc_proveedor_fk) references proveedor(ruc_proveedor),
	foreign key (id_producto_fk) references producto(id_producto),
	
	primary key(ruc_proveedor_fk, id_producto_fk)
);

create table comprobanteDePago(
	id_comprobante int primary key auto_increment,
	fecha_emision_comprobante date default current_date,
	hora_emision_comprobante time default current_time,
	tipo_pago varchar(50) not null
);

create table venta(
	id_venta int primary key auto_increment,
	fecha_venta date default current_date,
	hora_venta time default current_time,
	total_venta float not null,
	
	id_comprobante_fk int, foreign key(id_comprobante_fk) references comprobanteDePago(id_comprobante),
	dni_cliente_fk varchar(8), foreign key (dni_cliente_fk) references cliente(dni_cliente),
	id_sucursal_fk int, foreign key(id_sucursal_fk) references sucursal(id_sucursal),
	dni_trabajador_fk varchar(8), foreign key (dni_trabajador_fk) references trabajador(dni_trabajador)
);

create table compra(
	id_compra int primary key auto_increment,
	fecha_compra date default current_date,
	hora_compra time default current_time,
	total_compra float not null,
	
	id_comprobante_fk int, foreign key(id_comprobante_fk) references comprobanteDePago(id_comprobante),
	ruc_proveedor_fk varchar(11), foreign key (ruc_proveedor_fk) references proveedor(ruc_proveedor),
	id_sucursal_fk int, foreign key(id_sucursal_fk) references sucursal(id_sucursal),
	dni_trabajador_fk varchar(8), foreign key (dni_trabajador_fk) references trabajador(dni_trabajador)
);

create table detalleVenta(
	id_detalle_venta int primary key auto_increment,
	cantidad int not null,
	precio_unitario float not null,
	total float not null,
	
	id_venta_fk int, foreign key (id_venta_fk) references venta(id_venta),
	id_producto_fk int, foreign key (id_producto_fk) references producto(id_producto)
);

create table detalleCompra(
	id_detalle_compra int primary key auto_increment,
	cantidad int not null,
	precio_unitario float not null,
	total float not null,
	
	id_compra_fk int, foreign key (id_compra_fk) references compra(id_compra),
	id_producto_fk int, foreign key (id_producto_fk) references producto(id_producto)
);


INSERT INTO sucursal (nombre_sucursal, direccion_sucursal, telefono_sucursal, correo_sucursal) 
VALUES ('Sucursal A', 'Calle Principal 123', '123456789', 'sucursal_a@example.com');

INSERT INTO sucursal (nombre_sucursal, direccion_sucursal, telefono_sucursal, correo_sucursal) 
VALUES ('Sucursal B', 'Avenida Central 456', '987654321', 'sucursal_b@example.com');



