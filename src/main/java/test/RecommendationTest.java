package test;

import dao.PlaylistDAO;
import model.Musica;
import model.User;
import service.Randomizer;
import service.RecommendationManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recommend")
public class RecommendationTest extends HttpServlet {

    public static void main(String[] args) {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");


        // Create a RandomRecommendationManager
        Randomizer manager = new Randomizer();

        // Get random recommendations
        List<Musica> recommendations = manager.getRandomRecommendations(loggedUser, 1);

        // Print the recommendations
        for (Musica musica : recommendations) {
            System.out.println(musica.getNome() + " by " + musica.getArtista());
        }
    }
}