package br.edu.utfpr.td.tsi.posto.saude.dao.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.utfpr.td.tsi.posto.saude.dao.EspecialidadeDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.repository.EspecialidadeRepository;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Especialidade;

public class JPAEspecialidadeDao implements EspecialidadeDAO {

	@Autowired
	EspecialidadeRepository especialidadeRepository;

	@Override
	public void inserir(Especialidade e) {
		especialidadeRepository.save(e);
	}

	@Override
	public void atualizar(Long id, Especialidade e) {
		Optional<Especialidade> optionalEspecialidade = especialidadeRepository.findById(id);
		
		Especialidade especialidade = optionalEspecialidade.get();
		especialidade.setDescricao(e.getDescricao());
		especialidadeRepository.save(especialidade);

	}

	@Override
	public void remover(Long id) {
		especialidadeRepository.deleteById(id);
		
	}

	@Override
	public List<Especialidade> listarTodas() {
		return (List<Especialidade>) especialidadeRepository.findAll();
	}

	@Override
	public Especialidade procurar(Long id) {
		Optional<Especialidade> optionalEspecialidade = especialidadeRepository.findById(id);
		if (optionalEspecialidade.isPresent()) {
			return optionalEspecialidade.get();
		} else {
			return null;
		}
	}

}
