CREATE TABLE CATEGORIAS (
                            `ID_CATEGORIA` int(11) NOT NULL auto_increment,
                            `NOMBRE` varchar(50) NOT NULL,
                            `DESCRIPCION` varchar(100) NOT NULL,
                            constraint PK_Categoria primary key (ID_CATEGORIA)
)ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

SELECT * FROM CATEGORIAS;

INSERT INTO CATEGORIAS (NOMBRE,DESCRIPCION)
VALUES ("Otros","Categoria desconocida"),
       ("Herramientas de mano","Herramientas pequeñas que caben en la mano"),
       ("Herramientas de sujeción ","Herramientas que sujetan o de apriete"),
       ("Elementos de unión","Para unir madera metal u otros materiales"),
       ("Herramientas para construcción e instaladores","Herramientas para construcciones"),
       ("Herramientas para máquina-herramienta","Herramientas para euqipos electricos u otros"),
       ("Herramientas para madera y carpintería","Madera y herramientas de carpinteria"),
       ("Herramientas abrasivas","Herramientas varios"),
       ("Herramientas eléctricas y neumáticas","Herramientas varios"),
       ("Herramientas automotrices para jardinería","Herramientas varios"),
       ("Herramientas manuales para huerto y jardín","Herramientas varios"),
       ("Máquinas manuales para construcción y obra pública","Herramientas varios"),
       ("Materiales de desgaste para construcción y obra pública","Herramientas varios"),
       ("Medios y equipos de obra","Herramientas varios"),
       ("Herrajes","Herramientas varios"),
       ("Automatismos para puertas y ventanas","Herramientas varios"),
       ("Artículos de seguridad","Herramientas varios"),
       ("Equipamiento para soldadura","Herramientas varios"),
       ("Válvulas","Herramientas varios"),
       ("Tuberías y sus accesorios","Herramientas varios"),
       ("Componentes diversos","Herramientas varios"),
       ("Productos de fijación y sellado","Herramientas varios"),
       ("Pinturas, esmaltes y barnices","Herramientas varios"),
       ("Escaleras","Herramientas varios"),
       ("Equipamiento de taller","Herramientas varios"),
       ("Lubricantes","Herramientas varios"),
       ("Productos para limpieza profesional","Herramientas varios"),
       ("Utensilios de limpieza","Herramientas varios"),
       ("Productos de higiene personal","Herramientas varios"),
       ("Equipos de protección individual","Herramientas varios"),
       ("Menaje de cocina","Herramientas varios"),
       ("Artículos para el hogar","Herramientas varios"),
       ("Iluminación y alumbrado","Herramientas varios"),
       ("Medición dimensional","Herramientas varios"),
       ("Software para Ferretería y Suministro Industrial","Herramientas varios"),
       ("Varios ferretería","Herramientas varios"),
       ("Compra-venta","Herramientas varios"),
       ("Distribución","Herramientas varios");
-- --------------------------------------------------------
-- Estructura de tabla para la tabla `PERSONAS`
CREATE TABLE `PERSONAS` (
                            `ID_PERSONA` int(11) NOT NULL AUTO_INCREMENT,
                            `PRIMER_NOMBRE` varchar(20) NOT NULL,
                            `SEGUNDO_NOMBRE` varchar(20) NOT NULL,
                            `PRIMER_APELLIDO` varchar(20) NOT NULL,
                            `SEGUNDO_APELLIDO` varchar(20) NOT NULL,
                            `DIRECCION` varchar(50) DEFAULT "CIUDAD",
                            `TELEFONO` varchar(12) DEFAULT "NO PHONE",
                            `CORREO` varchar(20) DEFAULT "NO EMAIL",
                            CONSTRAINT PK_PERSONAS PRIMARY KEY (ID_PERSONA)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
-- Volcado de datos para la tabla `PERSONAS`
INSERT INTO PERSONAS (`PRIMER_NOMBRE`, `SEGUNDO_NOMBRE`, `PRIMER_APELLIDO`, `SEGUNDO_APELLIDO`, `DIRECCION`, `TELEFONO`, `CORREO`) VALUES
('Desconocido', '', 'Desconocido', '', 'Desconocido', '', 'desconocido');
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
(1, 'Desconocido', '');
-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `COMPRAS`
--

CREATE TABLE `COMPRAS` IF NOT EXISTS (
                           `ID_COMPRA` int(11) NOT NULL auto_increment,
                           `FECHA` datetime default current_timestamp,
                           `CANTIDAD` decimal(10,0) NOT NULL,
                           constraint PK_Compras primary key (id_compra)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `COMPRAS`
--

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `PRODUCTOS`
--

CREATE TABLE `PRODUCTOS` (
                             `ID_PRODUCTO` int(11) NOT NULL auto_increment,
                             `ID_CATEGORIA` int(11) NOT NULL,
                             `ID_PROVEEDORES` int(11) NOT NULL,
                             `ID_COMPRA` int(11) NOT NULL,
                             `MARCA` varchar(40) default "Sin Marca",
                             `NOMBRE` varchar(40) NOT NULL,
                             `DESCRIPCION` varchar(255) DEFAULT "",
                             `CODIGO` varchar(8) NOT NULL,
                             `CANTIDAD` decimal(10,00) NOT NULL,
                             `PRECIO_COMPRA` double NOT NULL,
                             `PRECIO_VENTA` double NOT NULL,
                             `IMG` varchar(150),
                             `ESTADO` tinyint(1) default 1,
                             CONSTRAINT PK_Producto primary key (id_producto),
                             CONSTRAINT FK_Producto_Categoria FOREIGN KEY (id_categoria)
                                 REFERENCES CATEGORIAS (id_categoria),
                             CONSTRAINT FK_Producto_Compra FOREIGN KEY (id_compra)
                                 REFERENCES COMPRAS (ID_COMPRA),
                                 constraint FK_Producto_Proveedor foreign key (ID_PROVEEDORES)
                                 references PROVEEDORES (ID_PROVEEDOR)
)
    ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
--
-- Volcado de datos para la tabla `PRODUCTOS`
--
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
drop table USUARIOS;
CREATE TABLE `USUARIOS` (
                            `ID_USUARIO` int(11) NOT NULL auto_increment,
                            `EMAIL` varchar(30) NOT NULL,
                            `NOMBRE` varchar(30) NOT NULL,
                            `APELLIDOS` varchar(30) NOT NULL,
                            `CONTRASENA` varchar(15) NOT NULL,
                            `ESTADO` tinyint(1) default 1,
                            `CARGO` varchar(20) NOT NULL,
                            constraint PK_usuraio primary key (id_usuario)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
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

insert into USUARIOS value (1,'carls10vasquez@gmail.com','carlos','vasquez','123a',true,'admin');
insert into USUARIOS value (2,'wilian122397@gmail.com','wilian','soch','12345',true,'admin');
insert into USUARIOS value (3,'ronysic13@gmail.com','ronald','sic','12345',true,'admin');
select * from USUARIOS;

SELECT * FROM USUARIOS Where EMAIL = 'carls10vasquez@gmail.com' and CONTRASENA = '123a';