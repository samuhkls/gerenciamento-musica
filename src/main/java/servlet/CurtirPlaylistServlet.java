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

@WebServlet("/curtir-playlist")
public class CurtirPlaylistServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");

        int playlistid = Integer.parseInt(request.getParameter("playlistid"));
        PlaylistDAO dao = new PlaylistDAO();
        Playlist playlist = dao.findById(playlistid);

        if(playlist != null){
            dao.curtirPlaylist(playlist, loggedUser);
            request.getSession().setAttribute("message", "Playlist curtida com sucesso!");
        } else {
            request.getSession().setAttribute("message", "Playlist nao encontrada!");
        }


        response.sendRedirect(request.getContextPath() + "/community");
    }
}
