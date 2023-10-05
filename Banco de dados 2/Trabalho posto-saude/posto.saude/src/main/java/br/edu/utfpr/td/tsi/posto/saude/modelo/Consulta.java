package br.edu.utfpr.td.tsi.posto.saude.modelo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



public class Consulta {
	
	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date data_consulta;
	private Paciente paciente_id;
	private Medico medico_id;
	private String descricao;
	private Status status;
	
	
	
	
	public Consulta(Long id, Date data_consulta, Paciente paciente, Medico medico, String descricao, Status status) {
		this.id=id;
		this.data_consulta=data_consulta;
		this.paciente_id=paciente;
		this.medico_id=medico;
		this.descricao=descricao;
		this.status=status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Paciente getPaciente_id() {
		return paciente_id;
	}
	public void setPaciente_id(Paciente paciente_id) {
		this.paciente_id = paciente_id;
	}
	public Medico getMedico_id() {
		return medico_id;
	}
	public void setMedico_id(Medico medico_id) {
		this.medico_id = medico_id;
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
	


	


}
