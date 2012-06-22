package br.com.orcafacil.integration.api;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/autenticacao")
public interface IAutenticationResource {
    
    @POST
    @Path("/login")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean autenticar(@FormParam("email") String email, @FormParam("apiKey") String apiKey);

}