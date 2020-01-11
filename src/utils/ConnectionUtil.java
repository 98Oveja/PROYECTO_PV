package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String dataBase = "u376498608_punto_de_venta";
    public static String host = "sql309.main-hosting.eu";
    public static String user = "u376498608_admin_e";
    public static String password = "12345678lol";
    public static String url = "jdbc:mysql://"+host+"/"+dataBase+"?autoReconnect=true&useSSL=false"; //connection ssl false
    private static Connection conn;
    //Constructor
    public ConnectionUtil(){
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn != null? "Data base is connected": "Data base is not connected");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Internal Error: "+e.getMessage());
        }
    }


    public Connection getConnection(){
        return conn;
    }

    public void offlineDataBase(){
        conn = null;
        System.out.println(conn == null? "Data base is Desconected succssesful":"You are connected alredy");
    }

}

