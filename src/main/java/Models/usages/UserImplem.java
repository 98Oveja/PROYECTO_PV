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
        try {
            ConnectionUtil connectionUtil = new ConnectionUtil();
            connection = connectionUtil.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into USUARIOS(email, nombre, apellidos, contrasena, estado, cargo, url_photo,dataSetting) values (?,?,?,?,?,?,?,?);")) {

                preparedStatement.setString(1,user.getEmail());
                preparedStatement.setString(2,user.getName());
                preparedStatement.setString(3,user.getLast_name());
                preparedStatement.setString(4,user.getPass());
                preparedStatement.setInt(5,user.getStatus());
                preparedStatement.setString(6,user.getAdmin());
                preparedStatement.setString(7,user.getUrlPhoto());
                preparedStatement.setString(8,user.getDataSetting());

                preparedStatement.execute();
                System.out.println("insertado");

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
    }

    @Override
    public void update(User user) {

        try {
            ConnectionUtil connectionUtil = new ConnectionUtil();
            connection = connectionUtil.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE USUARIOS SET EMAIL = ?, NOMBRE = ?, APELLIDOS = ?,   CONTRASENA = ?,  ESTADO = ?, CARGO = ?, URL_PHOTO = ?, dataSetting = ? WHERE ID_USUARIO = ?;")) {

                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getLast_name());
                preparedStatement.setString(4, user.getPass());
                preparedStatement.setInt(5, user.getStatus());
                preparedStatement.setString(6, user.getAdmin());
                preparedStatement.setString(7, user.getUrlPhoto());
                preparedStatement.setString(8, user.getDataSetting());

                preparedStatement.setInt(9,user.getId_ususer());
                preparedStatement.executeUpdate();
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
    }

    @Override
    public void delete(Integer id) {
    //TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }


    Connection connection = null;
    User userAux = null;
    
    @Override
    public boolean existEmail(String email){
        boolean status;
        try {
            ConnectionUtil connectionUtil = new ConnectionUtil();
            connection = connectionUtil.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from USUARIOS where  EMAIL = ?")) {

                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                       status = true;
                    }
                    else {
                        status = false;
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
        return status;
    }
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
                                resultSet.getInt("ID_USUARIO"),
                                resultSet.getString("EMAIL"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDOS"),
                                resultSet.getString("CONTRASENA"),
                                resultSet.getInt("ESTADO"),
                                resultSet.getString("CARGO"),
                                resultSet.getString("URL_PHOTO"),
                                resultSet.getString("dataSetting")
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
