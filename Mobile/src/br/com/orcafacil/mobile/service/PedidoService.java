package br.com.orcafacil.mobile.service;

import java.util.List;


import br.com.orcafacil.integration.api.IPedidoResource;
import br.com.orcafacil.integration.api.to.Credentials;
import br.com.orcafacil.integration.api.to.Pedido;
import br.com.orcafacil.integration.api.to.PedidoEmExecucao;
import br.com.orcafacil.mobile.util.Internet;
import br.com.orcafacil.mobile.util.WSUtil;


public class PedidoService implements IPedidoResource {

	private IPedidoResource instance;
	
	public PedidoService() {
		instance = (IPedidoResource) new WSUtil().get(IPedidoResource.class);
	}
	

	@Override
	public void cancelar(String apiKey, Integer id) {
		instance.cancelar(apiKey, id);
	}

	@Override
	public Pedido carregar(String apiKey, Integer id) {
		return instance.carregar(apiKey, id);
	}

	@Override
	public void salvar(String apiKey, Pedido pedido) {
		instance.salvar(apiKey, pedido);
	}


	@Override
	public List<PedidoEmExecucao> buscarTodos(String apiKey) {
		return instance.buscarTodos(apiKey);
	}


	@Override
	public void salvarLote(String apiKey, List<Pedido> lote) {
		instance.salvarLote(apiKey, lote);
	}

}
