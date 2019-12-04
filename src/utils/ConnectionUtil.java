package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String dataBase = "punto_de_venta";
    public static String host = "db4free.net";
    public static String user = "admin_e";
    public static String password = "12345678lol";
    public static String url = "jdbc:mysql://"+host+"/"+dataBase;
    private static Connection conn;
    //Constructor
    public ConnectionUtil(){
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
          //  System.out.println(conn != null? "Data base is connected": "Data base is not connected");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Internal Error: "+e.getMessage());
        }
        //jdbc:mysql://db4free.net:3306/punto_de_venta
    }


    public Connection getConnection(){
        return conn;
    }

    public void offlineDataBase(){
        conn = null;
        System.out.println(conn == null? "Data base is Desconected succssesful":"You are connected alredy");
    }



//    public static Connection conDB()
//    {
//        String User ="Ronaldo";
//        String Port =":3306";
//        String Host ="127.0.0.1";
//        String Password ="Datos18";
//        String Database = "PuntoDeVenta";
//        Connection con = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
////            con = DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net/sql10312746", "sql10312746", "AA3qZAZqgN");
//             con = DriverManager.getConnection("jdbc:mysql://"+Host+"/"+Database, User, Password);
//
//           return con;
//
//        } catch (ClassNotFoundException | SQLException ex) {
////            System.out.println(con.toString());
//            System.err.println("Data Base isn't connected : "+ex.getMessage());
//           return null;
//        }
//    }

}


/*
public static String driver = "com.mysql.jdbc.Driver";
    public static String dataBase = "sql10312746";
    public static String host = "sql10.freemysqlhosting.net";
    public static String user = "sql10312746";
    public static String password = "AA3qZAZqgN";
    public static String url = "jdbc:mysql://"+host+"/"+dataBase;
    private static Connection conn;
    //Constructor
    public connect(){
        conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn != null? "Data base is connected": "Data base is not connected");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Internal Error: Code = 500");
        }

    }

    public Connection getConnection(){
        return conn;
    }

    public void offlineDataBase(){
        conn = null;
        System.out.println(conn == null? "Data base is Desconected succssesful":"You are connected alredy");
    }



* */