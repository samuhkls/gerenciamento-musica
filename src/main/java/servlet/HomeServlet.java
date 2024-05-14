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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedUser = (User) req.getSession().getAttribute("loggedUser");
        TrendingGetter getter = new TrendingGetter();
        List<Musica> trendingMusicas = getter.getTrending();
        req.setAttribute("musicas", trendingMusicas);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}