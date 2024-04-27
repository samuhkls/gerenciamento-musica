package dao;

import model.Musica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class musicaDAO {

    public void createMusica(Musica musica){
        String SQL = "INSERT INTO MUSICA (NOME) VALUES (?)";

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa","sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, musica.getNome());
            preparedStatement.execute();

            System.out.println("success in insert car");

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection");

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

                String musicaNome = resultSet.getString("name");

                Musica musica = new Musica(musicaNome);

                musicas.add(musica);

            }

            System.out.println("success in select * car");

            connection.close();

            return musicas;

        } catch (Exception e) {

            System.out.println("fail in database connection");

            return Collections.emptyList();

        }

    }


}
