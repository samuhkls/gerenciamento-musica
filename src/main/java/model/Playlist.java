package model;

import java.util.ArrayList;

public class Playlist {
    private String nomePLaylist;
    private int quantidade;

    private String autor;

    private ArrayList<Musica> musicas;

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

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

}
