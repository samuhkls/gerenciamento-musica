import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.*;

public class RequestTrack {
    String API_KEY = "43fce9a2d0129c54d3c32cc47389254b";
    String USER_AGENT = "ListenerApp";

    String resposta;
    String trackNome;
    String trackArtista;
    int trackDuracao;

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
                    .queryString("track", "element")
                    .queryString("artist", "kendrick lamar")
                    .queryString("format", "json")
                    .asJson();

            JSONObject jsonResposta = new JSONObject(resposta.getBody()); // objeto json que recebe as informações da request

            System.out.println(jsonResposta);

            // o objeto track fica dentro de "object" entao primeiro precisamos acessar o object
            JSONObject trackObject = jsonResposta.getJSONObject("object").getJSONObject("track");
            trackNome = trackObject.getString("name");  // a partir de object, conseguimos acessar o nome da track
            trackArtista = trackObject.getJSONObject("artist").getString("name"); // o nome do artista fica dentro de "artist"
            trackDuracao = trackObject.getInt("duration");

            System.out.println("nome da musica: " + trackNome);
            System.out.println("nome do artista: " + trackArtista);
            System.out.println("duracao em ms: " + trackDuracao);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

    }

    public String getTrackNome() {
        return trackNome;
    }

    public String getTrackArtista() {
        return trackArtista;
    }

    public int getTrackDuracao() {
        return trackDuracao;
    }
}
