import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class setterMusica extends HttpServlet {

    Musica musica = new Musica();
    RequestTrack request;
    SearchTrack search;

    public setterMusica(RequestTrack request){
        this.request = request;
    }

    public void setarInfo(){
        search.pesquisar(musica, request);
        musica.setNome(request.getTrackNome());
        musica.setArtista(request.getTrackArtista());
        musica.setDuracao(request.getTrackDuracao());
    }

    public Musica getMusica(){
        return musica;
    }


}
