package br.com.orcafacil.integration.api.to;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Produto implements Serializable {
    private Long id;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
