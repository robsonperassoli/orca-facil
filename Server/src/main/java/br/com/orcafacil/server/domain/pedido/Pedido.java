package br.com.orcafacil.server.domain.pedido;

import br.com.orcafacil.server.domain.Cliente;
import br.com.orcafacil.server.domain.Fornecedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="PEDIDO")
@SequenceGenerator(name="gen_pedido", allocationSize=1)
public class Pedido implements Serializable {

    @Id
    @Column(name="PED_ID")
    @GeneratedValue(generator="gen_pedido",strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="ENT_CLIENTE")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name="ENT_FORNECEDOR")
    private Fornecedor fornecedor;
    
    @Column(name="PED_DATA")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date data = new Date();
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name="PED_ID")
    private List<ItemDoPedido> itens = new ArrayList<ItemDoPedido>();
    
    @Column(name="PED_REQUISICAO_CLIENTE")
    private Long requisicaoCliente;

    public Pedido() {}

    public Pedido(Cliente cliente, Fornecedor fornecedor, Long requisicaoCliente) {
        this.cliente = cliente;
        this.fornecedor = fornecedor;
        this.requisicaoCliente = requisicaoCliente;
    }

    public Long getId() {
        return id;
    }
    
    public void addItem(ItemDoPedido item){
        this.itens.add(item);
    }

    public List<ItemDoPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public Date getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Long getRequisicaoCliente() {
        return requisicaoCliente;
    }

}