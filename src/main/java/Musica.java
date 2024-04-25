public class Musica {
    private String nome;
    private String artista;
    private int duracao;

    public Musica(String nome, String artista, int minutos, int segundos){
        this.nome = nome;
        this.artista = artista;
        this.duracao = (minutos * 60) + segundos;
    }

    public Musica(String nome, String artista, int milisegundos){
        this.nome = nome;
        this.artista = artista;
        this.duracao = milisegundos/1000;
    }

    public Musica() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int minutos, int segundos) {
        this.duracao = (minutos * 60) + segundos;
    }
    public void setDuracao(int milisegundos){
        this.duracao = milisegundos/1000;
    }

    @Override
    public String toString() {
        int minutos = duracao / 60;
        int segundos = duracao % 60;
        return "Musica{" +
                "nome='" + nome + '\'' +
                ", artista='" + artista + '\'' +
                ", duracao=" + minutos +":"+segundos+
                '}';
    }
}
