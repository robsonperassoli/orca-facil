package br.com.orcafacil.server.persistence;

import br.com.orcafacil.server.domain.Fornecedor;
import br.com.orcafacil.server.repository.FornecedorRepository;
import br.gov.frameworkdemoiselle.template.JPACrud;
import java.util.List;

public class FornecedorDAO extends JPACrud<Fornecedor, Long> implements FornecedorRepository {

    @Override
    public void add(Fornecedor fornecedor) {
        if(fornecedor.getId() == null)
            insert(fornecedor);
        else
            update(fornecedor);
    }

    @Override
    public List<Fornecedor> getAll() {
        return findAll(); 
    }

    @Override
    public Fornecedor get(Long id) {
        return load(id);
    }
    
}
