package br.com.orcafacil.server.repository;

import br.com.orcafacil.server.domain.Cliente;
import br.com.orcafacil.server.domain.pedido.Pedido;
import java.util.List;

public interface PedidoRepository {
    
    public void add(Pedido pedido);
    public void remove(Pedido pedido);
    public List<Pedido> list(Cliente cliente);
    
}
