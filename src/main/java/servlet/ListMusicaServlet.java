package servlet;

import model.Musica;
import dao.musicaDAO;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/find-all-musicas")
public class ListMusicaServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            List<Musica> musicas = new musicaDAO().findAllMusicas();

            request.setAttribute("musicas", musicas);

            request.getRequestDispatcher("index.jsp").forward(request, response);

        }

    }


