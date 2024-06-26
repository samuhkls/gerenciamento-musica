package servlet;

import dao.PlaylistDAO;
import model.Playlist;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-playlist")
public class CreatePlaylistServlet extends HttpServlet {

    //funcao que cria playlist
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        String playlistName = request.getParameter("playlistName");

        Playlist playlist = new Playlist();
        playlist.setNomePLaylist(playlistName);
        playlist.setAutor(loggedUser.getUsername()); // o autor da playlist sempre vai ser o usuario logado no momento

        PlaylistDAO dao = new PlaylistDAO();
        dao.createPlaylist(playlist, loggedUser);

        response.sendRedirect("/profile");
    }
}
