package com.app.ellp.Module.Colaborador.Domain;

import com.app.ellp.Module.User.Domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Table(name = "colaborador")
@Entity(name = "colaborador")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(nullable = false)
    @Size(min = 3, max = 255)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String registro;
    @OneToOne
    @JoinColumn(name = "users_id")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 3, max = 255) String getNome() {
        return nome;
    }

    public void setNome(@Size(min = 3, max = 255) String nome) {
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
