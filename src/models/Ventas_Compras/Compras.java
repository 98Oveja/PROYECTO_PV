package models.Ventas_Compras;

import java.util.Date;

public class Compras {
//  MODELO DE LOS DATOS PARA LA ENTIDAD COMPRA
    private int Id_Proveedor;
    private Date Fecha;
    private double Total;
    private double Descuento;
//  MODELO PARA LOS DATOS PARA LA ENDIDAD DE DETALLE DE LA COMPRA
    private int Id_Compra;
    private int Id_Producto;
    private double Cantidad;
    private double Precio_Producto;
    private double SubTotal;
//    GETERS AND SETERS
    public int getId_Proveedor() {return Id_Proveedor;}
    public void setId_Proveedor(int id_Proveedor){Id_Proveedor = id_Proveedor;}
    public Date getFecha() {return Fecha;}
    public void setFecha(Date fecha) {Fecha = fecha;}
    public double getTotal(){return Total;}
    public void setTotal(double total) {Total = total;}
    public double getDescuento() {return Descuento;}
    public void setDescuento(double descuento){Descuento = descuento;}
    public int getId_Compra(){return Id_Compra;}
    public void setId_Compra(int id_Compra){Id_Compra = id_Compra;}
    public int getId_Producto(){return Id_Producto;}
    public void setId_Producto(int id_Producto){Id_Producto = id_Producto;}
    public double getCantidad(){return Cantidad;}
    public void setCantidad(double cantidad){Cantidad = cantidad;}
    public double getPrecio_Producto(){return Precio_Producto;}
    public void setPrecio_Producto(double precio_Producto){Precio_Producto = precio_Producto;}
    public double getSubTotal(){return SubTotal;}
    public void setSubTotal(double subTotal){SubTotal = subTotal;}
//    quitar los descuentod unitarios para cada producto de la venta








}
