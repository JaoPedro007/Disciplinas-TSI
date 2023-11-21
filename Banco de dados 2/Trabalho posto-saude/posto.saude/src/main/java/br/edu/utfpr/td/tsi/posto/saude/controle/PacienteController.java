package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Paciente;
import br.edu.utfpr.td.tsi.posto.saude.service.PacienteServiceImpl;

@Controller
public class PacienteController {

	@Autowired
	private PacienteServiceImpl pacienteService;

	@GetMapping(value = "/listarPacientes")
	public String listar(Model model) {
		List<Paciente> pacientes = pacienteService.listarTodos();
		model.addAttribute("pacientes", pacientes);
		return "listarPacientes";
	}

	@GetMapping(value = "/cadastrarPaciente")
	public String exibirPaginaCadastro(Model model) {
		return "cadastrarPaciente";
	}

	@PostMapping("/cadastrarPaciente")
	public String cadastrar(@ModelAttribute("e") Paciente p, Model model) {			
		if(!pacienteService.dataCorreta(p)) {
			model.addAttribute("error", "Não foi possível cadastrar. A data de nascimento deve ser anterior a data atual");
			return "cadastrarPaciente";
		}else {
			pacienteService.inserir(p);
			return "redirect:/listarPacientes";
		}
	}

	@GetMapping("/editarPaciente/{id}")
	public String editarPaciente(@PathVariable Long id, Model model) {
	    Paciente paciente = pacienteService.procurar(id);
	    model.addAttribute("paciente", paciente);
	    return "editarPaciente";
	}

	@PostMapping("/salvarPaciente")
	public String salvar(@ModelAttribute("e") Paciente p, Model model) {		
		if(!pacienteService.dataCorreta(p)) {
			model.addAttribute("error", "Não foi possível editar. A data de nascimento deve ser anterior a data atual");
			return "cadastrarPaciente";
		}else {
		    pacienteService.atualizar(p.getId(), p);
			return "redirect:/listarPacientes";
		}
		
	}
	
	
	@PostMapping("/removerPaciente/{id}")
	public String remover(Paciente p) {
	    pacienteService.remover(p.getId());
		return "redirect:/listarPacientes";
	}

}
