package servlet;

import model.Playlist;
import dao.PlaylistDAO;
import service.UpdatePlaylist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-playlist-name")
public class UpdatePlaylistServlet extends HttpServlet {
    private PlaylistDAO playlistDAO;

    public UpdatePlaylistServlet() {
        this.playlistDAO = new PlaylistDAO();
    }

    //funcao atualiza nome da playlist
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playlistId = Integer.parseInt(request.getParameter("playlistId"));
        String newName = request.getParameter("newName");

        Playlist playlist = playlistDAO.findById(playlistId);
        if (playlist != null) {
            playlist.setNomePLaylist(newName);
            playlistDAO.updatePlaylistName(playlist);
        }

        response.sendRedirect("/profile");
    }
}
