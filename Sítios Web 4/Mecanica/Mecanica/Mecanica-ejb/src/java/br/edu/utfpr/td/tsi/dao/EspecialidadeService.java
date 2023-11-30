/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.utfpr.td.tsi.dao;

import br.com.mecanica.interfaces.EspecialidadeDAO;
import br.com.mecanica.modelo.Especialidade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author João Pedro
 */
@Stateless
public class EspecialidadeService implements EspecialidadeDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Especialidade atualizar(Especialidade espec) {
        return em.merge(espec);

    }

    @Override
    public Especialidade inserir(Especialidade espec) throws Exception {
        try {
            List<Especialidade> aux = em.createQuery("select e from Especialidade e where upper(e.nome) = :nome", Especialidade.class)
                    .setParameter("nome", espec.getNome().trim().toUpperCase())
                    .getResultList();

        } catch (NoResultException nre) {
        } catch (Exception ex) {
            Logger.getLogger(EspecialidadeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.persist(espec);
        return espec;
    }

    @Override
    public List<Especialidade> listar(String nome) {
        if (nome == null || nome.isBlank()) {
            return em.createQuery("select e from Especialidade e order by e.nome", Especialidade.class).getResultList();
        } else {
            return em.createQuery("select e from Especialidade e where upper(e.nome) like :nome order by e.nome",
                    Especialidade.class)
                    .setParameter("nome", nome.trim() + "%")
                    .getResultList();
        }
    }

    @Override
    public void remover(Especialidade espec) {
        espec = em.merge(espec);
        em.remove(espec);    }

    @Override
    public Especialidade buscar(Long id) {
        try {
            return em.find(Especialidade.class, id);
        } catch(Throwable t) {
            t.printStackTrace();
        }
        return null;    }

}
