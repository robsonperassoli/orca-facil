package br.com.orcafacil.server.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PONTUACAO")
public class Pontuacao implements Serializable { 

    @Id
    @Column(name="PON_ID")
    private Integer id;
    
    @Column(name="PON_JUSTIFICATIVA")
    private String justificativa;
    
    @Enumerated
    @Column(name="PON_PONTUACAO")
    private TipoPontuacao tipoPontuacao;
    
    @ManyToOne
    @JoinColumn(name="PON_PONTUADO")
    private Entidade pontuado;
    
    @ManyToOne
    @JoinColumn(name="PON_PONTUADOR")
    private Entidade pontuador;

    public Pontuacao() {
    }

    public Pontuacao(String justificativa, TipoPontuacao tipoPontuacao, Entidade pontuado, Entidade pontuador) {
        this.justificativa = justificativa;
        this.pontuado = pontuado;
        this.pontuador = pontuador;
        this.tipoPontuacao = tipoPontuacao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public Entidade getPontuado() {
        return pontuado;
    }

    public Entidade getPontuador() {
        return pontuador;
    }

    public TipoPontuacao getTipoPontuacao() {
        return tipoPontuacao;
    }
        
}