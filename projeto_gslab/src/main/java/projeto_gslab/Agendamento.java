package projeto_gslab;

import java.sql.Date;

public class Agendamento {
    private int id;
    private String disciplina;
    private String turma;
    private Date dia;
    private String professor;
    private String observacoes;
    private int slot;
    private String sala_nome;
    private String utilizador_email;

    // Construtor
    public Agendamento(int id, String disciplina, String turma, Date dia, String professor, String observacoes, int slot, String sala_nome, String utilizador_email) {
        this.id = id;
        this.disciplina = disciplina;
        this.turma = turma;
        this.dia = dia;
        this.professor = professor;
        this.observacoes = observacoes;
        this.slot = slot;
        this.sala_nome = sala_nome;
        this.utilizador_email = utilizador_email;
    }

    // Setters e Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getSala_nome() {
        return sala_nome;
    }

    public void setSala_nome(String sala_nome) {
        this.sala_nome = sala_nome;
    }

    public String getUtilizador_email() {
        return utilizador_email;
    }

    public void setUtilizador_email(String utilizador_email) {
        this.utilizador_email = utilizador_email;
    }
}
