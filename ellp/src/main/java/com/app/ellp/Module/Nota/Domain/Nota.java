package com.app.ellp.Module.Nota.Domain;

import com.app.ellp.Module.Aluno.Domain.Aluno;
import com.app.ellp.Module.Disciplina.Domain.Disciplina;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notas")
public class Nota {

    @Id
    private String id;

    @DBRef
    private Aluno aluno;

    @DBRef
    private Disciplina disciplina;

    private Double primeiroBimestre;
    private Double segundoBimestre;
    private Double terceiroBimestre;
    private Double quartoBimestre;
    private Integer ano;

    // Construtor padrão
    public Nota() {}

    // Construtor com parâmetros
    public Nota(String id, Aluno aluno, Disciplina disciplina, Double primeiroBimestre,
                Double segundoBimestre, Double terceiroBimestre, Double quartoBimestre, Integer ano) {
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.primeiroBimestre = primeiroBimestre;
        this.segundoBimestre = segundoBimestre;
        this.terceiroBimestre = terceiroBimestre;
        this.quartoBimestre = quartoBimestre;
        this.ano = ano;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Double getPrimeiroBimestre() {
        return primeiroBimestre;
    }

    public void setPrimeiroBimestre(Double primeiroBimestre) {
        this.primeiroBimestre = primeiroBimestre;
    }

    public Double getSegundoBimestre() {
        return segundoBimestre;
    }

    public void setSegundoBimestre(Double segundoBimestre) {
        this.segundoBimestre = segundoBimestre;
    }

    public Double getTerceiroBimestre() {
        return terceiroBimestre;
    }

    public void setTerceiroBimestre(Double terceiroBimestre) {
        this.terceiroBimestre = terceiroBimestre;
    }

    public Double getQuartoBimestre() {
        return quartoBimestre;
    }

    public void setQuartoBimestre(Double quartoBimestre) {
        this.quartoBimestre = quartoBimestre;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}
