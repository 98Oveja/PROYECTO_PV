
DROP TABLE IF EXISTS `CATEGORIAS`;
DROP TABLE IF EXISTS `INVENTARIOS`;
DROP TABLE IF EXISTS `USUARIOS`;
DROP TABLE IF EXISTS `VENTAS`;
DROP TABLE IF EXISTS `PRODUCTOS`;
DROP TABLE IF EXISTS `DETALLE_VENTA`;
DROP TABLE IF EXISTS `COMPRAS`;
DROP TABLE IF EXISTS `PERSONAS`;
DROP TABLE IF EXISTS `PROVEEDORES`;
DROP TABLE IF EXISTS `CLIENTES`;
DROP TABLE IF EXISTS `EMPLEADOS`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `CATEGORIAS` (
	`ID_CATEGORIA` INT NOT NULL,
	`NOMBRE` VARCHAR(20) NOT NULL,
	`DESCRIP` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`ID_CATEGORIA`)
);

CREATE TABLE `INVENTARIOS` (
	`ID_INVENTARIO` INT NOT NULL,
	`ID_PRODUCTO` INT NOT NULL,
	`ID_EMPLEADO` VARCHAR(30) NOT NULL,
	`FECHA_INICIO` DATE NOT NULL,
	`FECHA_FIN` DATE NOT NULL,
	PRIMARY KEY (`ID_INVENTARIO`)
);

CREATE TABLE `USUARIOS` (
	`ID_USUARIO` INT NOT NULL,
	`NOMBRE` VARCHAR(30) NOT NULL,
	`APELLIDOS` VARCHAR(30) NOT NULL,
	`EMAIL` VARCHAR(30) NOT NULL,
	`CONTRASENA` VARCHAR(15) NOT NULL,
	`ESTADO` BOOLEAN NOT NULL,
	`CARGO` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`ID_USUARIO`)
);

CREATE TABLE `VENTAS` (
	`ID_VENTA` INT NOT NULL,
	`ID_CLIENTE` INT NOT NULL,
	`ID_EMPLEADO` INT NOT NULL,
	`TOTAL` DOUBLE NOT NULL,
	`FECHA` DATE NOT NULL,
	`ESTADO_VENTA` CHAR NOT NULL DEFAULT 'VENDIDO',
	PRIMARY KEY (`ID_VENTA`)
);

CREATE TABLE `PRODUCTOS` (
	`ID_PRODUCTO` INT NOT NULL,
	`ID_CATEGORIAS` INT NOT NULL,
	`ID_PROVEEDORES` INT NOT NULL,
	`ID_COMPRA` INT NOT NULL,
	`MARCA` VARCHAR(20) NOT NULL,
	`NOMBRE` VARCHAR(20) NOT NULL,
	`CANTIDAD` NUMERIC NOT NULL,
	`PRECIO_COMPRA` DOUBLE NOT NULL,
	`PRECIO_VENTA` DOUBLE NOT NULL,
	`IMG` VARCHAR(50) NOT NULL,
	`ESTADO` BOOLEAN NOT NULL,
	PRIMARY KEY (`ID_PRODUCTO`)
);

CREATE TABLE `DETALLE_VENTA` (
	`ID_DETALLE` INT NOT NULL,
	`ID_VENTA` INT NOT NULL,
	`ID_PRODUCTO` INT NOT NULL,
	`DESCRIPCION` VARCHAR(20) NOT NULL,
	`CANTIDAD` INT NOT NULL,
	`SUBTOTAL` DOUBLE NOT NULL,
	PRIMARY KEY (`ID_DETALLE`)
);

CREATE TABLE `COMPRAS` (
	`ID_COMPRA` INT NOT NULL,
	`FECHA` DATE NOT NULL,
	`CANTIDAD` NUMERIC NOT NULL,
	PRIMARY KEY (`ID_COMPRA`)
);

CREATE TABLE `PERSONAS` (
	`ID_PERSONA` INT NOT NULL,
	`PRIMER_NOMBRE` VARCHAR(12) NOT NULL,
	`SEGUNDO_NOMBRE` VARCHAR(12) NOT NULL,
	`PRIMER_APELLIDO` VARCHAR(12) NOT NULL,
	`SEGUNDO_APELLIDO` VARCHAR(12) NOT NULL,
	`DIRECCION` VARCHAR(40) NOT NULL,
	`TELEFONO` VARCHAR(10) NOT NULL,
	`CORREO` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`ID_PERSONA`)
);

