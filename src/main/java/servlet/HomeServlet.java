package servlet;

import model.Musica;
import model.Artista;
import model.User;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    //responsavel por chamar as funcoes da nossa pagina home
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        TrendingGetter getter = new TrendingGetter();
        List<Musica> trendingMusicas = getter.getTrending();
        request.setAttribute("musicas", trendingMusicas);

        TrendingArtistsGetter tgetter = new TrendingArtistsGetter();
        List<Artista> trendingArtistas = tgetter.getTrending();
        request.setAttribute("artistas", trendingArtistas);

        Randomizer randomizer = new Randomizer();
        int quantidadeRecomendacoes = 1;
        List<Musica> recomendacoesAleatorias = randomizer.getRandomRecommendations(loggedUser, quantidadeRecomendacoes);
        request.setAttribute("recomendacoesAleatorias", recomendacoesAleatorias);


        int quantidadeRecomendacoesArtista = 2;
        List<String> recomendacoesAleatoriasArtista = randomizer.getRandomArtistRecommendations(loggedUser, quantidadeRecomendacoesArtista);
        request.setAttribute("recomendacoesAleatoriasArtista", recomendacoesAleatoriasArtista);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, resp);
    }
}

