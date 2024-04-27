import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        RequestTrack request = new RequestTrack();
        SearchTrack pesquisa = new SearchTrack();


        request.pegarInfo();

        System.out.println("-------");

        setterMusica setter = new setterMusica(request);

        setter.setarInfo();

        Musica musica = setter.getMusica();
        System.out.println("nome da musica: " + musica.getNome());
        System.out.println("nome do artista: " + musica.getArtista());
        System.out.printf("duração formatada: %.2f \n", musica.getDuracao());
        System.out.println("----");
        System.out.println(musica);
    }



}
