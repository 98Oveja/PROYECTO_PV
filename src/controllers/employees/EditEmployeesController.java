package controllers.employees;

import javafx.fxml.FXML;
import models.Employ.dataEmploy;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EditEmployeesController {
    @FXML
    public ArrayList<dataEmploy> Datos = new ArrayList<>();
    @FXML
    public void searchData(int id_employ){
        try{

            ConnectionUtil connectionClass = new ConnectionUtil();
            Connection connection = connectionClass.getConnection();
            String Query = "SELECT P.ID_PERSONA, P.PRIMER_NOMBRE, P.SEGUNDO_NOMBRE, P.PRIMER_APELLIDO, P.SEGUNDO_APELLIDO, P.DIRECCION, P.CORREO, P.TELEFONO, P.url_foto,\n" +
                    "       E.ESTADO, E.FECHA_CONTRATACION, E.CARGO\n" +
                    "FROM PERSONAS P , EMPLEADOS E\n" +
                    "WHERE E.ID_EMPLEADO = P.ID_PERSONA AND E.ID_EMPLEADO = '"+ id_employ +"' ;";
            Statement instruccion = connection.createStatement();
            ResultSet result= instruccion.executeQuery(Query);
            if (result != null) {
                int i=0;
                while (result.next()) {
                    Datos.get(i).id = result.getInt("ID_PERSONA");
                    Datos.get(i).name1 = result.getString("PRIMER_NOMBRE");
                    Datos.get(i).name2 = result.getString("SEGUNDO_NOMBRE");
                    Datos.get(i).lastname1 = result.getString("PRIMER_APELLIDO");
                    Datos.get(i).lastname2 = result.getString("SEGUNDO_APELLIDO");
                    Datos.get(i).dir = result.getString("DIRECCION");
                    Datos.get(i).tel = result.getInt("TELEFONO");
                    Datos.get(i).correo = result.getString("CORREO");
                    Datos.get(i).img = result.getString("URL_FOTO");
                    Datos.get(i).estado = result.getBoolean("ESTADO");
                    Datos.get(i).fechaInicio = result.getString("FECHA_CONTRATACION");
                    Datos.get(i).cargo = result.getString("CARGO");
                    i++;
                }

            }
        }catch (Exception ex){
            System.out.println(ex);
            }
    }
}
