import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.*;

public class RequestTrack {
    String API_KEY = "43fce9a2d0129c54d3c32cc47389254b";
    String USER_AGENT = "ListenerApp";

    String resposta;

    @Override
    public String toString() {
        return "RequestTrack{} " + resposta ;
    }

    public void pegarInfo(){

        try{
            HttpResponse<JsonNode> resposta = Unirest.get("https://ws.audioscrobbler.com/2.0/")
                    .header("user-agent", USER_AGENT)
                    .queryString("api_key", API_KEY) // parametros para a formação da url, antes de mandar a request
                    .queryString("method", "track.getInfo")
                    .queryString("track", "Sad Girl")
                    .queryString("artist", "Lana Del Rey")
                    .queryString("format", "json")
                    .asJson();

            JSONObject jsonResposta = new JSONObject(resposta.getBody()); // objeto json que recebe as informações da request

            System.out.println(jsonResposta);

            // o objeto track fica dentro de "object" entao primeiro precisamos acessar o object
            JSONObject trackObject = jsonResposta.getJSONObject("object").getJSONObject("track");
            String trackNome = trackObject.getString("name");  // a partir de object, conseguimos acessar o nome da track
            String artistaNome = trackObject.getJSONObject("artist").getString("name"); // o nome do artista fica dentro de "artist"

            System.out.println("Track Name: " + trackNome);
            System.out.println("Artist Name: " + artistaNome);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

}
