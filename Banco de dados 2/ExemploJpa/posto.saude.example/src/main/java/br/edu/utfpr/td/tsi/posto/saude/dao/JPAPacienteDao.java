package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

public class JPAPacienteDao implements PacienteDao{
	
	@Autowired
	PacienteRepository pacienteRepository;

	@Override
	public void salvar(Paciente p) {
		pacienteRepository.save(p);
		
	}

	@Override
	public List<Paciente> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(String id) {
		// TODO Auto-generated method stub
		
	}

}
