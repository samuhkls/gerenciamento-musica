package servlet;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String username = request.getParameter("user-register");
        String email = request.getParameter("email-register");
        String password = request.getParameter("password-register");


        User user = new User(username, email, password);
        request.getSession().setAttribute("loggedUser", user);

        new UserDAO().createUser(user);

        resp.sendRedirect("/home");
    }

}
