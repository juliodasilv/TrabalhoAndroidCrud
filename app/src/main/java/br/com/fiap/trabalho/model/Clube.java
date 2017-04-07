package br.com.fiap.trabalho.model;

/**
 * Created by nicebadu on 18/03/2017.
 */

public class Clube {
    private int id;
    private String nome;

    public Clube() {
    }

    public Clube(int id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return nome;
    }
}
