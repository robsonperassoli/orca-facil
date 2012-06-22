package br.com.orcafacil.server.persistence;

import br.com.orcafacil.server.domain.Fornecedor;
import br.com.orcafacil.server.domain.PrecoProduto;
import br.com.orcafacil.server.domain.Produto;
import br.com.orcafacil.server.repository.ProdutoRepository;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;
import javax.persistence.Query;

@PersistenceController
public class ProdutoDAO extends JPACrud<Produto, Long> implements ProdutoRepository {

    @Override
    @Transactional
    public void add(Produto produto) {
        if(produto.getId() != null)
            update(produto);
        else
            insert(produto);
    }

    @Override
    @Transactional
    public void remove(Produto produto) {
        delete(produto.getId());
    }

    @Override
    public List<Produto> listAll() {
        return findAll();
    }

    public Produto get(Long id) {
        return load(id);
    }

    public List<Produto> listProdutosCom(String nome) {
        Query qry = createQuery("select p from Produto p where upper(p.nome) like :nome");
        qry.setParameter("nome", "%" + nome.toUpperCase() + "%");
        return qry.getResultList();
    }

    public List<PrecoProduto> getMelhoresPrecos(Produto produto, int count) {
        //TODO: Refatorar para pegar apenas o último preço de cada produto
        Query qry = createQuery("select p from PrecoProduto p where p.produto = :produto order by p.preco asc");
        qry.setParameter("produto", produto);
        qry.setMaxResults(count);
        return qry.getResultList();
    }

    public PrecoProduto getPreco(Produto produto, Fornecedor fornecedor) {
        Query qry = createQuery("select p from PrecoProduto p where p.produto = :produto and p.fornecedor = :fornecedor order by p.data desc");
        qry.setParameter("produto", produto);
        qry.setParameter("fornecedor", fornecedor);
        List<PrecoProduto> precosProduto = qry.getResultList();
        if(!precosProduto.isEmpty())
            return precosProduto.get(0);
        return null;
    }

}
