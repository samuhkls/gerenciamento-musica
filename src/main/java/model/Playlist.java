package model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String nomePLaylist;
    private int quantidade;
    private String autor;
    private List<Musica> musicas;
    private int id;
    private int qtdCurtidas;

    public int getQtdCurtidas() {
        return qtdCurtidas;
    }

    public void setQtdCurtidas(int qtdCurtidas) {
        this.qtdCurtidas = qtdCurtidas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Construtor que inicializa a lista de músicas
    public Playlist() {
        this.musicas = new ArrayList<>();
    }

    public String getNomePLaylist() {
        return nomePLaylist;
    }

    public void setNomePLaylist(String nomePLaylist) {
        this.nomePLaylist = nomePLaylist;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public void addMusica(Musica musica){
        musicas.add(musica);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Playlist: ").append(nomePLaylist).append("\n");
        builder.append("Autor: ").append(autor).append("\n");
        builder.append("Quantidade de músicas: ").append(quantidade).append("\n");
        builder.append("Músicas:\n");
        for (Musica musica : musicas) {
            builder.append("- ").append(musica).append("\n");
        }
        return builder.toString();
    }
}