CREATE TABLE `PROVEEDORES` (
	`ID_PROVEEDOR` INT NOT NULL,
	`ID_PERSONA` INT NOT NULL,
	`ORG` VARCHAR(50) NOT NULL,
	`NO_CUENTA` VARCHAR(30) NOT NULL,
	PRIMARY KEY (`ID_PROVEEDOR`)
);

CREATE TABLE `CLIENTES` (
	`ID_CLIENTE` INT NOT NULL,
	`ID_PERSONA` INT NOT NULL,
	`NIT` VARCHAR(12) NOT NULL,
	PRIMARY KEY (`ID_CLIENTE`)
);

CREATE TABLE `EMPLEADOS` (
	`ID_EMPLEADO` INT NOT NULL,
	`ID_PERSONA` INT NOT NULL,
	`ESTADO` BOOLEAN NOT NULL,
	`FECHA_CONTRATACION` DATE NOT NULL,
	`FECHA_RETIRO` DATE,
	`CARGO` VARCHAR(20) NOT NULL,
	PRIMARY KEY (`ID_EMPLEADO`)
);

ALTER TABLE `VENTAS` ADD FOREIGN KEY (`ID_EMPLEADO`) REFERENCES `EMPLEADOS`(`ID_EMPLEADO`);
ALTER TABLE `PRODUCTOS` ADD FOREIGN KEY (`ID_CATEGORIAS`) REFERENCES `CATEGORIAS`(`ID_CATEGORIA`);
ALTER TABLE `PRODUCTOS` ADD FOREIGN KEY (`ID_COMPRA`) REFERENCES `COMPRAS`(`ID_COMPRA`);
ALTER TABLE `DETALLE_VENTA` ADD FOREIGN KEY (`ID_PRODUCTO`) REFERENCES `PRODUCTOS`(`ID_PRODUCTO`);
ALTER TABLE `DETALLE_VENTA` ADD FOREIGN KEY (`ID_VENTA`) REFERENCES `VENTAS`(`ID_VENTA`);
ALTER TABLE `PROVEEDORES` ADD FOREIGN KEY (`ID_PERSONA`) REFERENCES `PERSONAS`(`ID_PERSONA`);
ALTER TABLE `CLIENTES` ADD FOREIGN KEY (`ID_PERSONA`) REFERENCES `PERSONAS`(`ID_PERSONA`);
ALTER TABLE `EMPLEADOS` ADD FOREIGN KEY (`ID_PERSONA`) REFERENCES `PERSONAS`(`ID_PERSONA`);

-- GuadalupeCaballero Green FranciscoLoranca MarcelaAguilar Pérez Fredy FranciscoAlarcón Licona SalomónAlarcón
-- López Gerardo GenaroAlatriste Pérez HipólitoAlcántar Camacho José IsraelAlcoverde Martínez Roberto AntonioAlderete Porras Jacinta GuillerminaAldrete Vargas AdolfoAlejo Guerrero Víctor HugoAlemán Mundo Ma. del RosarioAlemán Mundo MarcialAlmogabar Santos RocíoAlonso Ibarra Pascual Gerardo Alonso Navarro Jadilson Altamirano García De León Sánchez RodolfoDe Lira Ávalos AdánDel Razo Ruiz ArmandoDel Toro Arreola DavidDelgado Bugarín NormaDelgado Guajardo Héctor FedericoDelgado Salgado ClementeDíaz Cruz CarlosDíaz Morfín Julio César Díaz Núñez Juan ManuelDíaz Sánchez Julio EduardoDíaz Vivaldo José VicenteDomínguez Barrios GabrielDomínguez Romo GerardoDomínguez Velasco Miguel ÁngelDuarte Briz Jesús AlfredoDurán de Jesús Julián

