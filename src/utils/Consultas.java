package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Consultas {
String query;ConnectionUtil conn = new ConnectionUtil();
    Connection conexion = null;
    public void eliminarProveedor(String nombre,String movil, String org){
        String n1 = null,a1 = null;
        if (nombre.contains(" ")){
            String[] texto = nombre.split(" ");
            n1=texto[0];a1=texto[1];
        }
        query= "UPDATE PROVEEDORES INNER JOIN PERSONAS P on PROVEEDORES.ID_PERSONA = P.ID_PERSONA SET ESTADO=0 " +
                "WHERE P.PRIMER_NOMBRE=\""+n1+"\" AND P.PRIMER_APELLIDO=\""+a1+"\" "+
                "AND P.TELEFONO=\""+movil+"\" AND ORG=\""+org+"\";";
        conexion = conn.getConnection();
        try{
            PreparedStatement preparedStatement = conexion.prepareStatement(query);
            preparedStatement.execute();
        }
        catch (Exception e){}
    }
}
