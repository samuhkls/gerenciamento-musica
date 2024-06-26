package dao;

import model.User;
import servlet.config.ConnectionPoolConfig;

import java.sql.*;

public class UserDAO {
    public void createUser(User user){
        String SQL = "INSERT INTO USR (USERNAME, EMAIL, PASSWORD) VALUES (?, ?, ?)";
        String connect = "CREATE TABLE IF NOT EXISTS USR(ID INT PRIMARY KEY AUTO_INCREMENT, USERNAME VARCHAR(255), EMAIL VARCHAR(255), PASSWORD VARCHAR(255))";
        String PlaylistInicializate =  "CREATE TABLE IF NOT EXISTS PLAYLIST(ID SERIAL PRIMARY KEY, NOME VARCHAR(255), AUTOR VARCHAR(255), USERID INT, FOREIGN KEY (userId) REFERENCES USR(ID))";
        String PlaylistMusicaInicializate = "CREATE TABLE IF NOT EXISTS PlaylistMusica ("
                + "playlistId INT, "
                + "musicaId INT, "
                + "PRIMARY KEY (playlistId, musicaId), "
                + "FOREIGN KEY (playlistId) REFERENCES Playlist(id), "
                + "FOREIGN KEY (musicaId) REFERENCES Musica(id)"
                + ")";
        String MusicaInicializate = "CREATE TABLE IF NOT EXISTS MUSICA(ID SERIAL PRIMARY KEY, NOME VARCHAR(255), ARTISTA VARCHAR(255), DURACAO DOUBLE)";


        try{
            Connection connection = ConnectionPoolConfig.getConnection();


            PreparedStatement connectStatement = connection.prepareStatement(connect);
            connectStatement.executeUpdate();

            PreparedStatement musicaStatement = connection.prepareStatement(MusicaInicializate);
            musicaStatement.executeUpdate();

            PreparedStatement playlistStatement = connection.prepareStatement(PlaylistInicializate);
            playlistStatement.executeUpdate();

            PreparedStatement playlistMusicaStatement = connection.prepareStatement(PlaylistMusicaInicializate);
            playlistMusicaStatement.executeUpdate();

            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = ConnectionPoolConfig.getConnection();


            //CREATE TABLE MUSICA(ID SERIAL PRIMARY KEY, NOME VARCHAR(255), ARTISTA VARCHAR(255), DURACAO DOUBLE)

            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);


            System.out.println(user.getUsername());
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            System.out.println(user.getUsername());
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            System.out.println(user.getId());
            System.out.println("success in insert user");

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");
            e.printStackTrace();
        }


    }
    public boolean isEmailRegistered(String email) {
        String SQL = "SELECT COUNT(1) FROM USR WHERE EMAIL = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // e-mail existe se for maior que zero
            } else {
                throw new RuntimeException("Error checking email existence");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int findUserId(String nome){
        String SQL = "SELECT ID FROM USR WHERE USERNAME = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("ID");
            } else {
                throw new RuntimeException("User not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
