package br.com.orcafacil.integration.api.to;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Pontuacao implements Serializable {
    private Fornecedor fornecedor;
    private Integer tipoPontuacao;
    private String justificativa;

    public Pontuacao() {
    }

    public Pontuacao(Fornecedor fornecedor, Integer tipoPontuacao, String justificativa) {
        this.fornecedor = fornecedor;
        this.tipoPontuacao = tipoPontuacao;
        this.justificativa = justificativa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Integer getTipoPontuacao() {
        return tipoPontuacao;
    }

    public void setTipoPontuacao(Integer tipoPontuacao) {
        this.tipoPontuacao = tipoPontuacao;
    }

}
