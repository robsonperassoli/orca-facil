package br.com.orcafacil.server.persistence;

import br.com.orcafacil.server.domain.Categoria;
import br.com.orcafacil.server.repository.CategoriaRepository;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;

@PersistenceController
public class CategoriaDAO extends JPACrud<Categoria, Integer> implements CategoriaRepository {

    @Override
    @Transactional
    public void add(Categoria categoria) {
        if(categoria.getId() != null)
            update(categoria);
        else
            insert(categoria);
    }

    @Override
    @Transactional
    public void remove(Categoria categoria) {
        delete(categoria.getId());
    }

    @Override
    public List<Categoria> listAll() {
        return findAll();
    }

    public Categoria get(Integer id) {
        return load(id);
    }

}
