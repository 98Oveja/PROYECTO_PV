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
            case "inicio":
                aux = HOME_IMAGE;
                break;
            case "estadisiticas":
                aux = STATISTICS_IMAGE;
                break;
            case "empleados":
                aux = EMPLOYEES_IMAGE;
                break;
            case "productos":
                aux = PRODUCTS_IMAGE;
                break;
            case "proveedores":
                aux = SUPPLIERS_IMAGE;
                break;
            case "clientes":
                aux = CUSTOMERS_IMAGE;
                break;
            case "compras":
                aux = PURCHASES_IMAGE;
                break;
            case "calendario":
                aux = CALENDAR_IMAGE;
                break;
            case "ventas":
                aux = SALES_IMAGE;
                break;
            case "reportes":
                aux = REPORTS_IMAGE;
                break;
        }
        return aux;
    }
}
