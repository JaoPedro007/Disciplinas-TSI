package br.edu.utfpr.td.tsi.sw4.converters;

import br.edu.utfpr.td.tsi.sw4.modelo.Professor;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "profConverter")
@ApplicationScoped
public class ProfessorConverter implements Converter<Professor> {

    @PersistenceContext
    EntityManager em;

    @Override
    public Professor getAsObject(FacesContext context, 
            UIComponent component, String value) {
        try {
            Long id = Long.valueOf(value);
            return em.find( Professor.class, id);
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, 
            UIComponent component, Professor value) {
         if (value == null || value.getId() == null) {
             return null;
         }
         return String.valueOf( value.getId() );
    }
    
}
