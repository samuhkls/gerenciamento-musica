package dao;

import model.Musica;
import model.Playlist;
import model.User;
import servlet.config.ConnectionPoolConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    String connect = "CREATE TABLE IF NOT EXISTS PLAYLIST(ID SERIAL PRIMARY KEY, NOME VARCHAR(255), AUTOR VARCHAR(255), USERID INT, FOREIGN KEY (userId) REFERENCES USR(ID))";
    String SQL = "INSERT INTO PLAYLIST (NOME, AUTOR, USERID) VALUES (?, ?, ?)";


    public void createPlaylist(Playlist playlist, User user) {

        try {
            Connection connection = ConnectionPoolConfig.getConnection();


            PreparedStatement connectStatement = connection.prepareStatement(connect);
            connectStatement.executeUpdate();

            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (
                Connection connection = ConnectionPoolConfig.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, playlist.getNomePLaylist());
            preparedStatement.setString(2, playlist.getAutor());
            preparedStatement.setInt(3, user.getId());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    playlist.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating playlist failed, no ID obtained.");
                }
            }

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
                playlist.setId(resultSet.getInt("ID"));
                playlist.setNomePLaylist(resultSet.getString("NOME"));
                playlist.setAutor(resultSet.getString("AUTOR"));
                int qtd = getQuantidadeInPlaylist(playlist);
                List<Musica> musicas = getMusicasInPlaylist(playlist);
                playlist.setMusicas(musicas);
                playlist.setQuantidade(qtd);
                playlists.add(playlist);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playlists;
    }

    public String addMusicaToPlaylist(Playlist playlist, Musica musica) {
        initializeDatabase();
        String SQL = "INSERT INTO PlaylistMusica (playlistId, musicaId) VALUES (?, ?)";
        String checkSQL = "SELECT COUNT(*) FROM PlaylistMusica WHERE playlistId = ? AND musicaId = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement checkStatement = connection.prepareStatement(checkSQL)) {

            checkStatement.setInt(1, playlist.getId());
            checkStatement.setInt(2, musica.getId());

            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();

            if (resultSet.getInt(1) > 0) {
                return "Musica ja existe na playlist!";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            // pegando o id da playlist em que a musica ira entrar, e o id da musica em questao
            preparedStatement.setInt(1, playlist.getId());
            preparedStatement.setInt(2, musica.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Musica adicionada a playlist!";
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

    public Playlist getPlaylistByName(String playlistName, User user) {
        String SQL = "SELECT * FROM Playlist WHERE nome = ? AND userId = ?";
        Playlist playlist = null;

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setString(1, playlistName);
            preparedStatement.setInt(2, user.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                playlist = new Playlist();
                playlist.setId(resultSet.getInt("id"));
                playlist.setNomePLaylist(resultSet.getString("nome"));
                playlist.setAutor(resultSet.getString("autor"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playlist;
    }

    public int getQuantidadeInPlaylist(Playlist playlist) {
        String SQL = "SELECT COUNT(*) FROM PlaylistMusica WHERE playlistId = ?";
        int qtd = 0;

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            preparedStatement.setInt(1, playlist.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                qtd = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return qtd;
    }

    public List<Musica> getMostPopularMusicas() {
        String SQL = "SELECT Musica.*, COUNT(*) as playlist_count " +
                "FROM Musica " +
                "JOIN PlaylistMusica ON Musica.id = PlaylistMusica.musicaId " +
                "GROUP BY Musica.id " +
                "ORDER BY playlist_count DESC " +
                "LIMIT 10";

        List<Musica> musicas = new ArrayList<>();

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Musica musica = new Musica();
                musica.setId(resultSet.getInt("ID"));
                musica.setNome(resultSet.getString("NOME"));
                musica.setArtista(resultSet.getString("ARTISTA"));
                musica.setQtdPLaylists(resultSet.getInt("playlist_count"));
                musica.setDuracao(resultSet.getDouble("DURACAO"));
                musicas.add(musica);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return musicas;
    }

    public List<Playlist> getAllPlaylists() {
        String SQL = "SELECT * FROM Playlist";
        List<Playlist> playlists = new ArrayList<>();

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("ID"));
                playlist.setNomePLaylist(resultSet.getString("NOME"));
                playlist.setAutor(resultSet.getString("AUTOR"));
                int qtd = getQuantidadeInPlaylist(playlist);
                List<Musica> musicas = getMusicasInPlaylist(playlist); // necessario para mostrar as musicas no front
                playlist.setMusicas(musicas);
                playlist.setQuantidade(qtd);
                playlists.add(playlist);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playlists;
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

        String SQLCURTIDAS = "CREATE TABLE IF NOT EXISTS PlaylistLikes ("
                + "playlistId INT, "
                + "userId INT, "
                + "PRIMARY KEY (playlistId, userId), "
                + "FOREIGN KEY (playlistId) REFERENCES Playlist(id), "
                + "FOREIGN KEY (userId) REFERENCES USR(ID)"
                + ")";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(SQLCURTIDAS);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deletePlaylist(String playlistId) {
        String sql = "DELETE FROM PLAYLIST WHERE ID = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Definir o valor do parâmetro
            pstmt.setString(1, playlistId);

            // Executar a instrução SQL DELETE
            int affectedRows = pstmt.executeUpdate();

            // Se uma linha foi afetada, então a exclusão foi bem-sucedida
            return affectedRows == 1;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Playlist findById(int id) {
        Playlist playlist = null;
        String sql = "SELECT * FROM PLAYLIST WHERE ID = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                playlist = new Playlist();
                playlist.setId(rs.getInt("id"));
                playlist.setNomePLaylist(rs.getString("nomePlaylist"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlist;
    }

    public void updatePlaylistName(Playlist playlist) {
        String sql = "UPDATE PLAYLIST SET NOME = ? WHERE ID = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, playlist.getNomePLaylist());
            stmt.setInt(2, playlist.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("A playlist foi atualizada com sucesso.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void curtirPlaylist(Playlist playlist, User user) {

        String SQL = "INSERT INTO PlaylistLikes (playlistId, userId) VALUES (?, ?)";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            // setando ids da playlist a ser curtida, e o usuario que curtiu
            preparedStatement.setInt(1, playlist.getId());
            preparedStatement.setInt(2, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Playlist> getMaisCurtidas() {
        initializeDatabase();

        String SQL = "SELECT Playlist.*, COUNT(*) as likes_count "
                + "FROM Playlist "
                + "JOIN PlaylistLikes ON Playlist.id = PlaylistLikes.playlistId "
                + "GROUP BY Playlist.id "
                + "ORDER BY likes_count DESC "
                + "LIMIT 10";

        List<Playlist> playlists = new ArrayList<>();

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(resultSet.getInt("ID"));
                playlist.setNomePLaylist(resultSet.getString("NOME"));
                playlist.setAutor(resultSet.getString("AUTOR"));
                int qtdcurtidas = resultSet.getInt("likes_count");
                playlist.setQtdCurtidas(qtdcurtidas);
                int qtd = getQuantidadeInPlaylist(playlist);
                List<Musica> musicas = getMusicasInPlaylist(playlist); // necessario para mostrar as musicas no front
                playlist.setMusicas(musicas);
                playlist.setQuantidade(qtd);
                playlists.add(playlist);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return playlists;
    }
}


