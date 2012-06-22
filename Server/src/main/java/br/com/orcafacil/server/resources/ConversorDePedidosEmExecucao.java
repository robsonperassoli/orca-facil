package br.com.orcafacil.server.resources;

import br.com.orcafacil.integration.api.to.*;
import br.com.orcafacil.server.domain.pedido.ItemDoPedido;
import br.com.orcafacil.server.domain.pedido.Pedido;
import java.util.ArrayList;
import java.util.List;

class ConversorDePedidosEmExecucao {

    private List<Pedido> pedidosParaConverter;
    private List<PedidoEmExecucao> pedidosEmExecucao;
    
    
    public ConversorDePedidosEmExecucao(List<Pedido> pedidosParaConverter) {
        this.pedidosParaConverter = pedidosParaConverter;
        pedidosEmExecucao = new ArrayList<PedidoEmExecucao>();
    }

    public List<PedidoEmExecucao> convert() {
        for (br.com.orcafacil.server.domain.pedido.Pedido pedido : pedidosParaConverter) {
            PedidoEmExecucao novoPedido = new PedidoEmExecucao();
            if (foiCriadoPedidoParaRequisicao(pedido.getRequisicaoCliente())) {
                novoPedido = getPedidoDaRequisicao(pedido.getRequisicaoCliente());
            }
            novoPedido.setNumeroRequisicao(pedido.getRequisicaoCliente());
            novoPedido.setData(pedido.getData());
            novoPedido.getItens().addAll(converteParaItensDoPedido(pedido));
            adicionarPedidoEmExecucao(novoPedido);
        }
        
        return pedidosEmExecucao;
    }

    private boolean foiCriadoPedidoParaRequisicao(Long requisicaoCliente) {
        for(PedidoEmExecucao pedidoEmExecucao : pedidosEmExecucao){
            if(pedidoEmExecucao.getNumeroRequisicao().equals(requisicaoCliente))
                return true;
        }
        return false;
    }

    private PedidoEmExecucao getPedidoDaRequisicao(Long requisicaoCliente) {
        for(PedidoEmExecucao pedido : pedidosEmExecucao) {
            if(pedido.getNumeroRequisicao().equals(requisicaoCliente))
                return pedido;
        }
        return null;
    }

    private List<ItemDoPedidoEmExecucao> converteParaItensDoPedido(Pedido pedido) {
        List<ItemDoPedidoEmExecucao> itens = new ArrayList<ItemDoPedidoEmExecucao>();
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(pedido.getFornecedor().getId());
        fornecedor.setNome(pedido.getFornecedor().getNome());
        
        for(ItemDoPedido item : pedido.getItens()) {
            ItemDoPedidoEmExecucao novoItem = new ItemDoPedidoEmExecucao();
            Produto p = new Produto();
            p.setId(item.getProduto().getId());
            p.setNome(item.getProduto().getNome());
            novoItem.setProduto(p);
            novoItem.setQuantidade(item.getQuantidade());
            novoItem.setUnitario(item.getValorUnitario());
            novoItem.setValorEntrega(item.getValorEntrega());
            novoItem.setTotal(item.getValorTotal());
            novoItem.setFornecedor(fornecedor);
            itens.add(novoItem);
        }
        
        return itens;
    }

    private void adicionarPedidoEmExecucao(PedidoEmExecucao novoPedido) {
        for(PedidoEmExecucao pedido : pedidosEmExecucao) {
            if(pedido.getNumeroRequisicao().equals(novoPedido.getNumeroRequisicao())) {
                pedidosEmExecucao.remove(pedido);
                break;
            }
        }
        pedidosEmExecucao.add(novoPedido);
    }

}
