package DAO;

import java.sql.Date;

public class Sala {
    private String nome;
    private int capacidade;
    private String localizacao;
    private String email_utilizador;

    // Constructor without parameters
    public Sala() {
    }

    // Constructor with parameters
    public Sala(String nome, int capacidade, String localizacao, String email_utilizador) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.email_utilizador = email_utilizador;
    }

    // Getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getEmail_utilizador() {
        return email_utilizador;
    }

    public void setEmail_utilizador(String email_utilizador) {
        this.email_utilizador = email_utilizador;
    }

    // toString method
    @Override
    public String toString() {
        return "Sala{" +
                "nome='" + nome + '\'' +
                ", capacidade=" + capacidade +
                ", localizacao='" + localizacao + '\'' +
                ", email_utilizador='" + email_utilizador + '\'' +
                '}';
    }
}
