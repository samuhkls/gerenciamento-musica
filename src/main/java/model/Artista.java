package model;

public class Artista {

    private  String artista;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private  String nome;
    private String imageURL;

    public String getArtista() {
        return artista;
    }

    public void setArtista(String nome) {
        this.artista = nome;
    }

    public Artista(String nome){
        this.artista = nome;
    }

    public Artista(){

    }
    public Artista(String nome, String imageURL){
        this.artista = nome;
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
