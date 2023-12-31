/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi.sw4.beans;

import br.edu.utfpr.td.tsi.sw4.modelo.Curso;
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
@Named(value = "cursoBean")
@SessionScoped
public class CursoBean implements Serializable {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    Curso curso;

    List<Curso> listaCursos;

    public CursoBean() {
        curso = new Curso();
    }

    @PostConstruct
    public void init() {
        try {
            listaCursos = em.createQuery("select c from Curso c")
                    .getResultList();
        } catch (Throwable t) {
        }
    }

    public String confirmar() {
        try {
            boolean adicionar = false;
            utx.begin();
            if (curso.getId() == null) {
                em.persist(curso);
                adicionar = true;
            } else {
                curso = em.merge(curso);
            }
            utx.commit();
            if (adicionar) {
                listaCursos.add(curso);
            }
            curso = new Curso();
        } catch (Exception ex) {
            try {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar curso", null));
                utx.rollback();
            } catch (Throwable t) {
            }
        }
        return null;
    }

    public String excluir(Curso c) {
        try {
            utx.begin();
            c = em.merge(c);
            em.remove(c);
            utx.commit();
            listaCursos.remove(c);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Curso não foi excluído",
                            "Não foi possível excluir o Curso. Deve haver Turma relacionadas."));
        }
        return null;
    }

    public List<SelectItem> getCursos() {
        LinkedList<SelectItem> cursos = new LinkedList<>();
        try {
            List<Curso> curso = em.createQuery(
                    "select p from Curso p order by p.nome")
                    .getResultList();
            for (Curso c : curso) {
                cursos.add(new SelectItem(c, c.getNome()));
            }
        } catch (Exception ex) {

        }
        return cursos;
    }

    public String iniciarEdicao(Curso c) {
        curso = c;
        return null;
    }

    public List<Curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
