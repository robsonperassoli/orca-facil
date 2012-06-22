package br.com.orcafacil.server.view.converter;

import br.com.orcafacil.server.domain.Estado;
import br.com.orcafacil.server.domain.Marca;
import br.com.orcafacil.server.repository.MarcaRepository;
import br.gov.frameworkdemoiselle.util.Beans;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(value="marcaConverter", forClass=Estado.class)
public class MarcaConverter implements Converter {
 
    @Inject 
    private MarcaRepository marcaRepository = Beans.getReference(MarcaRepository.class);
    
    public Object getAsObject(FacesContext fc, UIComponent uic, String id) {
        if(id == null)
            return null;
        
        return marcaRepository.get(new Integer(id)); 
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object == null)
            return "";
        
        Marca marca = (Marca) object;
        return "" + marca.getId();
    }
    
}
