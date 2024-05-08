package dao;

import model.User;
import servlet.config.ConnectionPoolConfig;

import java.sql.*;

public class UserDAO {
    public void createUser(User user){
        String SQL = "INSERT INTO USR (USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
        String connect = "CREATE TABLE IF NOT EXISTS USR(ID INT PRIMARY KEY AUTO_INCREMENT, USERNAME VARCHAR(255), EMAIL VARCHAR(255), PASSWORD VARCHAR(255))";

        try{
            Connection connection = ConnectionPoolConfig.getConnection();


            PreparedStatement connectStatement = connection.prepareStatement(connect);
            connectStatement.executeUpdate();

            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = ConnectionPoolConfig.getConnection();


            //CREATE TABLE MUSICA(ID SERIAL PRIMARY KEY, NOME VARCHAR(255), ARTISTA VARCHAR(255), DURACAO DOUBLE)


            PreparedStatement connectStatement = connection.prepareStatement(connect);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            connectStatement.executeUpdate();

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.executeUpdate();

            System.out.println(user.getUsername());
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            System.out.println("success in insert user");

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");
            e.printStackTrace();
        }


    }
    public boolean verifyCredentials(User user){

        String SQL = "SELECT * FROM USR WHERE USERNAME = ?";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("success in select username");

            while (resultSet.next()) {

                String password = resultSet.getString("password");

                if (password.equals(user.getPassword())) {

                    return true;

                }

            }

            connection.close();

            return false;

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

            return false;

        }
    }


}
