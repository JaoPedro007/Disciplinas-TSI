package br.edu.utfpr.td.tsi.posto.saude.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.td.tsi.posto.saude.dao.jpa.JPAConsultaDao;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Consulta;

@Service
public class ConsultaServiceImpl implements ConsultaService{

	@Autowired
	JPAConsultaDao jpaConsultaDao;
	
	@Override
	public void inserir(Consulta c) {
		jpaConsultaDao.inserir(c);
	}

	@Override
	public void atualizar(Long id, Consulta c) {
		jpaConsultaDao.atualizar(id, c);
		
	}

	@Override
	public void editarConsulta(Long id, Consulta c) {
		jpaConsultaDao.editarConsulta(id, c);
		
	}

	@Override
	public void remover(Long id) {
		jpaConsultaDao.remover(id);
	}

	@Override
	public List<Consulta> listarTodas() {
		return jpaConsultaDao.listarTodas();
	}

	@Override
	public Consulta procurar(Long id) {
		return jpaConsultaDao.procurar(id);
	}

}
