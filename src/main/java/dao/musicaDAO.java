package dao;

import model.Musica;
import servlet.config.ConnectionPoolConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class musicaDAO {

    public void createMusica(Musica musica){
        String SQL = "INSERT INTO MUSICA (NOME, ARTISTA, DURACAO) VALUES (?, ?, ?)";
        String connect = "CREATE TABLE IF NOT EXISTS MUSICA(ID SERIAL PRIMARY KEY, NOME VARCHAR(255), ARTISTA VARCHAR(255), DURACAO DOUBLE)";

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

            PreparedStatement connectStatement = connection.prepareStatement(connect);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            connectStatement.executeUpdate();

            preparedStatement.setString(1, musica.getNome());
            preparedStatement.setString(2, musica.getArtista());
            preparedStatement.setDouble(3, musica.getDuracao());

            preparedStatement.executeUpdate();


            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    musica.setId(id);
                }else {
                    throw new SQLException("Creating musica failed, no ID obtained.");
                }
            }




            System.out.println(musica.getArtista());
            System.out.println(musica.getDuracao());
            System.out.println(musica.getNome());
            System.out.println(musica.getId());
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

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Musica> musicas = new ArrayList<>();

            while (resultSet.next()) {
                String musicaNome = resultSet.getString("NOME");
                String artista = resultSet.getString("ARTISTA");
                double duracao = resultSet.getDouble("DURACAO");
                int id = resultSet.getInt("ID");

                Musica musica = new Musica(musicaNome, artista, duracao);
                musica.setId(id);
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
    public int getMusicaByNome(String nome){

        String SQL = "SELECT * FROM Musica WHERE NOME = ?";
        Musica musica = new Musica();
        int id = 0;


        try{
            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1, nome);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                musica = new Musica();
                musica.setId(resultSet.getInt("id"));
                id = musica.getId();
                return id;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public Musica getMusicaById(int id) {
        String SQL = "SELECT * FROM Musica WHERE ID = ?";
        Musica musica = null;

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                musica = new Musica();
                musica.setId(resultSet.getInt("id"));
                musica.setNome(resultSet.getString("nome"));
                musica.setArtista(resultSet.getString("artista"));
                musica.setDuracao(resultSet.getDouble("duracao"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return musica;
    }
}