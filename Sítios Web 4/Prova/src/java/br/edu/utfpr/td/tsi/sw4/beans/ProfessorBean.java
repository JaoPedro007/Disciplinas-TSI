/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi.sw4.beans;

import br.edu.utfpr.td.tsi.sw4.modelo.Professor;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 *
 * @author João Pedro
 */
@Named(value = "professorBean")
@SessionScoped
public class ProfessorBean implements Serializable {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    Professor professor;

    List<Professor> listaProfessores;

    public ProfessorBean() {
        professor = new Professor();
    }

    @PostConstruct
    public void init() {
        try {
            listaProfessores = em.createQuery("select p from Professor p")
                    .getResultList();
        } catch (Throwable t) {
        }
    }

    public String confirmar() {
        try {
            boolean adicionar = false;
            utx.begin();
            if (professor.getId() == null) {

                Professor professorExiste = em.createQuery("SELECT p FROM Professor p WHERE p.cpf = :cpf", Professor.class)
                        .setParameter("cpf", professor.getCpf())
                        .setMaxResults(1)
                        .getResultStream().findFirst().orElse(null);

                if (professorExiste != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CPF já cadastrado", null));
                    return null;
                }
                em.persist(professor);
                adicionar = true;
            } else {
                professor = em.merge(professor);
            }
            utx.commit();
            if (adicionar) {
                listaProfessores.add(professor);
            }
            professor = new Professor();
        } catch (Exception ex) {
            try {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar professor", null));
                utx.rollback();
            } catch (Throwable t) {
            }
        }
        return null;
    }

    public String excluir(Professor p) {
        try {
            utx.begin();
            p = em.merge(p);
            em.remove(p);
            utx.commit();
            listaProfessores.remove(p);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Professor não foi excluído",
                            "Não foi possível excluir o Professor."));
        }
        return null;
    }

    public List<SelectItem> getProfessores() {
        LinkedList<SelectItem> professores = new LinkedList<>();
        try {
            List<Professor> prof = em.createQuery(
                    "select p from Professor p order by p.nome")
                    .getResultList();
            for (Professor p : prof) {
                professores.add(new SelectItem(p, p.getNome()));
            }
        } catch (Exception ex) {

        }
        return professores;
    }

    public String iniciarEdicao(Professor p) {
        professor = p;
        return null;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Professor> getListaProfessores() {
        return listaProfessores;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setListaProfessores(List<Professor> listaProfessores) {
        this.listaProfessores = listaProfessores;
    }
}
