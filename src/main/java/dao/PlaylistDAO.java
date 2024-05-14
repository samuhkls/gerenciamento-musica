package dao;

import model.Musica;
import model.Playlist;
import model.User;
import servlet.config.ConnectionPoolConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO  {

    String connect = "CREATE TABLE IF NOT EXISTS PLAYLIST(ID SERIAL PRIMARY KEY, NOME VARCHAR(255), AUTOR VARCHAR(255), USERID INT, FOREIGN KEY (userId) REFERENCES USR(ID))";
    String SQL = "INSERT INTO PLAYLIST (NOME, AUTOR, USERID) VALUES (?, ?, ?)";




    public void createPlaylist(Playlist playlist, User user){

        try{
            Connection connection = ConnectionPoolConfig.getConnection();


            PreparedStatement connectStatement = connection.prepareStatement(connect);
            connectStatement.executeUpdate();

            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (
        Connection connection = ConnectionPoolConfig.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, playlist.getNomePLaylist());
            preparedStatement.setString(2, playlist.getAutor());
            preparedStatement.setInt(3, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Playlist> getPlaylistsByUser(User user) {
        String SQL = "SELECT * FROM Playlist WHERE userId = ?";
        List<Playlist> playlists = new ArrayList<>();

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            // setando o id do usuario que de que ira procurar as playlists
            preparedStatement.setInt(1, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            // criando a lista com todas as playlists que pertencem aquele usuario
            while (resultSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setNomePLaylist(resultSet.getString("name"));
                playlist.setAutor(resultSet.getString("author"));
                playlist.setQuantidade(resultSet.getInt("quantity"));
                playlists.add(playlist);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playlists;
    }

    public void addMusicaToPlaylist(Playlist playlist, Musica musica) {
        initializeDatabase();
        String SQL = "INSERT INTO PlaylistMusica (playlistId, musicaId) VALUES (?, ?)";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            // pegando o id da playlist em que a musica ira entrar, e o id da musica em questao
            preparedStatement.setInt(1, playlist.getId());
            preparedStatement.setInt(2, musica.getId());

            preparedStatement.executeUpdate();

            System.out.println("musica sendo add no banco");
            System.out.println(playlist.getMusicas());
            System.out.println(musica.getNome());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Musica> getMusicasInPlaylist(Playlist playlist) {
        // essa string seleciona todas as musicas que estão em uma playlist especifica
        String SQL = "SELECT Musica.* " +
                "FROM Musica " +
                "JOIN PlaylistMusica ON Musica.id = PlaylistMusica.musicaId " +
                "WHERE PlaylistMusica.playlistId = ?";

        List<Musica> musicas = new ArrayList<>();

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            // pegando o id da playlist
            preparedStatement.setInt(1, playlist.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            // adicionando as musicas em uma nova lista para ser usada
            while (resultSet.next()) {
                Musica musica = new Musica();
                musica.setId(resultSet.getInt("id"));
                musica.setNome(resultSet.getString("nome"));
                musica.setArtista(resultSet.getString("artista"));
                musica.setDuracao(resultSet.getDouble("duracao"));
                musicas.add(musica);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return musicas;
    }

    public void initializeDatabase() {
        String SQL = "CREATE TABLE IF NOT EXISTS PlaylistMusica ("
                + "playlistId INT, "
                + "musicaId INT, "
                + "PRIMARY KEY (playlistId, musicaId), "
                + "FOREIGN KEY (playlistId) REFERENCES Playlist(id), "
                + "FOREIGN KEY (musicaId) REFERENCES Musica(id)"
                + ")";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

