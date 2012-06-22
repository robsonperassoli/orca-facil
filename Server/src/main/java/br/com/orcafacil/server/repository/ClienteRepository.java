package br.com.orcafacil.server.repository;

import br.com.orcafacil.server.domain.Cliente;
import java.util.List;

public interface ClienteRepository {
    public Cliente get(Long id);
    public List<Cliente> getAll();
    public Cliente getByApiKey(String apiKey);

    public void add(Cliente cliente);
}
