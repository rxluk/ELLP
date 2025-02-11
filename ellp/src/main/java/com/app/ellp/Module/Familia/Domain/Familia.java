package com.app.ellp.Module.Familia.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "familia")
public class Familia {

    @Id
    private String id;

    @Field
    private int grupoFamiliar;

    @Field
    private boolean acessoInternet;

    @Field
    private boolean possuiComputador;

    @Field
    private boolean casaPropria;

    @Field
    private boolean possuiCarro;

    @Field
    private int pessoasEmpregadas;

    @Field
    private double renda;

    // Construtor
    public Familia(String id, int grupoFamiliar, boolean acessoInternet, boolean possuiComputador,
                   boolean casaPropria, boolean possuiCarro, int pessoasEmpregadas, double renda) {
        this.id = id;
        this.grupoFamiliar = grupoFamiliar;
        this.acessoInternet = acessoInternet;
        this.possuiComputador = possuiComputador;
        this.casaPropria = casaPropria;
        this.possuiCarro = possuiCarro;
        this.pessoasEmpregadas = pessoasEmpregadas;
        this.renda = renda;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGrupoFamiliar() {
        return grupoFamiliar;
    }

    public void setGrupoFamiliar(int grupoFamiliar) {
        this.grupoFamiliar = grupoFamiliar;
    }

    public boolean isAcessoInternet() {
        return acessoInternet;
    }

    public void setAcessoInternet(boolean acessoInternet) {
        this.acessoInternet = acessoInternet;
    }

    public boolean isPossuiComputador() {
        return possuiComputador;
    }

    public void setPossuiComputador(boolean possuiComputador) {
        this.possuiComputador = possuiComputador;
    }

    public boolean isCasaPropria() {
        return casaPropria;
    }

    public void setCasaPropria(boolean casaPropria) {
        this.casaPropria = casaPropria;
    }

    public boolean isPossuiCarro() {
        return possuiCarro;
    }

    public void setPossuiCarro(boolean possuiCarro) {
        this.possuiCarro = possuiCarro;
    }

    public int getPessoasEmpregadas() {
        return pessoasEmpregadas;
    }

    public void setPessoasEmpregadas(int pessoasEmpregadas) {
        this.pessoasEmpregadas = pessoasEmpregadas;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }
}