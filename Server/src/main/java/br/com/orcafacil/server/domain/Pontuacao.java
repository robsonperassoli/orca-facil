package br.com.orcafacil.server.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="PONTUACAO")
public class Pontuacao implements Serializable { 

    @Id
    @Column(name="PON_ID")
    private Integer id;
    
    @Column(name="PON_JUSTIFICATIVA")
    private String justificativa;
    
    @Min(value=1)
    @Max(value=2)
    @Column(name="PON_PONTUACAO")
    private Integer pontuacao;
    
    @ManyToOne
    @JoinColumn(name="PON_PONTUADO")
    private Entidade pontuado;
    
    @ManyToOne
    @JoinColumn(name="PON_PONTUADOR")
    private Entidade pontuador;

    public Pontuacao() {
    }

    public Pontuacao(String justificativa, Integer pontuacao, Entidade pontuado, Entidade pontuador) {
        this.justificativa = justificativa;
        this.pontuacao = pontuacao;
        this.pontuado = pontuado;
        this.pontuador = pontuador;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public String getJustificativa() {
        return justificativa;
    }
        
}