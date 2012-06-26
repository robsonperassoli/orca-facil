package br.com.orcafacil.server.view.converter;

import br.com.orcafacil.server.domain.Estado;
import br.com.orcafacil.server.repository.EstadoRepository;
import br.gov.frameworkdemoiselle.util.Beans;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter(value="estadoConverter", forClass=Estado.class)
public class EstadoConverter implements Converter {
 
    @Inject 
    private EstadoRepository estadoRepository = Beans.getReference(EstadoRepository.class);
    
    public Object getAsObject(FacesContext fc, UIComponent uic, String uf) {
        if(uf == null)
            return null;
        
        return estadoRepository.get(uf); 
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object == null)
            return "";
        
        Estado estado = (Estado) object;
        return estado.getUf();
    }
    
}
