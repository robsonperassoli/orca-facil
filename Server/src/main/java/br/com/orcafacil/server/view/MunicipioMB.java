package br.com.orcafacil.server.view;

import br.com.orcafacil.server.domain.Municipio;
import br.com.orcafacil.server.repository.MunicipioRepository;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;
import javax.inject.Inject;

@ViewController
public class MunicipioMB {
    
    @Inject
    private MunicipioRepository municipioRepository;
    
    private Municipio municipio;

    public Municipio getMunicipio() {
        if(municipio == null)
            municipio = new Municipio();
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    
    public List<Municipio> getList(){
        return municipioRepository.listAll();
    }
    
    @Transactional
    public String salvar(){
        municipioRepository.add(municipio);
        return "municipio_list.jsf?faces-redirect=true";
    }
}
