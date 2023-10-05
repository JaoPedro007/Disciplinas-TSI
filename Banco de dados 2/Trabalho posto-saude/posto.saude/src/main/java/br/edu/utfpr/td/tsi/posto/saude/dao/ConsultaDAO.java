package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.util.List;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Consulta;

public interface ConsultaDAO {

	
	public Long inserir(Consulta c);

	public void atualizar(Long id, Consulta c);

	public void remover(Long id);

	public List<Consulta> listarTodas();
	
	public Consulta procurar(Long id);
}
