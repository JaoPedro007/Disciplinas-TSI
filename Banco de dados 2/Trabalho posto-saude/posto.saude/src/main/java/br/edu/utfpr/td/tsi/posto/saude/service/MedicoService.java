package br.edu.utfpr.td.tsi.posto.saude.service;

import java.util.List;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;

public interface MedicoService {
	public void inserir(Medico m);

	public void atualizar(Long id, Medico m);

	public void remover(Long id);

	public List<Medico> listarTodos();

	public Medico procurar(Long id);

}
