package controllers;

public class ViewImage {
    String HOME_IMAGE = "/images/home.png";             String STATISTICS_IMAGE = "/images/estadistica.png";
    String EMPLOYEES_IMAGE = "/images/empleados.png";   String PRODUCTS_IMAGE = "/images/producto.png";
    String SUPPLIERS_IMAGE = "/images/provider.png";    String CUSTOMERS_IMAGE = "/images/customers.png";
    String PURCHASES_IMAGE = "/images/icon_cart.png";   String CALENDAR_IMAGE = "/images/Calendar.png";
    String SALES_IMAGE = "/images/sales.png";           String REPORTS_IMAGE = "/images/report.png";

    public String getImageItem(String url){
        String aux = null;
        switch (url){
            case "Inicio":
                aux = HOME_IMAGE;
                break;
            case "Estadisiticas":
                aux = STATISTICS_IMAGE;
                break;
            case "Empleados":
                aux = EMPLOYEES_IMAGE;
                break;
            case "Productos":
                aux = PRODUCTS_IMAGE;
                break;
            case "Proveedores":
                aux = SUPPLIERS_IMAGE;
                break;
            case "Clientes":
                aux = CUSTOMERS_IMAGE;
                break;
            case "Compras":
                aux = PURCHASES_IMAGE;
                break;
            case "Calendario":
                aux = CALENDAR_IMAGE;
                break;
            case "Ventas":
                aux = SALES_IMAGE;
                break;
            case "Reportes":
                aux = REPORTS_IMAGE;
                break;
        }
        return aux;
    }
}
