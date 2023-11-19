package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.dao.BairroRepository;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;
import br.edu.utfpr.td.tsi.posto.saude.service.PacienteServiceImpl;

@Controller
public class PacienteController {

	@Autowired
	private BairroRepository bairroRepository;
	
	@Autowired
	private PacienteServiceImpl pacienteService;

	@GetMapping(value = "/cadastrarPaciente")	
	public String exibirPaginaCadastro(Model model) {
		Iterable<Bairro> bairros = bairroRepository.findAll();
		model.addAttribute("bairros", bairros);
		return "cadastrarPaciente";
	}

	@PostMapping("/cadastrarPaciente")	
	public String cadastrar(Paciente p) {
		pacienteService.cadastrar(p);
		return "redirect:/listarbairros";
	}
	
	@GetMapping("/listarPacientes")	
	public String listarTodos(Model model) {
		List<Paciente> pacientes = pacienteService.listarTodos();
		model.addAttribute("pacientes", pacientes);
		return "listarPacientes";
	}

}
