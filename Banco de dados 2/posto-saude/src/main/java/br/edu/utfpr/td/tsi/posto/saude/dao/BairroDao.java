package br.edu.utfpr.td.tsi.posto.saude.dao;

import java.util.List;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;

public interface BairroDao {
	
	public void cadastrar(Bairro bairro);
	public void remover(long id);
	public void alterar(long id, Bairro bairro);
	public List<Bairro> listar();

}
