package br.edu.utfpr.td.tsi.posto.saude.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.utfpr.td.tsi.posto.saude.modelo.Bairro;
import br.edu.utfpr.td.tsi.posto.saude.service.BairroServiceImpl;

@Controller
public class BairroController {

	@Autowired
	BairroServiceImpl bairroService;

	@GetMapping(value = "/listarbairros")
	public String listar(Model model) {
		List<Bairro> bairros = bairroService.listarTodos();
		model.addAttribute("bairros", bairros);	
		return "listarBairros";
	}

	@GetMapping(value = "/cadastrarBairro")
	public String exibirPaginaCadastro() {
		return "cadastrarBairro";
	}

	@PostMapping("/cadastrarBairro")
	public String cadastrar(Bairro bairro) {
		bairroService.inserir(bairro);
		return "redirect:/listarbairros";
	}
	
	@GetMapping("/editarBairro/{id}")
	public String editarBairro(@PathVariable Long id, Model model) {
	    Bairro bairro = bairroService.procurar(id);
	    model.addAttribute("bairro", bairro);
	    return "editarBairro";
	}

	@PostMapping("/salvarBairro")
	public String salvar(Bairro b) {
		bairroService.atualizar(b.getId(), b);
		return "redirect:/listarbairros";
	}
	
	
	@PostMapping("/removerBairro/{id}")
	public String remover(Bairro b) {
		bairroService.remover(b.getId());
		return "redirect:/listarbairros";
	}

}
