package br.com.orcafacil.server.view;

import br.com.orcafacil.server.domain.Categoria;
import br.com.orcafacil.server.repository.CategoriaRepository;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.List;
import javax.inject.Inject;

@ViewController
public class CategoriaMB {
    
    @Inject
    private CategoriaRepository categoriaRepository;
    
    private Categoria categoria;

    public Categoria getCategoria() {
        if(categoria == null)
            categoria = new Categoria();
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public List<Categoria> getList(){
        return categoriaRepository.listAll();
    }
    
    @Transactional
    public String salvar(){
        categoriaRepository.add(categoria);
        return "categoria_list.jsf?faces-redirect=true";
    }
}
