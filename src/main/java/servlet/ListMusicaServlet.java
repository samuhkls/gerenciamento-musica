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
@WebServlet("/lista-musicas")
public class ListMusicaServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            List<Musica> musicas = new musicaDAO().findAllMusicas();

            request.setAttribute("musicas", musicas);

            String teste;

            teste = musicas.get(1).getNome();

            request.getRequestDispatcher("pesquisa.jsp").forward(request, response);

            System.out.println(musicas.get(1).getNome());

        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/lista-musicas");
    }

    }


