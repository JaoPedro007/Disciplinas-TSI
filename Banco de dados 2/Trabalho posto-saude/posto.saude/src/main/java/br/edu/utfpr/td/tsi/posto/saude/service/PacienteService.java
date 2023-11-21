package br.edu.utfpr.td.tsi.posto.saude.service;

import java.util.List;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

public interface PacienteService {

	
	public void inserir(Paciente p);

	public void atualizar(Long id, Paciente p);

	public void remover(Long id);

	public List<Paciente> listarTodos();
	
	public Paciente procurar(Long id);
	
	public boolean dataCorreta(Paciente p);
	
}
