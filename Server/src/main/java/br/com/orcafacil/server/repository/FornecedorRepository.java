package br.com.orcafacil.server.repository;

import br.com.orcafacil.server.domain.Fornecedor;
import java.util.List;

public interface FornecedorRepository {
    public void add(Fornecedor fornecedor);
    public Fornecedor get(Long id);

    public List<Fornecedor> getAll();
}
