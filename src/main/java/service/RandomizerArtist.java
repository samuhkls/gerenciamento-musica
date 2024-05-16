//package service;
//
//import dao.PlaylistDAO;
//import model.Musica;
//import model.Artista;
//import model.Playlist;
//import model.User;
//
//import service.TrendingArtistsGetter;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class RandomizerArtist extends RecommendationManager{
//
//    public List<Artista> getRandomRecommendations(User user, int qtd) {
//
//        TrendingArtistsGetter trendingArtistsGetter = new TrendingArtistsGetter();
//        //pegando as playlists do usuario
//        List<Artista> artistas = trendingArtistsGetter.getTrending();
//
//
//        List<Artista> recomendacoes = new ArrayList<>();
//
//
//        for (int i = 0; i < artistas.size(); i++) {
//            // pegando todas as musicas da playlist deste loop
//            List<Artista> playlistArtista = trendingArtistsGetter.getTrending();
//
//
//            Collections.shuffle(playlistArtista);
//
//
//            List<Artista> artistaSelect = new ArrayList<>();
//            // selecionando as musicas da playlist
//            for (int j = 0; j < Math.min(qtd, playlistArtista.size()); j++) {
//                artistaSelect.add(playlistArtista.get(j));
//            }
//
//            // loop por cada musica selecionada
//            for (int j = 0; j < artistaSelect.size(); j++) {
//
//                // pegando as recomendações de cada musica e adicionando a lista
//                List<Artista> recommendations = recommendArtistas(artistaSelect.get(j));
//                recomendacoes.addAll(recommendations);
//            }
//        }
//
//        // randomizando a lista
//        Collections.shuffle(recomendacoes);
//
//        return recomendacoes;
//    }
//
//}
