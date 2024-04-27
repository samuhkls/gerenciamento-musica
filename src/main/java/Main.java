import dao.musicaDAO;
import model.Musica;
import service.SearchTrack;

public class Main {
    public static void main(String[] args) {
        RequestTrack request = new RequestTrack();
        SearchTrack pesquisa = new SearchTrack();

        request.pegarInfo();
        pesquisa.pesquisar();

        System.out.println("-------");

        setterMusica setter = new setterMusica(request);

        setter.setarInfo();

        Musica musica = setter.getMusica();
        System.out.println("nome da musica: " + musica.getNome());
        System.out.println("nome do artista: " + musica.getArtista());
        System.out.println("duracao da musica: " + musica.getDuracao());
        System.out.println("----");
        System.out.println(musica);

        musicaDAO dao = new musicaDAO();
        dao.createMusica(musica);
    }



}
