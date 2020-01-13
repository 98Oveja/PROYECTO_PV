package models.Employ;

import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class sqlEmploy {
    Date date =new Date();//varaiables para obtener la fecha actual del system
    long milsec = date.getTime();
    java.sql.Date dia = new java.sql.Date(milsec);
    List<dataEmploy> data = new ArrayList<>();

    public dataEmploy searchData(int id_employ) {
    dataEmploy xd = new dataEmploy();
        try {
            ConnectionUtil connectionClass = new ConnectionUtil();
            Connection connection = connectionClass.getConnection();
            String Query = "SELECT E.ID_EMPLEADO, P.ID_PERSONA, P.PRIMER_NOMBRE, P.SEGUNDO_NOMBRE, P.PRIMER_APELLIDO, P.SEGUNDO_APELLIDO, P.DIRECCION, P.CORREO, P.TELEFONO, P.url_foto, E.ESTADO, E.FECHA_CONTRATACION, E.CARGO FROM PERSONAS P , EMPLEADOS E WHERE E.ID_PERSONA = P.ID_PERSONA AND E.ID_EMPLEADO = "+id_employ+"";
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
                    xd.correo = result.getString("CORREO");
                    xd.tel = result.getString("TELEFONO");
                    xd.img = result.getString("url_foto").replace("*","\\");
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

    public void updateEmploy(int id, int idemploy, String tel, String url,String place) throws SQLException {
        url.replace("file:/","");
        String urlimage= url.replace("\\","*");
        System.out.println("----62: "+urlimage);
        ConnectionUtil connectionClass= new ConnectionUtil();
        Connection connection= connectionClass.getConnection();  /*coneccion establecida*/
        String sqlUpdate= "UPDATE PERSONAS SET TELEFONO ='"+tel+"', url_foto='"+urlimage+"'  WHERE PERSONAS.ID_PERSONA= "+id+";";
        Statement statement= connection.createStatement();
        statement.executeUpdate(sqlUpdate); //aca insertamos los dato
        System.out.println(sqlUpdate);
        //esteremos realizando el segundo update para la tabla empleados
        String sql2= "UPDATE EMPLEADOS SET CARGO='"+place+"' WHERE ID_EMPLEADO="+idemploy+"";
        statement.executeUpdate(sql2);
        System.out.println(sql2);
        statement.close();
    }

    public void deleteEmploy(int idemp){
            try{
                ConnectionUtil connectionClass= new ConnectionUtil();
                Connection connection= connectionClass.getConnection();  /*coneccion establecida*/
                String sqlDelete= "UPDATE EMPLEADOS SET ESTADO = 0, FECHA_RETIRO = '"+dia+"' WHERE ID_EMPLEADO="+idemp+"";
                Statement statement= connection.createStatement();
                statement.executeUpdate(sqlDelete); //aca insertamos los dato
                statement.close();
            }catch (Exception ex){
                System.out.println("ERROR EN DELETEeMPLOY "+ex);
            }

    }

    public List<dataEmploy> employDB(){
        try {
            data.clear();

            ConnectionUtil connectionClass = new ConnectionUtil();
            Connection connection = connectionClass.getConnection();
            String sle = "SELECT E.ID_EMPLEADO, P.ID_PERSONA, P.PRIMER_NOMBRE, P.SEGUNDO_NOMBRE, P.PRIMER_APELLIDO, P.SEGUNDO_APELLIDO, P.DIRECCION, P.CORREO, P.TELEFONO, P.url_foto, E.ESTADO, E.FECHA_CONTRATACION, E.CARGO FROM PERSONAS P , EMPLEADOS E WHERE E.ID_PERSONA = P.ID_PERSONA AND E.ESTADO=1 ORDER BY ID_EMPLEADO desc";
            Statement instruccion = connection.createStatement();
            ResultSet resulte = instruccion.executeQuery(sle);
            if (resulte != null) {
                while (resulte.next()) {
                    dataEmploy empl = new dataEmploy();
                    empl.idemp = resulte.getInt("ID_EMPLEADO");
                    empl.idper = resulte.getInt("ID_PERSONA");
                    empl.name1 = resulte.getString("PRIMER_NOMBRE");
                    empl.name2 = resulte.getString("SEGUNDO_NOMBRE");
                    empl.lastname1 = resulte.getString("PRIMER_APELLIDO");
                    empl.lastname2 = resulte.getString("SEGUNDO_APELLIDO");
                    empl.dir = resulte.getString("DIRECCION");
                    empl.correo = resulte.getString("CORREO");
                    empl.tel = resulte.getString("TELEFONO");
                    empl.img =  resulte.getString("url_foto");
                    empl.estado = resulte.getBoolean("ESTADO");
                    empl.fechaInicio = resulte.getString("FECHA_CONTRATACION");
                    empl.cargo = resulte.getString("CARGO");
                    data.add(empl);
                }

            }
            instruccion.close();
            System.out.println(data.size()+" recividos");
            return data;
        } catch (Exception ex) {
            System.out.println(ex +"error aqui");
            System.out.println("linea 117 ******************************************");
            return null;
        }
    }
}
