package br.edu.utfpr.td.tsi.posto.saude.dao;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

public interface PacienteDao {
	
	public void salvar(Paciente p);
	
	public java.util.List<Paciente> listarTodos();
	
	public void remover(String id);

}
