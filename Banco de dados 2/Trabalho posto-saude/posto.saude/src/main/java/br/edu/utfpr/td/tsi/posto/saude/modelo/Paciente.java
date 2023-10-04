package br.edu.utfpr.td.tsi.posto.saude.modelo;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


public class Paciente {
	private Long id;
	private String nome;
	private String sobrenome;
	private String telefone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data_nascimento;
    

	private Endereco endereco;

	public Paciente() {
	}

	public Paciente(Long id, String nome, String sobrenome, String telefone, LocalDate data_nascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.data_nascimento = data_nascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

}
