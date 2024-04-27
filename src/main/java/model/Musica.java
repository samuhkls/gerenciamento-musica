package model;

public class Musica {
    private String nome;
    private String artista;
    private double duracao;

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

    public double getDuracao() {
        return duracao;
    }


    public void setDuracao(double milisegundos){
        this.duracao = milisegundos/60000;
    }
}
