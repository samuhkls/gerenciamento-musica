package servlet;

import service.DeletePlaylist;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-playlist")
public class DeletePlaylistServlet extends HttpServlet {

    private DeletePlaylist playlistService;

    public DeletePlaylistServlet() {
        this.playlistService = new DeletePlaylist();
    }

    //funcao que deleta playlist
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String playlistId = req.getParameter("id");
        if (playlistId != null) {
            boolean isDeleted = playlistService.deletePlaylist(playlistId);
            if (isDeleted) {
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        resp.sendRedirect("/profile");
    }
}