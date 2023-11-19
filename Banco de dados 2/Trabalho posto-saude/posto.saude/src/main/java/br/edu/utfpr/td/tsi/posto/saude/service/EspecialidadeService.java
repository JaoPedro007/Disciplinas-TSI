package br.edu.utfpr.td.tsi.posto.saude.service;

import java.util.List;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Especialidade;

public interface EspecialidadeService {
	
	public void inserir(Especialidade especialidade);

	public void atualizar(Long id, Especialidade especialidade);

	public void remover(Long id);

	public List<Especialidade> listarTodas();
	
	public Especialidade procurar(Long id);

}
