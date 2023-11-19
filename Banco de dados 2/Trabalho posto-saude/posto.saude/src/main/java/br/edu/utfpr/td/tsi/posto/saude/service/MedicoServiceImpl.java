package br.edu.utfpr.td.tsi.posto.saude.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.posto.saude.dao.jpa.JPAMedicoDao;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;

@Service
public class MedicoServiceImpl implements MedicoService{

	@Autowired
	JPAMedicoDao jpaMedicoDao;
	
	@Override
	public void inserir(Medico m) {
		jpaMedicoDao.inserir(m);
	}

	@Override
	public void atualizar(Long id, Medico m) {
		jpaMedicoDao.atualizar(id, m);
		
	}

	@Override
	public void remover(Long id) {
		jpaMedicoDao.remover(id);
		
	}

	@Override
	public List<Medico> listarTodos() {
		return jpaMedicoDao.listarTodos();
	}

	@Override
	public Medico procurar(Long id) {
		return jpaMedicoDao.procurar(id);
	}

}
