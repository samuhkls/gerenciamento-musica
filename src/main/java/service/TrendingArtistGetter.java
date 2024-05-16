package service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.Artista;
import model.Musica;
import org.json.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class TrendingArtistGetter {


    String API_KEY = "43fce9a2d0129c54d3c32cc47389254b";
    String USER_AGENT = "ListenerApp";

    String resposta;

    @Override
    public String toString() {
        return "RequestTrack{} " + resposta ;
    }

    public List<Artista> getTrending(){
        try{
            HttpResponse<JsonNode> resposta = Unirest.get("https://ws.audioscrobbler.com/2.0/")
                    .header("user-agent", USER_AGENT)
                    .queryString("api_key", API_KEY) // parametros para a formação da url, antes de mandar a request
                    .queryString("method", "chart.getTopArtists")
                    .queryString("limit", "19")
                    .queryString("format", "json")
                    .asJson();



            String tracknome;
            String artistanome;
            String imageURL;

            List<Artista> trendingArtistas = new ArrayList<>();
            ImageGetter getter = new ImageGetter();

            JSONObject jsonResposta = new JSONObject(resposta.getBody());
            JSONArray jsonArray = jsonResposta.getJSONArray("array");
            JSONObject firstObject = jsonArray.getJSONObject(0);
            JSONObject artistsObject = firstObject.getJSONObject("artists");
            JSONArray artistArray = artistsObject.getJSONArray("artist");

            for(int i = 0; i< artistArray.length(); i++){
                String artistName = artistArray.getJSONObject(i).getString("name");
                System.out.println(artistName);

                Artista artista = new Artista(artistName);
                trendingArtistas.add(artista);
            }

            System.out.println(trendingArtistas);
            return trendingArtistas;
        }catch (UnirestException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

