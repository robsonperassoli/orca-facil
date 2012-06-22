package br.com.orcafacil.server.view;

import br.com.orcafacil.server.domain.Marca;
import br.com.orcafacil.server.repository.MarcaRepository;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;
import javax.inject.Inject;

@ViewController
public class MarcaMB {
    
    @Inject
    private MarcaRepository marcaRepository;
    
    private Marca marca;

    public Marca getMarca() {
        if(marca == null)
            marca = new Marca();
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    public List<Marca> getList(){
        return marcaRepository.listAll();
    }
    
    @Transactional
    public String salvar(){
        marcaRepository.add(marca);
        return "marca_list.jsf?faces-redirect=true";
    }
}
