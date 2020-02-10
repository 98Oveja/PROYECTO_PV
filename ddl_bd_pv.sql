create schema puntodeventa collate utf8_spanish_ci;
create table personas(
	ID_PERSONA int auto_increment primary key,
	PRIMER_NOMBRE varchar(12) not null,
	SEGUNDO_NOMBRE varchar(12) not null,
	PRIMER_APELLIDO varchar(12) not null,
	SEGUNDO_APELLIDO varchar(12) not null,
	DIRECCION varchar(40) default 'Ciudad' null,
	TELEFONO varchar(10) default 'No Phone' null,
	CORREO varchar(30) default 'No hay Correo' null,
	IMG_URL varchar(260) default 'No hay Foto' null
)charset=utf8mb4;
create table clientes(
	ID_CLIENTE int auto_increment primary key,
	ID_PERSONA int not null,
	NIT varchar(12) default 'C/F' null
)charset=utf8mb4;
create index FK_CLIENTE_y_PERSONA on clientes (ID_PERSONA);

create table marcas(
	ID_MARCA int auto_increment	primary key,
	NOMBRE varchar(100) null,
	IMG_URL varchar(260) default 'No hay Foto' null
)charset=utf8mb4;

create table productos(
	ID_PRODUCTO int auto_increment primary key,
	ID_MARCA int null,
	NOMBRE varchar(100) null,
	DESCRIPCION varchar(260) null,
	CODIGO_PRODUCTO varchar(10) null,
	DISPONIBILIDAD double null,
	UNIDAD_DE_MEDICION varchar(60) null,
	PRECIO_VENTA double null,
	PRECIO_COMPRA double null,
	IMG_URL varchar(260) default 'No hat Foto' null,
	ESTADO tinyint null,
	EQUIVALENCIA double null,
	UNIDAD_DE_EQUIVALENCIA varchar(15) null,
	MEDIDAS VARCHAR(50)
)charset=utf8mb4;
create index fk_marca_prod on productos (ID_MARCA);

create table categorias(
	ID_CATEGORIA int auto_increment	primary key,
	NOMBRE varchar(100) null,
	DESCRIPCION varchar(260) null,
	IMG_URL varchar(260) default 'No hay Foto' null
)charset=utf8mb4;

create table detalle_categorias_productos(
	ID_DETALLE int auto_increment primary key,
	ID_PRODUCTO int null,
	ID_CATEGORIA int null
)charset=utf8mb4;
create index fk_catego_detalle on detalle_categorias_productos (ID_CATEGORIA);
create index fk_prod_detalle on detalle_categorias_productos (ID_PRODUCTO);

create table ventas(
	ID_VENTA int auto_increment primary key,
	ID_CLIENTE int not null,
	TOTAL double not null,
	DESCUENTO double,
	FECHA datetime default current_timestamp() not null
)charset=utf8mb4;

create table detalle_venta(
	ID_DETALLE int auto_increment primary key,
	ID_VENTA int not null,
	ID_PRODUCTO int not null,
	DESCRIPCION varchar(20) not null,
	CANTIDAD int not null,
	PRECIO_PRODUCTO double,
	SUBTOTAL double not null
)charset=utf8mb4;
create index FK_Detalle_producto on detalle_venta (ID_PRODUCTO);
create index FK_Detalle_venta on detalle_venta (ID_VENTA);


create table usuarios(
	ID_USUARIO int auto_increment primary key,
	NOMBRES varchar(50) not null,
	APELLIDOS varchar(50) not null,
	EMAIL varchar(30) not null,
	CONTRASENA varchar(15) not null,
	ESTADO tinyint(1) default 1 null,
	CARGO varchar(20) not null,
	IMG_URL varchar(260) default 'No hay Foto',
	DATA_SETTING varchar(260) default 'No hay Configuraciones'


)charset=utf8mb4;

create table reportes(
	ID_REPORTE INT not null auto_increment primary key,
	ID_VENTA INT not null,
	ID_DETALLE INT not null,
	ID_USUARIO INT,
	NOMBRE VARCHAR(200),
	FECHA_GENERACION datetime default current_timestamp() not null,
	DETALLE_REPORTE_QUERY VARCHAR(400) 
)charset=utf8mb4;
create index FK_Reportes_a_Venta on reportes (ID_VENTA);
create index FK_Reportes_a_DetalleVenta on reportes (ID_DETALLE);
create index FK_Reportes_a_Usuario on reportes (ID_USUARIO);


