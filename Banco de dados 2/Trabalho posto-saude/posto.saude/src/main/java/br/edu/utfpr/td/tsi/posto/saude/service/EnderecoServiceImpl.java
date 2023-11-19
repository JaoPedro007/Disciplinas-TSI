package br.edu.utfpr.td.tsi.posto.saude.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.posto.saude.dao.jpa.JPAEnderecoDao;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Endereco;

@Service
public class EnderecoServiceImpl implements EnderecoService{

	
	@Autowired
	JPAEnderecoDao jpaEnderecoDao;
	
	
	@Override
	public List<Endereco> listarTodos() {
		return jpaEnderecoDao.listarTodos();
	}

	@Override
	public void inserir(Endereco endereco) {
		jpaEnderecoDao.inserir(endereco);
		
	}

	@Override
	public void atualizar(Long id, Endereco endereco) {
		jpaEnderecoDao.atualizar(id, endereco);
		
	}

	@Override
	public void remover(Long idEndereco) {
		jpaEnderecoDao.remover(idEndereco);
		
	}

	@Override
	public Endereco procurar(Long idEndereco) {
		return jpaEnderecoDao.procurar(idEndereco);
	}
	

}
