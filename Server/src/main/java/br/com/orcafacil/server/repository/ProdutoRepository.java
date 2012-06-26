package br.com.orcafacil.server.repository;

import br.com.orcafacil.server.domain.Fornecedor;
import br.com.orcafacil.server.domain.PrecoProduto;
import br.com.orcafacil.server.domain.Produto;
import java.util.List;

public interface ProdutoRepository {
    public void add(Produto produto);
    public void remove(Produto produto);
    public Produto get(Long id);
    public List<Produto> listAll();
    public List<Produto> listProdutosCom(String nome);
    
    public List<PrecoProduto> getMelhoresPrecos(Produto produto, int count);

    public PrecoProduto getPreco(Produto produto, Fornecedor fornecedor);
}
