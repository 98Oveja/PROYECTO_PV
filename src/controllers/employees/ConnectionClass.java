package controllers.employees;

import com.mysql.jdbc.Connection;
import javafx.fxml.FXML;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
public Connection connection;
    @FXML
    public static Connection conecctiondb() {

        String nameDB="ferreteria";
        String nameUser="root";
        String pass="";
     java.sql.Connection conn = null;


            try {
                Class.forName("com.mysql.jdbc.Driver");
                //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net/sql10312746", "sql10312746", "AA3qZAZqgN");
                java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ferreteria", "root", "");
                return (Connection) con;

            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println("ConnectionUtil : "+ex.getMessage());
                return null;
            }
        }
}
