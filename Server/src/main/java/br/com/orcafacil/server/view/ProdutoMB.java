package br.com.orcafacil.server.view;

import br.com.orcafacil.server.domain.Produto;
import br.com.orcafacil.server.repository.ProdutoRepository;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;
import javax.inject.Inject;

@ViewController
public class ProdutoMB {
    
    @Inject
    private ProdutoRepository produtoRepository;
    
    private Produto produto;

    public Produto getProduto() {
        if(produto == null)
            produto = new Produto();
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public List<Produto> getList(){
        return produtoRepository.listAll();
    }
    
    @Transactional
    public String salvar(){
        produtoRepository.add(produto);
        return "produto_list.jsf?faces-redirect=true";
    }
}
