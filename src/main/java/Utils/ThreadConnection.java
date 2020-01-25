package Utils;


import javafx.application.Platform;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ThreadConnection {
    Connection con;
    ResultSet resultSet = null;
    ConnectionUtil connectionUtil = new ConnectionUtil();
    public ResultSet res;
    String sql;
    ArrayList<Object> arrayListAux = new ArrayList();

    public ThreadConnection(String sql ){
        this.sql = sql;
    }

    public ThreadConnection() {
    }

    public void create(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        /*
                        Connection connection = connectionUtil.getConnection();
                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            ResultSet resultSet = preparedStatement.executeQuery();
                                while (resultSet.next()){

                                  System.out.println(resultSet.getObject("NOMBRE"));
                                }
                        } catch (SQLException e) {
                            System.out.println("error:"+e);
                        }

                         */
                    }
                };

                try{
                    Thread.sleep(1000);
                } catch (InterruptedException ex){}finally {
                }

                Platform.runLater(updater);
            }
        });

        thread.setDaemon(true);
        thread.start();
    }
}
