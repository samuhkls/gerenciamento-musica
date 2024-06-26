package model;

public class Musica {
    private String nome;
    private String artista;
    private double duracao;
    private String imageURL;
    private int id;
    private int qtdPLaylists;

    public int getQtdPLaylists() {
        return qtdPLaylists;
    }

    public void setQtdPLaylists(int qtdPLaylists) {
        this.qtdPLaylists = qtdPLaylists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    public Musica(String nome){
        this.nome = nome;
    }

    public Musica(String nome, String artista, String imageURL) {
        this.nome = nome;
        this.artista = artista;
        this.imageURL = imageURL;
    }

    public Musica(){

    }

    public Musica(String nome, String artista) {
        this.nome = nome;
        this.artista = artista;
    }

    public Musica(String trackNome, String artistaNome, Double duracao) {
        this.nome = trackNome;
        this.artista = artistaNome;
        this.duracao = duracao;
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

    public double getDuracao() {
        return duracao;
    }


    public void setDuracao(double milisegundos){
        this.duracao = milisegundos/60000;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Música: " + nome + " - Artista: " + artista;
    }

}

