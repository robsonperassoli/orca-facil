package br.com.orcafacil.server.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ESTADO")
public class Estado implements Serializable {

    @Id
    @Column(name="EST_UF")
    private String uf;
    
    @Column(name="EST_NOME")
    private String nome;

    public Estado() {
    }

    public Estado(String uf, String nome) {
        this.uf = uf;
        this.nome = nome;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getUf() {
        return uf;
    }

    public String getNome() {
        return nome;
    }
    
}