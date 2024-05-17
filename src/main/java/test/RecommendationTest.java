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


public class RecommendationTest {

    public static void main(String[] args) {
        RecommendationManager manager = new RecommendationManager();

        manager.recommendArtistas("nirvana");
    }


}