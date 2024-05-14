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
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String username = request.getParameter("user-login");
        String email = request.getParameter("email-login");
        String password = request.getParameter("password-login");

        User user = new User(username, email, password);
        boolean isValidUser = new UserDAO().verifyCredentials(user);

        if (isValidUser) {
            request.getSession().setAttribute("loggedUser", user);
            resp.sendRedirect("home");
        } else {
            request.setAttribute("message", "Invalid credentials!");
            request.getRequestDispatcher("login.jsp").forward(request, resp);
        }

    }


}
