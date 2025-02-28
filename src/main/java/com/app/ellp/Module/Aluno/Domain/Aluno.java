package com.app.ellp.Module.Aluno.Domain;

import com.app.ellp.Module.Escola.Domain.Escola;
import com.app.ellp.Module.Familia.Domain.Familia;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "alunos")
public class Aluno {

    @Id
    private String id;  // Usando String para o ID

    private String nome;
    private String bairro;
    private String rua;
    private int numero;
    private String serie;
    private Date dataNascimento;
    private boolean necessitaTransporte;
    private boolean recebeAtendimentoMedico;
    @DBRef
    private Familia familia;
    @DBRef
    private Escola escola;

    // Construtor vazio
    public Aluno() {}

    // Construtor com todos os parâmetros (com id como parâmetro)
    public Aluno(String id, String nome, String bairro, String rua, int numero, String serie, Date dataNascimento,
                 boolean necessitaTransporte, boolean recebeAtendimentoMedico, Familia familia, Escola escola) {
        this.id = id;
        this.nome = nome;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.serie = serie;
        this.dataNascimento = dataNascimento;
        this.necessitaTransporte = necessitaTransporte;
        this.recebeAtendimentoMedico = recebeAtendimentoMedico;
        this.familia = familia;
        this.escola = escola;
    }

    // Getters e Setters
    public String getId() {
        return id;  // Retorna o ID como String
    }

    public void setId(String id) {
        this.id = id;  // Atribui o ID como String
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean isNecessitaTransporte() {
        return necessitaTransporte;
    }

    public void setNecessitaTransporte(boolean necessitaTransporte) {
        this.necessitaTransporte = necessitaTransporte;
    }

    public boolean isRecebeAtendimentoMedico() {
        return recebeAtendimentoMedico;
    }

    public void setRecebeAtendimentoMedico(boolean recebeAtendimentoMedico) {
        this.recebeAtendimentoMedico = recebeAtendimentoMedico;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }
}