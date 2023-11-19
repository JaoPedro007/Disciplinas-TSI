package br.edu.utfpr.td.tsi.posto.saude.dao.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.utfpr.td.tsi.posto.saude.dao.MedicoDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.repository.EspecialidadeRepository;
import br.edu.utfpr.td.tsi.posto.saude.dao.repository.MedicoRepository;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Especialidade;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;

public class JPAMedicoDao implements MedicoDAO{

	@Autowired
	MedicoRepository medicoRepository;
	
	@Autowired
	EspecialidadeRepository especialidadeRepository;
	
	@Override
	public void inserir(Medico m) {
	    Especialidade especialidadePersistente = especialidadeRepository.findById(m.getEspecialidade().getId()).orElse(null);
	    
	    if (especialidadePersistente !=null) {
	        m.setEspecialidade(especialidadePersistente);
	    }
		
		medicoRepository.save(m);
	}

	@Override
	public void atualizar(Long id, Medico m) {
		Optional<Medico> optionalMedico = medicoRepository.findById(id);
		
		Medico medico = optionalMedico.get();
		medico.setCpf(m.getCpf());
		medico.setCrm(m.getCrm());
		medico.setEspecialidade(m.getEspecialidade());
		medico.setNome(m.getNome());
		medico.setSobrenome(m.getSobrenome());
		medico.setTelefone(m.getTelefone());
		
		medicoRepository.save(medico);		
	}

	@Override
	public void remover(Long id) {
		medicoRepository.deleteById(id);
		
	}

	@Override
	public List<Medico> listarTodos() {
		return (List<Medico>) medicoRepository.findAll();
	}

	@Override
	public Medico procurar(Long id) {
		Optional<Medico> optionalMedico = medicoRepository.findById(id);
		if (optionalMedico.isPresent()) {
			return optionalMedico.get();
		} else {
			return null;
		}
	}

}
