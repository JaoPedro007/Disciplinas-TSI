package br.edu.utfpr.td.tsi.posto.saude.service;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.posto.saude.dao.jpa.JPAPacienteDao;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	JPAPacienteDao jpaPacienteDao;

	@Override
	public void inserir(Paciente p) {
		jpaPacienteDao.inserir(p);
	}

	@Override
	public void atualizar(Long id, Paciente p) {
		jpaPacienteDao.atualizar(id, p);

	}

	@Override
	public void remover(Long id) {
		jpaPacienteDao.remover(id);
	}

	@Override
	public List<Paciente> listarTodos() {		
		return jpaPacienteDao.listarTodos();
	}

	@Override
	public Paciente procurar(Long id) {
		return jpaPacienteDao.procurar(id);
	}
	
	@Override
	public boolean dataCorreta(Paciente p) {		
		if(p.getData_nascimento().isAfter(LocalDate.now())) {
			return false;
		}else {
			return true;
		}			
		
	}
	

}
