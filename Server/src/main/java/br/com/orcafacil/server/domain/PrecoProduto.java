package br.com.orcafacil.server.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="TABELA_PRECO") 
public class PrecoProduto implements Serializable {

    @Id
    @Column(name="TAB_ID")
    private Long id;
    
    @Column(name="TAB_DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data = new Date();
    
    @Column(name="TAB_PRECO")
    private Double preco;
    
    @ManyToOne
    @JoinColumn(name="PRO_ID")
    private Produto produto;
    
    @Column(name="TAB_VALOR_KM")
    private Double valorKM;
    
    @ManyToOne
    @JoinColumn(name="ENT_ID")
    private Fornecedor fornecedor;
    
    public PrecoProduto() {}

    public PrecoProduto(Produto produto, Double preco, Double valorKM, Fornecedor fornecedor) {
        this.preco = preco;
        this.produto = produto;
        this.valorKM = valorKM;
        this.fornecedor = fornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public Double getPreco() {
        return preco;
    }

    public Double getValorKM() {
        return valorKM;
    }
    
}