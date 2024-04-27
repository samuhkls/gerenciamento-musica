package servlet;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.Musica;
import dao.musicaDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-musica")
public class CreateMusicaServlet extends HttpServlet{

        String trackNome;
        String artistaNome;

        double trackDuracao;
        Musica musica = new Musica();

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String musicanome = request.getParameter("musica");

            pesquisar(musicanome);

            new musicaDAO().createMusica(musica);

            response.sendRedirect("/find-all-musicas");

        }

        String API_KEY = "43fce9a2d0129c54d3c32cc47389254b";
        String USER_AGENT = "ListenerApp";

        String resposta;

        @Override
        public String toString() {
            return "RequestTrack{} " + resposta ;
        }

        public void pesquisar(String musicaNome){
            try{

                HttpResponse<JsonNode> resposta = Unirest.get("https://ws.audioscrobbler.com/2.0/")
                        .header("user-agent", USER_AGENT)
                        .queryString("api_key", API_KEY) // parametros para a formação da url, antes de mandar a request
                        .queryString("method", "track.search")
                        .queryString("limit", "5")
                        .queryString("track", musicaNome)
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


            }catch (UnirestException e) {
                e.printStackTrace();
            }
        }

    public void setarInfo(){
        musica.setNome(trackNome);
        musica.setArtista(artistaNome);
    }

    }

