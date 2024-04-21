public class Main {
    public static void main(String[] args) {
        RequestTrack request = new RequestTrack();
        SearchTrack pesquisa = new SearchTrack();

        request.pegarInfo();
        pesquisa.pesquisar();
    }
}
