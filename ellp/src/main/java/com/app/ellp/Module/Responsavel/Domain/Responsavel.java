package com.app.ellp.Module.Responsavel.Domain;

import com.app.ellp.Module.Familia.Domain.Familia;
import com.app.ellp.Module.Responsavel.Enums.Escolaridade;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor

@Document(collection = "responsavel")
public class Responsavel {
    @Id
    private String id;
    @Size(min = 3, max = 255)
    private String nome;
    @Field
    private Escolaridade escolaridade;
    private String emprego;
    private String celular;
    private boolean tipoResponsavel; // É mãe ou pai?
    @DBRef
    private Familia familia;

    public Responsavel(String nome, Escolaridade escolaridade, String emprego, String celular, Boolean tipoResponsavel) {
        this.nome = nome;
        this.escolaridade = escolaridade;
        this.emprego = emprego;
        this.celular = celular;
        this.tipoResponsavel = tipoResponsavel;
    }
    public Familia getFamilia() {
        return this.familia;
    }
    public void setFamilia(Familia familia) {
        this.familia = familia;
    }
}
