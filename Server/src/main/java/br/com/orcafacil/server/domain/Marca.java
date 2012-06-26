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
@Table(name="MARCA")
@SequenceGenerator(name="marca_gen",allocationSize=1)
public class Marca implements Serializable {

    @Id
    @Column(name="MAR_ID")
    @GeneratedValue(generator="marca_gen", strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name="MAR_NOME")
    private String nome;

    public Marca() {
    }

    public Marca(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
}