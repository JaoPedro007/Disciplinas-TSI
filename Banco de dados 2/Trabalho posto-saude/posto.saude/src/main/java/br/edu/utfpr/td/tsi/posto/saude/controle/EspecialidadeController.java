package br.edu.utfpr.td.tsi.posto.saude.controle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Especialidade;
import br.edu.utfpr.td.tsi.posto.saude.service.EspecialidadeServiceImpl;

@Controller
public class EspecialidadeController {


	@Autowired
	EspecialidadeServiceImpl especialidadeService;
	
	
	@GetMapping(value = "/listarEspecialidade")
	public String listar(Model model) {
		Iterable<Especialidade> especialidades = especialidadeService.listarTodas();
		model.addAttribute("especialidades", especialidades);	
		return "listarEspecialidades";
	}

	@GetMapping(value = "/cadastrarEspecialidade")
	public String exibirPaginaCadastro() {
		return "cadastrarEspecialidade";
	}

	@PostMapping("/cadastrarEspecialidade")
	public String cadastrar(Especialidade especialidade) {
		especialidadeService.inserir(especialidade);
		return "redirect:/listarEspecialidade";
	}
	
	
	@GetMapping("/editarEspecialidade/{id}")
	public String editarEspecialidade(@PathVariable Long id, Model model) {
	    Especialidade especialidade = especialidadeService.procurar(id);
	    model.addAttribute("especialidade", especialidade);
	    return "editarEspecialidade";
	}

	@PostMapping("/salvarEspecialidade")
	public String salvar(Especialidade e) {
		especialidadeService.atualizar(e.getId(), e);
		return "redirect:/listarEspecialidade";
	}
	
	
	@PostMapping("/removerEspecialidade/{id}")
	public String remover(Especialidade e) {
		especialidadeService.remover(e.getId());
		return "redirect:/listarEspecialidade";
	}
}
