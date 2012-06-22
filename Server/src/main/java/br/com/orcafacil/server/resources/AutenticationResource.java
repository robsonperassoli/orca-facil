package br.com.orcafacil.server.resources;

import br.com.orcafacil.integration.api.IAutenticationResource;


public class AutenticationResource implements IAutenticationResource {

    public Boolean autenticar(String email, String apiKey) {
        return true;
    }

}
