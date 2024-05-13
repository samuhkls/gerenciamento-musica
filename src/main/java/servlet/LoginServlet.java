package servlet;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("user-login");
        String email = req.getParameter("email-login");
        String password = req.getParameter("password-login");

        User user = new User(username, email, password);
        boolean isValidUser = new UserDAO().verifyCredentials(user);

        if (isValidUser) {
            req.getSession().setAttribute("loggedUser", email);
            resp.sendRedirect("home");
        } else {
            req.setAttribute("message", "Invalid credentials!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }


}
