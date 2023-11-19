package br.edu.utfpr.td.tsi.posto.saude.dao.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import br.edu.utfpr.td.tsi.posto.saude.dao.PacienteDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.repository.PacienteRepository;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;


public class JPAPacienteDao implements PacienteDAO{

	@Autowired
	PacienteRepository pacienteRepository;
	
	@Override
	public void inserir(Paciente p) {
		pacienteRepository.save(p);
	}

	@Override
	public void atualizar(Long id, Paciente p) {		
		Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);	
		
		Paciente paciente = optionalPaciente.get();
		paciente.setNome(p.getNome());
		paciente.setSobrenome(p.getSobrenome());
		paciente.setTelefone(p.getTelefone());
		paciente.setData_nascimento(p.getData_nascimento());           
        pacienteRepository.save(paciente);
		
	}

	@Override
	public void remover(Long id) {
		pacienteRepository.deleteById(id);
	}

	@Override
    public List<Paciente> listarTodos() {
        return (List<Paciente>) pacienteRepository.findAll();
    }

	@Override
	public Paciente procurar(Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            return optionalPaciente.get();
        } else {
            return null;
        }

	}

	@Override
	public boolean temConsultaAgendada(Long pacienteId) {
		return false;
	}

}
