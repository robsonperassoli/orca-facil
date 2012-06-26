package br.com.orcafacil.integration.api.to;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class ItemDoPedidoEmExecucao implements Serializable {
    
    private Produto produto;
    private double quantidade;
    private double unitario;
    private double valorEntrega;
    private double total;
    private Fornecedor fornecedor;
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getUnitario() {
        return unitario;
    }

    public void setUnitario(double unitario) {
        this.unitario = unitario;
    }

    public double getValorEntrega() {
        return valorEntrega;
    }

    public void setValorEntrega(double valorEntrega) {
        this.valorEntrega = valorEntrega;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
}
