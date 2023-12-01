/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mecanica.dao;

import br.com.mecanica.modelo.Especialidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author João Pedro
 */
@Stateless
public class EspecialidadeDAO {
    @PersistenceContext
    EntityManager em;
    
    public void inserir(Especialidade especialidade){
        em.persist(especialidade);
    }
    public void alterar(Especialidade especialidade){
        em.merge(especialidade);
    }
    public void remover(Especialidade especialidade){
        em.remove(especialidade);
    }
    public List<Especialidade> listar(){
        return em.createQuery("select e from Especialidade e order by e.nome").getResultList();
    }
    public Especialidade buscar(Long id){
        return em.find(Especialidade.class, id);
    }
    
}
