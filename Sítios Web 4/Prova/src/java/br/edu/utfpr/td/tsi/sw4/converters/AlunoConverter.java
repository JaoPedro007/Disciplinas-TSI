package br.edu.utfpr.td.tsi.sw4.converters;

import br.edu.utfpr.td.tsi.sw4.modelo.Aluno;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named(value = "alunoConverter")
@ApplicationScoped
public class AlunoConverter implements Converter<Aluno> {

    @PersistenceContext
    EntityManager em;

    @Override
    public Aluno getAsObject(FacesContext context, 
            UIComponent component, String value) {
        try {
            Long id = Long.valueOf(value);
            return em.find( Aluno.class, id);
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, 
            UIComponent component, Aluno value) {
         if (value == null || value.getId() == null) {
             return null;
         }
         return String.valueOf( value.getId() );
    }
    
}
