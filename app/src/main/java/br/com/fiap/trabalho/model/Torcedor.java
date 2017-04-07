package br.com.fiap.trabalho.model;

/**
 * Created by nicebadu on 18/03/2017.
 */

public class Torcedor {
    private int id;
    private String nome;
    private Clube clube;

    public Torcedor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Clube getClube() {
        return clube;
    }

    public void setClube(Clube clube) {
        this.clube = clube;
    }

    @Override
    public String toString() {
        return this.getNome() + " - " + this.getClube().getNome();
    }
}
