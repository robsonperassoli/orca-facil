package br.com.orcafacil.server.domain.pedido;

import br.com.orcafacil.server.domain.Produto;
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
@Table(name = "ITEM_PEDIDO")
@SequenceGenerator(name="gen_item_pedido", allocationSize=1)
public class ItemDoPedido implements Serializable {
    @Id
    @Column(name="IPE_ID")
    @GeneratedValue(generator="gen_item_pedido",strategy=GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="PRO_ID")
    private Produto produto;

    @Column(name="IPE_QUANTIDADE")
    private Double quantidade;
    
    @Column(name="IPE_STATUS")
    private String status;

    @Column(name="IPE_VALOR_ENTREGA")
    private Double valorEntrega;

    @Column(name="IPE_TOTAL")
    private Double valorTotal;
    
    @Column(name="IPE_UNITARIO")
    private Double valorUnitario;

    public ItemDoPedido() {
    }

    public ItemDoPedido(Produto produto, Double quantidade, Double valorEntrega, Double valorUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorEntrega = valorEntrega;
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        this.valorTotal = (valorUnitario * quantidade) + valorEntrega;
        return valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public String getStatus() {
        return status;
    }

    public Double getValorEntrega() {
        return valorEntrega;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }
    
}