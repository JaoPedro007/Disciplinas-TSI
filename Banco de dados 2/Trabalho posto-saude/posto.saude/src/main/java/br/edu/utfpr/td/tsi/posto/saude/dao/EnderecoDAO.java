package br.edu.utfpr.td.tsi.posto.saude.dao;


import java.util.List;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Endereco;

public interface EnderecoDAO {
	
	public List<Endereco> listarTodos();

	public void inserir(Endereco endereco);

	public void atualizar(Long id, Endereco endereco);

	public void remover(Long idEndereco);
		
	public Endereco procurar(Long idEndereco);

}
