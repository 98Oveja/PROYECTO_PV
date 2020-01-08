package models.Employ;
import javafx.fxml.FXML;
import utils.ConnectionUtil;
import java.sql.*;

public class EditEmploy {

        @FXML
        public dataEmploy searchData(int id_employ) {
        dataEmploy xd = new dataEmploy();
            try {

                ConnectionUtil connectionClass = new ConnectionUtil();
                Connection connection = connectionClass.getConnection();
                String Query = "SELECT E.ID_EMPLEADO, P.ID_PERSONA, P.PRIMER_NOMBRE, P.SEGUNDO_NOMBRE, P.PRIMER_APELLIDO, P.SEGUNDO_APELLIDO, P.DIRECCION, P.CORREO, P.TELEFONO, P.url_foto, E.ESTADO, E.FECHA_CONTRATACION, E.CARGO FROM PERSONAS P , EMPLEADOS E WHERE E.ID_EMPLEADO = P.ID_PERSONA AND E.ID_EMPLEADO = '"+id_employ+"'";
                Statement instruccion = connection.createStatement();
                ResultSet result = instruccion.executeQuery(Query);
                if (result != null) {
                    while (result.next()) {
                        xd.idemp = result.getInt("ID_EMPLEADO");
                        xd.idper = result.getInt("ID_PERSONA");
                        xd.name1 = result.getString("PRIMER_NOMBRE");
                        xd.name2 = result.getString("SEGUNDO_NOMBRE");
                        xd.lastname1 = result.getString("PRIMER_APELLIDO");
                        xd.lastname2 = result.getString("SEGUNDO_APELLIDO");
                        xd.dir = result.getString("DIRECCION");
                        xd.tel = result.getInt("TELEFONO");
                        xd.correo = result.getString("CORREO");
                        xd.img = result.getString("url_foto");
                        xd.estado = result.getBoolean("ESTADO");
                        xd.fechaInicio = result.getString("FECHA_CONTRATACION");
                        xd.cargo = result.getString("CARGO");
                    }
                }
                instruccion.close();
                return xd;
            } catch (Exception ex) {
                System.out.println(ex +"error aqui");
                return null;
            }
        }

    public void updateEmploy(int id, int idemploy, String name1,String name2, String last1, String last2,String dir, int tel, String email, String url,String date,String place) throws SQLException {
        String urlimage= url.replace("\\","*");
        ConnectionUtil connectionClass= new ConnectionUtil();
        Connection connection= connectionClass.getConnection();  /*coneccion establecida*/
        String sqlUpdate= "UPDATE PERSONAS SET PERSONAS.PRIMER_NOMBRE ='"+name1+"', SEGUNDO_NOMBRE='"+name2+"', PRIMER_APELLIDO='"+last1+"', SEGUNDO_APELLIDO='"+last2+"', DIRECCION= '"+dir+"'" +
                ", TELEFONO = '"+tel+"', CORREO='"+email+"', url_foto='"+urlimage+"'  WHERE ID_PERSONA = '"+id+"'";
        Statement statement= connection.createStatement();
        statement.executeUpdate(sqlUpdate); //aca insertamos los dato

        //esteremos realizando el segundo update para la tabla empleados
        String sql2= "UPDATE EMPLEADOS SET CARGO='"+place+"' WHERE ID_EMPLEADO='"+idemploy+"'";
        statement.executeUpdate(sql2);
        statement.close();
    }
}
