package service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dao.musicaDAO;
import model.Musica;
import org.json.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TrendingGetter {

    String API_KEY = "43fce9a2d0129c54d3c32cc47389254b";
    String USER_AGENT = "ListenerApp";

    String resposta;

    @Override
    public String toString() {
        return "RequestTrack{} " + resposta ;
    }

    public List<Musica> getTrending(){
        try{
            HttpResponse<JsonNode> resposta = Unirest.get("https://ws.audioscrobbler.com/2.0/")
                    .header("user-agent", USER_AGENT)
                    .queryString("api_key", API_KEY) // parametros para a formação da url, antes de mandar a request
                    .queryString("method", "chart.getTopTracks")
                    .queryString("limit", "19")
                    .queryString("format", "json")
                    .asJson();

            JSONObject jsonResposta = new JSONObject(resposta.getBody());
            JSONArray trendingList = jsonResposta.getJSONObject("object").getJSONObject("tracks").getJSONArray("track");

            String tracknome;
            String artistanome;
            String imageURL;

            List<Musica> trendingMusicas = new ArrayList<>();
            ImageGetter getter = new ImageGetter();

            for(int i = 0; i< trendingList.length(); i++){
                tracknome = trendingList.getJSONObject(i).getString("name");
                artistanome = trendingList.getJSONObject(i).getJSONObject("artist")
                        .getString("name");

                imageURL = getter.getImage(tracknome, artistanome);


                Musica musica = new Musica(tracknome, artistanome);
                musica.setImageURL(imageURL);
                trendingMusicas.add(musica);
            }

            return trendingMusicas;
        }catch (UnirestException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}