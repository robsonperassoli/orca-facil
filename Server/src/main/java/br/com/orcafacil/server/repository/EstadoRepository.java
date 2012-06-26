package br.com.orcafacil.server.repository;

import br.com.orcafacil.server.domain.Estado;
import java.util.List;

public interface EstadoRepository {
    public void add(Estado estado);
    public void remove(Estado estado);
    public Estado get(String uf);
    public List<Estado> listAll();
            
}
