package br.com.orcafacil.server.view.converter;

import br.com.orcafacil.server.domain.Categoria;
import br.com.orcafacil.server.domain.Estado;
import br.com.orcafacil.server.domain.Marca;
import br.com.orcafacil.server.repository.CategoriaRepository;
import br.com.orcafacil.server.repository.MarcaRepository;
import br.gov.frameworkdemoiselle.util.Beans;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(value="categoriaConverter", forClass=Estado.class)
public class CategoriaConverter implements Converter {
 
    @Inject 
    private CategoriaRepository categoriaRepository = Beans.getReference(CategoriaRepository.class);
    
    public Object getAsObject(FacesContext fc, UIComponent uic, String id) {
        if(id == null)
            return null;
        
        return categoriaRepository.get(new Integer(id)); 
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object == null)
            return "";
        
        Categoria categoria = (Categoria) object;
        return "" + categoria.getId();
    }
    
}
