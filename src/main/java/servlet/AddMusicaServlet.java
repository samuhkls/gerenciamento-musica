package servlet;

import dao.PlaylistDAO;
import dao.musicaDAO;
import model.Musica;
import model.Playlist;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/add-musica")
public class AddMusicaServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");

        int musicaId = Integer.parseInt(request.getParameter("musicaId"));
        Musica musica = new musicaDAO().getMusicaById(musicaId);

        String playlistName = request.getParameter("playlistName");
        PlaylistDAO dao = new PlaylistDAO();
        Playlist play = dao.getPlaylistByName(playlistName, loggedUser);

        dao.addMusicaToPlaylist(play, musica);

        response.sendRedirect("/lista-musicas");
    }
}
