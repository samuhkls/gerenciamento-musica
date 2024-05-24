package servlet;

import dao.PlaylistDAO;
import dao.musicaDAO;
import model.Musica;
import model.Playlist;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/add-musica")
public class AddMusicaServlet extends HttpServlet {

    //adiciona musica selecionada para uma playlist especifica
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");

        int musicaId = Integer.parseInt(request.getParameter("musicaId"));
        Musica musica = new musicaDAO().getMusicaById(musicaId);

        String playlistName = request.getParameter("playlistName");
        PlaylistDAO dao = new PlaylistDAO();
        Playlist play = dao.getPlaylistByName(playlistName, loggedUser); // pegando a playlist a ser adicionada
        String message = "Selecione uma playlist!"; // atribuindo message para caso do if ser verdadeiro
        if(playlistName == null){ // se não tem nome selecionado
            request.getSession().setAttribute("message", message);
        }else if(playlistName != null){ // se tem
            message = dao.addMusicaToPlaylist(play, musica);
            request.getSession().setAttribute("message", message);
        }


        response.sendRedirect(request.getContextPath() + "/lista-musicas");
    }
}
