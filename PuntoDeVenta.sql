-- ------------------------------------
-- user: Ronaldo
-- port: 3306 
-- Host: 127.0.0.1
--
--
-- ------------------------------------
-- select version();
-- create database PuntoDeVenta;
-- use PuntoDeVenta;
CREATE TABLE CATEGORIAS (
  `ID_CATEGORIA` int(11) NOT NULL auto_increment,
  `NOMBRE` varchar(20) NOT NULL,
  `DESCRIPCION` varchar(50) NOT NULL,
  constraint PK_Categoria primary key (ID_CATEGORIA)
)ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
-- drop table Categorias; -- select * from Categorias;
-- Volcado de datos para la tabla `CATEGORIAS`
INSERT INTO CATEGORIAS(`NOMBRE`, `DESCRIPCION`) VALUES
('producto1', 'descripcion de producto 1'),
('producto2', 'descripcion de producto 2'),
('producto3', 'descripcion de producto 3'),
('producto4', 'descripcion de producto 4'),
('producto5', 'descripcion de producto 5'),
('producto6', 'descripcion de producto 6'),
('producto7', 'descripcion de producto 7');
-- select * from Categorias;
-- --------------------------------------------------------
-- Estructura de tabla para la tabla `PERSONAS`
CREATE TABLE `PERSONAS` (
  `ID_PERSONA` int(11) NOT NULL AUTO_INCREMENT,
  `PRIMER_NOMBRE` varchar(12) NOT NULL,
  `SEGUNDO_NOMBRE` varchar(12) NOT NULL,
  `PRIMER_APELLIDO` varchar(12) NOT NULL,
  `SEGUNDO_APELLIDO` varchar(12) NOT NULL,
  `DIRECCION` varchar(40) DEFAULT "CIUDAD",
  `TELEFONO` varchar(10) DEFAULT "NO PHONE",
  `CORREO` varchar(20) DEFAULT "NO EMAIL",
  CONSTRAINT PK_PERSONAS PRIMARY KEY (ID_PERSONA)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
-- Volcado de datos para la tabla `PERSONAS`
INSERT INTO `PERSONAS` (`PRIMER_NOMBRE`, `SEGUNDO_NOMBRE`, `PRIMER_APELLIDO`, `SEGUNDO_APELLIDO`, `DIRECCION`, `TELEFONO`, `CORREO`) VALUES
('Alfredo', 'Manuel', 'Bautista', 'Encina', 'Guatemala', '87238723', 'algred@gmail.com'),
('Alejandro', 'Rodrigo', 'Mejía', 'Vázquez', 'Solola', '87420012', 'alejandro@gmail.com'),
('Juan', 'Miguel', 'de Jesús', 'Benítez', 'Quetzaltenango', '67210902', 'juanmi@gmail.com'),
('Manrique', 'José', 'Gregorio', 'Bermúdez', 'Totonicapan', '87239912', 'manjose@gmail.com'),
('Antonio', 'Bermúdez', 'Salvador', 'Bernal', 'Guatemala', '98325200', 'antonio@gmail.com'),
('Taide', 'Betancourt', 'Rosales', 'Gigliola', 'San Marcos', '87310022', 'taideb@gmail.com'),
('Miguel', 'Ángel', 'Vázquez', 'Betanzos', 'Jutiapa', '57230211', 'miguel@gmail.com'),
('Torres', 'Noel', 'Blanco', 'Velasco', 'Guatemala', '84002312', 'noel@gmail.com'),
('Humberto', 'Alejandro', 'Bolaños', 'Sánchez', 'Guatemala', '33202312', 'torres@gmail.com'),
('César', 'Bremermann', 'Borraz', 'Briseño', 'Quetzaltenango', '63219832', 'cesar@gmail.com'),
('Abel', 'Arias', 'Buenfil', 'Díaz', 'Guatemala', '87238723', 'algred@gmail.com'),
('Iván', 'Solis', 'Burguete', 'García', 'Totonicapan', '32400193', 'ivan@gmail.com'),
('Miguel', 'Ángel', 'Bustamante', 'Guerrero', 'Quetzaltenango', '88734522', 'miguel@gmail.com'),
('Maria', 'Cristina', 'Guerrero', 'Gomez', 'Totonicapan', '77340293', 'maria@gmail.com'),
('Celia', 'Carolina', 'Torres', 'Mendez', 'Quetzaltenango', '87420323', 'celia@gmail.com'),
('Fredy', 'Francisco', 'Alarcon', 'Licona', 'Guatemala', '87321104', 'fredy@gmail.com'),
('Leonel', 'Herson', 'López', 'Pérez', 'Solola', '76421200', 'leonel@gmail.com'),
('José', 'Daniel', 'Camacho', 'Porras', 'Guatemala', '98123200', 'jose@gmail.com'),
('Diana', 'Carina', 'Barrera', 'Mendez', 'Quetzaltenango', '76231222', 'diana@gmail.com'),
('Ana', 'Sofia', 'Porras', 'Vásquez', 'Totonicapan', '53232309', 'ana@gmail.com'),
('Elizabeth', 'Sofia', 'Garcia', 'Loarca', 'Quetzaltenango', '87324534', 'elizabeth@gmail.com');
SELECT * FROM PERSONAS;
insert INTO PERSONAS(`PRIMER_NOMBRE`, `SEGUNDO_NOMBRE`, `PRIMER_APELLIDO`, `SEGUNDO_APELLIDO`) 
VALUES
('Luis', 'Fernando', 'Palomo', 'Kempez');
delete from Personas where id_persona = 23;
-- ---------------------------------------------------------
--
-- Estructura de tabla para la tabla `CLIENTES`
--

CREATE TABLE CLIENTES (
  `ID_CLIENTE` int(11) NOT NULL auto_increment,
  `ID_PERSONA` int(11) NOT NULL,
  `NIT` varchar(12) DEFAULT "C/F",
  CONSTRAINT PK_CLIENTES primary key (ID_CLIENTE),
  CONSTRAINT FK_CLIENTE_y_PERSONA FOREIGN KEY (ID_PERSONA) REFERENCES PERSONAS(ID_PERSONA)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
--
-- Volcado de datos para la tabla `CLIENTES`
--

INSERT INTO `CLIENTES` (`ID_PERSONA`, `NIT`) VALUES
(11, '174887-4'),
(12, '782312-5'),
(13, '87211A-9'),
(14, '87323F-9'),
(15, '828210-F'),
(16, '98234F-5'),
(17, '983241-8'),
(18, '87123C-2'),
(18, '983144-1'),
(20, '873212-G');

INSERT INTO `CLIENTES` (`ID_PERSONA`) VALUES
(21),(22);
select * from Clientes;
--
-- Estructura de tabla para la tabla `EMPLEADOS`
--

CREATE TABLE `EMPLEADOS` (
  `ID_EMPLEADO` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PERSONA` int(11) NOT NULL,
  `ESTADO` tinyint(1) DEFAULT 1,
  `FECHA_CONTRATACION` datetime DEFAULT current_timestamp,
  `FECHA_RETIRO` datetime DEFAULT NULL,
  `CARGO` varchar(20) NOT NULL,
  CONSTRAINT PK_EMPLEADO primary key (ID_EMPLEADO),
  CONSTRAINT FK_EMPLEADO_Y_PERSONA FOREIGN KEY (ID_PERSONA) 
  REFERENCES PERSONAS (ID_PERSONA)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `EMPLEADOS`
--

INSERT INTO `EMPLEADOS` (`ID_PERSONA`, `CARGO`) VALUES
(6, 'Administrador'),
(7, 'Vendedor'),
(8, 'Vendedor'),
(9, 'Bodegero'),
(10,'Bodegero');
SELECT * FROM EMPLEADOS;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `PROVEEDORES`
--

CREATE TABLE `PROVEEDORES` (
  `ID_PROVEEDOR` int(11) NOT NULL auto_increment,
  `ID_PERSONA` int(11) NOT NULL,
  `ORG` varchar(50) default "Independiente",
  `NO_CUENTA` varchar(30) default "No hay Datos",
  CONSTRAINT PK_proveedores primary key (ID_PROVEEDOR),
  CONSTRAINT FK_PROVEEDOR_Y_PERSONA FOREIGN KEY (ID_PERSONA) 
  REFERENCES PERSONAS (ID_PERSONA)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `PROVEEDORES`
--
INSERT INTO `PROVEEDORES` (`ID_PERSONA`, `ORG`, `NO_CUENTA`) VALUES
(1, 'organizacion xc', '1223445xxxx'),
(2, 'organizacion x4', '1233445xxxx'),
(3, 'organizacion xd', '1234445xxxx'),
(4, 'organizacion xr', '1234455xxxx');
INSERT INTO `PROVEEDORES` (`ID_PERSONA`) VALUES
(5),(21);
SELECT * FROM PROVEEDORES;


-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `COMPRAS`
--

CREATE TABLE `COMPRAS` (
  `ID_COMPRA` int(11) NOT NULL auto_increment,
  `FECHA` datetime default current_timestamp,
  `CANTIDAD` decimal(10,0) NOT NULL,
  constraint PK_Compras primary key (id_compra)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `COMPRAS`
--

INSERT INTO `COMPRAS` (`FECHA`, `CANTIDAD`) VALUES
('2019-11-12 14:30:55', '12'),
('2019-11-16 8:30:55', '13'),
('2019-11-17 12:30:55', '14'),
('2019-11-20 09:30:55', '10'),
('2019-11-22 17:30:55', '11');

INSERT INTO `COMPRAS` (`CANTIDAD`) VALUES('10');
select * from compras;
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `PRODUCTOS`
--

CREATE TABLE `PRODUCTOS` (
  `ID_PRODUCTO` int(11) NOT NULL auto_increment,
  `ID_CATEGORIA` int(11) NOT NULL,
  `ID_PROVEEDORES` int(11) NOT NULL,
  `ID_COMPRA` int(11) NOT NULL,
  `MARCA` varchar(20) default "Sin Marca",
  `NOMBRE` varchar(20) NOT NULL,
  `CANTIDAD` decimal(10,0) NOT NULL,
  `PRECIO_COMPRA` double NOT NULL,
  `PRECIO_VENTA` double NOT NULL,
  `IMG` varchar(50) default "URL no Found",
  `ESTADO` tinyint(1) default 1,
  CONSTRAINT PK_Producto primary key (id_producto),
  CONSTRAINT FK_Producto_Categoria FOREIGN KEY (id_categoria) 
  REFERENCES categorias (id_categoria),
  CONSTRAINT FK_Producto_Compra FOREIGN KEY (id_compra) 
  REFERENCES compras (id_compras)
--   constraint FK_Producto_Proveedor foreign key (id_proveedores)
-- references Proveedor (id_proveedor)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
alter table productos add constraint FK_Producto_Proveedor foreign key (id_proveedores)
references Proveedor (id_proveedor);
desc Productos;
--
-- Volcado de datos para la tabla `PRODUCTOS`
--

INSERT INTO `PRODUCTOS` (`ID_CATEGORIA`, `ID_PROVEEDORES`, `ID_COMPRA`, `MARCA`, `NOMBRE`, `CANTIDAD`, `PRECIO_COMPRA`, `PRECIO_VENTA`, `IMG`, `ESTADO`) VALUES
(1, 3, 1, 'Marca x', 'martillo', '123', 11.24, 15.5, 'img/martillo.jpg', 1),
(1, 1, 5, 'Marca x', 'martillo', '123', 11.24, 15.5, 'img/martillo.jpg', 1);
select * from productos;
-- Estructura de tabla para la tabla `DETALLE_VENTA`
--

CREATE TABLE `DETALLE_VENTA` (
  `ID_DETALLE` int(11) NOT NULL auto_increment,
  `ID_VENTA` int(11) NOT NULL,
  `ID_PRODUCTO` int(11) NOT NULL,
  `DESCRIPCION` varchar(20) NOT NULL,
  `CANTIDAD` int NOT NULL,
  `SUBTOTAL` double NOT NULL,
  CONSTRAINT PK_DETALLEVENTA PRIMARY KEY (ID_DETALLE),
  CONSTRAINT FK_Detalle_venta foreign key (id_venta) references Ventas(id_venta),
  CONSTRAINT FK_Detalle_producto foreign key (id_producto) references productos(id_producto)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
select * from Detalle_Venta;

-- --------------------------------------------------------


--
-- Estructura de tabla para la tabla `INVENTARIOS`
--

-- CREATE TABLE `INVENTARIOS` (
--   `ID_INVENTARIO` int(11) NOT NULL,
--   `ID_PRODUCTO` int(11) NOT NULL,
--   `ID_EMPLEADO` varchar(30) NOT NULL,
--   `FECHA_INICIO` date NOT NULL,
--   `FECHA_FIN` date NOT NULL
-- ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------




-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USUARIOS`
--

CREATE TABLE `USUARIOS` (
  `ID_USUARIO` int(11) NOT NULL auto_increment,
  `NOMBRE` varchar(30) NOT NULL,
  `APELLIDOS` varchar(30) NOT NULL,
  `CONTRASENA` varchar(15) NOT NULL,
  `ESTADO` tinyint(1) default 1,
  `CARGO` varchar(20) NOT NULL,
  constraint PK_usuraio primary key (id_usuario)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
select * from Usuarios;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `VENTAS`
--

CREATE TABLE `VENTAS` (
  `ID_VENTA` int(11) NOT NULL auto_increment,
  `ID_CLIENTE` int(11) NOT NULL,
  `ID_EMPLEADO` int(11) NOT NULL,
  `TOTAL` double NOT NULL,
  `FECHA` datetime NOT NULL default current_timestamp,
  constraint PK_ventas primary key (id_venta)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
select * from ventas;
desc ventas;
-- ---------------------
SELECT Host, User FROM mysql.user;
-- WHERE LENGTH(Password) > 16;

-- --
-- -- Índices para tablas volcadas
-- --

-- --
-- -- Indices de la tabla `CATEGORIAS`
-- --
-- ALTER TABLE `CATEGORIAS`
--   ADD PRIMARY KEY (`ID_CATEGORIA`);

-- --
-- -- Indices de la tabla `CLIENTES`
-- --
-- ALTER TABLE `CLIENTES`
--   ADD PRIMARY KEY (`ID_CLIENTE`),
--   ADD KEY `ID_PERSONA` (`ID_PERSONA`);

-- --
-- -- Indices de la tabla `COMPRAS`
-- --
-- ALTER TABLE `COMPRAS`
--   ADD PRIMARY KEY (`ID_COMPRA`);

-- --
-- -- Indices de la tabla `DETALLE_VENTA`
-- --
-- ALTER TABLE `DETALLE_VENTA`
--   ADD PRIMARY KEY (`ID_DETALLE`),
--   ADD KEY `ID_PRODUCTO` (`ID_PRODUCTO`),
--   ADD KEY `ID_VENTA` (`ID_VENTA`);

-- --
-- -- Indices de la tabla `EMPLEADOS`
-- --
-- ALTER TABLE `EMPLEADOS`
--   ADD PRIMARY KEY (`ID_EMPLEADO`),
--   ADD KEY `ID_PERSONA` (`ID_PERSONA`);

-- --
-- -- Indices de la tabla `INVENTARIOS`
-- --
-- ALTER TABLE `INVENTARIOS`
--   ADD PRIMARY KEY (`ID_INVENTARIO`);

-- --
-- -- Indices de la tabla `PERSONAS`
-- --
-- ALTER TABLE `PERSONAS`
--   ADD PRIMARY KEY (`ID_PERSONA`);

-- --
-- -- Indices de la tabla `PRODUCTOS`
-- --
-- ALTER TABLE `PRODUCTOS`
--   ADD PRIMARY KEY (`ID_PRODUCTO`),
--   ADD KEY `ID_CATEGORIAS` (`ID_CATEGORIAS`),
--   ADD KEY `ID_COMPRA` (`ID_COMPRA`);

-- --
-- -- Indices de la tabla `PROVEEDORES`
-- --
-- ALTER TABLE `PROVEEDORES`
--   ADD PRIMARY KEY (`ID_PROVEEDOR`),
--   ADD KEY `ID_PERSONA` (`ID_PERSONA`);

-- --
-- -- Indices de la tabla `USUARIOS`
-- --
-- ALTER TABLE `USUARIOS`
--   ADD PRIMARY KEY (`ID_USUARIO`);

-- --
-- -- Indices de la tabla `VENTAS`
-- --
-- ALTER TABLE `VENTAS`
--   ADD PRIMARY KEY (`ID_VENTA`),
--   ADD KEY `ID_EMPLEADO` (`ID_EMPLEADO`);
-- COMMIT;

-- /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
-- /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
