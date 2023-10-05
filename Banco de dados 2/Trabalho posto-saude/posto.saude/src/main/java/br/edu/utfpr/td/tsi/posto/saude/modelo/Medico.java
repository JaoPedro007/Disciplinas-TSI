package br.edu.utfpr.td.tsi.posto.saude.modelo;

public class Medico {

	private Long id;
	private String nome;
	private String sobrenome;
	private String crm;
	private String telefone;
	private String cpf;
	private Especialidade especialidade;

	public Medico(Long id, String nome, String sobrenome, String crm, String telefone, String cpf, Especialidade especialidade) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.crm = crm;
		this.telefone = telefone;
		this.cpf = cpf;
		this.especialidade = especialidade;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

}
