package test;

import model.Musica;
import service.RecommendationManager;

public class RecommendationTest {
    public static void main(String[] args) {
        Musica musica = new Musica("rosemary", "deftones");
        RecommendationManager manager = new RecommendationManager();
        manager.recommendTracks(musica);
    }



}
