package br.com.orcafacil.server.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MUNICIPIO")
@SequenceGenerator(name="MUN_SEQ", allocationSize=1)
public class Municipio implements Serializable  {
    @Id
    @Column(name="MUN_ID")
    @GeneratedValue(generator="MUN_SEQ", strategy=GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(name="MUN_NOME")
    private String nome;
    
    @ManyToOne
    @JoinColumn(name="EST_UF")
    private Estado estado;

    public Municipio() {
    }

    public Municipio(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}