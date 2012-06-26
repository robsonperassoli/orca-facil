package br.com.orcafacil.integration.api.to;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class ItemDoPedido implements Serializable {
    private Integer item;
    private Produto produto;
    private double quantidade;

    public ItemDoPedido() {
    }

    public ItemDoPedido(Integer item, Produto produto, double quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public Integer getItem() {
        return item;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
