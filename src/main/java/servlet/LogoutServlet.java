package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        request.getSession().invalidate();

        request.setAttribute("message", "Success on logout");

        request.getRequestDispatcher("login.jsp").forward(request, resp);

    }
}
