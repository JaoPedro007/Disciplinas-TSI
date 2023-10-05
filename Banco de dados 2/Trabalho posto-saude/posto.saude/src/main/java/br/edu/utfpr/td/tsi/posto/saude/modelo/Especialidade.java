package br.edu.utfpr.td.tsi.posto.saude.modelo;


public class Especialidade {
	
	private Long id;
	private String descricao;
	
	public Especialidade() {
		
	}

	public Especialidade(Long id, String descricao) {
		this.id=id;
		this.descricao=descricao;
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
