package br.com.orcafacil.mobile.service;

import br.com.orcafacil.integration.api.IPontuacaoResource;
import br.com.orcafacil.integration.api.to.Fornecedor;
import br.com.orcafacil.integration.api.to.Pontuacao;
import br.com.orcafacil.mobile.util.WSUtil;

public class PontuacaoService implements IPontuacaoResource {

	private IPontuacaoResource instance;
	
	public PontuacaoService() {
		instance = (IPontuacaoResource) new WSUtil().get(IPontuacaoResource.class);
	}

	@Override
	public void pontuar(String apiKey, Pontuacao pontuacao) {
		instance.pontuar(apiKey, pontuacao);
	}
	

}
