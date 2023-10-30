package br.edu.utfpr.td.tsi.sw4.beans;

import br.edu.utfpr.td.tsi.sw4.modelo.Curso;
import br.edu.utfpr.td.tsi.sw4.modelo.Professor;
import br.edu.utfpr.td.tsi.sw4.modelo.Turma;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Named(value = "turmaBean")
@SessionScoped
public class TurmaBean implements Serializable {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    Turma turma;
    Professor professorAtual;
    Curso cursoAtual;

    private boolean editando = false;

    public TurmaBean() {
        turma = new Turma();
    }

    public String confirmar() {
        try {
            utx.begin();
            
            if (turma.getDataInicio().isAfter(turma.getDataFim())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "A data de início não pode ser após a data de término.", null));
                return null;
            }

            LocalDate dataAtual = LocalDate.now();
            if (turma.getDataInicio().isBefore(dataAtual)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "A data de início não pode ser anterior à data atual.", null));
                return null;
            }
            if (turma.getId() == null) {
                Turma turmaExiste = em.createQuery("SELECT t FROM Turma t WHERE t.descricao = :descricao", Turma.class)
                        .setParameter("descricao", turma.getDescricao())
                        .setMaxResults(1)
                        .getResultStream().findFirst().orElse(null);

                if (turmaExiste != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Turma já existe", null));
                    return null;
                }
            }
            Curso curso = em.find(Curso.class, cursoAtual.getId());
            Professor professor = em.find(Professor.class, professorAtual.getId());
            turma.setProfessor(professorAtual);
            turma.setCurso(cursoAtual);

            double cargaHoraria = curso.getCargaHoraria();
            double valorHoraProfessor = professor.getValorHora();
            int vagas = turma.getVagas();

            double valor = (cargaHoraria * (100.0 + valorHoraProfessor) * 2.5) / vagas;
            turma.setValor(valor);
            if (turma.getId() == null) {
                em.persist(turma);
            } else {
                turma = em.merge(turma);
            }
            utx.commit();
            editando = false;
            turma = new Turma();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Turma> getListaTurmas() {
        try {
            if (professorAtual == null) {
                return em.createQuery(
                        "select p from Turma p order by p.id")
                        .getResultList();
            } else {
                return em.createQuery(
                        "select p from Turma p where p.professor = :prof or p.curso = :curso"
                        + " order by p.id")
                        .setParameter("prof", professorAtual).setParameter("curso", cursoAtual)
                        .getResultList();
            }
        } catch (Throwable t) {
            t.printStackTrace();
            return new LinkedList<Turma>();
        }
    }

    public String excluir(Turma t) {
        try {
            utx.begin();
            t = em.merge(t);
            em.remove(t);
            utx.commit();
        } catch (Throwable e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Turma não excluída",
                            "Não foi possível excluir a turma."));
        }
        return null;
    }

    public List<SelectItem> getTurmas() {
        LinkedList<SelectItem> turmas = new LinkedList<>();
        try {
            List<Turma> turma = em.createQuery(
                    "select t from Turma t order by t.descricao")
                    .getResultList();
            for (Turma t : turma) {
                turmas.add(new SelectItem(t, t.getDescricao()));
            }
        } catch (Exception ex) {

        }
        return turmas;
    }

    public Curso getCursoAtual() {
        return cursoAtual;
    }

    public void setCursoAtual(Curso cursoAtual) {
        this.cursoAtual = cursoAtual;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Professor getProfessorAtual() {
        return professorAtual;
    }

    public void setProfessorAtual(Professor professorAtual) {
        this.professorAtual = professorAtual;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public String editar(Turma t) {
        turma = t;
        editando = true;
        return null;
    }

}
