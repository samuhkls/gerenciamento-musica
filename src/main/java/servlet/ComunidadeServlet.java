package servlet;

import dao.PlaylistDAO;
import model.Musica;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/comunidade")
public class ComunidadeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");

        PlaylistDAO dao = new PlaylistDAO();
        List<Musica> popularMusicas = dao.getMusicasPopulares();


        request.setAttribute("populares", popularMusicas);

        request.getRequestDispatcher("comunidade.jsp").forward(request, response);
    }
}
