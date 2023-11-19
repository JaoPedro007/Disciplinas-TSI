package br.edu.utfpr.td.tsi.posto.saude.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.posto.saude.dao.jpa.JPABairroDao;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;

@Service
public class BairroServiceImpl implements BairroService{

	@Autowired
	JPABairroDao jpaBairroDao;

	@Override
	public void inserir(Bairro bairro) {
		jpaBairroDao.inserir(bairro);
		
	}

	@Override
	public void atualizar(Long id, Bairro bairro) {
		jpaBairroDao.atualizar(id, bairro);
		
	}

	@Override
	public void remover(Long id) {
		jpaBairroDao.remover(id);
		
	}

	@Override
	public List<Bairro> listarTodos() {
		return jpaBairroDao.listarTodos();
	}

	@Override
	public Bairro procurar(Long id) {
		return jpaBairroDao.procurar(id);
	}



}
