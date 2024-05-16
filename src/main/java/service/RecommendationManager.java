package service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.Musica;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecommendationManager {
    String API_KEY = "43fce9a2d0129c54d3c32cc47389254b";
    String USER_AGENT = "ListenerApp";

    String resposta;
    String tracknome;
    String artistanome;

    //List<Musica>

    public List<Musica> recommendTracks(Musica musica) {
        tracknome = musica.getNome();
        artistanome = musica.getArtista();
        try {
            HttpResponse<JsonNode> resposta = Unirest.get("https://ws.audioscrobbler.com/2.0/")
                    .header("user-agent", USER_AGENT)
                    .queryString("api_key", API_KEY) // parametros para a formação da url, antes de mandar a request
                    .queryString("method", "track.getSimilar")
                    .queryString("limit", 19)
                    .queryString("track", tracknome)
                    .queryString("artist", artistanome)
                    .queryString("format", "json")
                    .asJson();

            JSONObject jsonResposta = new JSONObject(resposta.getBody());
            System.out.println(jsonResposta);

            JSONArray recommendList = jsonResposta.getJSONObject("object").getJSONObject("similartracks").getJSONArray("track");

            List<Musica> musicasRecomendadas = new ArrayList<>();


            for(int i = 0; i< recommendList.length(); i++) {
                tracknome = recommendList.getJSONObject(i).getString("name");
                System.out.println(tracknome);
                artistanome = recommendList.getJSONObject(i).getJSONObject("artist")
                        .getString("name");
                System.out.println(artistanome);

                System.out.println(tracknome + " - " + artistanome);

                musica = new Musica(tracknome, artistanome);
                musicasRecomendadas.add(musica);

            }
            System.out.println(musicasRecomendadas);
            return musicasRecomendadas;


        } catch (UnirestException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    public void recommendArtistas(String artistanome){
        try {
            HttpResponse<JsonNode> resposta = Unirest.get("https://ws.audioscrobbler.com/2.0/")
                    .header("user-agent", USER_AGENT)
                    .queryString("api_key", API_KEY) // parametros para a formação da url, antes de mandar a request
                    .queryString("method", "artist.getSimilar")
                    .queryString("limit", 2)
                    .queryString("artist", artistanome)
                    .queryString("format", "json")
                    .asJson();

            JSONObject jsonResposta = new JSONObject(resposta.getBody());
            System.out.println(jsonResposta);

            JSONArray similarArtists = jsonResposta.getJSONObject("object")
                    .getJSONObject("similarartists").getJSONArray("artist");

            List<String> artistasRecomendados = new ArrayList<>();


            for(int i = 0; i<similarArtists.length(); i++){
                artistanome = similarArtists.getJSONObject(i).getString("name");
                System.out.println(artistanome);

                artistasRecomendados.add(artistanome);
                System.out.println(artistasRecomendados);
            }

            System.out.println();



        }catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
