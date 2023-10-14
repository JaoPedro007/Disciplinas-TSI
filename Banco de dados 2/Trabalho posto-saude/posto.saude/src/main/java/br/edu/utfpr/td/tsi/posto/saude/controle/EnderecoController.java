package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.dao.BairroDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.EnderecoDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.PacienteDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Endereco;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

@Controller
public class EnderecoController {

	@Autowired
	EnderecoDAO enderecoDao;
	
	@Autowired
	PacienteDAO pacienteDao;
	
	@Autowired
	BairroDAO bairroDao;

	@GetMapping(value = "/listarEnderecos")
	public String listar(Model model) {
		List<Endereco> enderecos = enderecoDao.listarTodos();
		model.addAttribute("enderecos", enderecos);	
		return "listarEnderecos";
	}

	@GetMapping(value = "/cadastrarEndereco")
	public String exibirPaginaCadastro(Model model) {
		List<Paciente> pacientes = pacienteDao.listarTodos();
		List<Bairro> bairros = bairroDao.listarTodos();
		model.addAttribute("pacientes", pacientes);
		model.addAttribute( "bairros", bairros);
		return "cadastrarEndereco";
	}

	@PostMapping("/cadastrarEndereco")
	public String cadastrar(@ModelAttribute("e")Endereco endereco) {
		enderecoDao.inserir(endereco);
		return "redirect:/listarEnderecos";
	}
	
	@GetMapping("/editarEndereco/{id}")
	public String exibirPaginaEdicaoEndereco(@PathVariable Long id, Model model) {
	    List<Paciente> pacientes = pacienteDao.listarTodos();
	    List<Bairro> bairros = bairroDao.listarTodos();
	    Endereco endereco = enderecoDao.procurar(id);
	    model.addAttribute("endereco", endereco);
	    model.addAttribute("paciente", pacientes);
	    model.addAttribute("bairros", bairros);
	    return "editarEndereco";
	}

	@PostMapping("/salvarEndereco")
	public String editarConsulta(@ModelAttribute("endereco") Endereco endereco) {
	    enderecoDao.atualizar(endereco.getId(), endereco);
	    return "redirect:/listarEnderecos";
	}


	
	
}
