package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.dao.EspecialidadeDAO;
import br.edu.utfpr.td.tsi.posto.saude.dao.MedicoDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Especialidade;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Medico;


@Controller
public class MedicoController {
	
	@Autowired
	private MedicoDAO medicoDao;
	
	@Autowired
	private EspecialidadeDAO especialidadeDao;

	
	@GetMapping(value = "/listarMedicos")
	public String listar(Model model) {
		List<Medico> medicos = medicoDao.listarTodos();
		model.addAttribute("medicos", medicos);	
		return "listarMedicos";
	}

	@GetMapping(value = "/cadastrarMedico")
	public String exibirPaginaCadastro(Model model) {
		List<Especialidade> especialidades = especialidadeDao.listarTodas();
		model.addAttribute("especialidades", especialidades);
		return "cadastrarMedico";
		
	}
	@PostMapping("/cadastrarMedico")
	public String cadastrar(@ModelAttribute("m") Medico m) {
	    medicoDao.inserir(m);
		return "redirect:/listarMedicos";
	}

}
