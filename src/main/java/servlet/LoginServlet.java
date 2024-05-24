package servlet;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    //funcao responsavel pelo direcionamento
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, resp);
    }

    //funcao responsavel pelo login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String username = request.getParameter("user-login");
        String email = request.getParameter("email-login");
        String password = request.getParameter("password-login");

        if (username == null || email == null || password == null ||
                username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("message", "Todos os campos sao obrigatorios!");
            request.getRequestDispatcher("login.jsp").forward(request, resp);
            return;
        }

        User user = new User(username, email, password);
        UserDAO dao = new UserDAO();

        try {
            // pegando id do user (com base no username) do banco
            user.setId(dao.findUserId(user.getUsername()));
            boolean isValidUser = dao.verifyCredentials(user);

            if (isValidUser) {
                request.getSession().setAttribute("loggedUser", user);
                resp.sendRedirect("/home");
            } else {
                request.setAttribute("message", "Credenciais invalidas!");
                request.getRequestDispatcher("login.jsp").forward(request, resp);
            }
            } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "O login digitado nao existe, tente novamente.");
            request.getRequestDispatcher("login.jsp").forward(request, resp);
        }
    }
}
