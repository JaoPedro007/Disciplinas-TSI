package br.edu.utfpr.td.tsi.posto.saude.dao.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.utfpr.td.tsi.posto.saude.dao.BairroDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.repository.BairroRepository;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;

public class JPABairroDao implements BairroDAO{
	
	@Autowired
	BairroRepository bairroRepository;

	@Override
	public void inserir(Bairro bairro) {
		bairroRepository.save(bairro);
		
	}

	@Override
	public void atualizar(Long id, Bairro b) {
		Optional<Bairro> optionalBairro = bairroRepository.findById(id);	
		
		Bairro bairro = optionalBairro.get();
		bairro.setNome(b.getNome());
           
        bairroRepository.save(bairro);
		
	}

	@Override
	public void remover(Long id) {
		bairroRepository.deleteById(id);
		
	}

	@Override
	public List<Bairro> listarTodos() {
		return (List<Bairro>) bairroRepository.findAll();
	}

	@Override
	public Bairro procurar(Long id) {
        Optional<Bairro> optionalBairro = bairroRepository.findById(id);
        if (optionalBairro.isPresent()) {
            return optionalBairro.get();
        } else {
            return null;
        }
	}

}
