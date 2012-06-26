package br.com.orcafacil.mobile.service;


import br.com.orcafacil.integration.api.IAutenticationResource;
import br.com.orcafacil.mobile.util.WSUtil;

public class AutenticationService implements IAutenticationResource {

	private IAutenticationResource instance;
	
	public AutenticationService() {
		instance = (IAutenticationResource) new WSUtil().get(IAutenticationResource.class);
	}
	
	@Override
	public Boolean autenticar(String email, String apiKey) {
		return instance.autenticar(email, apiKey);
	}

}
