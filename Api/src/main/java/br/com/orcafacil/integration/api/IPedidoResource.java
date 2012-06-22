package br.com.orcafacil.integration.api;

import br.com.orcafacil.integration.api.to.Pedido;
import br.com.orcafacil.integration.api.to.PedidoEmExecucao;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(value="/pedidos")
public interface IPedidoResource {
    
    @POST
    @Path("/salvar/{apiKey}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void salvar(@PathParam("apiKey") String apiKey, Pedido pedido);
    
    @POST
    @Path("/salvar/lote/{apiKey}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void salvarLote(@PathParam("apiKey") String apiKey, List<Pedido> lote);
    
    @GET
    @Path("/carregar/{id}/{apiKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pedido carregar(@PathParam("apiKey") String apiKey, @PathParam("id") Integer id);
    
    @GET
    @Path("/cancelar/{id}/{apiKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public void cancelar(@PathParam("apiKey") String apiKey, @PathParam("id") Integer id);
    
    @GET
    @Path("/buscarTodos/{apiKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PedidoEmExecucao> buscarTodos(@PathParam("apiKey") String apiKey);
}
