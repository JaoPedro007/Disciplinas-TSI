/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi.sw4.beans;

import br.edu.utfpr.td.tsi.sw4.modelo.Aluno;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
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
@Named(value = "alunoBean")
@SessionScoped
public class AlunoBean implements Serializable {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction utx;

    Aluno aluno;

    private int idade;

    List<Aluno> listaAlunos;

    private boolean menor;

    public AlunoBean() {
        aluno = new Aluno();
    }

    @PostConstruct
    public void init() {
        try {
            listaAlunos = em.createQuery("select a from Aluno a")
                    .getResultList();
        } catch (Throwable t) {
        }
    }

    public String confirmar() {
        try {
            boolean adicionar = false;
            utx.begin();
            if (aluno.getId() == null) {

                Aluno alunoExiste = em.createQuery("SELECT a FROM Aluno a WHERE a.cpf = :cpf", Aluno.class)
                        .setParameter("cpf", aluno.getCpf())
                        .setMaxResults(1)
                        .getResultStream().findFirst().orElse(null);

                if (alunoExiste != null) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "CPF já cadastrado", null));
                    return null;
                }
                if (!validarAluno(aluno)) {
                    return null;
                } else {
                    em.persist(aluno);
                    utx.commit();
                    adicionar = true;
                }
            } else {
                if (!validarAluno(aluno)) {
                    return null;

                } else {
                    aluno = em.merge(aluno);
                    utx.commit();
                }
            }
            if (adicionar) {
                listaAlunos.add(aluno);
            }
            aluno = new Aluno();
        } catch (Exception ex) {
            try {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar aluno", null));
                utx.rollback();
            } catch (Throwable t) {
            }
        }
        return null;
    }

    public String excluir(Aluno a) {
        try {
            utx.begin();
            a = em.merge(a);
            em.remove(a);
            utx.commit();
            listaAlunos.remove(a);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Aluno não foi excluído",
                            "Não foi possível excluir o Aluno."));
        }
        return null;
    }

    private boolean validarAluno(Aluno aluno) {
        verificaMenor();

        if (menor && (aluno.getNomeResponsavel() == null || aluno.getNomeResponsavel().isEmpty())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Nome do responsável é obrigatório para alunos menores de 16 anos", null));
            return false;
        }

        if (idade >= 16 && (aluno.getNomeResponsavel() != null && !aluno.getNomeResponsavel().isEmpty())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alunos com 16 anos ou mais não podem ter um responsável associado", null));
            return false;
        }

        return true;
    }

    public void verificaMenor() {
        if (aluno != null && aluno.getDataNascimento() != null) {
            LocalDate dataNascimento = aluno.getDataNascimento();
            LocalDate dataAtual = LocalDate.now();
            Period periodo = Period.between(dataNascimento, dataAtual);
            idade = periodo.getYears();
            menor = idade < 16;
        }
    }

    public List<SelectItem> getAlunos() {
        LinkedList<SelectItem> alunos = new LinkedList<>();
        try {
            List<Aluno> aluno = em.createQuery(
                    "select a from Aluno a order by a.nome")
                    .getResultList();
            for (Aluno a : aluno) {
                alunos.add(new SelectItem(a, a.getNome()));
            }
        } catch (Exception ex) {

        }
        return alunos;
    }

    public String iniciarEdicao(Aluno a) {
        aluno = a;
        verificaMenor();
        return null;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(List<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public boolean isMenor() {
        return menor;
    }

    public void setMenor(boolean menor) {
        this.menor = menor;
    }
}
