package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.dao.PacienteDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;

@Controller
public class PacienteController {

	@Autowired
	private PacienteDAO pacienteDAO;

	@GetMapping(value = "/listarPacientes")
	public String listar(Model model) {
		List<Paciente> pacientes = pacienteDAO.listarTodos();
		model.addAttribute("pacientes", pacientes);
		return "listarPacientes";
	}

	@GetMapping(value = "/cadastrarPaciente")
	public String exibirPaginaCadastro(Model model) {
		return "cadastrarPaciente";
	}

	@PostMapping("/cadastrarPaciente")
	public String cadastrar(Paciente p) {
		pacienteDAO.inserir(p);
		return "redirect:/listarPacientes";
	}

	@GetMapping("/editarPaciente/{id}")
	public String editarPaciente(@PathVariable Long id, Model model) {
	    Paciente paciente = pacienteDAO.procurar(id);
	    model.addAttribute("paciente", paciente);
	    return "editarPaciente";
	}

	@PostMapping("/salvarPaciente")
	public String salvar(Paciente p) {
	    pacienteDAO.atualizar(p.getId(), p);
		return "redirect:/listarPacientes";
	}
	
	
	@PostMapping("/removerPaciente/{id}")
	public String remover(Paciente p) {
	    pacienteDAO.remover(p.getId());
		return "redirect:/listarPacientes";
	}

}
