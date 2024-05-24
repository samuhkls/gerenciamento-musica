package servlet;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.Musica;
import dao.musicaDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import service.SearchTrack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-musica")
public class CreateMusicaServlet extends HttpServlet{

        SearchTrack search = new SearchTrack();

        //funcao que pesquisa musica
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String musicanome = request.getParameter("musica");
            String redirectUrl = "/home";
            String message = "Não foi possivel pesquisar a musica, porque nenhuma musica foi digitada.";

            try {
                if(musicanome != null && !musicanome.trim().isEmpty()){
                    Musica musica = search.pesquisar(musicanome);
                    new musicaDAO().createMusica(musica);
                    redirectUrl = "/lista-musicas";
                    message = null;
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            if (message != null) {
                request.setAttribute("message", message);
            }
            response.sendRedirect(redirectUrl);
        }
    }

