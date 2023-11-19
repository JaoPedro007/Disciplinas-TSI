package br.edu.utfpr.td.tsi.posto.saude.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "consulta", schema = "posto-saude")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date data_consulta;
	
	@OneToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
	private Paciente paciente;
	
	@OneToOne
    @JoinColumn(name = "medico_id", referencedColumnName = "id")	
	private Medico medico;
	
	@Column(nullable = false)
	private String descricao;
	
	private Status status;
		
	
	
	public Consulta(Long id, Date data_consulta, Paciente paciente, Medico medico, String descricao, Status status) {
		this.id=id;
		this.data_consulta=data_consulta;
		this.paciente=paciente;
		this.medico=medico;
		this.descricao=descricao;
		this.status=status;
	}
	
	public Consulta() {
		
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getData_consulta() {
		return data_consulta;
	}
	public void setData_consulta(Date data_consulta) {
		this.data_consulta = data_consulta;
	}
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}


	


}
