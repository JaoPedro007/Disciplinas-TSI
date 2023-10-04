package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.dao.BairroDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.EnderecoDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.PacienteDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

@Controller
public class PacienteController {

	@Autowired
	private PacienteDAO pacienteDAO;

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Autowired
	private BairroDAO bairroDAO;
	
	@GetMapping(value = "/listarPacientes")
	public String listar(Model model) {
		List<Paciente> pacientes = pacienteDAO.listarTodos();
		model.addAttribute("pacientes", pacientes);	
		return "listarPacientes";
	}

	@GetMapping(value = "/cadastrarPaciente")
	public String exibirPaginaCadastro(Model model) {
		List<Bairro> bairros = bairroDAO.listarTodos();
		model.addAttribute("bairros", bairros);
		return "cadastrarPaciente";
	}

	@PostMapping("/cadastrarPaciente")
	public String cadastrar(Paciente p) {
		Long idPaciente = pacienteDAO.inserir(p);
		enderecoDAO.inserir(p.getEndereco(), idPaciente);
		return "redirect:/listarPacientes";
	}

}
