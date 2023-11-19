package br.edu.utfpr.td.tsi.posto.saude.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.posto.saude.dao.jpa.JPAEspecialidadeDao;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Especialidade;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService{

	@Autowired
	JPAEspecialidadeDao jpaEspecialidadeDao;
	
	
	@Override
	public void inserir(Especialidade especialidade) {
		jpaEspecialidadeDao.inserir(especialidade);
		
	}

	@Override
	public void atualizar(Long id, Especialidade especialidade) {
		jpaEspecialidadeDao.atualizar(id, especialidade);
		
	}

	@Override
	public void remover(Long id) {
		jpaEspecialidadeDao.remover(id);
		
	}

	@Override
	public List<Especialidade> listarTodas() {
		return jpaEspecialidadeDao.listarTodas();
	}

	@Override
	public Especialidade procurar(Long id) {
		return jpaEspecialidadeDao.procurar(id);
	}
	
	

}
