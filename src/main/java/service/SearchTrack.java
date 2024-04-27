package service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.*;

public class SearchTrack {
    String API_KEY = "43fce9a2d0129c54d3c32cc47389254b";
    String USER_AGENT = "ListenerApp";

    String resposta;

    @Override
    public String toString() {
        return "RequestTrack{} " + resposta ;
    }

    public void pesquisar(){
        try{

            HttpResponse<JsonNode> resposta = Unirest.get("https://ws.audioscrobbler.com/2.0/")
                    .header("user-agent", USER_AGENT)
                    .queryString("api_key", API_KEY) // parametros para a formação da url, antes de mandar a request
                    .queryString("method", "track.search")
                    .queryString("limit", "5")
                    .queryString("track", "smooth operator")
                    .queryString("format", "json")
                    .asJson();

            JSONObject jsonResposta = new JSONObject(resposta.getBody());

            System.out.println(jsonResposta);

            // formatando a resposta json, que retorna um array com todos os resultados chamado "track"
            JSONArray trackObject = jsonResposta.getJSONObject("object").getJSONObject("results").getJSONObject("trackmatches").getJSONArray("track");
            JSONObject track = trackObject.getJSONObject(0); // acessando o primeiro index, que é o primeiro resultado

            String trackNome = track.getString("name");  // o nome é um atributo do objeto musica
            System.out.println("Nome da musica: " + trackNome);

            String artistaNome = track.getString("artist"); // o nome do artista tambem é um atributo do objeto musica
            System.out.println("Nome do artista: " + artistaNome);

        }catch (UnirestException e) {
            e.printStackTrace();
        }
    }


}
