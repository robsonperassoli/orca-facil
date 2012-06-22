package br.com.orcafacil.server.domain.pedido;

import br.com.orcafacil.server.domain.Cliente;
import br.com.orcafacil.server.domain.Fornecedor;
import br.com.orcafacil.server.domain.PrecoProduto;
import br.com.orcafacil.server.domain.Produto;
import br.com.orcafacil.server.repository.ClienteRepository;
import br.com.orcafacil.server.repository.ProdutoRepository;
import br.com.orcafacil.server.service.PedidoService;
import br.gov.frameworkdemoiselle.util.Beans;
import java.util.ArrayList;
import java.util.List;

public class ProcessadorDePedidosNovos {

    private ClienteRepository clienteRepository = Beans.getReference(ClienteRepository.class);
    
    private ProdutoRepository produtoRepository = Beans.getReference(ProdutoRepository.class);
    
    private PedidoService pedidoService = Beans.getReference(PedidoService.class);
    
    private br.com.orcafacil.integration.api.to.Pedido pedidoAProcessar;
    private List<Pedido> pedidosGerados;
    private Cliente cliente;
    private Long requisicaoCliente;    
    
    
    public ProcessadorDePedidosNovos(br.com.orcafacil.integration.api.to.Pedido pedidoAProcessar, Cliente cliente) {
        this.pedidoAProcessar = pedidoAProcessar;
        this.cliente = cliente;
        pedidosGerados = new ArrayList<Pedido>();
        
        requisicaoCliente = pedidoService.getNumeroRequisicao(cliente);
    }
    
    public List<Pedido> processar() {
        for(br.com.orcafacil.integration.api.to.ItemDoPedido itemRequisitado : pedidoAProcessar.getItens()){
            Produto produto = produtoRepository.get(itemRequisitado.getProduto().getId());
            Fornecedor fornecedorEscolhido = buscarFornecedorAdequado(produto);
            
            Pedido novoPedido;
            if(fornecedorJaPossuiPedidos(fornecedorEscolhido))
                novoPedido = buscarPedidoDoFornecedor(fornecedorEscolhido);
            else    
                novoPedido = new Pedido(cliente, fornecedorEscolhido, requisicaoCliente);
            
            //verificar distância para calcular o valor de entrega
            double distanciaEmKm = 0;
            PrecoProduto preco = produto.getPreco(fornecedorEscolhido);
            Double valorEntrega = preco.getValorKM() + distanciaEmKm;
            
            ItemDoPedido novoItem = new ItemDoPedido(produto, itemRequisitado.getQuantidade(), valorEntrega, preco.getPreco());
            novoPedido.addItem(novoItem);
            
            addPedidoGerado(novoPedido);
        }
        
        return pedidosGerados;
    }

    private boolean fornecedorJaPossuiPedidos(Fornecedor fornecedorEscolhido) {
        for(Pedido pedido : pedidosGerados) {
            if(pedido.getFornecedor().getId().equals(fornecedorEscolhido.getId()))
                return true;
        }
        return false;
    }

    private Fornecedor buscarFornecedorAdequado(Produto produto) {
        /**
         * seleciona 4 fornecedores com o melhor preço 
         * após isso selecionar os melhor pontuados
         * em seguida verifica o local mais próximo
         */
        // enviar e-mail para fornecedores
        List<PrecoProduto> melhoresPrecos = produto.getMelhoresPrecos();
        List<Fornecedor> melhoresFornecedores = new ArrayList<Fornecedor>();
        for(PrecoProduto preco : melhoresPrecos)
            melhoresFornecedores.add(preco.getFornecedor());
        
        return melhoresFornecedores.get(0);
    }

    private Pedido buscarPedidoDoFornecedor(Fornecedor fornecedorEscolhido) {
        for(Pedido pedido : pedidosGerados){
            if( pedido.getFornecedor().getId().equals(fornecedorEscolhido.getId()))
                return pedido;
        }
        return null;
    }

    private void addPedidoGerado(Pedido novoPedido) {
        for(Pedido pedido : pedidosGerados){
            if(pedido.getFornecedor().getId().equals(novoPedido.getFornecedor().getId())){
                pedidosGerados.remove(pedido);
                break;
            }
        }
        pedidosGerados.add(novoPedido);
    }

}