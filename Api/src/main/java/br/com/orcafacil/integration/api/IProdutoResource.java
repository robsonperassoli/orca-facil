package br.com.orcafacil.integration.api;

import br.com.orcafacil.integration.api.to.Produto;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value="/produtos")
public interface IProdutoResource {
    
    @GET
    @Path("/carregar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto carregar(@PathParam("id") Long id);
    
    @GET
    @Path("/buscarTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> buscarTodos();
    
    @GET
    @Path("/buscar/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> buscar(@PathParam("nome") String nome);
}
