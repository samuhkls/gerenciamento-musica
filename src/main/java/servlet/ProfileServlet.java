package servlet;
import dao.*;
import model.*;
import service.*;
import dao.PlaylistDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        PlaylistDAO dao = new PlaylistDAO();

        if(loggedUser != null) {
            String username = loggedUser.getUsername();
            request.setAttribute("username", username);

            String email = loggedUser.getEmail();
            request.setAttribute("email", email);

            int id = loggedUser.getId();
            request.setAttribute("id", id);

            List<Playlist> playlists = dao.getPlaylistsByUser(loggedUser);

            request.setAttribute("playlists", playlists);

            RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
            dispatcher.forward(request, response);

        } else {
            response.sendRedirect("/login");
        }
    }
}