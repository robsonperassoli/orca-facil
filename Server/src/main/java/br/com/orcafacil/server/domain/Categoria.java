package br.com.orcafacil.server.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORIA")
@SequenceGenerator(name="categoria_gen", allocationSize=1)
public class Categoria implements Serializable {

    @Id
    @Column(name="CAT_ID")
    @GeneratedValue(generator="categoria_gen", strategy=GenerationType.SEQUENCE) 
    private Integer id;
    
    @Column(name="CAT_NOME")
    private String nome;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }
    
}