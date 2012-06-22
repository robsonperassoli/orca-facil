package br.com.orcafacil.server.view;

import br.com.orcafacil.server.domain.Cliente;
import br.com.orcafacil.server.repository.ClienteRepository;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;
import javax.inject.Inject;

@ViewController
public class ClienteMB {
    
    @Inject
    private ClienteRepository clienteRepository;
    
    private Cliente cliente;

    public Cliente getCliente() {
        if(cliente == null)
            cliente = new Cliente();
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getList(){
        return clienteRepository.getAll();
    }
    
    @Transactional
    public String salvar(){
        clienteRepository.add(cliente);
        return "cliente_list.jsf?faces-redirect=true";
    }
}
