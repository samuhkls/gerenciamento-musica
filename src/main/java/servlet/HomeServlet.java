package servlet;

import model.Musica;
import model.User;
import service.TrendingGetter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        TrendingGetter getter = new TrendingGetter();
        List<Musica> trendingMusicas = getter.getTrending();
        request.setAttribute("musicas", trendingMusicas);
        request.getRequestDispatcher("/index.jsp").forward(request, resp);
    }
}