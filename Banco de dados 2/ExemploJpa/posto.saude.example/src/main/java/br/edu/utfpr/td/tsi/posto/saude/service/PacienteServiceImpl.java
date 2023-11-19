package br.edu.utfpr.td.tsi.posto.saude.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.utfpr.td.tsi.posto.saude.dao.PacienteDao;
import br.edu.utfpr.td.tsi.posto.saude.dao.PacienteRepository;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

@Service
public class PacienteServiceImpl implements PacienteService {
	
	@Autowired
	private PacienteDao pacienteDao;		
		
	@Transactional
	public void cadastrar(Paciente p) {
		String idpaciente =  UUID.randomUUID().toString();
		p.setIdPaciente(idpaciente);		
		pacienteDao.salvar(p);		
	}

	@Override
	public List<Paciente> listarTodos() {
		return null;
	}

	@Override
	public void remover(String idPaciente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Paciente p) {
		// TODO Auto-generated method stub
		
	}

}
