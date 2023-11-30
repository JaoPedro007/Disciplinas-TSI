/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mecanica.beans;

import br.com.mecanica.dao.EspecialidadeDAO;
import br.com.mecanica.modelo.Especialidade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author João Pedro
 */
@Named(value = "especBean")
@SessionScoped
public class EspecialidadeBean implements Serializable{

    @EJB
    EspecialidadeDAO especialidadeDao;

    Especialidade especialidade;

    List<Especialidade> listaEspecialidades;

    @PostConstruct
    public void iniciar() {
        especialidade = new Especialidade();
        listaEspecialidades = especialidadeDao.listar();
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public List<Especialidade> getListaEspecialidades() {
        return listaEspecialidades;
    }

    public void setListaEspecialidades(List<Especialidade> listaEspecialidades) {
        this.listaEspecialidades = listaEspecialidades;
    }

    public String confirmar() {
        try {
            especialidadeDao.inserir(especialidade);
            listaEspecialidades.add(especialidade);
            especialidade = new Especialidade();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro gravando marca:" + e.getMessage()));
        }
        return null;
    }
}
