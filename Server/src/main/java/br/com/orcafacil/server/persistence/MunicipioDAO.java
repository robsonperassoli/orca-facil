package br.com.orcafacil.server.persistence;

import br.com.orcafacil.server.domain.Municipio;
import br.com.orcafacil.server.domain.Marca;
import br.com.orcafacil.server.repository.MunicipioRepository;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;

@PersistenceController
public class MunicipioDAO extends JPACrud<Municipio, Integer> implements MunicipioRepository {

    @Override
    @Transactional
    public void add(Municipio municipio) {
        if(municipio.getId() != null)
            update(municipio);
        else
            insert(municipio);
    }

    @Override
    @Transactional
    public void remove(Municipio municipio) {
        delete(municipio.getId());
    }

    @Override
    public List<Municipio> listAll() {
        return findAll();
    }

}
