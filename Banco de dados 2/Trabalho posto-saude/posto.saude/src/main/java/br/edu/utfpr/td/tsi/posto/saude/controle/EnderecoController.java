package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Endereco;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;
import br.edu.utfpr.td.tsi.posto.saude.service.BairroServiceImpl;
import br.edu.utfpr.td.tsi.posto.saude.service.EnderecoServiceImpl;
import br.edu.utfpr.td.tsi.posto.saude.service.PacienteServiceImpl;

@Controller
public class EnderecoController {

	@Autowired
	EnderecoServiceImpl enderecoService;
	
	@Autowired
	BairroServiceImpl bairroService;

	@Autowired
	PacienteServiceImpl pacienteService;
	
	@GetMapping(value = "/listarEnderecos")
	public String listar(Model model) {
		List<Endereco> enderecos = enderecoService.listarTodos();
		model.addAttribute("enderecos", enderecos);	
		return "listarEnderecos";
	}

	@GetMapping(value = "/cadastrarEndereco")
	public String exibirPaginaCadastro(Model model) {
		List<Paciente> pacientes = pacienteService.listarTodos();
		List<Bairro> bairros = bairroService.listarTodos();
		model.addAttribute("pacientes", pacientes);
		model.addAttribute( "bairros", bairros);
		return "cadastrarEndereco";
	}

	@PostMapping("/cadastrarEndereco")
	public String cadastrar(@ModelAttribute("e")Endereco endereco) {
		enderecoService.inserir(endereco);
		return "redirect:/listarEnderecos";
	}
	
	@GetMapping("/editarEndereco/{id}")
	public String exibirPaginaEdicaoEndereco(@PathVariable Long id, Model model) {
	    List<Paciente> pacientes = pacienteService.listarTodos();
	    List<Bairro> bairros = bairroService.listarTodos();
	    Endereco endereco = enderecoService.procurar(id);
	    model.addAttribute("endereco", endereco);
	    model.addAttribute("pacientes", pacientes);
	    model.addAttribute("bairros", bairros);
	    return "editarEndereco";
	}

	@PostMapping("/salvarEndereco")
	public String editarConsulta(@ModelAttribute("endereco") Endereco endereco) {
		enderecoService.atualizar(endereco.getId(), endereco);
	    return "redirect:/listarEnderecos";
	}
	
	@PostMapping("/removerEndereco/{id}")
	public String remover(Endereco endereco) {
		enderecoService.remover(endereco.getId());
		return "redirect:/listarEnderecos";
	}

	
	
}
