package br.com.orcafacil.server.repository;

import br.com.orcafacil.server.domain.Marca;
import java.util.List;

public interface MarcaRepository {
    public void add(Marca marca);
    public void remove(Marca marca);
    public Marca get(Integer id);
    public List<Marca> listAll();
            
}