create procedure consutaNitDirTelCliente(IN idCliente int)
begin
    set @id = idCliente;
    SELECT PERSONAS.TELEFONO,PERSONAS.DIRECCION, CLIENTES.NIT
    from (personas inner join clientes on personas.ID_PERSONA = clientes.ID_PERSONA)
    where personas.PRIMER_NOMBRE =
          (SELECT PERSONAS.PRIMER_NOMBRE from personas inner join clientes on personas.ID_PERSONA = clientes.ID_PERSONA
           where clientes.ID_CLIENTE = @id);
end;

create procedure getAllCategory()
begin
    select * from CATEGORIAS;
end;

create procedure getAllMarcars()
begin
    select NOMBRE from marcas;
end;

create procedure getDataProductoByNameMarca(IN nombreMarca varchar(100))
BEGIN
    select PRODUCTOS.NOMBRE, PRODUCTOS.DESCRIPCION, PRODUCTOS.DISPONIBILIDAD,
           PRODUCTOS.PRECIO_VENTA,PRODUCTOS.CODIGO_PRODUCTO,
           PRODUCTOS.ID_MARCA, PRODUCTOS.UNIDAD_DE_MEDICION from PRODUCTOS
        inner join MARCAS c on PRODUCTOS.ID_MARCA = c.ID_MARCA where c.NOMBRE = nombreMarca;
end;

create procedure getDataProductsByNameProd(IN thisProduct varchar(40))
begin
select productos.DISPONIBILIDAD,
       productos.PRECIO_VENTA,
       productos.CODIGO_PRODUCTO,
       productos.DESCRIPCION,
       PRODUCTOS.ID_MARCA
       from productos where productos.NOMBRE = thisProduct;
end;

create function getIdCostumerbyName(costumerName varchar(30), costumerLastname varchar(30)) returns int
begin
    set @id := (select clientes.ID_CLIENTE
    from clientes
             inner join personas on personas.ID_PERSONA =  clientes.ID_PERSONA
    where personas.PRIMER_NOMBRE = costumerName and personas.PRIMER_APELLIDO = costumerLastname);
    return @id;
end;

create procedure getNameMarcbyID(IN thisId int)
begin
    select NOMBRE from marcas where ID_MARCA = thisId;
end;

create procedure getProducByNameforBuy(IN producto varchar(100))
begin
select ID_MARCA, DESCRIPCION,CODIGO_PRODUCTO,DISPONIBILIDAD,
       UNIDAD_DE_MEDICION,PRECIO_VENTA,PRECIO_COMPRA from productos where NOMBRE = producto;
end;

create procedure getProductNameByMarcName(IN nombreMarca varchar(100))
BEGIN
    select PRODUCTOS.NOMBRE, PRODUCTOS.DESCRIPCION from PRODUCTOS
    inner join MARCAS c on PRODUCTOS.ID_MARCA = c.ID_MARCA where c.NOMBRE = nombreMarca;
end;

create procedure getProductbyLikeName(IN nombre varchar(100))
begin
    SET @NOMBRE = concat('%',nombre);
    SET @NOMBRE2 = CONCAT(nombre,'%');
        select
            productos.NOMBRE,
            productos.DESCRIPCION
        from productos
            where  productos.NOMBRE LIKE @NOMBRE2 or  productos.NOMBRE like @NOMBRE;
end;

create procedure getProductsByCategoryName(IN categoria varchar(100))
begin
    select productos.NOMBRE, productos.DESCRIPCION from productos
    inner join detalle_categorias_productos dcp
    on productos.ID_PRODUCTO = dcp.ID_PRODUCTO
    inner join categorias c on dcp.ID_CATEGOTIA = c.ID_CATEGORIA
    where c.NOMBRE = categoria;
end;

create procedure getProductsByLikeCode(IN codigo varchar(10))
begin
    SET @CODIGOEND = concat('%',codigo);
    SET @CODIGOSTART = CONCAT(codigo,'%');
    select
        productos.NOMBRE,
        productos.DESCRIPCION
    from productos
    where  productos.CODIGO_PRODUCTO LIKE @CODIGOSTART or  productos.CODIGO_PRODUCTO like @CODIGOEND;
end;

create procedure selectAllCostumers()
begin
    select personas.PRIMER_NOMBRE, personas.PRIMER_APELLIDO from (personas inner join clientes on personas.ID_PERSONA = clientes.ID_PERSONA);
end;
create procedure selectAllProducts()
begin
    SELECT productos.NOMBRE from  productos;
end;