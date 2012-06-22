package br.com.orcafacil.server.repository;

import br.com.orcafacil.server.domain.Municipio;
import java.util.List;

public interface MunicipioRepository {
    public void add(Municipio municipio);
    public void remove(Municipio municipio);
    public List<Municipio> listAll();
            
}
