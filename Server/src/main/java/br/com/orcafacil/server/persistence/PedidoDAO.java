package br.com.orcafacil.server.persistence;

import br.com.orcafacil.server.domain.Cliente;
import br.com.orcafacil.server.domain.pedido.Pedido;
import br.com.orcafacil.server.repository.PedidoRepository;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;
import javax.persistence.Query;

@PersistenceController
public class PedidoDAO extends JPACrud<Pedido, Long> implements PedidoRepository {

    @Transactional
    public void add(Pedido pedido) {
        if(pedido.getId() == null)
            insert(pedido);
        else
            update(pedido);
    }

    public void remove(Pedido pedido) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pedido> list(Cliente cliente) {
        Query qry = createQuery("select p from Pedido p where p.cliente = :cliente order by p.requisicaoCliente desc");
        qry.setParameter("cliente", cliente);
        return qry.getResultList();
    }
    
}
