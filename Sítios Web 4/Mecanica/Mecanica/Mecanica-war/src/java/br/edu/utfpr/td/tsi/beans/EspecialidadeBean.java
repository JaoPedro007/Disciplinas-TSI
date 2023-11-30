/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi.beans;

import br.com.mecanica.interfaces.EspecialidadeDAO;
import br.com.mecanica.modelo.Especialidade;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 *
 * @author João Pedro
 */
@Named("especBean")
@SessionScoped
public class EspecialidadeBean implements Serializable {

    Especialidade espec;

    @EJB(lookup = "java:module/env/br.edu.utfpr.td.tsi/EspecialidadeBean/especService")
    EspecialidadeDAO especService;

    List<Especialidade> listaEspecs;

    public EspecialidadeBean() {
        espec = new Especialidade();
    }

    @PostConstruct
    public void init() {
        try {
            espec = new Especialidade();
            listaEspecs = especService.listar(null);
        } catch (Throwable t) {
        }
    }

    public String confirmar() {
        try {
            boolean adicionar = false;
            if (espec.getId() == null) {
                espec = especService.inserir(espec);
                adicionar = true;
            } else {
                espec = especService.atualizar(espec);
            }
            if (adicionar) {
                listaEspecs.add(espec);
            }
            espec = new Especialidade();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Especialidade já existe",
                            "Não foi possível adicionar a Especialidade, já existe outra com este nome."));
        }
        return null;
    }

    public String remover(Especialidade d) {
        try {
            especService.remover(d);
            listaEspecs.remove(d);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Departamento não exluido",
                            "Não foi possível excluir a Especialidade. Deve haver mecânicos relacionados."));
        }
        return null;
    }

    public String iniciarEdicao(Especialidade e) {
        espec = e;
        return null;
    }

    public List<SelectItem> getItensDepartamento() {
        LinkedList<SelectItem> items = new LinkedList<>();
        items.add(new SelectItem(null, "Selecione..."));
        try {
            for (Especialidade e : especService.listar(null)) {
                items.add(new SelectItem(e, e.getNome()));
            }
        } catch (Exception ex) {

        }
        return items;
    }

    public Especialidade getEspec() {
        return espec;
    }

    public void setEspec(Especialidade espec) {
        this.espec = espec;
    }

    public EspecialidadeDAO getEspecService() {
        return especService;
    }

    public void setEspecService(EspecialidadeDAO especService) {
        this.especService = especService;
    }

    public List<Especialidade> getListaEspecs() {
        return listaEspecs;
    }

    public void setListaEspecs(List<Especialidade> listaEspecs) {
        this.listaEspecs = listaEspecs;
    }

}
