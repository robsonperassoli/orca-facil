package br.com.orcafacil.server.domain;

import br.com.orcafacil.server.repository.ProdutoRepository;
import br.gov.frameworkdemoiselle.util.Beans;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="PRODUTO")
@SequenceGenerator(name="PRO_SEQ",allocationSize=1)
public class Produto implements Serializable {

    @Id
    @Column(name="PRO_ID")
    @GeneratedValue(generator="PRO_SEQ", strategy=GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name="PRO_NOME")
    private String nome;
    
    @ManyToOne
    @JoinColumn(name="CAT_ID")
    private Categoria categoria;
    
    @Column(name="PRO_DISPONIVEL")
    private Boolean disponivel;
    
    @ManyToOne
    @JoinColumn(name="MAR_ID")
    private Marca marca;
    
    @Transient
    private ProdutoRepository produtoRepository = Beans.getReference(ProdutoRepository.class);
    
    public Produto() {
        
    }

    public Produto(String nome, Boolean disponivel, Marca marca, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
        this.disponivel = disponivel;
        this.marca = marca;
    }

    public Long getId() {
        return id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PrecoProduto> getMelhoresPrecos(){
        return produtoRepository.getMelhoresPrecos(this, 4);
    }
    
    public PrecoProduto getPreco(Fornecedor fornecedor){
        return produtoRepository.getPreco(this, fornecedor);
    }
    
}