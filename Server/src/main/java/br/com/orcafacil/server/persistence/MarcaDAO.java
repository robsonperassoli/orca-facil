package br.com.orcafacil.server.persistence;

import br.com.orcafacil.server.domain.Marca;
import br.com.orcafacil.server.repository.MarcaRepository;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;

@PersistenceController
public class MarcaDAO extends JPACrud<Marca, Integer> implements MarcaRepository {

    @Override
    @Transactional
    public void add(Marca marca) {
        if(marca.getId() != null)
            update(marca);
        else
            insert(marca);
    }

    @Override
    @Transactional
    public void remove(Marca marca) {
        delete(marca.getId());
    }

    @Override
    public List<Marca> listAll() {
        return findAll();
    }

    public Marca get(Integer id) {
        return load(id);
    }

}
