package br.edu.utfpr.td.tsi.posto.saude.dao;


import br.edu.utfpr.td.tsi.posto.saude.modelo.Endereco;

public interface EnderecoDAO {

	public void inserir(Endereco endereco, Long idPaciente);

	public void atualizar(Long idPaciente, Endereco endereco);

	public void remover(Long idPaciente);
		
	public Endereco procurar(Long idPaciente);

}
