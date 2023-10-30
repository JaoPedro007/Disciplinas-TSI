package br.edu.utfpr.td.tsi.sw4.beans;

import br.edu.utfpr.td.tsi.sw4.modelo.Aluno;
import br.edu.utfpr.td.tsi.sw4.modelo.Matricula;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Named(value = "matriculaBean")
@SessionScoped
public class MatriculaBean implements Serializable {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    Matricula matricula;
    Turma turmaAtual;
    Aluno alunoAtual;

    private boolean editando = false;

    public MatriculaBean() {
        matricula = new Matricula();
    }

    public String confirmar() {
        try {
            utx.begin();
            turmaAtual = em.find(Turma.class, turmaAtual.getId());

            // Essa é uma verificar para ver se a data de início já passou
            LocalDate dataAtual = LocalDate.now();
            if (dataAtual.isAfter(turmaAtual.getDataInicio())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "A data de início da turma já passou.", null));
                return null;
            }

            // Verificar se tem vagas na turma disponíveis
            if (turmaAtual.getVagas() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Turma sem vagas disponíveis.", null));
                return null;
            }

            matricula.setAluno(alunoAtual);
            matricula.setTurma(turmaAtual);

            // Como não foi especificado como a data de pagamento seria preenchida, estou usando a data de início da turma
            matricula.setDataPagamento(turmaAtual.getDataInicio());
            matricula.setData(LocalDate.now());

            if (matricula.getId() == null) {
                em.persist(matricula);
                turmaAtual.setVagas(turmaAtual.getVagas() - 1);
            } else {
                matricula = em.merge(matricula);
            }

            utx.commit();
            editando = false;
            matricula = new Matricula();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Matricula> getListaMatriculas() {
        try {
            if (turmaAtual == null && alunoAtual == null) {
                return em.createQuery(
                        "select m from Matricula m order by m.id")
                        .getResultList();
            } else {
                return em.createQuery(
                        "select m from Matricula m where m.aluno = :aluno or m.turma = :turma"
                        + " order by m.id")
                        .setParameter("aluno", alunoAtual).setParameter("turma", turmaAtual)
                        .getResultList();
            }
        } catch (Throwable t) {
            t.printStackTrace();
            return new LinkedList<Matricula>();
        }
    }

    public String cancelar(Matricula m) {
        try {
            utx.begin();

            // Essa é a validação que não permite o cancelamento da matricula caso a data de inicio da turma já tenha passado
            LocalDate dataAtual = LocalDate.now();
            if (dataAtual.isAfter(m.getTurma().getDataInicio())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não é possível excluir a matrícula, a data de início da turma já passou.", null));
                return null;
            }
            //turmaAtual.setVagas(turmaAtual.getVagas() + 1);
            m = em.merge(m);
            em.remove(m);
            utx.commit();

        } catch (Throwable e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Matrícula não excluída",
                            "Não foi possível excluir a matrícula."));
        }
        return null;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public String editar(Matricula m) {
        matricula = m;
        editando = true;
        return null;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Turma getTurmaAtual() {
        return turmaAtual;
    }

    public void setTurmaAtual(Turma turmaAtual) {
        this.turmaAtual = turmaAtual;
    }

    public Aluno getAlunoAtual() {
        return alunoAtual;
    }

    public void setAlunoAtual(Aluno alunoAtual) {
        this.alunoAtual = alunoAtual;
    }

}
