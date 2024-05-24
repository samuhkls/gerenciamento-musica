package servlet;

import dao.PlaylistDAO;
import model.Musica;
import dao.musicaDAO;
import model.Playlist;
import model.User;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/lista-musicas")
public class ListMusicaServlet extends HttpServlet {
    Playlist play = new Playlist();


    //funcao que chama todas as musicas
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");

        List<Musica> musicas = new musicaDAO().findAllMusicas();

        request.setAttribute("musicas", musicas);

        List<Playlist> playlists = new PlaylistDAO().getPlaylistsByUser(loggedUser);
        request.setAttribute("playlists", playlists);

        String message = (String) request.getSession().getAttribute("message");
        request.setAttribute("message", message);
        request.getSession().removeAttribute("message"); // removendo a mensagem para ela nao persistir

        request.getRequestDispatcher("pesquisa.jsp").forward(request, response);

        }

    //adiciona musica pesquisada na lista musica
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String musicanome = request.getParameter("item-nome");
        String artista = request.getParameter("item-artista");

        Musica musica = new Musica(musicanome, artista);

        play.addMusica(musica);

    }

    }


