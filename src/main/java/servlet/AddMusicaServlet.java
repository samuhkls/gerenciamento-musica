package servlet;

import dao.PlaylistDAO;
import model.Musica;
import model.Playlist;
import model.User;

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
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");

        String musicaNome = request.getParameter("musicaNome");
        String artistaNome = request.getParameter("musicaArtista");

        Musica musica = new Musica(musicaNome, artistaNome);

        Playlist play = new Playlist();
        play.addMusica(musica);

        PlaylistDAO dao = new PlaylistDAO();
        dao.createPlaylist(play, loggedUser);
        dao.addMusicaToPlaylist(play, musica);

        for (int i = 0; i < play.getMusicas().size(); i++){
            System.out.println(play.getMusicas().get(i));
        }
        response.sendRedirect("/lista-musicas");
    }
}
