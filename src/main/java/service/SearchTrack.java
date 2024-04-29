package service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import dao.musicaDAO;
import model.Musica;
import org.json.*;

public class SearchTrack {
    String API_KEY = "43fce9a2d0129c54d3c32cc47389254b";
    String USER_AGENT = "ListenerApp";
    String resposta;

    String trackNome;
    String artistaNome;

    double trackDuracao;


    Musica musica = new Musica();


    @Override
    public String toString() {
        return "RequestTrack{} " + resposta ;
    }

    public Musica pesquisar(String musica1){

        try{

            HttpResponse<JsonNode> resposta = Unirest.get("https://ws.audioscrobbler.com/2.0/")
                    .header("user-agent", USER_AGENT)
                    .queryString("api_key", API_KEY) // parametros para a formação da url, antes de mandar a request
                    .queryString("method", "track.search")
                    .queryString("limit", "5")
                    .queryString("track", musica1)
                    .queryString("format", "json")
                    .asJson();

            JSONObject jsonResposta = new JSONObject(resposta.getBody());

            System.out.println(jsonResposta);

            // formatando a resposta json, que retorna um array com todos os resultados chamado "track"
            JSONArray trackObject = jsonResposta.getJSONObject("object").getJSONObject("results").getJSONObject("trackmatches").getJSONArray("track");
            JSONObject track = trackObject.getJSONObject(0); // acessando o primeiro index, que é o primeiro resultado

            trackNome = track.getString("name");  // o nome é um atributo do objeto musica
            System.out.println("Nome da musica: " + trackNome);

            artistaNome = track.getString("artist"); // o nome do artista tambem é um atributo do objeto musica
            System.out.println("Nome do artista: " + artistaNome);

            trackDuracao = getDuracao(trackNome, artistaNome);




            musica.setNome(trackNome);
            musica.setArtista(artistaNome);
            musica.setDuracao(trackDuracao);


        }catch (UnirestException e) {
            e.printStackTrace();
        }
        return musica;
    }
    public Double getDuracao(String trackNome, String artistaNome) throws UnirestException {
        HttpResponse<JsonNode> resposta = Unirest.get("https://ws.audioscrobbler.com/2.0/")
                .header("user-agent", USER_AGENT)
                .queryString("api_key", API_KEY) // parametros para a formação da url, antes de mandar a request
                .queryString("method", "track.getInfo")
                .queryString("track", trackNome)
                .queryString("artist", artistaNome)
                .queryString("format", "json")
                .asJson();

        JSONObject jsonResposta = new JSONObject(resposta.getBody()); // objeto json que recebe as informações da request

        System.out.println(jsonResposta);

        // o objeto track fica dentro de "object" entao primeiro precisamos acessar o object
        JSONObject trackObject = jsonResposta.getJSONObject("object").getJSONObject("track");
        trackDuracao = trackObject.getInt("duration");
        return trackDuracao;
    }


}
