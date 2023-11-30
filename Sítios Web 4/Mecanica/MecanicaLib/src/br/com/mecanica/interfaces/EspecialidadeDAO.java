/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mecanica.interfaces;

import br.com.mecanica.modelo.Especialidade;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author João Pedro
 */
@Remote
public interface EspecialidadeDAO {

    Especialidade atualizar(Especialidade espec);

    Especialidade inserir(Especialidade espec) throws Exception;

    List<Especialidade> listar(String nome);

    void remover(Especialidade espec);

    Especialidade buscar(Long id);

}
