package br.com.orcafacil.integration.api.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;

@JsonAutoDetect
public class PedidoEmExecucao implements Serializable {
    
    public static final int APROVADO = 1;
    public static final int PARCIALMENTE_APROVADO = 2;
    public static final int CANCELADO = 3;

    @JsonIgnore
    public int getSituacao() {
        return PARCIALMENTE_APROVADO;
    }
    
    private Long numeroRequisicao;
    private Date data;
    private List<ItemDoPedidoEmExecucao> itens = new ArrayList<ItemDoPedidoEmExecucao>();

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getNumeroRequisicao() {
        return numeroRequisicao;
    }

    public void setNumeroRequisicao(Long numeroRequisicao) {
        this.numeroRequisicao = numeroRequisicao;
    }

    public List<ItemDoPedidoEmExecucao> getItens() {
        return itens;
    }

    public void setItens(List<ItemDoPedidoEmExecucao> itens) {
        this.itens = itens;
    }

}
