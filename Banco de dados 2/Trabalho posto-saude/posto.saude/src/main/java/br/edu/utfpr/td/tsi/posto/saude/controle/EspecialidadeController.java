package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.dao.EspecialidadeDAO;
import br.edu.utfpr.td.tsi.posto.saude.modelo.Especialidade;

@Controller
public class EspecialidadeController {


	@Autowired
	EspecialidadeDAO especialidadeDAO;

	@GetMapping(value = "/listarEspecialidade")
	public String listar(Model model) {
		List<Especialidade> especialidades= especialidadeDAO.listarTodas();
		model.addAttribute("especialidades", especialidades);	
		return "listarEspecialidades";
	}

	@GetMapping(value = "/cadastrarEspecialidade")
	public String exibirPaginaCadastro() {
		return "cadastrarEspecialidade";
	}

	@PostMapping("/cadastrarEspecialidade")
	public String cadastrar(Especialidade especialidade) {
		especialidadeDAO.inserir(especialidade);
		return "redirect:/listarEspecialidade";
	}
	
	
	@GetMapping("/editarEspecialidade/{id}")
	public String editarEspecialidade(@PathVariable Long id, Model model) {
	    Especialidade especialidade = especialidadeDAO.procurar(id);
	    model.addAttribute("especialidade", especialidade);
	    return "editarEspecialidade";
	}

	@PostMapping("/salvarEspecialidade")
	public String salvar(Especialidade e) {
	    especialidadeDAO.atualizar(e.getId(), e);
		return "redirect:/listarEspecialidade";
	}
	
	
	@PostMapping("/removerEspecialidade/{id}")
	public String remover(Especialidade e) {
	    especialidadeDAO.remover(e.getId());
		return "redirect:/listarEspecialidade";
	}
}
