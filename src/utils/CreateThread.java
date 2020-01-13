package utils;

import javafx.concurrent.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateThread extends Task<ResultSet> {

    String sql = null;
    ConnectionUtil conn = null;
    Connection con = null;
    PreparedStatement preparedStatement = null;

    public CreateThread(String value) {
        sql = value;
    }

    @Override
    protected ResultSet call() throws Exception {
        updateMessage("Processing");
        ResultSet result = returnData();
        updateMessage("Done");

        return result;
    }

    private void createConection() throws SQLException {
        conn = new ConnectionUtil();
        con = conn.getConnection();
        preparedStatement = con.prepareStatement(sql);

    }

    private ResultSet returnData(){
        try {
            createConection();
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet != null){
                return resultSet;
            }
        }catch (Exception e ){
            System.out.println(e+"error");
        }
        return null;
    }


}
