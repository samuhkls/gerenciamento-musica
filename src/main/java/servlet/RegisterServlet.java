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
        UserDAO dao = new UserDAO();

        try {
            // Checar se o email existe
            boolean isEmailRegistered = dao.isEmailRegistered(email);
            if (isEmailRegistered) {
                request.setAttribute("message", "E-mail já está registrado. Escolha outro e-mail.");
                request.getRequestDispatcher("login.jsp").forward(request, resp);
                return;
            }


            // Inserir no banco novo usuario
            request.getSession().setAttribute("loggedUser", user);
            new UserDAO().createUser(user);

            resp.sendRedirect("/home");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Erro ao verificar o e-mail. Tente novamente.");
            request.getRequestDispatcher("login.jsp").forward(request, resp);
        }
}}
