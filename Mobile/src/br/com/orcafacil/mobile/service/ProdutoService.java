package br.com.orcafacil.mobile.service;

import java.util.List;


import br.com.orcafacil.integration.api.IProdutoResource;
import br.com.orcafacil.integration.api.to.Produto;
import br.com.orcafacil.mobile.util.WSUtil;

public class ProdutoService implements IProdutoResource {
	
	private IProdutoResource instance;
	
	public ProdutoService() {
		instance = (IProdutoResource) new WSUtil().get(IProdutoResource.class);
	}

	@Override
	public List<Produto> buscar(String nome) {
		return instance.buscar(nome);
	}

	@Override
	public List<Produto> buscarTodos() {
		return instance.buscarTodos();
	}

	@Override
	public Produto carregar(Long id) {
		return instance.carregar(id);
	}

}
