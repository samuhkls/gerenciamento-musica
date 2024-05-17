package service;
import dao.PlaylistDAO;
import model.Musica;
import model.Playlist;
import model.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Randomizer extends RecommendationManager{
    public List<Musica> getRandomRecommendations(User user, int qtd) {
        PlaylistDAO playlistDAO = new PlaylistDAO();
        //pegando as playlists do usuario
        List<Playlist> playlists = playlistDAO.getPlaylistsByUser(user);


        List<Musica> recomendacoes = new ArrayList<>();


        for (int i = 0; i < playlists.size(); i++) {
            // pegando todas as musicas da playlist deste loop
            List<Musica> playlistMusicas = playlistDAO.getMusicasInPlaylist(playlists.get(i));


            Collections.shuffle(playlistMusicas);


            List<Musica> musicasSelect = new ArrayList<>();
            // selecionando as musicas da playlist
            for (int j = 0; j < Math.min(qtd, playlistMusicas.size()); j++) {
                musicasSelect.add(playlistMusicas.get(j));
            }

            // loop por cada musica selecionada
            for (int j = 0; j < musicasSelect.size(); j++) {

                // pegando as recomendações de cada musica e adicionando a lista
                List<Musica> recommendations = recommendTracks(musicasSelect.get(j));
                recomendacoes.addAll(recommendations);
            }
        }

        // randomizando a lista
        Collections.shuffle(recomendacoes);

        return recomendacoes;
    }
    public List<String> getRandomArtistRecommendations(User user, int qtd) {
        PlaylistDAO playlistDAO = new PlaylistDAO();

        List<Playlist> playlists = playlistDAO.getPlaylistsByUser(user);

        List<String> recomendacoes = new ArrayList<>();

        for (Playlist playlist : playlists) {
            List<Musica> playlistMusicas = playlistDAO.getMusicasInPlaylist(playlist);

            Collections.shuffle(playlistMusicas);

            List<Musica> musicasSelect = playlistMusicas.subList(0, Math.min(qtd, playlistMusicas.size()));

            for (Musica musica : musicasSelect) {
                String artista = musica.getArtista();
                List<String> artistRecommendations = recommendArtistas(artista);
                recomendacoes.addAll(artistRecommendations);
            }
        }

        Collections.shuffle(recomendacoes);

        return recomendacoes;
    }
}
