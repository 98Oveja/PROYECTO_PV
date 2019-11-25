package models.Ventas_Compras;
import java.security.PrivateKey;
import java.util.Date;

public class Ventas {
//  VARIABLES PARA LA TUPLA DE VENTAS
    private int id_Cliente, id_Empleado;
    private double total;
    private Date fecha;
//  VARIABLES PARA EL DETALLE DE LA VENTA
    private int id_Venta, id_Poducto;
    private String descripcion;
    private double subTotal;
    private int canidad;
//  VARIABLES COMPLEMENTARIAS PARA LA CLASE
    private String nombre_Empleado;
    private String nombre_Cliente;
    private double calculoTotal;
    private float descuento;
/*
Procedimiento: Es un conjunto de instrucciones que cumplen una tarea
Función: Como un procedimiento pero retorna un valor
Clase: Concepto de programación orientada a objetos, es una forma de encapsular funcionalidad, contiene campos y métodos.
Método: Puede ser un procedimiento o una función, la diferencia es que le pertenece a una clase.
 */


//  METODO PARA BUSCAR A AUN EMPLEADO POR NOMRE
    public int getNombreEmpleadoById(String NombreEmpleado_In){

        return  id_Empleado;
    }
//  MEDODO PARA BUSCAR EL CLIENTE POR NOMBRE
    public int getNombreClienteById(String NombreCliente_In){

        return  id_Cliente;
    }
//  METODO PARA MOSTRAR LAS VENTAS
    public void listaDeVentas(){

    }
//  METODO PARA INSERTAR UNA VENTA
    public void registrarVenta(){

    }
//  METODO PARA CANCELAR UNA VENTA
//  METODO PARA VER EL DATALLE DE VENTA
//  METODO PARA INSERTAR EL DETALLE DE VENTA
//  METODO PARA BUSCAR UNA VENTA









}
