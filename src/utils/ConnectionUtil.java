
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
//    Connection conn = null;
    public static Connection conDB()
    {
        String User ="Ronaldo";
        String Port =":3306";
        String Host ="127.0.0.1";
        String Password ="Datos18";
        String Database = "PuntoDeVenta";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net/sql10312746", "sql10312746", "AA3qZAZqgN");
//             con = DriverManager.getConnection("jdbc:mysql://"+Host+"/"+Database, User, Password);

           return con;

        } catch (ClassNotFoundException | SQLException ex) {
//            System.out.println(con.toString());
            System.err.println("Data Base isn't connected : "+ex.getMessage());
           return null;
        }
    }

}
