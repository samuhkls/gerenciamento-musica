package dao;

import model.Musica;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class musicaDAO {

    public void createMusica(Musica musica){
        String SQL = "INSERT INTO MUSICA (NOME, ARTISTA, DURACAO) VALUES (?, ?, ?)";
        String connect = "CREATE TABLE IF NOT EXISTS MUSICA(ID SERIAL PRIMARY KEY, NOME VARCHAR(255), ARTISTA VARCHAR(255), DURACAO DOUBLE)";

        try{
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa","sa");
            System.out.println("success in database connection");

            PreparedStatement connectStatement = connection.prepareStatement(connect);
            connectStatement.executeUpdate();

            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa","sa");
            System.out.println("success in database connection");

            //CREATE TABLE MUSICA(ID SERIAL PRIMARY KEY, NOME VARCHAR(255), ARTISTA VARCHAR(255), DURACAO DOUBLE)


            PreparedStatement connectStatement = connection.prepareStatement(connect);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            connectStatement.executeUpdate();

            preparedStatement.setString(1, musica.getNome());
            preparedStatement.setString(2, musica.getArtista());
            preparedStatement.setDouble(3, musica.getDuracao());

            preparedStatement.executeUpdate();

            System.out.println(musica.getArtista());
            System.out.println(musica.getDuracao());
            System.out.println(musica.getNome());
            System.out.println("success in insert music");

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");
            e.printStackTrace();
        }
    }

    public List<Musica> findAllMusicas() {

        String SQL = "SELECT * FROM MUSICA";

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Musica> musicas = new ArrayList<>();

            while (resultSet.next()) {
                String musicaNome = resultSet.getString("NOME");
                String artista = resultSet.getString("ARTISTA");
                double duracao = resultSet.getDouble("DURACAO");

                Musica musica = new Musica(musicaNome, artista, duracao);
                musicas.add(musica);

            }

            System.out.println("success in select * musics");
            connection.close();

            return musicas;
        } catch (Exception e) {

            System.out.println("fail in database connection");
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}