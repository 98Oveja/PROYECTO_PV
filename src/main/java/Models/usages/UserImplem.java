package Models.usages;

import Models.User;
import Models.interfaces.userImpl;
import Utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImplem implements userImpl {
    @Override
    public void insert(User user) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(User user) {
    //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) {
    //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }


    Connection connection = null;
    User userAux = null;
    @Override
    public User read(String user, String pass) {
        try {
            ConnectionUtil connectionUtil = new ConnectionUtil();
            connection = connectionUtil.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from USUARIOS where CONTRASENA = ? and EMAIL = ?")) {

                preparedStatement.setString(1, pass);
                preparedStatement.setString(2,user);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userAux = new User(
                                resultSet.getString("NOMBRE"),
                                resultSet.getInt("ESTADO"),
                                resultSet.getString("CARGO"),
                                resultSet.getString("URL_PHOTO")
                        );
                        //System.out.println(userAux);
                    }
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userAux;
    }
}
