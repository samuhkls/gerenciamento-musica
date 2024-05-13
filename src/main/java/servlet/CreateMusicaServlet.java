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

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String musicanome = request.getParameter("musica");

            Musica musica = search.pesquisar(musicanome);

            System.out.println("Nome da musica: " + musica.getNome());
            System.out.println("Nome do artista: " + musica.getArtista());

            new musicaDAO().createMusica(musica);

            response.sendRedirect("/lista-musicas");

        }


    }

