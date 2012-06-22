package br.com.orcafacil.integration.api;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/pontuacao")
public interface IPontuacaoResource {
    
    @POST
    @Path("/pontuar")
    public void pontuar(@FormParam("pontuacao") Integer pontuacao);
}
