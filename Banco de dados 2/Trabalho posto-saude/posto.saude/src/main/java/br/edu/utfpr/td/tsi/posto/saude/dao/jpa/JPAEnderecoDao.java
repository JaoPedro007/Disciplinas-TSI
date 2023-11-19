package br.edu.utfpr.td.tsi.posto.saude.dao.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.utfpr.td.tsi.posto.saude.dao.EnderecoDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.repository.BairroRepository;
import br.edu.utfpr.td.tsi.posto.saude.dao.repository.EnderecoRepository;
import br.edu.utfpr.td.tsi.posto.saude.dao.repository.PacienteRepository;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Endereco;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

public class JPAEnderecoDao implements EnderecoDAO {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	BairroRepository bairroRepository;
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Override
	public List<Endereco> listarTodos() {
		return (List<Endereco>) enderecoRepository.findAll();
	}

	@Override
	public void inserir(Endereco endereco) {
	    Bairro bairroPersistente = bairroRepository.findById(endereco.getBairro().getId()).orElse(null);
	    Paciente pacientePersistente = pacienteRepository.findById(endereco.getPaciente().getId()).orElse(null);


	    if (bairroPersistente !=null && pacientePersistente !=null) {
	        endereco.setBairro(bairroPersistente);
	        endereco.setPaciente(pacientePersistente);
	    }
		
		enderecoRepository.save(endereco);
	}

	@Override
	public void atualizar(Long id, Endereco e) {		
		Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
		if(optionalEndereco.isPresent()) {			

			Endereco endereco = optionalEndereco.get();
			
	        if (e.getBairro() != null && e.getPaciente() !=null) {
	            endereco.setBairro(e.getBairro());
				endereco.setPaciente(e.getPaciente());

	        }
			endereco.setCep(e.getCep());
			endereco.setComplemento(e.getComplemento());
			endereco.setLogradouro(e.getLogradouro());
			endereco.setNumero(e.getNumero());

			enderecoRepository.save(endereco);
		}
		else {
		}




	}

	@Override
	public void remover(Long idEndereco) {
		enderecoRepository.deleteById(idEndereco);

	}

	@Override
	public Endereco procurar(Long idEndereco) {
		Optional<Endereco> optionalEndereco = enderecoRepository.findById(idEndereco);
		if (optionalEndereco.isPresent()) {
			return optionalEndereco.get();
		} else {
			return null;
		}
	}

}
