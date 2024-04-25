public class setterMusica {

    Musica musica = new Musica();
    RequestTrack request;

    public setterMusica(RequestTrack request){
        this.request = request;
    }

    public void setarInfo(){
        request.pegarInfo();
        musica.setNome(request.getTrackNome());
        musica.setArtista(request.getTrackArtista());
        musica.setDuracao(request.getTrackDuracao());
    }

    public Musica getMusica(){
        return musica;
    }


}
