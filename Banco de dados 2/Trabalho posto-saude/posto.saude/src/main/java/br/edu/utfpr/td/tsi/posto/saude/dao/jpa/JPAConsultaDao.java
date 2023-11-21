package br.edu.utfpr.td.tsi.posto.saude.dao.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.utfpr.td.tsi.posto.saude.dao.ConsultaDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.repository.ConsultaRepository;
import br.edu.utfpr.td.tsi.posto.saude.dao.repository.MedicoRepository;
import br.edu.utfpr.td.tsi.posto.saude.dao.repository.PacienteRepository;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Consulta;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Status;

public class JPAConsultaDao implements ConsultaDAO {
	
	@Autowired
	ConsultaRepository consultaRepository;
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Autowired
	MedicoRepository medicoRepository;

	@Override
	public void inserir(Consulta c) {
		c.setStatus(Status.AGENDADA);
		consultaRepository.save(c);
	}

	@Override
	public void atualizar(Long id, Consulta c) {
		Optional<Consulta> optionalConsulta = consultaRepository.findById(id);	
		
		Consulta consulta = optionalConsulta.get();
		consulta.setData_consulta(c.getData_consulta());
		consulta.setDescricao(c.getDescricao());
		consulta.setMedico(c.getMedico());
		consulta.setPaciente(c.getPaciente());
		consulta.setStatus(c.getStatus());
		
		
		
		consultaRepository.save(consulta);
		
	}

	@Override
	public void editarConsulta(Long id, Consulta c) {
		consultaRepository.save(c);
		
	}

	@Override
	public void remover(Long id) {
		consultaRepository.deleteById(id);
		
	}

	@Override
	public List<Consulta> listarTodas() {
		return (List<Consulta>) consultaRepository.findAll();
	}

	@Override
	public Consulta procurar(Long id) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);
        if (optionalConsulta.isPresent()) {
            return optionalConsulta.get();
        } else {
            return null;
        }

	}

	@Override
	public boolean temConsultaAgendada(Long pacienteId) {
        int count = consultaRepository.countConsultasAgendadas(pacienteId, Status.AGENDADA);
        return count > 0;
	}


}
