package test;

import model.Musica;
import model.Playlist;

public class PlaylistTest {
    public static void main(String[] args) {

        Playlist play = new Playlist();
        Musica musica = new Musica("Hypnotyze", "Notorious B.I.G");
        play.addMusica(musica);

        for (int i = 0; i < play.getMusicas().size(); i++){
            System.out.println(play.getMusicas().get(i));
        }
    }
}
