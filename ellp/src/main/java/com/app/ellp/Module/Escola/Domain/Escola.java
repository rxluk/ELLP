package com.app.ellp.Module.Escola.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "escolas")
public class Escola {

    @Id
    private String id; // ID único para cada escola (gerado automaticamente)
    private String nome; // Nome da escola
    private String telefone; // Número de telefone da escola
    private String bairro; // Bairro da escola
    private String rua; // Rua onde a escola está localizada
    private int numero; // Número do local da escola (ex: número do prédio)

    // Construtor
    public Escola(String id, String nome, String telefone, String bairro, String rua, int numero) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}