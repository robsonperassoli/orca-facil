package br.com.orcafacil.server.persistence;

import br.com.orcafacil.server.domain.Estado;
import br.com.orcafacil.server.domain.Marca;
import br.com.orcafacil.server.repository.EstadoRepository;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;

@PersistenceController
public class EstadoDAO extends JPACrud<Estado, String> implements EstadoRepository {

    @Override
    @Transactional
    public void add(Estado estado) {
        if(estado.getUf() != null)
            update(estado);
        else
            insert(estado);
    }

    @Override
    @Transactional
    public void remove(Estado estado) {
        delete(estado.getUf());
    }

    @Override
    public List<Estado> listAll() {
        return findAll();
    }

    public Estado get(String uf) {
        return load(uf);
    }

}
