package br.com.orcafacil.server.service;

import br.com.orcafacil.server.domain.Cliente;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PedidoService {
    
    @Inject 
    private EntityManager entityManager;
    
    public Long getNumeroRequisicao(Cliente cliente) {
        Query qry = entityManager.createQuery("select max(p.requisicaoCliente) from Pedido p where p.cliente = :cliente");
        qry.setParameter("cliente", cliente);
        Long requisicaoCliente = (Long) qry.getSingleResult();
        if(requisicaoCliente == null)
            requisicaoCliente = 1l;
        else
            requisicaoCliente++;
        return requisicaoCliente;
    }
}
