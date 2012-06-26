package br.com.orcafacil.integration.api;

import br.com.orcafacil.integration.api.to.Pontuacao;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/pontuacao")
public interface IPontuacaoResource {
    
    @POST
    @Path("/pontuar/{apiKey}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void pontuar(@PathParam("apiKey") String apiKey, Pontuacao pontuacao);
    
}
