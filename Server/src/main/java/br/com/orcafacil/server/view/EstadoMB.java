package br.com.orcafacil.server.view;

import br.com.orcafacil.server.domain.Estado;
import br.com.orcafacil.server.domain.Marca;
import br.com.orcafacil.server.repository.EstadoRepository;
import br.com.orcafacil.server.repository.MarcaRepository;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;
import javax.inject.Inject;

@ViewController
public class EstadoMB {
    
    @Inject
    private EstadoRepository estadoRepository;
    
    private Estado estado;

    public Estado getEstado() {
        if(estado == null)
            estado = new Estado();
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getList(){
        return estadoRepository.listAll();
    }
    
    @Transactional
    public String salvar(){
        estadoRepository.add(estado);
        return "estado_list.jsf?faces-redirect=true";
    }
}
