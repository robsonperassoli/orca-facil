package br.com.orcafacil.server.persistence;

import br.com.orcafacil.server.domain.Cliente;
import br.com.orcafacil.server.repository.ClienteRepository;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import java.util.List;
import javax.persistence.Query;

@PersistenceController
public class ClienteDAO extends JPACrud<Cliente, Long> implements ClienteRepository {

    public Cliente getByApiKey(String apiKey) {
        Query qry = createQuery("select c from Cliente c where c.apiKey = :apiKey");
        qry.setParameter("apiKey", apiKey);
        List<Cliente> list = qry.getResultList();
        if(!list.isEmpty())
            return list.get(0);
        return null;
    }

    public Cliente get(Long id) {
        return load(id);
    }

    @Override
    public List<Cliente> getAll() {
        return findAll();
    }

    @Override
    public void add(Cliente cliente) {
        if(cliente == null)
            insert(cliente);
        else
            update(cliente);
    }
    
}