INSERT INTO PERSONAS VALUES
(1,'Alfredo', 'Manuel','Bautista','Encina','Guatemala', '87238723','algred@gmail.com'),
(2,'Alejandro','Rodrigo','Mejía','Vázquez','Solola', '87420012','alejandro@gmail.com'),
(3,'Juan','Miguel','de Jesús','Benítez','Quetzaltenango', '67210902','juanmi@gmail.com'),
(4,'Manrique', 'José','Gregorio','Bermúdez','Totonicapan', '87239912','manjose@gmail.com'),
(5,'Antonio', 'Bermúdez','Salvador','Bernal','Guatemala', '98325200','antonio@gmail.com'),
(6,'Taide', 'Betancourt','Rosales','Gigliola','San Marcos', '87310022','taideb@gmail.com'),
(7,'Miguel', 'Ángel','Vázquez','Betanzos','Jutiapa', '57230211','miguel@gmail.com'),
(8,'Torres', 'Noel','Blanco','Velasco','Guatemala', '84002312','noel@gmail.com'),
(9,'Humberto', 'Alejandro','Bolaños','Sánchez','Guatemala', '33202312','torres@gmail.com'),
(10,'César', 'Bremermann','Borraz','Briseño','Quetzaltenango', '63219832','cesar@gmail.com'),
(11,'Abel', 'Arias','Buenfil','Díaz','Guatemala', '87238723','algred@gmail.com'),
(12,'Iván', 'Solis','Burguete','García','Totonicapan', '32400193','ivan@gmail.com'),
(13,'Miguel','Ángel','Bustamante','Guerrero','Quetzaltenango', '88734522','miguel@gmail.com'),
(14,'Maria', 'Cristina','Guerrero','Gomez','Totonicapan', '77340293','maria@gmail.com'),
(15,'Celia', 'Carolina','Torres','Mendez','Quetzaltenango','87420323','celia@gmail.com'),
(16,'Fredy', 'Francisco','Alarcon','Licona','Guatemala', '87321104','fredy@gmail.com'),
(17,'Leonel', 'Herson','López','Pérez','Solola', '76421200','leonel@gmail.com'),
(18,'José', 'Daniel','Camacho','Porras','Guatemala', '98123200','jose@gmail.com'),
(19,'Diana', 'Carina','Barrera','Mendez','Quetzaltenango', '76231222','diana@gmail.com'),
(20,'Ana', 'Sofia','Porras','Vásquez','Totonicapan', '53232309','ana@gmail.com');


INSERT INTO EMPLEADOS VALUES
(1,6,true,STR_TO_DATE('02.11.2003' ,GET_FORMAT(date,'USA')),STR_TO_DATE('11.25.2027' ,GET_FORMAT(date,'USA')),'Administrador'),
(2,7,true,STR_TO_DATE('05.20.2003' ,GET_FORMAT(date,'USA')),STR_TO_DATE('11.20.2027' ,GET_FORMAT(date,'USA')),'Vendedor'),	
(3,8,true,STR_TO_DATE('02.02.2003' ,GET_FORMAT(date,'USA')),STR_TO_DATE('11.20.2003' ,GET_FORMAT(date,'USA')),'Vendedor'),
(4,9,true,STR_TO_DATE('03.08.2003' ,GET_FORMAT(date,'USA')),STR_TO_DATE('11.20.2027' ,GET_FORMAT(date,'USA')),'Bodegero'),
(5,10,true,STR_TO_DATE('04.10.2003' ,GET_FORMAT(date,'USA')),STR_TO_DATE('11.20.2027' ,GET_FORMAT(date,'USA')),'Bodegero');

INSERT INTO CLIENTES VALUES
(1,11,'174887-4'),
(2,12,'782312-5'),
(3,13,'87211A-9'),
(4,14,'87323F-9'),
(5,15,'828210-F'),
(6,16,'98234F-5'),
(7,17,'983241-8'),
(8,18,'87123C-2'),
(9,18,'983144-1'),
(10,20,'873212-G');


SELECT PERSONAS.PRIMER_NOMBRE, PERSONAS.PRIMER_APELLIDO, EMPLEADOS.CARGO, EMPLEADOS.FECHA_CONTRATACION 
FROM (PERSONAS INNER JOIN EMPLEADOS) 
WHERE PERSONAS.ID_PERSONA = EMPLEADOS.ID_PERSONA


