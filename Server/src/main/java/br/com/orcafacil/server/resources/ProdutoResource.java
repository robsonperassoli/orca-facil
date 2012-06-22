package br.com.orcafacil.server.resources;

import br.com.orcafacil.integration.api.IProdutoResource;
import br.com.orcafacil.server.domain.Produto;
import br.com.orcafacil.server.repository.ProdutoRepository;
import br.gov.frameworkdemoiselle.util.Beans;
import java.util.ArrayList;
import java.util.List;

public class ProdutoResource implements IProdutoResource {

    private ProdutoRepository produtoRepository = Beans.getReference(ProdutoRepository.class);
    
    public br.com.orcafacil.integration.api.to.Produto carregar(Long id) {
        Produto produto = produtoRepository.get(id);
        return convertProduto(produto);
    }

    public List<br.com.orcafacil.integration.api.to.Produto> buscarTodos() {
        List<Produto> todosProdutos = produtoRepository.listAll();
        return converteLista(todosProdutos);
    }

    public List<br.com.orcafacil.integration.api.to.Produto> buscar(String nome) {
        List<Produto> produtosComNome = produtoRepository.listProdutosCom(nome);
        return converteLista(produtosComNome);
    }

    /**
     * Converter uma entidade Produto para o TransferObject Produto
     * @param produto
     * @return 
     */
    private br.com.orcafacil.integration.api.to.Produto convertProduto(Produto produto) {
        br.com.orcafacil.integration.api.to.Produto produtoRetorno = new br.com.orcafacil.integration.api.to.Produto();
        produtoRetorno.setId(produto.getId());
        produtoRetorno.setNome(produto.getNome());
        return produtoRetorno;
    }

    /**
     * Converte uma lista de Entidade Produto para uma lista de transferência
     * @param todosProdutos
     * @return 
     */
    private List<br.com.orcafacil.integration.api.to.Produto> converteLista(List<Produto> produtosParaConverter) {
        List<br.com.orcafacil.integration.api.to.Produto> produtosConvertidos = new ArrayList<br.com.orcafacil.integration.api.to.Produto>();
        for(Produto produtoParaConverter : produtosParaConverter)
            produtosConvertidos.add(convertProduto(produtoParaConverter));
        return produtosConvertidos;
    }
    
}
