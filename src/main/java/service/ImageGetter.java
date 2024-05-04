package service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.Collections;

public class ImageGetter {

    String API_KEY = "43fce9a2d0129c54d3c32cc47389254b";
    String USER_AGENT = "ListenerApp";

    String resposta;
    String imageURL;

    @Override
    public String toString() {
        return "RequestTrack{} " + resposta ;
    }


    public String getImage(String trackNome, String artistaNome){
        try{
            HttpResponse<JsonNode> resposta = Unirest.get("https://ws.audioscrobbler.com/2.0/")
                    .header("user-agent", USER_AGENT)
                    .queryString("api_key", API_KEY) // parametros para a formação da url, antes de mandar a request
                    .queryString("method", "track.getInfo")
                    .queryString("track", trackNome)
                    .queryString("artist", artistaNome)
                    .queryString("format", "json")
                    .asJson();

            JSONObject jsonResposta = new JSONObject(resposta.getBody());
            try{
                imageURL = jsonResposta.getJSONObject("object").getJSONObject("track")
                        .getJSONObject("album").getJSONArray("image")
                        .getJSONObject(0).getString("#text");
            }catch (Exception e){
                return "https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png";
            }

            return imageURL;
        }catch (UnirestException e) {
            e.printStackTrace();
            return "erro";
        }
    }
}
