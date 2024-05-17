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
import java.util.List;

@WebServlet("/community")
public class ComunidadeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");

        List<Musica> popularMusicas = new PlaylistDAO().getMostPopularMusicas();
        request.setAttribute("popularMusicas", popularMusicas);

        List<Playlist> allPlaylists = new PlaylistDAO().getAllPlaylists();
        request.setAttribute("allPlaylists", allPlaylists);

        request.getRequestDispatcher("comunidade.jsp").forward(request, response);
    }
}