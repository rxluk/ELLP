package com.app.ellp.Module.Colaborador.Domain;

import com.app.ellp.Module.User.Domain.User;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "colaborador")
public class Colaborador {
    @Id
    private String id;
    
    @Size(min = 3, max = 255)
    private String nome;
    
    @Indexed(unique = true)
    private String email;
    
    @Indexed(unique = true)
    private String registro;
    
    @DBRef
    private User user;

    public Colaborador() {
    }

    public Colaborador(String nome, String email, String registro, User user) {
        this.nome = nome;
        this.email = email;
        this.user = user;
        this.registro = registro;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}
