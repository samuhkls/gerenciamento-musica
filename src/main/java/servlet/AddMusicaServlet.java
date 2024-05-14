package servlet;

import model.Musica;
import model.Playlist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-musica")
public class AddMusicaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String musicaNome = request.getParameter("musicaNome");
        String artistaNome = request.getParameter("musicaArtista");
        // Assuming you have a method to get a music by its name
        Musica musica = new Musica(musicaNome, artistaNome);
        // Assuming you have a method to get the current user's playlist
        Playlist play = new Playlist();
        play.addMusica(musica);
        // Redirect the user back to the music list
        for (int i = 0; i < play.getMusicas().size(); i++){
            System.out.println(play.getMusicas().get(i));
        }
        response.sendRedirect("/lista-musicas");
    }
}
