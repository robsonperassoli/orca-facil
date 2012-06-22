package br.com.orcafacil.server.resources;

import br.com.orcafacil.integration.api.IPedidoResource;
import br.com.orcafacil.integration.api.to.Pedido;
import br.com.orcafacil.integration.api.to.PedidoEmExecucao;
import br.com.orcafacil.server.domain.Cliente;
import br.com.orcafacil.server.domain.pedido.ProcessadorDePedidosNovos;
import br.com.orcafacil.server.repository.ClienteRepository;
import br.com.orcafacil.server.repository.PedidoRepository;
import br.gov.frameworkdemoiselle.util.Beans;
import java.util.List;

public class PedidoResource implements IPedidoResource {

    private PedidoRepository pedidoRepository = Beans.getReference(PedidoRepository.class);
    private ClienteRepository clienteRepository = Beans.getReference(ClienteRepository.class);
    
    @Override
    public void salvar(String apiKey, Pedido pedido) {
        Cliente cliente = clienteRepository.getByApiKey(apiKey);
        List<br.com.orcafacil.server.domain.pedido.Pedido> pedidosGerados = new ProcessadorDePedidosNovos(pedido, cliente).processar();
        for(br.com.orcafacil.server.domain.pedido.Pedido pedidoGerado: pedidosGerados)
            pedidoRepository.add(pedidoGerado);
    }
    
    @Override
    public void cancelar(String apiKey, Integer intgr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PedidoEmExecucao> buscarTodos(String apiKey) {
        Cliente cliente = clienteRepository.getByApiKey(apiKey);
        return new ConversorDePedidosEmExecucao(pedidoRepository.list(cliente)).convert();
    }

    
    @Override
    public Pedido carregar(String apiKey, Integer intgr) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void salvarLote(String apiKey, List<Pedido> lote) {
        for(Pedido pedido : lote)
            salvar(apiKey, pedido);
    }


}
