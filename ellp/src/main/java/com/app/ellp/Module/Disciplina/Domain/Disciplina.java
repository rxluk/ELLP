package com.app.ellp.Module.Disciplina.Domain;

import com.app.ellp.Module.Escola.Domain.Escola;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "disciplinas")
public class Disciplina {

    @Id
    private String id;

    private String nome;

    @DBRef
    private Escola escola;

    // Construtor alterado para aceitar o ID da escola
    public Disciplina(String id, String nome, Escola escola) {
        this.id = id;
        this.nome = nome;
        this.escola = escola;
    }

    // Construtor para criar a disciplina com o ID da escola diretamente
    public Disciplina(String nome, Escola escola) {
        this.nome = nome;
        this.escola = escola;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    // Método para retornar apenas o ID da escola
    public String getEscolaId() {
        return escola != null ? escola.getId() : null;
    }
}