package br.edu.utfpr.td.tsi.posto.saude.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco", schema = "posto-saude")
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false)
	private String numero;
	
	@Column(nullable = false)
	private String cep;
	
	@Column(nullable = false)
	private String complemento;
	
	
	@ManyToOne
    @JoinColumn(name = "bairro_id", referencedColumnName = "id")	
	private Bairro bairro;
	
	@OneToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")	
	private Paciente paciente;
	

	public Endereco(Long id, String logradouro, String numero, String cep, String complemento, Paciente paciente, Bairro bairro) {
		this.id=id;
		this.logradouro=logradouro;
		this.numero=numero;
		this.cep=cep;
		this.complemento=complemento;
		this.paciente=paciente;
		this.bairro=bairro;
	}

	public Endereco() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}



}
