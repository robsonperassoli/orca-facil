package br.com.orcafacil.server.repository;

import br.com.orcafacil.server.domain.Categoria;
import java.util.List;

public interface CategoriaRepository {
    public void add(Categoria categoria);
    public void remove(Categoria categoria);
    public Categoria get(Integer id);
    public List<Categoria> listAll();
            
}
