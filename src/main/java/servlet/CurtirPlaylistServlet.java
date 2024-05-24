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

        String id = request.getParameter("playlistid");
        int playlistid = Integer.parseInt(id);
        PlaylistDAO dao = new PlaylistDAO();
        Playlist playlist = dao.findById(playlistid);

        if(playlist != null){
            if (playlist.getAutor() != null && !playlist.getAutor().equals(loggedUser.getUsername())) { // verificando se o autor da playlist é o usuario logado
                dao.curtirPlaylist(playlist, loggedUser);
                request.getSession().setAttribute("message", "Playlist curtida com sucesso!");
            } else {
                request.getSession().setAttribute("message", "Você não pode curtir sua própria playlist!");
            }
            request.getSession().removeAttribute("message"); // removendo a mensagem para ela nao persistir
        } else {
            request.getSession().setAttribute("message", "Playlist nao encontrada!");
            request.getSession().removeAttribute("message"); // removendo a mensagem para ela nao persistir
        }


        response.sendRedirect(request.getContextPath() + "/community");
    }
}
